package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class Login_Form extends AppCompatActivity {
   private EditText correoElectronico;
    private EditText pass;
    private String URL="https://avproyect.000webhostapp.com/Login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        correoElectronico = (EditText) findViewById(R.id.correoElectronico);
        pass = (EditText) findViewById(R.id.passwordLogin);

    }

    public void aceptar(View view) {
        if(correoElectronico.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast.makeText(Login_Form.this,"ERROR: no deje campos vacios",Toast.LENGTH_LONG).show();
        }
        else
        {
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equals("Error1")) {
                        Toast.makeText(Login_Form.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                    } else if (response.equals("error_datos")) {

                        Toast.makeText(Login_Form.this, "La combinacion de usuario y contraseña no coinciden", Toast.LENGTH_LONG).show();

                    }
                    else if (response.equals("Bien")){
                        Toast.makeText(Login_Form.this, "Datos cargados correctamente", Toast.LENGTH_LONG).show();


                        //CODIGO DE PRUEBA, NADA DEFINITIVO
                        Intent mostrar = new Intent(getApplicationContext(), InicioConductor.class);
                        startActivity(mostrar);
                        finish();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login_Form.this, "Acceso denegado\n"+error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("correo", correoElectronico.getText().toString());
                    parametros.put("contraseña", pass.getText().toString());

                    return parametros;
                }

            }
            ;

            RequestQueue rQ = Volley.newRequestQueue(Login_Form.this);
            rQ.add(request);
        }
    }
// CODIGO EXTRA POR SI ACASO???

    public void crearCuenta(View view) {
        Intent intent= new Intent(this, tipo_cuenta.class);
        startActivity(intent);


    }
/*
    public void aceptar(View view) {
        EditText correoElectronico = (EditText) findViewById(R.id.correoElectronico);
        if (correoElectronico.getText().toString().equals("pasajero")){
            Intent mostrar = new Intent(getApplicationContext(), InicioPasajero.class);
            startActivity(mostrar);
            finish();

        }else if (correoElectronico.getText().toString().equals("conductor")){
            Intent mostrar = new Intent(getApplicationContext(), InicioConductor.class);
            startActivity(mostrar);
            finish();
        }else if (correoElectronico.getText().toString().equals("propietario")){
            Intent mostrar = new Intent(getApplicationContext(), InicioPropietario.class);
            startActivity(mostrar);
            finish();
        }else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Error", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

     */




}
