package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InicioConductor extends AppCompatActivity {
    private ImageButton IniciarRuta, Rutas, Solicitudes, Perfil, Config, Salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_conductor);

        IniciarRuta=findViewById(R.id.btnConductorIniciarRuta);
        Rutas=findViewById(R.id.btnConductorRutas);
        Solicitudes=findViewById(R.id.btnConductorSolicitudes);
        Perfil=findViewById(R.id.btnConductorPerfil);
        Config=findViewById(R.id.btnConductorConfiguracion);
        Salir=findViewById(R.id.btnConductorSalir);

        IniciarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Iniciar Ruta",Toast.LENGTH_LONG).show();
            }
        });

        Rutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Rutas",Toast.LENGTH_LONG).show();
            }
        });

        Solicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Solicitudes",Toast.LENGTH_LONG).show();
            }
        });

        Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Mi Perfil",Toast.LENGTH_LONG).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(InicioConductor.this);

                LayoutInflater inflater = InicioConductor.this.getLayoutInflater();

                View v = inflater.inflate(R.layout.cuadrodialogo_perfilconductor, null);

                Button editarconductor = (Button)v.findViewById(R.id.btnEditarPerfilConductor);
                Button guardarconductor = (Button)v.findViewById(R.id.btnCambiosConductor);
                Button cerrar = (Button)v.findViewById(R.id.btnCerrarConductor);

                final EditText nombre = (EditText)v.findViewById(R.id.etCambioNombresConductor);
                final EditText apellido = (EditText)v.findViewById(R.id.etCambioApellidosConductor);
                final EditText contra = (EditText)v.findViewById(R.id.etCambiopasswordConductor);
                final EditText contra2 = (EditText)v.findViewById(R.id.etCambiopassword2Conductor);

                builder.setView(v);
                final AlertDialog alertDialog = builder.create();

                alertDialog.show();

                editarconductor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nombre.setEnabled(true);
                        apellido.setEnabled(true);
                        contra2.setVisibility(View.VISIBLE);
                        contra.setEnabled(true);
                        contra2.setEnabled(true);
                    }
                });

                guardarconductor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Codigo para Editar", Toast.LENGTH_SHORT).show();
                        //AQUI SI PODES PEGAR EL CODIGO QUE SOLO ES PARA EDITAR PARA HACER LOS OTROS EN BASE A ESTE
                    }
                });

                cerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });


        Config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Activity Configuracion",Toast.LENGTH_LONG).show();
            }
        });

        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearProfile();
                finish();
            }
        });
    }

    private void clearProfile() {

        SharedPreferences preferences;
        Context context = this.getApplicationContext();
        preferences=context.getSharedPreferences("logeo", Context.MODE_PRIVATE);
        //Para borrar el registro de algun dato en elfichero compartido
        // sencillamente empleamos el metodo remove(key)
        // del objeto SharedPreferences.Editor
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("correo");
        editor.remove("contraseña");
        editor.remove("nombre");
        editor.remove("apellido");
        editor.remove("jefe");
        editor.remove("tipo_usuario");
        editor.remove("rutaFoto");
        editor.commit();


    }
}
