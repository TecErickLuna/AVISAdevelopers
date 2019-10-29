package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
    }

    public void crearCuenta(View view) {
        Intent intent= new Intent(this, tipo_cuenta.class);
        startActivity(intent);


    }

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

    public void Logeo(View view) {


    }
}
