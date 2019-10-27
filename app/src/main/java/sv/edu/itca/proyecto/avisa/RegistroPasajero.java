package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class RegistroPasajero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasajero);
    }
    public void buscarFotoRegistroPasajero(View view) {
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder4.setView(inflater.inflate(R.layout.activity_opciones_fotografia, null)).setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder4.create();
        builder4.show();
    }
}
