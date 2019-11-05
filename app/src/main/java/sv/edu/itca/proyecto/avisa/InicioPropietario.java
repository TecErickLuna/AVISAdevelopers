package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class InicioPropietario extends AppCompatActivity {
    private ImageButton Unidades, Favoritas, Agregar, Perfil, Config, Salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_propietario);

        Unidades=findViewById(R.id.btnPropietarioUnidades);
        Favoritas=findViewById(R.id.btnPropietarioFavoritos);
        Agregar=findViewById(R.id.btnPropietarioAgregar);
        Perfil=findViewById(R.id.btnPropietarioPerfil);
        Config=findViewById(R.id.btnPropietarioConfig);
        Salir=findViewById(R.id.btnPropietarioSalir);

        Unidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity unidades Propietario",Toast.LENGTH_LONG).show();

            }
        });

        Favoritas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Unidades Favoritas",Toast.LENGTH_LONG).show();
            }
        });

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Agregar Unidades",Toast.LENGTH_LONG).show();
                Intent rpasajero = new Intent(InicioPropietario.this, AgregarUnidades.class);
                startActivity(rpasajero);
            }
        });

        Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Perfil Propietario",Toast.LENGTH_LONG).show();
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

                clearProfile();
                finish();
            }
        });
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
