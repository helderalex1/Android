package amsi.dei.estg.ipleiria.RightPrice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades.TabLoginRegistar;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.listener.LoginListener;


//Atividade que é apresentada no inicio quando não existe login
public class MainActivity extends FullScreen implements LoginListener {

    //declaração de variaveis de dados
    private String username = null;
    private String password = null;
    private String token = null;
    private String IP_novo;
    private ConstraintLayout MAin_constraint;

    //declaraçao de variaveis de objetos
    private ImageButton buton_login_registar;
    private EditText editTextIP;


    //declaraçao do PopUp
    private TabLoginRegistar tabLoginRegistar = new TabLoginRegistar(this);


    // funçaoi que excuta quando atividade é iniciada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton_login_registar = findViewById(R.id.btn_Login_Registo);
        editTextIP = findViewById(R.id.editTextipComputador);
        MAin_constraint=findViewById(R.id.MAin_constraint);
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setLoginListener(this);

        SharedPreferences sharedPreferences= getSharedPreferences(SingletonGerirOrcamentos.PREF_IP, Context.MODE_PRIVATE);
        IP_novo=sharedPreferences.getString(SingletonGerirOrcamentos.IP,"http://192.168.1.12");
        editTextIP.setText(IP_novo);
        SingletonGerirOrcamentos.mURLAPIOrcamentos = IP_novo;


        buton_login_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLoginRegistar.show(getSupportFragmentManager(), "Login/Registo");

            }
        });

        MAin_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        SharedPreferences sharedPreferenceuser = getSharedPreferences(MenuMainActivity.PREF_USER, MODE_PRIVATE);
        username = sharedPreferenceuser.getString(MenuMainActivity.USERNAME, null);
        password = sharedPreferenceuser.getString(MenuMainActivity.PASSWORD, null);
        String tipo_utilizador = sharedPreferenceuser.getString(MenuMainActivity.FUNCAO, null);
        try {
            if (tipo_utilizador != null && username != null && password != null) {
                Boolean ConectadoInternet = SingletonGerirOrcamentos.isConnectionInternet(getApplicationContext());
                if (ConectadoInternet == true) {
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).loginAPI(username, password,1, getApplicationContext());
                } else {
                    Intent intent = new Intent(this, MenuMainActivity.class);
                    intent.putExtra(MenuMainActivity.TipoUtilizador, tipo_utilizador);
                    startActivity(intent);
                    Toast.makeText(this, "Erro a fazer login com o servidor. Esta aplicação só ira servir de consulta", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro a fazer o login. Contacte a administração", Toast.LENGTH_LONG).show();
        }
    }





    //funcao de fechar o popup de login/Registar
    public void Fechar_popup(View view) {
        tabLoginRegistar.dismiss();
        noneColor();
    }

    @Override
    public void onValidateLogin(String token, String username, int TipoValidate) {
       String tipo_utilizador;
        if (token!=null){
            SharedPreferences sharedPreferences= getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
            tipo_utilizador= sharedPreferences.getString(MenuMainActivity.FUNCAO,null);
            if(TipoValidate==0){
                tabLoginRegistar.dismiss();
            }
            Intent intent = new Intent( this, MenuMainActivity.class);
            try {
                intent.putExtra(MenuMainActivity.TipoUtilizador,tipo_utilizador);
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, "Erro a fazer o login. Contacte a administração", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onErroLogin(String erro) {
        Toast.makeText(this, erro, Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferenceuser = getSharedPreferences(MenuMainActivity.PREF_USER, MODE_PRIVATE);
        String tipo_utilizador = sharedPreferenceuser.getString(MenuMainActivity.FUNCAO, null);

        if (tipo_utilizador != null){
            Intent intent = new Intent(this, MenuMainActivity.class);
            intent.putExtra(MenuMainActivity.TipoUtilizador, tipo_utilizador);
            startActivity(intent);
            Toast.makeText(this, "Erro a fazer login com o servidor. Esta aplicação só ira servir de consulta", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErroRegistar(String erro) {
        Toast.makeText(this, erro, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidateRegistar(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
        tabLoginRegistar.dismiss();
    }


    //funços de imagens das redes sociais

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

    //funçao para abrir a rede social pretendida
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @SuppressLint("ResourceAsColor")
    public void AlterarIP(View view) {
        IP_novo=editTextIP.getText().toString();
        SingletonGerirOrcamentos.mURLAPIOrcamentos = IP_novo;

        if (view !=null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
        Toast.makeText(this, "Novo IP guardado", Toast.LENGTH_SHORT).show();
      SharedPreferences  sharedPreferences= getSharedPreferences(SingletonGerirOrcamentos.PREF_IP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(SingletonGerirOrcamentos.IP,IP_novo);
        editor.apply();
    }


}