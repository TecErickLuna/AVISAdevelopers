package sv.edu.itca.proyecto.avisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class RegistroConductor extends AppCompatActivity {
    private TextInputEditText Correo, contraseña, nombre, apellido;
    private String tipo_usuario="conductor", foto;
    private String URL = "https://avproyect.000webhostapp.com/consultaLogin.php";
    private AppCompatSpinner jefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_conductor);

        Correo = findViewById(R.id.etCorreoConductor);
        contraseña = findViewById(R.id.etpasswordConductor);
        nombre = findViewById(R.id.etNombresConductor);
        apellido = findViewById(R.id.etApellidosConductor);

        tipo_usuario = "Conductor";
        foto = "ninguna";

    }

    public void RegistrarmeConductor(View view) {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    Toast.makeText(RegistroConductor.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                } else if (response.equals("1")) {

                    Toast.makeText(RegistroConductor.this, "Cuenta Registrada Exitosamente", Toast.LENGTH_SHORT).show();
                    finish();
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
                Toast.makeText(RegistroConductor.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("correo", Correo.getText().toString());
                parametros.put("contraseña", contraseña.getText().toString());
                parametros.put("nombre", nombre.getText().toString());
                parametros.put("apellido", apellido.getText().toString());
                parametros.put("jefe", "trabajo_solo");
                parametros.put("tipo_usuario", tipo_usuario);
                parametros.put("foto", foto);
                return parametros;
            }

        };

        RequestQueue rQ = Volley.newRequestQueue(RegistroConductor.this);
        rQ.add(request);
    }


    public void buscarFotoRegistroConductor(View view) {
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder4.setView(inflater.inflate(R.layout.activity_opciones_fotografia, null)).setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder4.create();
        builder4.show();
    }
}
