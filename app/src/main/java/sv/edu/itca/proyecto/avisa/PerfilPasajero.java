package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class PerfilPasajero extends AppCompatActivity {
private Button cambios;
private SharedPreferences misPreferencias;
    private TextInputEditText Correo, contraseña,contraseña2, nombre, apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pasajero);

        cambios=findViewById(R.id.btnCambiosPasajero);
        Correo = findViewById(R.id.etCambioCorreoPasajero);
        contraseña = findViewById(R.id.etCambiopasswordPasajero);
        contraseña2 = findViewById(R.id.etCambiopassword2Pasajero);
        nombre = findViewById(R.id.etCambioNombresPasajero);
        apellido = findViewById(R.id.etCambioApellidosPasajero);


        Context context=this.getApplicationContext();
        misPreferencias=context.getSharedPreferences("logeo",Context.MODE_PRIVATE);
        Correo.setText(misPreferencias.getString("correo",""));
        contraseña.setText(misPreferencias.getString("contraseña",""));
        nombre.setText(misPreferencias.getString("nombre",""));
        apellido.setText(misPreferencias.getString("apellido",""));

    }

    public void cambiosPasajero(View view) {

        //ESTO ES PARA QUE CARGUE LOS DATOS DE LA BASE DE DATOS
        if (cambios.getText().equals("EDITAR PERFIL")){



        }

    }
}
