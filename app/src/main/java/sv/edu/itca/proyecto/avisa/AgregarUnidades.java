package sv.edu.itca.proyecto.avisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class AgregarUnidades extends AppCompatActivity {
    private Button AgregarUnidad;
    private TextInputEditText alias,correo,tarifa,info,horarios;
    private Spinner recorrido;

    private ImageButton foto;
    private String URL = "https://avproyect.000webhostapp.com/registroUnidades.php";
    private Bitmap bitmap;
    private static int SELECT_PICTURE=1;
    private static int TAKE_PICTURE = 2;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_unidades);

        AgregarUnidad = (Button)findViewById(R.id.btnPropietarioAgregarUnidad);
        alias=findViewById(R.id.etAliasUnidad);
        correo=findViewById(R.id.etConductor);
        tarifa=findViewById(R.id.etTarifa);
        info=findViewById(R.id.etColorbaseUnidad);
        horarios=findViewById(R.id.etHorario);
        recorrido=findViewById(R.id.etRecorrido);
        foto=findViewById(R.id.fotoMicro);


        AgregarUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Codigo de Agregar",Toast.LENGTH_LONG).show();

                if (alias.getText().toString().isEmpty()){
                    alias.setError("El alias debe ser unico y no puede estar vacio");
                }
                else if (correo.getText().toString().isEmpty()){
                    correo.setError("El correo no puede estar vacio");
                }
                else if (tarifa.getText().toString().isEmpty()){
                    tarifa.setError("La informacion de la tarifa no puede estar vacia");
                }
                else if (info.getText().toString().isEmpty()){
                    info.setError("La informacion del microbus es importante y no puede estar vacia");
                }
                else if (horarios.getText().toString().isEmpty()){
                    horarios.setError("Los horarios no pueden quedar vacios");
                }
                else {

                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("Error")) {
                                Toast.makeText(AgregarUnidades.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                            } else if (response.equals("Subio imagen Correctamente")) {

                                Toast.makeText(AgregarUnidades.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(AgregarUnidades.this, "Error php: \n" + response, Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AgregarUnidades.this, "Acceso denegado :( \n" + error, Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parametros = new Hashtable<String, String>();
                            parametros.put("alias", alias.getText().toString().trim());
                            parametros.put("correo_conductor", correo.getText().toString().trim());
                            parametros.put("tarifa", tarifa.getText().toString().trim());
                            parametros.put("info", info.getText().toString().trim());
                            parametros.put("horarios", horarios.getText().toString().trim());
                            parametros.put("ruta",recorrido.getSelectedItem().toString());
                            parametros.put("imagen", getStringImage(bitmap));
                            return parametros;
                        }

                    };

                    RequestQueue rQ = Volley.newRequestQueue(AgregarUnidades.this);
                    rQ.add(request);


                }



            }
        });
    }

    //huguk

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream bObj = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bObj);
        byte[] imageBytes=bObj.toByteArray();
        String imagenCodificada = Base64.encodeToString(imageBytes,Base64.DEFAULT);

        return imagenCodificada;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE ) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    ImageButton iv = (ImageButton) findViewById(R.id.picRegistroConductor);
                    iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                }
            } else {
                ImageButton iv = (ImageButton) findViewById(R.id.picRegistroConductor);
                iv.setImageBitmap(BitmapFactory.decodeFile(name));
            }
        }else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri rutaArchivo = data.getData();
            try {

                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),rutaArchivo);
                foto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public void fotoconductor(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.cuadrodialogofotoconductor, null);



        ImageButton camara = (ImageButton)v.findViewById(R.id.btnTomarFotoConductor);
        ImageButton galeria = (ImageButton)v.findViewById(R.id.btnTomardeGaleriaConductor);
        ImageButton eliminar = (ImageButton)v.findViewById(R.id.btnEliminarFotoConductor);

        builder.setView(v);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");//intent.setType("image/PNG");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"seleccione una imagen"),SELECT_PICTURE);
                alertDialog.dismiss();

            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int code = TAKE_PICTURE;
                Uri output = Uri.fromFile(new File(name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output.toString());

                startActivityForResult(intent, code);
                alertDialog.dismiss();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


}
