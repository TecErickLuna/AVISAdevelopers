package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuscarUnidades extends AppCompatActivity {
    private EditText buscarUnidad, alias, correoC, tarifa, info, horarios, aliasOc, correoOc, tarifaOc, infoOc, horarioOc, rutaOc;
    private Button buscar, rutaMicro;
    private ImageButton cerrarventanabuscar;
    private ImageView imgMicro;
    private TextView rutatexto;
    private String rutaurl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_unidades);

        buscarUnidad = findViewById(R.id.etBuscarUnidad);
        alias = findViewById(R.id.txtAlias);
        correoC = findViewById(R.id.txtCorreoConductor);
        tarifa = findViewById(R.id.txtTarifa);
        info = findViewById(R.id.txtInfo);
        horarios = findViewById(R.id.txtHorarios);
        rutatexto = findViewById(R.id.rutaurl);

        rutaMicro = findViewById(R.id.rutaMicro);

        imgMicro = findViewById(R.id.imgMicroBuscar);



            aliasOc = findViewById(R.id.aliasoculto);
        correoOc = findViewById(R.id.correooculto);
        tarifaOc = findViewById(R.id.tarifaoculto);
        infoOc = findViewById(R.id.infooculto);
        horarioOc = findViewById(R.id.horariooculto);
        rutaOc = findViewById(R.id.rutaoculto);





        cerrarventanabuscar=findViewById(R.id.btnCerrarVentanaBuscar);

        cerrarventanabuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buscar = findViewById(R.id.btnBuscarUnidad);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUnidad("https://avproyect.000webhostapp.com/buscarMicrobuses.php?alias="+buscarUnidad.getText()+"");
            }
        });

    }

    private void buscarUnidad(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        aliasOc.setVisibility(View.VISIBLE);
                        alias.setText(jsonObject.getString("alias"));
                        correoOc.setVisibility(View.VISIBLE);
                        correoC.setText(jsonObject.getString("correo_conductor"));
                        tarifaOc.setVisibility(View.VISIBLE);
                        tarifa.setText(jsonObject.getString("tarifa"));
                        infoOc.setVisibility(View.VISIBLE);
                        info.setText(jsonObject.getString("info"));
                        horarioOc.setVisibility(View.VISIBLE);
                        horarios.setText(jsonObject.getString("horarios"));
                        rutaOc.setVisibility(View.VISIBLE);
                        rutaMicro.setVisibility(View.VISIBLE);
                        rutaMicro.setText(jsonObject.getString("ruta"));
                        rutatexto.setText(jsonObject.getString("imagen"));


                        Picasso.get().load(rutatexto.getText().toString()).into(imgMicro);

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Dato no Encontrado", Toast.LENGTH_LONG).show();

            }
        }
        );
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(jsonArrayRequest);
    }

    public void ruta(View view) {
       /* if (rutaMicro.getText().equals("AHUACHAPAN-SANTA ANA")) {*/
            Intent rpasajero = new Intent(BuscarUnidades.this, MapaRutaAhuacha.class);
            startActivity(rpasajero);


      /*  } else if (rutaMicro.getText().equals("METAPAN-SANTA ANA")) {
            Intent rpasajero = new Intent(BuscarUnidades.this, MapaRutaMetapan.class);
            startActivity(rpasajero);


        }*/

    }
}
