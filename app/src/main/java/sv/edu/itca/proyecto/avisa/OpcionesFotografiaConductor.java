package sv.edu.itca.proyecto.avisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class OpcionesFotografiaConductor extends AppCompatActivity {

    private ImageButton foto;
    private Bitmap bitmap;
    private static int SELECT_PICTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_fotografia_conductor);

        foto=findViewById(R.id.ibfoto);
    }


    public void tomarfotodecamaraconductor(View view) {
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==SELECT_PICTURE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri rutaArchivo = data.getData();
            try {

                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),rutaArchivo);
                foto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

 */

    public void tomarfotodegaleriaconductor(View view) {
      /*  Intent intent = new Intent();
        intent.setType("image/*");//intent.setType("image/PNG");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"seleccione una imagen"),SELECT_PICTURE);

       */

        Toast.makeText(OpcionesFotografiaConductor.this,"Dio clic",Toast.LENGTH_LONG).show();

    }

    public void eliminarfotoconductor(View view) {
    }
}
