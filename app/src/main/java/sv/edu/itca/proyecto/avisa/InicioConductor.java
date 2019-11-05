package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
                Toast.makeText(getApplicationContext(),"en Desarrollo",Toast.LENGTH_LONG).show();
            }
        });

        Rutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Rutas",Toast.LENGTH_LONG).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(InicioConductor.this);

                LayoutInflater inflater = InicioConductor.this.getLayoutInflater();

                View v = inflater.inflate(R.layout.cuadrodialogo_rutas, null);



                Button meta = (Button)v.findViewById(R.id.mostrar_rutameta);
                Button ahuacha = (Button)v.findViewById(R.id.mostrar_rutaahuacha);

                builder.setView(v);
                final AlertDialog alertDialog = builder.create();

                alertDialog.show();

                meta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        Intent rpasajero = new Intent(InicioConductor.this, MapaRutaAhuacha.class);
                        startActivity(rpasajero);
                    }
                });

                ahuacha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        Intent rpasajero = new Intent(InicioConductor.this, MapaRutaMetapan.class);
                        startActivity(rpasajero);

                    }
                });
            }
        });

        Solicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Buscar",Toast.LENGTH_LONG).show();
                Intent rpasajero = new Intent(InicioConductor.this, BuscarUnidades.class);
                startActivity(rpasajero);
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

                final AlertDialog.Builder builder = new AlertDialog.Builder(InicioConductor.this);

                LayoutInflater inflater = InicioConductor.this.getLayoutInflater();

                View v = inflater.inflate(R.layout.video, null);



                ImageButton face = (ImageButton)v.findViewById(R.id.btnFacebook);
                ImageButton inta = (ImageButton)v.findViewById(R.id.btnInstagram);
                ImageButton twit = (ImageButton)v.findViewById(R.id.btnTwitter);


                builder.setView(v);
                final AlertDialog alertDialog = builder.create();

                alertDialog.show();
                face.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"Contactanos",Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100043481850033");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    }
                });
                inta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"Contactanos",Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("https://www.instagram.com/avisavamos/?hl=es-la");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                twit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "en Mantenimiento...", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(InicioConductor.this);
                builder.setMessage("Opciones");
                builder.setCancelable(false);
                builder.setTitle("Seguro que desea Salir?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearProfile();
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.create();
                builder.show();
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
