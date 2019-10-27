package sv.edu.itca.proyecto.avisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class RegistroPasajero extends AppCompatActivity {
    private TextInputEditText Correo, contraseña, nombre, apellido;
    private String tipo_usuario="propietario", foto;
    private String URL = "https://avproyect.000webhostapp.com/consultaLogin.php";


    private ImageButton foto2;
    private String URL2="";
    private Bitmap bitmap;
    private static int SELECT_PICTURE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasajero);

        Correo = findViewById(R.id.etCorreoConductor);
        contraseña = findViewById(R.id.etpasswordConductor);
        nombre = findViewById(R.id.etNombresConductor);
        apellido = findViewById(R.id.etApellidosConductor);
        tipo_usuario = "Conductor";
        foto = "ninguna";

        foto2=findViewById(R.id.ibPerfil);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==SELECT_PICTURE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri rutaArchivo = data.getData();
            try {

                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),rutaArchivo);
                foto2.setImageBitmap(bitmap);
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

    public void registrarPasajero(View view) {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    Toast.makeText(RegistroPasajero.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                } else if (response.equals("1")) {

                    Toast.makeText(RegistroPasajero.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();

                    /*try {

                        JSONArray jsonArray = new JSONArray(response);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }*/
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroPasajero.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<>();
                parametros.put("correo", Correo.getText().toString());
                parametros.put("contraseña", contraseña.getText().toString());
                parametros.put("nombre", nombre.getText().toString());
                parametros.put("apellido", apellido.getText().toString());
                parametros.put("jefe", "no_tiene");
                parametros.put("tipo_usuario", tipo_usuario);
                parametros.put("imagen",getStringImage(bitmap));
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
}
