package sv.edu.itca.proyecto.avisa;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class MapaRutaAhuacha extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private View poput = null;
    // private Button mTypeBtn, mTypeBtn2;
    private SearchView searchView;
    FloatingActionsMenu grupoBotones;
    FloatingActionButton fabSatelital, fabNormal, fabAmbos, fabIda, fabVuelta, fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_ruta_ahuacha);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        searchView =findViewById(R.id.sv_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // mTypeBtn = (Button) findViewById(R.id.btnSatelital);
        // mTypeBtn2 = (Button) findViewById(R.id.btnDefecto);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                    } catch (IOException e) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Ubicación no Encontrada",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });








        grupoBotones = findViewById(R.id.floatingGroup);
        fabNormal = findViewById(R.id.fabNormal);
        fabSatelital = findViewById(R.id.fabSatelital);
        fabAmbos = findViewById(R.id.fabAmbos);
        fabIda = findViewById(R.id.fabIda);
        fabVuelta = findViewById(R.id.fabVuelta);
        fabBack = findViewById(R.id.fabBack);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fabNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });


        fabSatelital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        Antut(googleMap);

        fabVuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                Antut(googleMap);
                Vuelta(googleMap);
            }
        });

        fabIda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMap.clear();
                Antut(googleMap);
                Ida(googleMap);
            }
        });

        fabAmbos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                Antut(googleMap);
                Ida(googleMap);
                Vuelta(googleMap);
            }
        });
    }

    public void Antut (GoogleMap googleMap){
        mMap = googleMap;
        final LatLng punto1 = new LatLng (13.974328, -89.569358);
        final LatLng punto2 = new LatLng (13.970319, -89.572866);
        final LatLng punto3 = new LatLng (13.976172, -89.589046);
        final LatLng punto4 = new LatLng (13.984919, -89.548218);

        mMap.addMarker(new MarkerOptions().position(punto1).title("ITCA-FEPADE").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapaitca)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto1, 17));

        mMap.addMarker(new MarkerOptions().position(punto2).title("UES").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapaunacional)));

        mMap.addMarker(new MarkerOptions().position(punto3).title("UNASA").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapaunasa)));

        mMap.addMarker(new MarkerOptions().position(punto4).title("UNICAES").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapaunicaes)));

    }

    public void Vuelta (GoogleMap googleMap){
        mMap = googleMap;
        final LatLng puntoSalida2 = new LatLng (13.974328, -89.569358);
        final LatLng puntoLlegada2 = new LatLng (13.923775, -89.843213);


        mMap.addMarker(new MarkerOptions().position(puntoSalida2).title("Punto Salida").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapaitca)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoSalida2, 17));



        LatLng inicio = new LatLng(13.974207, -89.569035);

        mMap.addPolyline(new PolylineOptions().add(
                inicio,
                new LatLng(13.974990, -89.568635),
                new LatLng(13.975342, -89.568970),
                new LatLng(13.975927, -89.570451),
                new LatLng(13.976140, -89.570846),
                new LatLng(13.975230, -89.571363),
                new LatLng(13.971462, -89.572534),
                new LatLng(13.969189, -89.573182),
                new LatLng(13.967007, -89.574283),
                new LatLng(13.965105, -89.574744),
                new LatLng(13.965080, -89.574765),
                new LatLng(13.963616, -89.575360),
                new LatLng(13.962634, -89.575572),
                new LatLng(13.961846, -89.576181),
                new LatLng(13.961846, -89.576181),
                new LatLng(13.959347, -89.577670),
                new LatLng(13.972079, -89.584312),
                new LatLng(13.973288, -89.585443),
                new LatLng(13.973502, -89.585443),
                new LatLng(13.973679, -89.585692),
                new LatLng(13.973679, -89.585940),
                new LatLng(13.973877, -89.586299),
                new LatLng(13.976173, -89.589200),
                new LatLng(13.976379, -89.589584),
                new LatLng(13.976618, -89.590135),
                new LatLng(13.976903, -89.590993),
                new LatLng(13.977129, -89.592106),
                new LatLng(13.977685, -89.593470),
                new LatLng(13.977951, -89.593921),
                new LatLng(13.978746, -89.594794),
                new LatLng(13.979788, -89.595584),
                new LatLng(13.980738, -89.595994),
                new LatLng(13.981235, -89.596129),
                new LatLng(13.982129, -89.596260),
                new LatLng(13.984002, -89.596423),
                new LatLng(13.984569, -89.596463),
                new LatLng(13.984689, -89.596421),
                new LatLng(13.984830, -89.596331),
                new LatLng(13.984987, -89.596331),
                new LatLng(13.985122, -89.596417),
                new LatLng(13.985192, -89.596578),
                new LatLng(13.985138, -89.596758),
                new LatLng(13.984967, -89.597107),
                new LatLng(13.984888, -89.597770),
                new LatLng(13.984915, -89.598292),
                new LatLng(13.985003, -89.598842),
                new LatLng(13.985807, -89.602541),
                new LatLng(13.986263, -89.603901),
                new LatLng(13.987563, -89.607111),
                new LatLng(13.987804, -89.607810),
                new LatLng(13.987931, -89.608501),
                new LatLng(13.987974, -89.609211),
                new LatLng(13.988150, -89.614731),
                new LatLng(13.988512, -89.618086),
                new LatLng(13.988707, -89.619810),
                new LatLng(13.988752, -89.620627),
                new LatLng(13.988632, -89.621735),
                new LatLng(13.988349, -89.623254),
                new LatLng(13.988218, -89.624555),
                new LatLng(13.988183, -89.627321),
                new LatLng(13.988103, -89.628384),
                new LatLng(13.987973, -89.629357),
                new LatLng(13.987682, -89.631618),
                new LatLng(13.987358, -89.633908),
                new LatLng(13.987335, -89.634119),
                new LatLng(13.987257, -89.634913),
                new LatLng(13.987222, -89.635718),
                new LatLng(13.987223, -89.637559),
                new LatLng(13.987268, -89.638738),
                new LatLng(13.987481, -89.641049),
                new LatLng(13.987727, -89.643292),
                new LatLng(13.987905, -89.645610),
                new LatLng(13.987961, -89.647351),
                new LatLng(13.988010, -89.649094),
                new LatLng(13.988039, -89.649919),
                new LatLng(13.988039, -89.650940),
                new LatLng(13.988018, -89.652537),
                new LatLng(13.988069, -89.653389),
                new LatLng(13.988481, -89.656510),
                new LatLng(13.988677, -89.658094),
                new LatLng(13.988760, -89.658655),
                new LatLng(13.988971, -89.659565),
                new LatLng(13.990127, -89.663244),
                new LatLng(13.990205, -89.663882),
                new LatLng(13.990125, -89.664298),
                new LatLng(13.989989, -89.664707),
                new LatLng(13.987960, -89.668577),
                new LatLng(13.987675, -89.669309),
                new LatLng(13.987579, -89.670011),
                new LatLng(13.987692, -89.671194),
                new LatLng(13.987954, -89.672886),
                new LatLng(13.988242, -89.674473),
                new LatLng(13.988402, -89.675242),
                new LatLng(13.988629, -89.676608),
                new LatLng(13.989026, -89.679207),
                new LatLng(13.989183, -89.680123),
                new LatLng(13.989178, -89.680621),
                new LatLng(13.989089, -89.681158),
                new LatLng(13.988764, -89.681979),
                new LatLng(13.988000, -89.683673),
                new LatLng(13.987906, -89.684028),
                new LatLng(13.987827, -89.684551),
                new LatLng(13.987857, -89.684826),
                new LatLng(13.988159, -89.686825),
                new LatLng(13.988404, -89.688601),
                new LatLng(13.988424, -89.688831),
                new LatLng(13.988336, -89.689334),
                new LatLng(13.988160, -89.689713),
                new LatLng(13.987863, -89.690086),
                new LatLng(13.987660, -89.690267),
                new LatLng(13.987199, -89.690519),
                new LatLng(13.986117, -89.690795),
                new LatLng(13.985366, -89.690924),
                new LatLng(13.984873, -89.690875),
                new LatLng(13.983869, -89.690590),
                new LatLng(13.983050, -89.690398),
                new LatLng(13.982369, -89.690326),
                new LatLng(13.981818, -89.690352),
                new LatLng(13.980834, -89.690414),
                new LatLng(13.980223, -89.690478),
                new LatLng(13.978341, -89.690688),
                new LatLng(13.977633, -89.690810),
                new LatLng(13.977158, -89.691042),
                new LatLng(13.976712, -89.691394),
                new LatLng(13.976006, -89.692330),
                new LatLng(13.973848, -89.695726),
                new LatLng(13.973532, -89.696796),
                new LatLng(13.973205, -89.702727),
                new LatLng(13.973039, -89.706867),
                new LatLng(13.972754, -89.713135),
                new LatLng(13.972506, -89.719045),
                new LatLng(13.972349, -89.722061),
                new LatLng(13.972321, -89.723503),
                new LatLng(13.972235, -89.724749),
                new LatLng(13.972145, -89.726439),
                new LatLng(13.972109, -89.728157),
                new LatLng(13.972025, -89.729577),
                new LatLng(13.971846, -89.732659),
                new LatLng(13.971763, -89.735803),
                new LatLng(13.971646, -89.738289),
                new LatLng(13.971543, -89.740514),
                new LatLng(13.971417, -89.742941),
                new LatLng(13.971373, -89.744516),
                new LatLng(13.971248, -89.746772),
                new LatLng(13.971152, -89.748768),
                new LatLng(13.971017, -89.751010),
                new LatLng(13.970829, -89.751727),
                new LatLng(13.970557, -89.752312),
                new LatLng(13.970308, -89.752705),
                new LatLng(13.968423, -89.754681),
                new LatLng(13.965965, -89.757137),
                new LatLng(13.965222, -89.757979),
                new LatLng(13.964540, -89.759058),
                new LatLng(13.964101, -89.759928),
                new LatLng(13.963764, -89.760652),
                new LatLng(13.963300, -89.762313),
                new LatLng(13.962579, -89.764938),
                new LatLng(13.961146, -89.770521),
                new LatLng(13.960675, -89.772218),
                new LatLng(13.959799, -89.775507),
                new LatLng(13.958370, -89.781031),
                new LatLng(13.957270, -89.785264),
                new LatLng(13.953582, -89.799303),
                new LatLng(13.951743, -89.806174),
                new LatLng(13.950305, -89.811748),
                new LatLng(13.950037, -89.812901),
                new LatLng(13.950012, -89.813949),
                new LatLng(13.950228, -89.814845),
                new LatLng(13.950633, -89.815796),
                new LatLng(13.950733, -89.815958),
                new LatLng(13.950765, -89.816174),
                new LatLng(13.948740, -89.817732),
                new LatLng(13.947224, -89.818840),
                new LatLng(13.946698, -89.819353),
                new LatLng(13.946285, -89.819900),
                new LatLng(13.946079, -89.820348),
                new LatLng(13.945857, -89.821037),
                new LatLng(13.945496, -89.822802),
                new LatLng(13.945216, -89.823155),
                new LatLng(13.944793, -89.823383),
                new LatLng(13.944331, -89.823544),
                new LatLng(13.944012, -89.823782),
                new LatLng(13.943862, -89.823973),
                new LatLng(13.943495, -89.824623),
                new LatLng(13.943080, -89.825096),
                new LatLng(13.941446, -89.826787),
                new LatLng(13.940301, -89.828242),
                new LatLng(13.939974, -89.828450),
                new LatLng(13.939234, -89.828566),
                new LatLng(13.938758, -89.828676),
                new LatLng(13.938304, -89.828870),
                new LatLng(13.937867, -89.829199),
                new LatLng(13.937579, -89.829529),
                new LatLng(13.937214, -89.829930),
                new LatLng(13.936754, -89.830215),
                new LatLng(13.936272, -89.830354),
                new LatLng(13.935742, -89.830524),
                new LatLng(13.935210, -89.830902),
                new LatLng(13.934287, -89.831670),
                new LatLng(13.933275, -89.832514),
                new LatLng(13.932986, -89.832821),
                new LatLng(13.932679, -89.833254),
                new LatLng(13.932417, -89.833714),
                new LatLng(13.932238, -89.834321),
                new LatLng(13.931995, -89.835298),
                new LatLng(13.931767, -89.835944),
                new LatLng(13.931470, -89.836475),
                new LatLng(13.931143, -89.836906),
                new LatLng(13.930075, -89.837974),
                new LatLng(13.929130, -89.838871),
                new LatLng(13.928778, -89.839129),
                new LatLng(13.928092, -89.839519),
                new LatLng(13.927664, -89.839880),
                new LatLng(13.927335, -89.840234),
                new LatLng(13.925826, -89.841900),
                new LatLng(13.925582, -89.842116),
                new LatLng(13.924173, -89.843362),
                new LatLng(13.924002, -89.843376),
                new LatLng(13.923891, -89.843319),
                puntoLlegada2
                )
                        .width(8)
                        .color(Color.BLACK)
        );
    }

    public void Ida(GoogleMap googleMap){

        mMap = googleMap;

        final LatLng puntoSalida = new LatLng (13.924345, -89.843133);
        final LatLng puntoLlegada = new LatLng (13.974207, -89.569035);


        mMap.addMarker(new MarkerOptions().position(puntoSalida).title("Punto Salida").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapamicro)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoSalida, 17));



        LatLng inicio = new LatLng(13.924345, -89.843133);

        mMap.addPolyline(new PolylineOptions().add(
                inicio,
                new LatLng(13.924626, -89.842903),
                new LatLng(13.925726, -89.841943),
                new LatLng(13.926676, -89.840903),
                new LatLng(13.927583, -89.839894),
                new LatLng(13.927906, -89.839613),
                new LatLng(13.928295, -89.839362),
                new LatLng(13.928757, -89.839109),
                new LatLng(13.929068, -89.838868),
                new LatLng(13.929320, -89.838642),
                new LatLng(13.931146, -89.836833),
                new LatLng(13.931334, -89.836608),
                new LatLng(13.931531, -89.836282),
                new LatLng(13.931810, -89.835713),
                new LatLng(13.931961, -89.835264),
                new LatLng(13.932260, -89.834061),
                new LatLng(13.932443, -89.833582),
                new LatLng(13.932675, -89.833153),
                new LatLng(13.932883, -89.832862),
                new LatLng(13.933095, -89.832608),
                new LatLng(13.933147, -89.832561),
                new LatLng(13.934443, -89.831502),
                new LatLng(13.935526, -89.830581),
                new LatLng(13.935790, -89.830444),
                new LatLng(13.936071, -89.830338),
                new LatLng(13.936888, -89.830099),
                new LatLng(13.937052, -89.829994),
                new LatLng(13.937226, -89.829845),
                new LatLng(13.937384, -89.829698),
                new LatLng(13.937541, -89.829500),
                new LatLng(13.937816, -89.829191),
                new LatLng(13.938040, -89.829001),
                new LatLng(13.938305, -89.828820),
                new LatLng(13.938752, -89.828615),
                new LatLng(13.939168, -89.828516),
                new LatLng(13.939970, -89.828405),
                new LatLng(13.940062, -89.828349),
                new LatLng(13.940392, -89.828082),
                new LatLng(13.940617, -89.827784),
                new LatLng(13.941623, -89.826497),
                new LatLng(13.941770, -89.826344),
                new LatLng(13.941904, -89.826169),
                new LatLng(13.943019, -89.825092),
                new LatLng(13.943349, -89.824681),
                new LatLng(13.943478, -89.824502),
                new LatLng(13.943853, -89.823851),
                new LatLng(13.943983, -89.823699),
                new LatLng(13.944370, -89.823429),
                new LatLng(13.945101, -89.823163),
                new LatLng(13.945268, -89.823005),
                new LatLng(13.945389, -89.822829),
                new LatLng(13.945498, -89.822587),
                new LatLng(13.945871, -89.820814),
                new LatLng(13.946069, -89.820189),
                new LatLng(13.946311, -89.819718),
                new LatLng(13.946587, -89.819335),
                new LatLng(13.946906, -89.819002),
                new LatLng(13.947613, -89.818454),
                new LatLng(13.949046, -89.817418),
                new LatLng(13.950242, -89.816554),
                new LatLng(13.950319, -89.816445),
                new LatLng(13.950395, -89.816180),
                new LatLng(13.950417, -89.815930),
                new LatLng(13.950177, -89.815306),
                new LatLng(13.950080, -89.814869),
                new LatLng(13.950048, -89.814579),
                new LatLng(13.949920, -89.813611),
                new LatLng(13.949969, -89.812852),
                new LatLng(13.950037, -89.812599),
                new LatLng(13.950468, -89.810947),
                new LatLng(13.951254, -89.807869),
                new LatLng(13.952132, -89.804479),
                new LatLng(13.953009, -89.801260),
                new LatLng(13.953995, -89.797585),
                new LatLng(13.954940, -89.793952),
                new LatLng(13.955618, -89.791356),
                new LatLng(13.956451, -89.788189),
                new LatLng(13.957244, -89.785108),
                new LatLng(13.958041, -89.782032),
                new LatLng(13.958755, -89.779157),
                new LatLng(13.959396, -89.776801),
                new LatLng(13.960116, -89.774078),
                new LatLng(13.960819, -89.771432),
                new LatLng(13.961574, -89.768588),
                new LatLng(13.961884, -89.767336),
                new LatLng(13.962212, -89.766150),
                new LatLng(13.962389, -89.765559),
                new LatLng(13.962925, -89.763474),
                new LatLng(13.963562, -89.761082),
                new LatLng(13.964108, -89.759695),
                new LatLng(13.964427, -89.759097),
                new LatLng(13.964499, -89.758965),
                new LatLng(13.965098, -89.758058),
                new LatLng(13.965900, -89.757117),
                new LatLng(13.966119, -89.756878),
                new LatLng(13.967066, -89.755925),
                new LatLng(13.967186, -89.755744),
                new LatLng(13.967854, -89.755103),
                new LatLng(13.967995, -89.754905),
                new LatLng(13.968617, -89.754271),
                new LatLng(13.968755, -89.754186),
                new LatLng(13.969604, -89.753393),
                new LatLng(13.970295, -89.752627),
                new LatLng(13.970381, -89.752483),
                new LatLng(13.970648, -89.751996),
                new LatLng(13.970891, -89.751386),
                new LatLng(13.970972, -89.750903),
                new LatLng(13.970972, -89.750798),
                new LatLng(13.971080, -89.748275),
                new LatLng(13.971197, -89.746055),
                new LatLng(13.971279, -89.744701),
                new LatLng(13.971357, -89.742586),
                new LatLng(13.971403, -89.741652),
                new LatLng(13.971501, -89.739619),
                new LatLng(13.971582, -89.737902),
                new LatLng(13.971600, -89.737189),
                new LatLng(13.971627, -89.736365),
                new LatLng(13.971643, -89.735208),
                new LatLng(13.971720, -89.734281),
                new LatLng(13.971715, -89.733576),
                new LatLng(13.971801, -89.731902),
                new LatLng(13.971896, -89.730582),
                new LatLng(13.971975, -89.729125),
                new LatLng(13.971999, -89.728112),
                new LatLng(13.972055, -89.726153),
                new LatLng(13.972091, -89.725610),
                new LatLng(13.972230, -89.723104),
                new LatLng(13.972227, -89.723031),
                new LatLng(13.972332, -89.720420),
                new LatLng(13.972369, -89.719338),
                new LatLng(13.972457, -89.717322),
                new LatLng(13.972567, -89.714592),
                new LatLng(13.972659, -89.713248),
                new LatLng(13.972693, -89.712700),
                new LatLng(13.972725, -89.711586),
                new LatLng(13.972788, -89.710013),
                new LatLng(13.972859, -89.708584),
                new LatLng(13.972932, -89.707301),
                new LatLng(13.972983, -89.705979),
                new LatLng(13.973123, -89.702791),
                new LatLng(13.973232, -89.700328),
                new LatLng(13.973280, -89.699086),
                new LatLng(13.973391, -89.697027),
                new LatLng(13.973537, -89.696346),
                new LatLng(13.973763, -89.695671),
                new LatLng(13.974048, -89.695113),
                new LatLng(13.974723, -89.694082),
                new LatLng(13.976489, -89.691431),
                new LatLng(13.976964, -89.690990),
                new LatLng(13.977391, -89.690772),
                new LatLng(13.978050, -89.690599),
                new LatLng(13.979125, -89.690481),
                new LatLng(13.981932, -89.690255),
                new LatLng(13.982861, -89.690307),
                new LatLng(13.983571, -89.690450),
                new LatLng(13.985058, -89.690799),
                new LatLng(13.985529, -89.690802),
                new LatLng(13.986116, -89.690718),
                new LatLng(13.986723, -89.690632),
                new LatLng(13.987230, -89.690467),
                new LatLng(13.987486, -89.690336),
                new LatLng(13.987824, -89.690030),
                new LatLng(13.988045, -89.689730),
                new LatLng(13.988230, -89.689300),
                new LatLng(13.988311, -89.688906),
                new LatLng(13.988297, -89.688579),
                new LatLng(13.988320, -89.688718),
                new LatLng(13.988130, -89.687464),
                new LatLng(13.987850, -89.685276),
                new LatLng(13.987745, -89.684601),
                new LatLng(13.987779, -89.684096),
                new LatLng(13.987871, -89.683704),
                new LatLng(13.988521, -89.682182),
                new LatLng(13.988922, -89.681262),
                new LatLng(13.989070, -89.680709),
                new LatLng(13.989093, -89.680185),
                new LatLng(13.989075, -89.680002),
                new LatLng(13.988909, -89.679119),
                new LatLng(13.988675, -89.677599),
                new LatLng(13.988487, -89.676329),
                new LatLng(13.988237, -89.674845),
                new LatLng(13.987707, -89.671915),
                new LatLng(13.987546, -89.670817),
                new LatLng(13.987471, -89.669858),
                new LatLng(13.987741, -89.668843),
                new LatLng(13.987952, -89.668286),
                new LatLng(13.989621, -89.665197),
                new LatLng(13.989926, -89.664489),
                new LatLng(13.990003, -89.664248),
                new LatLng(13.990066, -89.663856),
                new LatLng(13.990020, -89.663441),
                new LatLng(13.989952, -89.663132),
                new LatLng(13.988801, -89.659459),
                new LatLng(13.988704, -89.659100),
                new LatLng(13.988532, -89.658200),
                new LatLng(13.988119, -89.654902),
                new LatLng(13.987937, -89.653375),
                new LatLng(13.987897, -89.652616),
                new LatLng(13.987903, -89.651490),
                new LatLng(13.987880, -89.650942),
                new LatLng(13.987868, -89.649879),
                new LatLng(13.987726, -89.647927),
                new LatLng(13.987455, -89.643274),
                new LatLng(13.987399, -89.642104),
                new LatLng(13.987314, -89.640472),
                new LatLng(13.987172, -89.639061),
                new LatLng(13.987172, -89.638006),
                new LatLng(13.987149, -89.635515),
                new LatLng(13.987177, -89.634566),
                new LatLng(13.987647, -89.631226),
                new LatLng(13.987874, -89.629130),
                new LatLng(13.988039, -89.627778),
                new LatLng(13.988084, -89.626873),
                new LatLng(13.988113, -89.626098),
                new LatLng(13.988146, -89.624404),
                new LatLng(13.988208, -89.623877),
                new LatLng(13.988303, -89.623099),
                new LatLng(13.988579, -89.621621),
                new LatLng(13.988664, -89.621039),
                new LatLng(13.988647, -89.620976),
                new LatLng(13.988658, -89.620517),
                new LatLng(13.988608, -89.619762),
                new LatLng(13.988518, -89.618880),
                new LatLng(13.988106, -89.615278),
                new LatLng(13.987984, -89.613938),
                new LatLng(13.987934, -89.612906),
                new LatLng(13.987853, -89.608627),
                new LatLng(13.987782, -89.608233),
                new LatLng(13.987484, -89.607250),
                new LatLng(13.985863, -89.603193),
                new LatLng(13.985731, -89.602786),
                new LatLng(13.985380, -89.601216),
                new LatLng(13.984917, -89.598857),
                new LatLng(13.984792, -89.598141),
                new LatLng(13.984797, -89.597576),
                new LatLng(13.984792, -89.597115),
                new LatLng(13.984742, -89.596873),
                new LatLng(13.984644, -89.596716),
                new LatLng(13.984486, -89.596632),
                new LatLng(13.984127, -89.596575),
                new LatLng(13.983814, -89.596521),
                new LatLng(13.983325, -89.596492),
                new LatLng(13.981618, -89.596334),
                new LatLng(13.981327, -89.596287),
                new LatLng(13.980761, -89.596109),
                new LatLng(13.980226, -89.595872),
                new LatLng(13.979807, -89.595695),
                new LatLng(13.979024, -89.595168),
                new LatLng(13.978401, -89.594609),
                new LatLng(13.977883, -89.593979),
                new LatLng(13.977612, -89.593581),
                new LatLng(13.977389, -89.593024),
                new LatLng(13.977065, -89.592210),
                new LatLng(13.976930, -89.591326),
                new LatLng(13.976734, -89.590593),
                new LatLng(13.976487, -89.589964),
                new LatLng(13.976231, -89.589334),
                new LatLng(13.975665, -89.588571),
                new LatLng(13.975336, -89.588118),
                new LatLng(13.974418, -89.587081),
                new LatLng(13.973570, -89.586164),
                new LatLng(13.973460, -89.586022),
                new LatLng(13.973285, -89.585963),
                new LatLng(13.973154, -89.585817),
                new LatLng(13.973164, -89.585621),
                new LatLng(13.973254, -89.585475),
                new LatLng(13.973375, -89.585425),
                new LatLng(13.973560, -89.585424),
                new LatLng(13.973750, -89.585325),
                new LatLng(13.973909, -89.585160),
                new LatLng(13.974213, -89.584861),
                new LatLng(13.974377, -89.584701),
                new LatLng(13.975465, -89.583398),
                new LatLng(13.976977, -89.581591),
                new LatLng(13.977771, -89.580624),
                new LatLng(13.978070, -89.580120),
                new LatLng(13.978202, -89.579824),
                new LatLng(13.978391, -89.579374),
                new LatLng(13.978620, -89.578898),
                new LatLng(13.978709, -89.578739),
                new LatLng(13.978951, -89.578480),
                new LatLng(13.979299, -89.578202),
                new LatLng(13.979755, -89.577855),
                new LatLng(13.979750, -89.577764),
                new LatLng(13.979398, -89.576548),
                new LatLng(13.979382, -89.576467),
                new LatLng(13.978520, -89.576670),
                new LatLng(13.978394, -89.576239),
                new LatLng(13.977792, -89.573916),
                new LatLng(13.977376, -89.572357),
                new LatLng(13.977354, -89.572225),
                new LatLng(13.977221, -89.572054),
                new LatLng(13.976558, -89.571485),
                new LatLng(13.976140, -89.570846),
                new LatLng(13.975230, -89.571363),
                new LatLng(13.972450, -89.572239),
                new LatLng(13.971210, -89.572605),
                new LatLng(13.970346, -89.572870),
                new LatLng(13.971210, -89.572605),
                new LatLng(13.972450, -89.572239),
                new LatLng(13.975230, -89.571363),
                new LatLng(13.976140, -89.570846),
                new LatLng(13.975903, -89.570457),
                new LatLng(13.975659, -89.569858),
                new LatLng(13.975344, -89.569065),
                new LatLng(13.975304, -89.568982),
                new LatLng(13.974992, -89.568651),
                new LatLng(13.974959, -89.568656),
                new LatLng(13.974390, -89.568974),
                puntoLlegada
                )
                        .width(8)
                        .color(Color.rgb(216,40,34))
        );

    }
}