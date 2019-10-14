package sv.edu.itca.proyecto.avisa;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class progressbar extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float desde;
    private float hasta;

    public progressbar(Context context, ProgressBar progressBar, TextView textView, Float desde, Float hasta){
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.desde = desde;
        this.hasta = hasta;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t){

        super.applyTransformation(interpolatedTime, t);
        float value= desde + (hasta - desde) * interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value+" %");

        if (value==hasta){

            context.startActivity(new Intent(context, Login_Form.class));
        }
    }


}
