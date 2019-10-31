package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Toast;

public class InicioPasajero extends AppCompatActivity implements View.OnClickListener {
private Layout layout;
private CardView perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_pasajero);

        perfil=(CardView) findViewById(R.id.cvPerfilPasaero);

        perfil.setOnClickListener(InicioPasajero.this);
/*
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mostrar = new Intent(getApplicationContext(), PerfilPasajero.class);
                startActivity(mostrar);
            }
        });*/


    }




    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.cvPerfilPasaero :
                Toast.makeText(this,"mande algo",Toast.LENGTH_LONG).show();
                intent=new Intent(InicioPasajero.this,PerfilPasajero.class);
                startActivity(intent);
                break;

                default: break;
        }

    }
}
