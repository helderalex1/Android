package amsi.dei.estg.ipleiria.RightPrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void facebookOnClick(View view) {
        try {
            goToUrl("facebook://feed");
        }catch(Exception e){
            goToUrl("https://www.facebook.com/");
        }
    }

    public void instagramOnClick(View view) {
        try {
            goToUrl("instagram://feed")   ;
        }catch(Exception e){
            goToUrl("https://www.instagram.com/");
        }

    }

    public void linkedinOnClick(View view) {
        try {
            goToUrl("linkedin://feed")   ;
        }catch(Exception e){
            goToUrl("https://www.linkedin.com/");
        }

    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}