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

public class MapaRutaMetapan extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // private Button mTypeBtn, mTypeBtn2;
    private SearchView searchView;
    FloatingActionsMenu grupoBotones;
    FloatingActionButton fabSatelital, fabNormal, fabAmbos, fabIda, fabVuelta, fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_ruta_metapan);

        searchView =findViewById(R.id.sv_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
    public void Vuelta (GoogleMap googleMap) {
        mMap = googleMap;
        final LatLng puntoSalida2 = new LatLng(13.970357, -89.572788);
        final LatLng puntoLlegada2 = new LatLng(14.325531, -89.443572);

        mMap.addMarker(new MarkerOptions().position(puntoSalida2));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoSalida2, 17));


        LatLng inicio = new LatLng(13.970357, -89.572788);

        mMap.addPolyline(new PolylineOptions().add(
                inicio,
                new LatLng(13.971099, -89.572570),
                new LatLng(13.971754, -89.572376),
                new LatLng(13.972361, -89.572216),
                new LatLng(13.973268, -89.571954),
                new LatLng(13.974320, -89.571618),
                new LatLng(13.975218, -89.571346),
                new LatLng(13.975531, -89.571169),
                new LatLng(13.976086, -89.570862),
                new LatLng(13.976120, -89.570813),
                new LatLng(13.975890, -89.570455),
                new LatLng(13.975803, -89.570209),
                new LatLng(13.975646, -89.569827),
                new LatLng(13.975312, -89.568957),
                new LatLng(13.974983, -89.568657),
                new LatLng(13.974364, -89.568989),
                new LatLng(13.974058, -89.569156),
                new LatLng(13.974034, -89.569125),
                new LatLng(13.974298, -89.568983),
                new LatLng(13.975344, -89.568430),
                new LatLng(13.975952, -89.568063),
                new LatLng(13.976380, -89.567826),
                new LatLng(13.977116, -89.566829),
                new LatLng(13.977898, -89.565810),
                new LatLng(13.978279, -89.565386),
                new LatLng(13.978647, -89.564965),
                new LatLng(13.978828, -89.564855),
                new LatLng(13.979065, -89.564762),
                new LatLng(13.979589, -89.564646),
                new LatLng(13.980100, -89.564500),
                new LatLng(13.980621, -89.564407),
                new LatLng(13.980651, -89.564342),
                new LatLng(13.980568, -89.564038),
                new LatLng(13.980244, -89.562804),
                new LatLng(13.980116, -89.562159),
                new LatLng(13.980007, -89.561333),
                new LatLng(13.979927, -89.560537),
                new LatLng(13.979939, -89.559999),
                new LatLng(13.979892, -89.559975),
                new LatLng(13.979660, -89.560044),
                new LatLng(13.978419, -89.560464),
                new LatLng(13.978267, -89.560491),
                new LatLng(13.977738, -89.560642),
                new LatLng(13.977389, -89.560749),
                new LatLng(13.977006, -89.560906),
                new LatLng(13.976701, -89.560983),
                new LatLng(13.976578, -89.560953),
                new LatLng(13.976451, -89.560823),
                new LatLng(13.976418, -89.560588),
                new LatLng(13.976496, -89.560330),
                new LatLng(13.977138, -89.558997),
                new LatLng(13.977704, -89.557891),
                new LatLng(13.978199, -89.556907),
                new LatLng(13.978347, -89.556618),
                new LatLng(13.978724, -89.556009),
                new LatLng(13.978972, -89.555738),
                new LatLng(13.979353, -89.555346),
                new LatLng(13.980074, -89.554851),
                new LatLng(13.981462, -89.554238),
                new LatLng(13.982072, -89.553956),
                new LatLng(13.982593, -89.553671),
                new LatLng(13.983080, -89.553301),
                new LatLng(13.983651, -89.552729),
                new LatLng(13.984094, -89.552228),
                new LatLng(13.984603, -89.551513),
                new LatLng(13.984760, -89.551044),
                new LatLng(13.984821, -89.550631),
                new LatLng(13.984866, -89.550024),
                new LatLng(13.984943, -89.548943),
                new LatLng(13.984975, -89.547581),
                new LatLng(13.984918, -89.547432),
                new LatLng(13.984970, -89.547179),
                new LatLng(13.985127, -89.547061),
                new LatLng(13.985327, -89.546663),
                new LatLng(13.985538, -89.546383),
                new LatLng(13.985817, -89.546109),
                new LatLng(13.986385, -89.545608),
                new LatLng(13.986886, -89.545170),
                new LatLng(13.988481, -89.543792),
                new LatLng(13.989414, -89.542966),
                new LatLng(13.989800, -89.542636),
                new LatLng(13.990220, -89.542344),
                new LatLng(13.990694, -89.542062),
                new LatLng(13.991041, -89.541903),
                new LatLng(13.991828, -89.541648),
                new LatLng(13.992715, -89.541515),
                new LatLng(13.993942, -89.541453),
                new LatLng(13.996070, -89.541404),
                new LatLng(13.997616, -89.541419),
                new LatLng(13.998776, -89.541499),
                new LatLng(14.001504, -89.541694),
                new LatLng(14.003889, -89.541870),
                new LatLng(14.005654, -89.542026),
                new LatLng(14.006670, -89.542134),
                new LatLng(14.007701, -89.542292),
                new LatLng(14.010615, -89.542893),
                new LatLng(14.010849, -89.542936),
                new LatLng(14.011279, -89.542908),
                new LatLng(14.011408, -89.542927),
                new LatLng(14.011551, -89.542885),
                new LatLng(14.012118, -89.542620),
                new LatLng(14.012483, -89.542339),
                new LatLng(14.013230, -89.541562),
                new LatLng(14.013828, -89.540869),
                new LatLng(14.015263, -89.539264),
                new LatLng(14.016412, -89.537975),
                new LatLng(14.016746, -89.537646),
                new LatLng(14.016932, -89.537479),
                new LatLng(14.017130, -89.537325),
                new LatLng(14.017417, -89.537167),
                new LatLng(14.017813, -89.536979),
                new LatLng(14.018213, -89.536837),
                new LatLng(14.019078, -89.536598),
                new LatLng(14.020633, -89.536205),
                new LatLng(14.021926, -89.535866),
                new LatLng(14.022731, -89.535612),
                new LatLng(14.023043, -89.535485),
                new LatLng(14.023288, -89.535277),
                new LatLng(14.023477, -89.535108),
                new LatLng(14.023588, -89.534839),
                new LatLng(14.023798, -89.534402),
                new LatLng(14.024563, -89.532896),
                new LatLng(14.024816, -89.532492),
                new LatLng(14.025808, -89.531045),
                new LatLng(14.026183, -89.530516),
                new LatLng(14.026406, -89.530210),
                new LatLng(14.026894, -89.529691),
                new LatLng(14.027947, -89.528754),
                new LatLng(14.029219, -89.527679),
                new LatLng(14.031829, -89.525484),
                new LatLng(14.032673, -89.524788),
                new LatLng(14.033443, -89.524126),
                new LatLng(14.033893, -89.523754),
                new LatLng(14.034311, -89.523443),
                new LatLng(14.034662, -89.523155),
                new LatLng(14.035057, -89.522968),
                new LatLng(14.035880, -89.522620),
                new LatLng(14.037595, -89.521898),
                new LatLng(14.038741, -89.521379),
                new LatLng(14.039860, -89.520890),
                new LatLng(14.041300, -89.520286),
                new LatLng(14.042095, -89.519937),
                new LatLng(14.042915, -89.519586),
                new LatLng(14.043803, -89.519229),
                new LatLng(14.044402, -89.518961),
                new LatLng(14.045699, -89.518402),
                new LatLng(14.046329, -89.518138),
                new LatLng(14.047164, -89.517702),
                new LatLng(14.047994, -89.517435),
                new LatLng(14.048952, -89.517010),
                new LatLng(14.049513, -89.516753),
                new LatLng(14.049926, -89.516562),
                new LatLng(14.050442, -89.516375),
                new LatLng(14.051044, -89.516122),
                new LatLng(14.051487, -89.516006),
                new LatLng(14.051831, -89.516000),
                new LatLng(14.052071, -89.516012),
                new LatLng(14.052478, -89.516048),
                new LatLng(14.052750, -89.516027),
                new LatLng(14.052970, -89.515951),
                new LatLng(14.053171, -89.515862),
                new LatLng(14.054581, -89.515063),
                new LatLng(14.054982, -89.514707),
                new LatLng(14.055295, -89.514350),
                new LatLng(14.055512, -89.514045),
                new LatLng(14.055626, -89.513741),
                new LatLng(14.055642, -89.513412),
                new LatLng(14.055550, -89.512980),
                new LatLng(14.055385, -89.512461),
                new LatLng(14.055119, -89.511424),
                new LatLng(14.055096, -89.511119),
                new LatLng(14.055305, -89.510713),
                new LatLng(14.055550, -89.510453),
                new LatLng(14.055845, -89.510334),
                new LatLng(14.056246, -89.510340),
                new LatLng(14.057052, -89.510326),
                new LatLng(14.057325, -89.510207),
                new LatLng(14.057534, -89.510000),
                new LatLng(14.057697, -89.509726),
                new LatLng(14.058076, -89.509130),
                new LatLng(14.058355, -89.508846),
                new LatLng(14.058715, -89.508647),
                new LatLng(14.059188, -89.508584),
                new LatLng(14.059615, -89.508673),
                new LatLng(14.060401, -89.509052),
                new LatLng(14.061267, -89.509408),
                new LatLng(14.061617, -89.509416),
                new LatLng(14.061993, -89.509297),
                new LatLng(14.062337, -89.509035),
                new LatLng(14.062594, -89.508684),
                new LatLng(14.062764, -89.508399),
                new LatLng(14.063494, -89.507211),
                new LatLng(14.064830, -89.504918),
                new LatLng(14.065155, -89.504543),
                new LatLng(14.066872, -89.503069),
                new LatLng(14.067183, -89.502858),
                new LatLng(14.067467, -89.502767),
                new LatLng(14.067753, -89.502725),
                new LatLng(14.071532, -89.502711),
                new LatLng(14.072567, -89.502714),
                new LatLng(14.074370, -89.502715),
                new LatLng(14.074951, -89.502705),
                new LatLng(14.075274, -89.502627),
                new LatLng(14.075599, -89.502485),
                new LatLng(14.076147, -89.502196),
                new LatLng(14.080553, -89.499923),
                new LatLng(14.081063, -89.499730),
                new LatLng(14.081777, -89.499612),
                new LatLng(14.082513, -89.499667),
                new LatLng(14.086058, -89.500084),
                new LatLng(14.086486, -89.500105),
                new LatLng(14.087922, -89.500106),
                new LatLng(14.091292, -89.499942),
                new LatLng(14.093249, -89.499543),
                new LatLng(14.093749, -89.499496),
                new LatLng(14.094244, -89.499470),
                new LatLng(14.095727, -89.499579),
                new LatLng(14.099286, -89.499837),
                new LatLng(14.100632, -89.499921),
                new LatLng(14.102197, -89.500033),
                new LatLng(14.102906, -89.499978),
                new LatLng(14.105793, -89.499706),
                new LatLng(14.107210, -89.499465),
                new LatLng(14.108893, -89.499198),
                new LatLng(14.109987, -89.498905),
                new LatLng(14.110591, -89.498802),
                new LatLng(14.111196, -89.498759),
                new LatLng(14.111558, -89.498773),
                new LatLng(14.111927, -89.498819),
                new LatLng(14.114354, -89.499331),
                new LatLng(14.114714, -89.499368),
                new LatLng(14.115101, -89.499314),
                new LatLng(14.115367, -89.499245),
                new LatLng(14.115852, -89.499063),
                new LatLng(14.116253, -89.498960),
                new LatLng(14.117090, -89.498927),
                new LatLng(14.117753, -89.498993),
                new LatLng(14.122676, -89.499457),
                new LatLng(14.123707, -89.499567),
                new LatLng(14.126733, -89.499862),
                new LatLng(14.127432, -89.499996),
                new LatLng(14.127922, -89.500155),
                new LatLng(14.128486, -89.500420),
                new LatLng(14.129724, -89.501326),
                new LatLng(14.132420, -89.504933),
                new LatLng(14.133854, -89.506835),
                new LatLng(14.134175, -89.507098),
                new LatLng(14.136519, -89.507925),
                new LatLng(14.137169, -89.508269),
                new LatLng(14.137564, -89.508623),
                new LatLng(14.137844, -89.509014),
                new LatLng(14.138164, -89.509692),
                new LatLng(14.138257, -89.510232),
                new LatLng(14.138274, -89.510576),
                new LatLng(14.138159, -89.512116),
                new LatLng(14.138240, -89.512513),
                new LatLng(14.138484, -89.512844),
                new LatLng(14.138795, -89.513039),
                new LatLng(14.140249, -89.513663),
                new LatLng(14.140506, -89.513658),
                new LatLng(14.141976, -89.513345),
                new LatLng(14.142349, -89.513432),
                new LatLng(14.143674, -89.514091),
                new LatLng(14.144052, -89.514241),
                new LatLng(14.144446, -89.514293),
                new LatLng(14.146144, -89.513976),
                new LatLng(14.146789, -89.514037),
                new LatLng(14.147084, -89.514239),
                new LatLng(14.147885, -89.514723),
                new LatLng(14.148195, -89.514760),
                new LatLng(14.150082, -89.514349),
                new LatLng(14.152836, -89.514013),
                new LatLng(14.153233, -89.513904),
                new LatLng(14.155679, -89.512388),
                new LatLng(14.155947, -89.511857),
                new LatLng(14.156012, -89.511436),
                new LatLng(14.155467, -89.509307),
                new LatLng(14.155527, -89.508181),
                new LatLng(14.155824, -89.507574),
                new LatLng(14.157384, -89.505307),
                new LatLng(14.159387, -89.502399),
                new LatLng(14.161016, -89.500097),
                new LatLng(14.161688, -89.499701),
                new LatLng(14.162150, -89.499615),
                new LatLng(14.162798, -89.499652),
                new LatLng(14.163326, -89.499917),
                new LatLng(14.164095, -89.500605),
                new LatLng(14.164591, -89.500932),
                new LatLng(14.165158, -89.501103),
                new LatLng(14.165445, -89.501136),
                new LatLng(14.168502, -89.501082),
                new LatLng(14.169124, -89.500963),
                new LatLng(14.169509, -89.500748),
                new LatLng(14.176054, -89.495521),
                new LatLng(14.176794, -89.495215),
                new LatLng(14.182937, -89.493927),
                new LatLng(14.184025, -89.493813),
                new LatLng(14.185623, -89.494075),
                new LatLng(14.187794, -89.494560),
                new LatLng(14.188100, -89.494769),
                new LatLng(14.188420, -89.495133),
                new LatLng(14.188773, -89.495755),
                new LatLng(14.189306, -89.496756),
                new LatLng(14.189702, -89.496995),
                new LatLng(14.190279, -89.497058),
                new LatLng(14.192270, -89.496918),
                new LatLng(14.193129, -89.496444),
                new LatLng(14.196027, -89.494399),
                new LatLng(14.196584, -89.494160),
                new LatLng(14.204928, -89.494772),
                new LatLng(14.205764, -89.494999),
                new LatLng(14.211078, -89.496798),
                new LatLng(14.215571, -89.497070),
                new LatLng(14.216689, -89.497064),
                new LatLng(14.217390, -89.496851),
                new LatLng(14.221008, -89.495020),
                new LatLng(14.221338, -89.494673),
                new LatLng(14.221532, -89.494199),
                new LatLng(14.221594, -89.493854),
                new LatLng(14.221517, -89.493357),
                new LatLng(14.220753, -89.488295),
                new LatLng(14.220722, -89.487585),
                new LatLng(14.220984, -89.487123),
                new LatLng(14.222093, -89.486049),
                new LatLng(14.222348, -89.485667),
                new LatLng(14.222464, -89.485347),
                new LatLng(14.222966, -89.483488),
                new LatLng(14.223258, -89.483056),
                new LatLng(14.223686, -89.482793),
                new LatLng(14.226102, -89.482390),
                new LatLng(14.226399, -89.482242),
                new LatLng(14.226697, -89.481931),
                new LatLng(14.226984, -89.481365),
                new LatLng(14.228237, -89.478475),
                new LatLng(14.228356, -89.478073),
                new LatLng(14.228682, -89.473907),
                new LatLng(14.228679, -89.473323),
                new LatLng(14.228350, -89.472086),
                new LatLng(14.227472, -89.470300),
                new LatLng(14.226257, -89.468415),
                new LatLng(14.226173, -89.467928),
                new LatLng(14.226662, -89.466676),
                new LatLng(14.226750, -89.466555),
                new LatLng(14.227037, -89.466233),
                new LatLng(14.228950, -89.465589),
                new LatLng(14.229745, -89.465755),
                new LatLng(14.232340, -89.466822),
                new LatLng(14.233976, -89.467435),
                new LatLng(14.236052, -89.468648),
                new LatLng(14.236502, -89.469055),
                new LatLng(14.238612, -89.471282),
                new LatLng(14.239068, -89.471562),
                new LatLng(14.240965, -89.472585),
                new LatLng(14.241448, -89.472667),
                new LatLng(14.242003, -89.472547),
                new LatLng(14.246840, -89.469437),
                new LatLng(14.247460, -89.468873),
                new LatLng(14.248012, -89.468266),
                new LatLng(14.250767, -89.464853),
                new LatLng(14.251003, -89.464467),
                new LatLng(14.251142, -89.464159),
                new LatLng(14.251198, -89.463596),
                new LatLng(14.251233, -89.462944),
                new LatLng(14.251524, -89.462352),
                new LatLng(14.251942, -89.462102),
                new LatLng(14.252426, -89.461926),
                new LatLng(14.253035, -89.461863),
                new LatLng(14.253225, -89.461800),
                new LatLng(14.253464, -89.461625),
                new LatLng(14.254239, -89.460857),
                new LatLng(14.254548, -89.460666),
                new LatLng(14.254951, -89.460634),
                new LatLng(14.255435, -89.460755),
                new LatLng(14.256457, -89.461062),
                new LatLng(14.257015, -89.461076),
                new LatLng(14.257377, -89.461021),
                new LatLng(14.262172, -89.459838),
                new LatLng(14.262907, -89.459755),
                new LatLng(14.263338, -89.459754),
                new LatLng(14.263773, -89.459807),
                new LatLng(14.267007, -89.460455),
                new LatLng(14.267646, -89.460509),
                new LatLng(14.272504, -89.460541),
                new LatLng(14.273700, -89.460586),
                new LatLng(14.274923, -89.460718),
                new LatLng(14.283360, -89.461650),
                new LatLng(14.289485, -89.462284),
                new LatLng(14.293800, -89.463302),
                new LatLng(14.294674, -89.463428),
                new LatLng(14.295240, -89.463395),
                new LatLng(14.299200, -89.462912),
                new LatLng(14.299963, -89.462567),
                new LatLng(14.300270, -89.462413),
                new LatLng(14.300624, -89.462094),
                new LatLng(14.300932, -89.461728),
                new LatLng(14.301224, -89.461287),
                new LatLng(14.304441, -89.455643),
                new LatLng(14.304762, -89.455103),
                new LatLng(14.305079, -89.454704),
                new LatLng(14.305644, -89.454234),
                new LatLng(14.306296, -89.453892),
                new LatLng(14.310532, -89.451843),
                new LatLng(14.310738, -89.451689),
                new LatLng(14.316930, -89.446535),
                new LatLng(14.318463, -89.445293),
                new LatLng(14.322587, -89.444298),
                new LatLng(14.323474, -89.444041),
                new LatLng(14.325097, -89.443691),
                puntoLlegada2
                )
                        .width(8)
                        .color(Color.BLACK)
        );
    }


    public void Ida(GoogleMap googleMap){

            mMap = googleMap;

            final LatLng puntoSalida = new LatLng(14.325485, -89.443689);
            final LatLng puntoLlegada = new LatLng(13.974030, -89.569173);


            mMap.addMarker(new MarkerOptions().position(puntoSalida).title("Punto Salida").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconomapamicro)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoSalida, 17));


            LatLng inicio = new LatLng(14.325485, -89.443689);

            mMap.addPolyline(new PolylineOptions().add(
                    inicio,
                    new LatLng(14.322999, -89.444258),
                    new LatLng(14.320990, -89.444728),
                    new LatLng(14.318762, -89.445293),
                    new LatLng(14.318554, -89.445382),
                    new LatLng(14.318418, -89.445441),
                    new LatLng(14.318101, -89.445653),
                    new LatLng(14.317263, -89.446340),
                    new LatLng(14.315199, -89.448075),
                    new LatLng(14.314221, -89.448890),
                    new LatLng(14.313744, -89.449286),
                    new LatLng(14.312409, -89.450418),
                    new LatLng(14.311116, -89.451469),
                    new LatLng(14.310638, -89.451864),
                    new LatLng(14.309597, -89.452393),
                    new LatLng(14.307479, -89.453442),
                    new LatLng(14.306123, -89.454047),
                    new LatLng(14.305850, -89.454203),
                    new LatLng(14.305447, -89.454488),
                    new LatLng(14.305184, -89.454734),
                    new LatLng(14.304895, -89.455053),
                    new LatLng(14.304592, -89.455511),
                    new LatLng(14.304404, -89.455860),
                    new LatLng(14.303627, -89.457286),
                    new LatLng(14.302874, -89.458612),
                    new LatLng(14.302099, -89.459945),
                    new LatLng(14.301534, -89.460998),
                    new LatLng(14.301302, -89.461385),
                    new LatLng(14.301096, -89.461685),
                    new LatLng(14.300818, -89.462029),
                    new LatLng(14.300450, -89.462366),
                    new LatLng(14.300239, -89.462517),
                    new LatLng(14.299872, -89.462721),
                    new LatLng(14.299563, -89.462865),
                    new LatLng(14.299099, -89.463007),
                    new LatLng(14.298935, -89.463035),
                    new LatLng(14.298365, -89.463113),
                    new LatLng(14.297687, -89.463172),
                    new LatLng(14.296540, -89.463343),
                    new LatLng(14.295396, -89.463452),
                    new LatLng(14.295012, -89.463477),
                    new LatLng(14.294062, -89.463409),
                    new LatLng(14.293579, -89.463319),
                    new LatLng(14.291662, -89.462846),
                    new LatLng(14.290463, -89.462538),
                    new LatLng(14.289850, -89.462413),
                    new LatLng(14.289458, -89.462336),
                    new LatLng(14.289227, -89.462311),
                    new LatLng(14.287477, -89.462135),
                    new LatLng(14.284601, -89.461836),
                    new LatLng(14.283476, -89.461717),
                    new LatLng(14.281600, -89.461535),
                    new LatLng(14.279966, -89.461318),
                    new LatLng(14.278523, -89.461166),
                    new LatLng(14.276031, -89.460910),
                    new LatLng(14.274591, -89.460752),
                    new LatLng(14.274048, -89.460697),
                    new LatLng(14.273651, -89.460642),
                    new LatLng(14.272876, -89.460626),
                    new LatLng(14.270396, -89.460604),
                    new LatLng(14.268595, -89.460583),
                    new LatLng(14.267949, -89.460584),
                    new LatLng(14.267506, -89.460555),
                    new LatLng(14.266980, -89.460503),
                    new LatLng(14.266988, -89.460484),
                    new LatLng(14.265082, -89.460130),
                    new LatLng(14.263575, -89.459839),
                    new LatLng(14.263370, -89.459788),
                    new LatLng(14.263120, -89.459793),
                    new LatLng(14.262822, -89.459806),
                    new LatLng(14.262385, -89.459833),
                    new LatLng(14.262180, -89.459867),
                    new LatLng(14.260168, -89.460369),
                    new LatLng(14.257323, -89.461081),
                    new LatLng(14.256965, -89.461147),
                    new LatLng(14.256498, -89.461119),
                    new LatLng(14.255830, -89.460935),
                    new LatLng(14.255370, -89.460766),
                    new LatLng(14.255025, -89.460695),
                    new LatLng(14.254825, -89.460689),
                    new LatLng(14.254514, -89.460736),
                    new LatLng(14.254292, -89.460859),
                    new LatLng(14.254143, -89.461030),
                    new LatLng(14.253592, -89.461607),
                    new LatLng(14.253281, -89.461832),
                    new LatLng(14.252990, -89.461940),
                    new LatLng(14.252300, -89.462021),
                    new LatLng(14.251924, -89.462148),
                    new LatLng(14.251647, -89.462354),
                    new LatLng(14.251464, -89.462604),
                    new LatLng(14.251321, -89.463084),
                    new LatLng(14.251279, -89.463651),
                    new LatLng(14.251230, -89.464063),
                    new LatLng(14.251119, -89.464399),
                    new LatLng(14.251092, -89.464491),
                    new LatLng(14.250934, -89.464767),
                    new LatLng(14.250757, -89.464987),
                    new LatLng(14.248032, -89.468315),
                    new LatLng(14.247592, -89.468798),
                    new LatLng(14.247214, -89.469203),
                    new LatLng(14.246806, -89.469517),
                    new LatLng(14.246289, -89.469875),
                    new LatLng(14.242985, -89.471967),
                    new LatLng(14.242082, -89.472546),
                    new LatLng(14.241935, -89.472629),
                    new LatLng(14.241691, -89.472704),
                    new LatLng(14.241502, -89.472736),
                    new LatLng(14.241283, -89.472706),
                    new LatLng(14.240990, -89.472663),
                    new LatLng(14.240636, -89.472493),
                    new LatLng(14.238877, -89.471540),
                    new LatLng(14.238583, -89.471325),
                    new LatLng(14.238529, -89.471288),
                    new LatLng(14.236342, -89.468989),
                    new LatLng(14.236013, -89.468712),
                    new LatLng(14.234511, -89.467810),
                    new LatLng(14.234044, -89.467577),
                    new LatLng(14.231969, -89.466732),
                    new LatLng(14.229982, -89.465919),
                    new LatLng(14.229639, -89.465779),
                    new LatLng(14.229369, -89.465713),
                    new LatLng(14.229233, -89.465663),
                    new LatLng(14.229049, -89.465654),
                    new LatLng(14.228919, -89.465624),
                    new LatLng(14.228773, -89.465665),
                    new LatLng(14.228565, -89.465690),
                    new LatLng(14.227153, -89.466275),
                    new LatLng(14.226872, -89.466442),
                    new LatLng(14.226700, -89.466658),
                    new LatLng(14.226205, -89.467810),
                    new LatLng(14.226239, -89.468231),
                    new LatLng(14.226338, -89.468500),
                    new LatLng(14.226588, -89.468896),
                    new LatLng(14.227179, -89.469792),
                    new LatLng(14.227619, -89.470490),
                    new LatLng(14.227933, -89.471030),
                    new LatLng(14.228326, -89.471748),
                    new LatLng(14.228502, -89.472190),
                    new LatLng(14.228584, -89.472526),
                    new LatLng(14.228699, -89.473073),
                    new LatLng(14.228760, -89.473737),
                    new LatLng(14.228733, -89.474136),
                    new LatLng(14.228437, -89.477940),
                    new LatLng(14.228335, -89.478361),
                    new LatLng(14.228240, -89.478658),
                    new LatLng(14.226869, -89.481816),
                    new LatLng(14.226692, -89.482054),
                    new LatLng(14.226530, -89.482221),
                    new LatLng(14.226354, -89.482332),
                    new LatLng(14.226091, -89.482449),
                    new LatLng(14.223865, -89.482797),
                    new LatLng(14.223556, -89.482903),
                    new LatLng(14.223371, -89.483023),
                    new LatLng(14.223072, -89.483350),
                    new LatLng(14.223017, -89.483513),
                    new LatLng(14.222542, -89.485519),
                    new LatLng(14.222425, -89.485796),
                    new LatLng(14.222163, -89.486109),
                    new LatLng(14.221171, -89.486999),
                    new LatLng(14.220908, -89.487435),
                    new LatLng(14.220790, -89.487628),
                    new LatLng(14.220783, -89.487999),
                    new LatLng(14.220997, -89.489433),
                    new LatLng(14.221182, -89.490722),
                    new LatLng(14.221668, -89.493829),
                    new LatLng(14.221609, -89.494340),
                    new LatLng(14.221408, -89.494762),
                    new LatLng(14.221145, -89.495034),
                    new LatLng(14.220901, -89.495221),
                    new LatLng(14.218249, -89.496485),
                    new LatLng(14.217302, -89.496927),
                    new LatLng(14.216915, -89.497060),
                    new LatLng(14.216533, -89.497117),
                    new LatLng(14.213231, -89.497065),
                    new LatLng(14.212739, -89.497036),
                    new LatLng(14.211145, -89.496892),
                    new LatLng(14.210878, -89.496795),
                    new LatLng(14.207436, -89.495637),
                    new LatLng(14.206215, -89.495241),
                    new LatLng(14.205352, -89.494965),
                    new LatLng(14.205010, -89.494917),
                    new LatLng(14.203624, -89.494747),
                    new LatLng(14.201605, -89.494583),
                    new LatLng(14.199019, -89.494359),
                    new LatLng(14.197704, -89.494260),
                    new LatLng(14.196516, -89.494205),
                    new LatLng(14.196114, -89.494373),
                    new LatLng(14.193232, -89.496443),
                    new LatLng(14.192748, -89.496741),
                    new LatLng(14.192270, -89.496957),
                    new LatLng(14.191964, -89.497037),
                    new LatLng(14.190078, -89.497114),
                    new LatLng(14.189617, -89.497002),
                    new LatLng(14.189273, -89.496792),
                    new LatLng(14.189053, -89.496518),
                    new LatLng(14.189001, -89.496428),
                    new LatLng(14.188699, -89.495723),
                    new LatLng(14.188372, -89.495132),
                    new LatLng(14.188002, -89.494742),
                    new LatLng(14.187626, -89.494549),
                    new LatLng(14.187337, -89.494458),
                    new LatLng(14.187133, -89.494418),
                    new LatLng(14.184381, -89.493887),
                    new LatLng(14.183967, -89.493835),
                    new LatLng(14.183803, -89.493869),
                    new LatLng(14.182819, -89.493971),
                    new LatLng(14.177982, -89.494999),
                    new LatLng(14.176722, -89.495259),
                    new LatLng(14.176122, -89.495542),
                    new LatLng(14.175907, -89.495693),
                    new LatLng(14.172535, -89.498417),
                    new LatLng(14.170059, -89.500391),
                    new LatLng(14.169674, -89.500702),
                    new LatLng(14.169133, -89.501021),
                    new LatLng(14.168545, -89.501160),
                    new LatLng(14.168221, -89.501178),
                    new LatLng(14.165246, -89.501218),
                    new LatLng(14.164590, -89.500960),
                    new LatLng(14.163538, -89.500162),
                    new LatLng(14.163154, -89.499876),
                    new LatLng(14.162679, -89.499667),
                    new LatLng(14.162484, -89.499621),
                    new LatLng(14.162059, -89.499648),
                    new LatLng(14.161584, -89.499776),
                    new LatLng(14.161227, -89.499988),
                    new LatLng(14.160921, -89.500329),
                    new LatLng(14.155930, -89.507585),
                    new LatLng(14.155711, -89.508020),
                    new LatLng(14.155561, -89.509113),
                    new LatLng(14.155676, -89.509879),
                    new LatLng(14.156068, -89.511170),
                    new LatLng(14.156087, -89.511633),
                    new LatLng(14.155953, -89.512081),
                    new LatLng(14.155746, -89.512370),
                    new LatLng(14.155492, -89.512630),
                    new LatLng(14.153263, -89.513942),
                    new LatLng(14.152709, -89.514069),
                    new LatLng(14.150171, -89.514360),
                    new LatLng(14.148326, -89.514802),
                    new LatLng(14.148007, -89.514807),
                    new LatLng(14.147821, -89.514761),
                    new LatLng(14.147661, -89.514691),
                    new LatLng(14.146875, -89.514116),
                    new LatLng(14.146559, -89.514024),
                    new LatLng(14.146238, -89.514017),
                    new LatLng(14.145946, -89.514056),
                    new LatLng(14.144515, -89.514319),
                    new LatLng(14.144137, -89.514314),
                    new LatLng(14.143758, -89.514211),
                    new LatLng(14.142508, -89.513518),
                    new LatLng(14.142271, -89.513438),
                    new LatLng(14.141962, -89.513386),
                    new LatLng(14.140472, -89.513709),
                    new LatLng(14.140182, -89.513692),
                    new LatLng(14.138530, -89.512911),
                    new LatLng(14.138219, -89.512518),
                    new LatLng(14.138107, -89.511999),
                    new LatLng(14.138156, -89.511508),
                    new LatLng(14.138241, -89.510118),
                    new LatLng(14.138144, -89.509676),
                    new LatLng(14.137850, -89.509040),
                    new LatLng(14.137565, -89.508644),
                    new LatLng(14.137105, -89.508284),
                    new LatLng(14.136367, -89.507906),
                    new LatLng(14.134965, -89.507507),
                    new LatLng(14.134030, -89.507082),
                    new LatLng(14.133884, -89.506952),
                    new LatLng(14.133450, -89.506380),
                    new LatLng(14.130254, -89.502016),
                    new LatLng(14.129744, -89.501406),
                    new LatLng(14.129188, -89.500930),
                    new LatLng(14.128366, -89.500396),
                    new LatLng(14.127777, -89.500127),
                    new LatLng(14.127310, -89.500001),
                    new LatLng(14.125636, -89.499817),
                    new LatLng(14.121210, -89.499338),
                    new LatLng(14.118589, -89.499122),
                    new LatLng(14.117079, -89.499025),
                    new LatLng(14.116561, -89.499019),
                    new LatLng(14.115970, -89.499092),
                    new LatLng(14.115080, -89.499350),
                    new LatLng(14.114112, -89.499377),
                    new LatLng(14.111642, -89.498855),
                    new LatLng(14.110974, -89.498841),
                    new LatLng(14.109873, -89.499021),
                    new LatLng(14.105753, -89.499755),
                    new LatLng(14.102328, -89.500087),
                    new LatLng(14.102328, -89.500087),
                    new LatLng(14.094268, -89.499529),
                    new LatLng(14.093555, -89.499543),
                    new LatLng(14.091262, -89.499995),
                    new LatLng(14.090844, -89.500016),
                    new LatLng(14.086129, -89.500158),
                    new LatLng(14.081979, -89.499640),
                    new LatLng(14.081414, -89.499701),
                    new LatLng(14.080622, -89.499971),
                    new LatLng(14.075332, -89.502657),
                    new LatLng(14.074870, -89.502760),
                    new LatLng(14.067836, -89.502747),
                    new LatLng(14.067286, -89.502839),
                    new LatLng(14.066945, -89.503057),
                    new LatLng(14.065156, -89.504566),
                    new LatLng(14.064822, -89.505000),
                    new LatLng(14.062419, -89.509134),
                    new LatLng(14.062081, -89.509425),
                    new LatLng(14.061517, -89.509516),
                    new LatLng(14.061163, -89.509442),
                    new LatLng(14.059557, -89.508701),
                    new LatLng(14.059002, -89.508656),
                    new LatLng(14.058504, -89.508856),
                    new LatLng(14.058215, -89.509172),
                    new LatLng(14.057565, -89.510218),
                    new LatLng(14.057175, -89.510436),
                    new LatLng(14.056947, -89.510449),
                    new LatLng(14.056430, -89.510434),
                    new LatLng(14.055770, -89.510478),
                    new LatLng(14.055480, -89.510751),
                    new LatLng(14.055293, -89.511010),
                    new LatLng(14.055241, -89.511473),
                    new LatLng(14.055386, -89.512059),
                    new LatLng(14.055744, -89.513309),
                    new LatLng(14.055783, -89.513648),
                    new LatLng(14.055663, -89.514153),
                    new LatLng(14.055275, -89.514635),
                    new LatLng(14.054738, -89.515103),
                    new LatLng(14.054469, -89.515296),
                    new LatLng(14.052877, -89.516060),
                    new LatLng(14.052539, -89.516118),
                    new LatLng(14.051698, -89.516065),
                    new LatLng(14.051367, -89.516108),
                    new LatLng(14.051031, -89.516214),
                    new LatLng(14.047589, -89.517718),
                    new LatLng(14.046237, -89.518283),
                    new LatLng(14.035933, -89.522639),
                    new LatLng(14.034897, -89.523119),
                    new LatLng(14.034085, -89.523637),
                    new LatLng(14.031832, -89.525532),
                    new LatLng(14.028390, -89.528477),
                    new LatLng(14.026851, -89.529768),
                    new LatLng(14.026153, -89.530688),
                    new LatLng(14.024562, -89.533009),
                    new LatLng(14.023486, -89.535166),
                    new LatLng(14.023034, -89.535563),
                    new LatLng(14.022084, -89.535834),
                    new LatLng(14.019890, -89.536447),
                    new LatLng(14.018093, -89.536924),
                    new LatLng(14.017467, -89.537184),
                    new LatLng(14.016903, -89.537551),
                    new LatLng(14.012599, -89.542301),
                    new LatLng(14.011720, -89.543225),
                    new LatLng(14.011596, -89.543332),
                    new LatLng(14.011430, -89.543337),
                    new LatLng(14.011326, -89.543267),
                    new LatLng(14.011274, -89.543118),
                    new LatLng(14.010797, -89.542958),
                    new LatLng(14.007300, -89.542235),
                    new LatLng(14.006827, -89.542166),
                    new LatLng(14.005575, -89.542050),
                    new LatLng(14.001305, -89.541714),
                    new LatLng(13.997307, -89.541441),
                    new LatLng(13.995771, -89.541463),
                    new LatLng(13.992958, -89.541553),
                    new LatLng(13.992132, -89.541649),
                    new LatLng(13.991610, -89.541765),
                    new LatLng(13.990961, -89.541974),
                    new LatLng(13.990549, -89.542199),
                    new LatLng(13.989868, -89.542644),
                    new LatLng(13.986571, -89.545519),
                    new LatLng(13.985978, -89.546034),
                    new LatLng(13.985794, -89.546271),
                    new LatLng(13.985464, -89.546863),
                    new LatLng(13.985321, -89.547162),
                    new LatLng(13.985395, -89.547318),
                    new LatLng(13.985367, -89.547507),
                    new LatLng(13.985220, -89.547597),
                    new LatLng(13.985187, -89.547808),
                    new LatLng(13.985159, -89.548016),    //PUNTO UNICAES
                    new LatLng(13.984983, -89.550796),
                    new LatLng(13.984884, -89.551304),
                    new LatLng(13.984718, -89.551708),
                    new LatLng(13.984323, -89.552214),
                    new LatLng(13.983083, -89.553538),
                    new LatLng(13.981971, -89.554206),
                    new LatLng(13.980649, -89.554780),
                    new LatLng(13.979997, -89.555095),
                    new LatLng(13.979561, -89.555410),
                    new LatLng(13.979246, -89.555676),
                    new LatLng(13.978996, -89.556003),
                    new LatLng(13.978639, -89.556474),
                    new LatLng(13.978015, -89.557734),
                    new LatLng(13.976892, -89.559945),
                    new LatLng(13.976867, -89.560212),
                    new LatLng(13.976912, -89.560353),
                    new LatLng(13.977027, -89.560501),
                    new LatLng(13.977056, -89.560699),
                    new LatLng(13.977001, -89.560848),
                    new LatLng(13.976750, -89.561047),
                    new LatLng(13.976265, -89.561315),
                    new LatLng(13.974472, -89.562438),
                    new LatLng(13.974601, -89.562648),
                    new LatLng(13.975181, -89.564056),
                    new LatLng(13.975228, -89.564298),
                    new LatLng(13.975235, -89.564600),
                    new LatLng(13.975433, -89.565223),
                    new LatLng(13.975565, -89.565358),
                    new LatLng(13.976470, -89.565987),
                    new LatLng(13.977266, -89.566831),
                    new LatLng(13.976396, -89.567897),
                    new LatLng(13.975936, -89.568103),
                    new LatLng(13.975276, -89.568507),
                    puntoLlegada
                    )
                            .width(8)
                            .color(Color.rgb(216, 40, 34))
            );

        }
}
