package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opcion_perfil extends AppCompatActivity {
    private Button editPasajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_perfil);

        editPasajero=(Button)findViewById(R.id.btnEditPerfilPasajero);

        editPasajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpasajero = new Intent(Opcion_perfil.this, PerfilPasajero.class);
                startActivity(rpasajero);
            }
        });
    }
}
