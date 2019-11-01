package sv.edu.itca.proyecto.avisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class PerfilPasajero extends AppCompatActivity {
private Button cambios;
private SharedPreferences misPreferencias;
    private TextInputEditText Correo, contraseña,contraseña2, nombre, apellido;
    private ImageButton fotoPasa;
    private static int SELECT_PICTURE=1;
    private String tipo_usuario="pasajero";
    private Bitmap bitmap;
    private String URL = "https://avproyect.000webhostapp.com/registroUsuarios.php";


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


        Context context=this.getApplicationContext();
        misPreferencias=context.getSharedPreferences("logeo",Context.MODE_PRIVATE);
        Correo.setText(misPreferencias.getString("correo",""));
        contraseña.setText(misPreferencias.getString("contraseña",""));
        nombre.setText(misPreferencias.getString("nombre",""));
        apellido.setText(misPreferencias.getString("apellido",""));

    }

    public void cambiosPasajero(View view) {

        if (cambios.getText().equals("EDITAR PERFIL")){
            contraseña.setEnabled(true);
            contraseña2.setVisibility(View.VISIBLE);
            contraseña2.setEnabled(true);
            nombre.setEnabled(true);
            apellido.setEnabled(true);
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
                    parametros.put("jefe", "N/A");
                    parametros.put("tipo_usuario", tipo_usuario);
                    parametros.put("imagen", getStringImage(bitmap) );
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
            contraseña2.setVisibility(View.INVISIBLE);
            cambios.setText("EDITAR PERFIL");
        }

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
            if (requestCode==SELECT_PICTURE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                Uri rutaArchivo = data.getData();
                try {

                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),rutaArchivo);
                    fotoPasa.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        public void preview(View view) {

            Intent intent = new Intent();
            intent.setType("image/*");//intent.setType("image/PNG");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"seleccione una imagen"),SELECT_PICTURE);



        }


        //AQUI GUARDAREMOS NUEVAMENTE LOS DATOS.

    }

