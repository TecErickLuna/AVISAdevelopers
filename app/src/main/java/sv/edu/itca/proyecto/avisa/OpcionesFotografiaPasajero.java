package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class OpcionesFotografiaPasajero extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_fotografia_pasajero);

    }

    public void tomarfotodecamarapasajero(View view) {
        finish();
    }

    public void tomarfotodegaleriapasajero(View view) {
     finish();

    }

    public void eliminarfotopasajero(View view) {
     finish();
    }



}
