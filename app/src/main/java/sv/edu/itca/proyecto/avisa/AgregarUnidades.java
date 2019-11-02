package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AgregarUnidades extends AppCompatActivity {
    private Button AgregarUnidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_unidades);

        AgregarUnidad = (Button)findViewById(R.id.btnPropietarioAgregarUnidad);

        AgregarUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Codigo de Agregar",Toast.LENGTH_LONG).show();
            }
        });
    }
}
