package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class InicioPasajero extends AppCompatActivity {
    private ImageButton Unidades, Favoritas, Buscar, Perfil, Config, Salir;
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
              Toast.makeText(getApplicationContext(),"Nose si lo pondremos en verdad",Toast.LENGTH_LONG).show();
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
              finish();
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

}
