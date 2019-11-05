package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InicioPasajero extends AppCompatActivity {
    private ImageButton Unidades, Favoritas, Buscar, Perfil, Config, Salir;
    private EditText ApellidoPrueba;

private Layout layout;
private CardView perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_pasajero);

        Unidades=findViewById(R.id.btnPasajeroUnidades);
        Favoritas=findViewById(R.id.btnPasajeroFavoritos);
        Buscar=findViewById(R.id.btnPasajeroBuscarUnidades);
        Perfil=findViewById(R.id.btnPasajeroPerfil);
        Config=findViewById(R.id.btnPasajeroConfig);
        Salir=findViewById(R.id.btnPasajeroSalir);




        perfil=(CardView) findViewById(R.id.cvPerfilPasaero);

      Unidades.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(getApplicationContext(),"Activity con Todas las unidades",Toast.LENGTH_LONG).show();
          }
      });

      Favoritas.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(getApplicationContext(),"Activity Fav",Toast.LENGTH_LONG).show();
          }
      });

      Buscar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(getApplicationContext(),"Buscar Unidades",Toast.LENGTH_LONG).show();
              Intent rpasajero = new Intent(InicioPasajero.this, BuscarUnidades.class);
              startActivity(rpasajero);

          }
      });

      perfil.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent rpasajero = new Intent(InicioPasajero.this, PerfilPasajero.class);
              startActivity(rpasajero);
          }
      });

      Config.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(getApplicationContext(),"Configuracion",Toast.LENGTH_LONG).show();
          }
      });

      Salir.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final AlertDialog.Builder builder = new AlertDialog.Builder(InicioPasajero.this);
              builder.setMessage("Opciones");
              builder.setCancelable(false);
              builder.setTitle("Seguro que desea Salir?");

              builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      clearProfile();
                      finish();
                  }
              });

              builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                  }
              });

              builder.create();
              builder.show();

          }
      });
/*
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mostrar = new Intent(getApplicationContext(), PerfilPasajero.class);
                startActivity(mostrar);
            }
        });*/


    }
    private void clearProfile() {

        SharedPreferences preferences;
        Context context = this.getApplicationContext();
        preferences=context.getSharedPreferences("logeo", Context.MODE_PRIVATE);
        //Para borrar el registro de algun dato en elfichero compartido
        // sencillamente empleamos el metodo remove(key)
        // del objeto SharedPreferences.Editor
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("correo");
        editor.remove("contraseña");
        editor.remove("nombre");
        editor.remove("apellido");
        editor.remove("jefe");
        editor.remove("tipo_usuario");
        editor.remove("rutaFoto");
        editor.commit();


    }

}
