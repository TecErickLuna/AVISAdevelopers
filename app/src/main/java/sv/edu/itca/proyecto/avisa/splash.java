package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    ProgressBar progressBar;
    TextView conteoprogreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar=findViewById(R.id.progres_bar);
        conteoprogreso=findViewById(R.id.conteoProgreso);


        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        animacionProgreso();

    }

    private void animacionProgreso() {
        progressbar anim = new progressbar(this, progressBar, conteoprogreso, 0f , 100f);
        anim.setDuration(2000);
        progressBar.setAnimation(anim);


    }

}
