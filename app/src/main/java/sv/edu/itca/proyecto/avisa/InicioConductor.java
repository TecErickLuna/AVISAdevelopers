package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class InicioConductor extends AppCompatActivity {
    private ImageButton IniciarRuta, Rutas, Solicitudes, Perfil, Config, Salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_conductor);

        IniciarRuta=findViewById(R.id.btnConductorIniciarRuta);
        Rutas=findViewById(R.id.btnConductorRutas);
        Solicitudes=findViewById(R.id.btnConductorSolicitudes);
        Perfil=findViewById(R.id.btnConductorPerfil);
        Config=findViewById(R.id.btnConductorConfiguracion);
        Salir=findViewById(R.id.btnConductorSalir);

        IniciarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Iniciar Ruta",Toast.LENGTH_LONG).show();
            }
        });

        Rutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Rutas",Toast.LENGTH_LONG).show();
            }
        });

        Solicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Solicitudes",Toast.LENGTH_LONG).show();
            }
        });

        Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Perfil",Toast.LENGTH_LONG).show();
            }
        });

        Config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Configuracion",Toast.LENGTH_LONG).show();
            }
        });

        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
