package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tipo_cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_cuenta);
    }


    public void registroConductor(View view) {
        Intent rconductor = new Intent(this, RegistroConductor.class);
        startActivity(rconductor);
        finish();
    }
    public void registroPasajero(View view) {
        Intent rpasajero = new Intent(this, RegistroPasajero.class);
        startActivity(rpasajero);
        finish();
    }
    public void registroPropietario(View view) {
        Intent rpropietario = new Intent(this, RegistroPropietario.class);
        startActivity(rpropietario);
        finish();
    }



}
