package amsi.dei.estg.ipleiria.RightPrice;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;


//funcao que coloca as atividades em FullScreen
public abstract class FullScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        noneColor();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        noneColor();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        noneColor();
        super.onWindowFocusChanged(hasFocus);
    }

    public void noneColor() {
        Window w = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(
                   View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                   |View.SYSTEM_UI_FLAG_FULLSCREEN
                   |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                   |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                   );

    }
}
