package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class RegistroPropietario extends AppCompatActivity {
    private TextInputEditText Correo, contraseña, nombre, apellido;
    private String tipo_usuario="propietario", foto;
    private String URL = "https://avproyect.000webhostapp.com/consultaLogin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_propietario);

        Correo = findViewById(R.id.etCorreoConductor);
        contraseña = findViewById(R.id.etpasswordConductor);
        nombre = findViewById(R.id.etNombresConductor);
        apellido = findViewById(R.id.etApellidosConductor);
        tipo_usuario = "Conductor";
        foto = "ninguna";


    }

    public void RegistrarmePropietario(View view) {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    Toast.makeText(RegistroPropietario.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                } else if (response.equals("1")) {

                    Toast.makeText(RegistroPropietario.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(RegistroPropietario.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("correo", Correo.getText().toString());
                parametros.put("contraseña", contraseña.getText().toString());
                parametros.put("nombre", nombre.getText().toString());
                parametros.put("apellido", apellido.getText().toString());
                parametros.put("jefe", "no_tiene");
                parametros.put("tipo_usuario", tipo_usuario);
                parametros.put("foto", foto);
                return parametros;
            }

        };

        RequestQueue rQ = Volley.newRequestQueue(RegistroPropietario.this);
        rQ.add(request);
    }
}
