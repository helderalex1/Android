package amsi.dei.estg.ipleiria.RightPrice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Admin.MqttHelper;
import amsi.dei.estg.ipleiria.RightPrice.Admin.Listas.ListaAceitarUtilizadoresAdmin;
import amsi.dei.estg.ipleiria.RightPrice.Admin.Listas.ListaUtilizadoresAdmin;
import amsi.dei.estg.ipleiria.RightPrice.Admin.MainFragmentAdmin;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Listas.ListaClientesDoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Listas.ListaProdutoFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.MainFragmentFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades.PerfilUtilizador;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Listas.ListaClientesInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Listas.ListaConhecerFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.MainFragmentInstalador;


public class MenuMainActivity extends FullScreen implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    public static final String numConta ="";
    private int numeconta=0;
    private MqttHelper mqttHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBarDrawerToggle toggle = null;
        super.onCreate(savedInstanceState);

        numeconta= Integer.parseInt(getIntent().getStringExtra(numConta));
        drawer=null;
        switch (numeconta){
            case 1:
                setContentView(R.layout.activity_menu_instalador);
                drawer = findViewById(R.id.drawer_layout_instalador);
                break;
            case 2:
                setContentView(R.layout.activity_menu_fornecedor);
                drawer = findViewById(R.id.drawer_layout_fornecedor);
                break;
            case 3:
                setContentView(R.layout.activity_menu_administrador);
                drawer= findViewById(R.id.drawer_layout_administrador);

                startMqtt();

                break;
        }

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);

            toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.ndOpen, R.string.ndClose);
            toggle.syncState();
            drawer.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();

    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem .getItemId()) {
            //Funçoes que abrem as diversas funções do Instalador
            case R.id.nav_instalador_estatistica:
                fragment = new MainFragmentInstalador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_instalador_cliente:
                fragment = new ListaClientesInstalador();
                setTitle(menuItem.getTitle());
                break;

            case R.id.nav_instalador_adicionar_fornecedor:
                fragment = new ListaConhecerFornecedor();
                setTitle(menuItem.getTitle());
                break;


            //Funções que abrem as diversas opções de menu do Fornecedor
            case R.id.nav_fornecedor_estatistica:
                fragment = new MainFragmentFornecedor();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_fornecedor_produto:
                fragment = new ListaProdutoFornecedor();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_fornecedor_instalador:
                fragment = new ListaClientesDoFornecedor();
                setTitle(menuItem.getTitle());
                break;


          //Funções que abrem as diversas opçoes de menu do Administrador
            case R.id.nav_administrador_estatistica:
                fragment = new MainFragmentAdmin();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_administrador_clientes:
                fragment = new ListaUtilizadoresAdmin();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_administrador_cliente_pendente:
                fragment = new ListaAceitarUtilizadoresAdmin();
                setTitle(menuItem.getTitle());
                break;
           //Funçoes comuns em todos os menus
            case R.id.nav_conta:
                fragment = new PerfilUtilizador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_sair:
                finish();
                break;
        }
        if(fragment != null){
            fragmentManager.beginTransaction().replace(R.id.framelayout2,fragment).commit();
        }
            drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", mqttMessage.toString());
                Toast.makeText(MenuMainActivity.this, mqttMessage.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }


}