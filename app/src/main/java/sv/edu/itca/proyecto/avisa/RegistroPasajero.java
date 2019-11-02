package sv.edu.itca.proyecto.avisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class RegistroPasajero extends AppCompatActivity {
    private TextInputEditText Correo, contraseña, nombre, apellido;
    private String tipo_usuario="pasajero";
    private String URL = "https://avproyect.000webhostapp.com/registroUsuarios.php";
    private AppCompatSpinner jefe;

    private ImageButton foto;
    private Bitmap bitmap;
    private static int SELECT_PICTURE=1;
    private static int TAKE_PICTURE = 2;
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasajero);

        Correo = findViewById(R.id.etCorreoPasajero);
        contraseña = findViewById(R.id.etpasswordPasajero);
        nombre = findViewById(R.id.etNombresPasajero);
        apellido = findViewById(R.id.etApellidosPasajero);

        tipo_usuario = "pasajero";
        foto = findViewById(R.id.picRegistroPasajero);

    }

    public void RegistrarmePasajero(View view) {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Error")) {
                    Toast.makeText(RegistroPasajero.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                } else if (response.equals("Subio imagen Correctamente")) {

                    Toast.makeText(RegistroPasajero.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else {
                    Toast.makeText(RegistroPasajero.this,"Error php: \n"+response,Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroPasajero.this, "Acceso denegado :( \n"+error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("correo", Correo.getText().toString().trim());
                parametros.put("contraseña", contraseña.getText().toString().trim());
                parametros.put("nombre", nombre.getText().toString().trim());
                parametros.put("apellido", apellido.getText().toString().trim());
                parametros.put("jefe", "N/A");
                parametros.put("tipo_usuario", tipo_usuario);
                parametros.put("imagen", getStringImage(bitmap) );
                return parametros;
            }

        };

        RequestQueue rQ = Volley.newRequestQueue(RegistroPasajero.this);
        rQ.add(request);
    }

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
                    ImageButton iv = (ImageButton) findViewById(R.id.picRegistroPasajero);
                    iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                }
            } else {
                ImageButton iv = (ImageButton) findViewById(R.id.picRegistroPasajero);
                iv.setImageBitmap(BitmapFactory.decodeFile(name));
            }
        }else if(requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null)
            {
                Uri rutaArchivo = data.getData();
                try {

                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), rutaArchivo);
                    foto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }

    public void fotopasajero(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.cuadrodialogofotopasajero, null);



        ImageButton camara = (ImageButton)v.findViewById(R.id.btnTomarFotoPasajero);
        ImageButton galeria = (ImageButton)v.findViewById(R.id.btnTomardeGaleriaPasajero);
        ImageButton eliminar = (ImageButton)v.findViewById(R.id.btnEliminarFotoPasajero);

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





    /*
    public void buscarFotoRegistroPasajero(View view) {
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder4.setView(inflater.inflate(R.layout.activity_opciones_fotografia_pasajero, null)).setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder4.create();
        builder4.show();
    }*/
}
