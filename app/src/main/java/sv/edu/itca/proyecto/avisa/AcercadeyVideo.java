package sv.edu.itca.proyecto.avisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class AcercadeyVideo extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{

    public static final String DEVELOPER_KEY = ConfigYoutube.DEVELOPER_KEY;
    private static String VIDEO_ID = ConfigYoutube.YOUTUBE_VIDEO_CODE;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerFragment myYouTubePlayerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercadey_video);

        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);

        myYouTubePlayerFragment.initialize(ConfigYoutube.DEVELOPER_KEY, AcercadeyVideo.this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
// this.player = player;
            player.loadVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "Error al Cargar", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
// Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayer.Provider) findViewById(R.id.youtubeplayerfragment);
    }
    public class ConfigYoutube {
        // Google Console APIs developer clave
        public static final String
                DEVELOPER_KEY = "AIzaSyAab5Qf-mrerokjS-Gqabi-al3Tr_RT2ic";
        // YouTube video id
        public static final String YOUTUBE_VIDEO_CODE = "gq_pzhPHsig";
    }
}
