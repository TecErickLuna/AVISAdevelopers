package sv.edu.itca.proyecto.avisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class PerfilPasajero extends AppCompatActivity {
private Button cambios;
private SharedPreferences misPreferencias;
private SharedPreferences.Editor editar;
    private TextInputEditText Correo, contraseña,contraseña2, nombre, apellido,jefe;
    private ImageButton fotoPasa;
    private static int SELECT_PICTURE=1;
    private static int TAKE_PICTURE = 2;
    private String name = "";
    private String tipo_usuario;
    private Bitmap bitmap;
    private String URL = "https://avproyect.000webhostapp.com/actualizarUsuarios.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pasajero);

        cambios=findViewById(R.id.btnCambiosPasajero);
        Correo = findViewById(R.id.etCambioCorreoPasajero);
        contraseña = findViewById(R.id.etCambiopasswordPasajero);
        contraseña2 = findViewById(R.id.etCambiopassword2Pasajero);
        nombre = findViewById(R.id.etCambioNombresPasajero);
        apellido = findViewById(R.id.etCambioApellidosPasajero);
        fotoPasa=findViewById(R.id.fotoPasa);
        jefe=findViewById(R.id.etCambioJefePasajero);


        Context context=this.getApplicationContext();
        misPreferencias=context.getSharedPreferences("logeo",Context.MODE_PRIVATE);
        Correo.setText(misPreferencias.getString("correo",""));
        contraseña.setText(misPreferencias.getString("contraseña",""));
        nombre.setText(misPreferencias.getString("nombre",""));
        apellido.setText(misPreferencias.getString("apellido",""));

        tipo_usuario=misPreferencias.getString("tipo_usuario","");

        if (jefe.getText().toString().equals("conductor")){
            jefe.setVisibility(View.VISIBLE);
        }
        else{
            jefe.setVisibility(View.INVISIBLE);
        }

        fotoPasa.setEnabled(false);

        Picasso.get().load("https://avproyect.000webhostapp.com/fotos/"+Correo.getText().toString()+".png").into(fotoPasa);

    }

    public void cambiosPasajero(View view) {

        if (cambios.getText().equals("EDITAR PERFIL")){
            contraseña.setEnabled(true);
            contraseña2.setVisibility(View.VISIBLE);
            contraseña2.setEnabled(true);
            nombre.setEnabled(true);
            apellido.setEnabled(true);
            jefe.setEnabled(true);
            cambios.setText("GUARDAR CAMBIOS");
        }
        else if (cambios.getText().equals("GUARDAR CAMBIOS")){

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equals("Error")) {
                        Toast.makeText(PerfilPasajero.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                    } else if (response.equals("Subio imagen Correctamente")) {

                        Toast.makeText(PerfilPasajero.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else {
                        Toast.makeText(PerfilPasajero.this,"Error php: \n"+response,Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PerfilPasajero.this, "Acceso denegado :( \n"+error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("correo", Correo.getText().toString().trim());
                    parametros.put("contraseña", contraseña.getText().toString().trim());
                    parametros.put("nombre", nombre.getText().toString().trim());
                    parametros.put("apellido", apellido.getText().toString().trim());
                    parametros.put("jefe", jefe.getText().toString());
                    parametros.put("tipo_usuario", tipo_usuario);
                    parametros.put("rutaFoto",misPreferencias.getString("rutaFoto",""));
                    return parametros;
                }

            };

            RequestQueue rQ = Volley.newRequestQueue(PerfilPasajero.this);
            rQ.add(request);

            nombre.setEnabled(false);
            apellido.setEnabled(false);
            Correo.setEnabled(false);
            contraseña.setEnabled(false);
            contraseña2.setEnabled(false);
            jefe.setEnabled(false);
            contraseña2.setVisibility(View.INVISIBLE);
            cambios.setText("EDITAR PERFIL");

            editar = misPreferencias.edit();
            editar.putString("correo",Correo.getText().toString());
            editar.putString("contraseña",contraseña.getText().toString());
            editar.putString("nombre",nombre.getText().toString());
            editar.putString("apellido",apellido.getText().toString());
            editar.putString("tipo_usuario",tipo_usuario);
            editar.commit();
        }

    }
/*
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
                        ImageButton iv = (ImageButton) findViewById(R.id.fotoPasa);
                        iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                    }
                } else {
                    ImageButton iv = (ImageButton) findViewById(R.id.fotoPasa);
                    iv.setImageBitmap(BitmapFactory.decodeFile(name));
                }
            }else if (requestCode==SELECT_PICTURE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                Uri rutaArchivo = data.getData();
                try {

                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),rutaArchivo);
                    fotoPasa.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    public void editFotoPasajero(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.cuadrodialog_foto_edit_pasajero, null);



        ImageButton camara = (ImageButton)v.findViewById(R.id.btnTomarFotoPasajeroActu);
        ImageButton galeria = (ImageButton)v.findViewById(R.id.btnTomardeGaleriaPasajeroActu);
        ImageButton eliminar = (ImageButton)v.findViewById(R.id.btnEliminarFotoPasajeroActu);

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


    }*/


    //AQUI GUARDAREMOS NUEVAMENTE LOS DATOS.

    }

