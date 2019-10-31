package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Login_Form extends AppCompatActivity {
    private EditText correoElectronico;
    private EditText pass;
    private String URL="https://avproyect.000webhostapp.com/Login.php";
    public SharedPreferences preferences;
    public SharedPreferences.Editor editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        correoElectronico = (EditText) findViewById(R.id.correoElectronico);
        pass = (EditText) findViewById(R.id.passwordLogin);

        Context context = this.getApplicationContext();
        preferences=context.getSharedPreferences("logeo",Context.MODE_PRIVATE);

        String user=preferences.getString("correo","");
        String typeUser=preferences.getString("tipo_usuario","");


        //SI YA CARGO SUS DATOS, ENTONCES NO LE PEDIRA LOGGEO PERO PARA HACER PRUEBAS PUEDEN COMENTAR ESTE CODIGO
        if (!user.isEmpty()){


            if (typeUser.equals("conductor")){
                Intent mostrar = new Intent(getApplicationContext(), InicioConductor.class);
                startActivity(mostrar);
                finish();
            }
            else if (typeUser.equals("pasajero")){
                Intent mostrar = new Intent(getApplicationContext(), InicioPasajero.class);
                startActivity(mostrar);
                finish();
            }
            else if (typeUser.equals("propietario")){
                Intent mostrar = new Intent(getApplicationContext(), InicioPropietario.class);
                startActivity(mostrar);
                finish();
            }

        }

        //FIN LOGGEO AUTOMATICO


    }

    public void crearCuenta(View view) {
        Intent intent= new Intent(this, tipo_cuenta.class);
        startActivity(intent);


    }

    public void Logeo(View view) {

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
                    else {
                        Toast.makeText(Login_Form.this, "Datos cargados correctamente\n"+response, Toast.LENGTH_LONG).show();

                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            String correo=jsonArray.getJSONObject(0).getString("Correo");
                            String contraseña=jsonArray.getJSONObject(0).getString("Contraseña");
                            String nombre = jsonArray.getJSONObject(0).getString("Nombre");
                            String apellido = jsonArray.getJSONObject(0).getString("Apellido");
                            String jefe = jsonArray.getJSONObject(0).getString("Jefe");
                            String tipo_usuario=jsonArray.getJSONObject(0).getString("Tipo_Usuario");
                            String rutaFoto=jsonArray.getJSONObject(0).getString("Foto_perfil");

                             preferences =getSharedPreferences("logeo", Context.MODE_PRIVATE);
                             editar = preferences.edit();
                            editar.putString("correo",correo);
                            editar.putString("contraseña",contraseña);
                            editar.putString("nombre",nombre);
                            editar.putString("apellido",apellido);
                            editar.putString("jefe",jefe);
                            editar.putString("tipo_usuario",tipo_usuario);
                            editar.putString("rutaFoto",rutaFoto);
                            editar.commit();

                            if (tipo_usuario.equals("conductor")){
                                Intent mostrar = new Intent(getApplicationContext(), InicioConductor.class);
                                startActivity(mostrar);
                                finish();
                            }
                            else if (tipo_usuario.equals("pasajero")){
                                Intent mostrar = new Intent(getApplicationContext(), InicioPasajero.class);
                                startActivity(mostrar);
                                finish();
                            }
                            else if (tipo_usuario.equals("propietario")){
                                Intent mostrar = new Intent(getApplicationContext(), InicioPropietario.class);
                                startActivity(mostrar);
                                finish();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                        //CODIGO DE PRUEBA, NADA DEFINITIVO


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
}
