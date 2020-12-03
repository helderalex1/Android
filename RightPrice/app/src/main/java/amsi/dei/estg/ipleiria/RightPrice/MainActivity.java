package amsi.dei.estg.ipleiria.RightPrice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades.TabLoginRegistar;

public class MainActivity extends FullScreen{
    ImageButton buton_login_registar;


    private TabLoginRegistar tabLoginRegistar = new TabLoginRegistar(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton_login_registar = findViewById(R.id.btn_Login_Registo);


        buton_login_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLoginRegistar.show(getSupportFragmentManager(),"Login/Registo");

            }


        });
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



    public void Fechar_popup(View view) {
        tabLoginRegistar.dismiss();
        noneColor();
    }


    public void onclick_login(View view) {
       tabLoginRegistar.dismiss();
        Intent intent = new Intent( this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.numConta,"3");
        startActivity(intent);
    }
}