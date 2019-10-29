package sv.edu.itca.proyecto.avisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class RegistroConductor extends AppCompatActivity {
    private TextInputEditText Correo, contraseña, nombre, apellido;
    private String tipo_usuario="conductor";
    private String URL = "https://avproyect.000webhostapp.com/registroUsuarios.php";
    private EditText jefe;

    private ImageButton foto;
    private Bitmap bitmap;
    private static int SELECT_PICTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_conductor);

        Correo = findViewById(R.id.etCorreoConductor);
        contraseña = findViewById(R.id.etpasswordConductor);
        nombre = findViewById(R.id.etNombresConductor);
        apellido = findViewById(R.id.etApellidosConductor);
        jefe=findViewById(R.id.etJefe);
        tipo_usuario = "conductor";
        foto = findViewById(R.id.imgconductor);

    }

    public void RegistrarmeConductor(View view) {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Error")) {
                    Toast.makeText(RegistroConductor.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                } else if (response.equals("Subio imagen Correctamente")) {

                    Toast.makeText(RegistroConductor.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();
                    finish();
                    /*try {

                        JSONArray jsonArray = new JSONArray(response);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }*/
                }
                else {
                    Toast.makeText(RegistroConductor.this,"Error php: \n"+response,Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroConductor.this, "Acceso denegado :( \n"+error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("correo", Correo.getText().toString().trim());
                parametros.put("contraseña", contraseña.getText().toString().trim());
                parametros.put("nombre", nombre.getText().toString().trim());
                parametros.put("apellido", apellido.getText().toString().trim());
                parametros.put("jefe", jefe.getText().toString().trim());
                parametros.put("tipo_usuario", tipo_usuario);
                parametros.put("imagen", getStringImage(bitmap) );
                return parametros;
            }

        };

        RequestQueue rQ = Volley.newRequestQueue(RegistroConductor.this);
        rQ.add(request);
    }

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream bObj = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bObj);
        byte[] imageBytes=bObj.toByteArray();
        String imagenCodificada = Base64.encodeToString(imageBytes,Base64.DEFAULT);

        return imagenCodificada;
    }


    public void buscarFotoRegistroConductor(View view) {
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder4.setView(inflater.inflate(R.layout.activity_opciones_fotografia_conductor, null)).setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder4.create();
        builder4.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SELECT_PICTURE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri rutaArchivo = data.getData();
            try {

                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),rutaArchivo);
                foto.setImageBitmap(bitmap);
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
}
