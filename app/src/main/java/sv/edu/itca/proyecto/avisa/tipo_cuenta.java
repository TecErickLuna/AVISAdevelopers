package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tipo_cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_cuenta);
    }


    public void registroConductor(View view) {
        Intent rconductor = new Intent(this, RegistroConductor.class);
        startActivity(rconductor);
    }
}
