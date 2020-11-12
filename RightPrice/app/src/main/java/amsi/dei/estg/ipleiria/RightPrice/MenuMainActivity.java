package amsi.dei.estg.ipleiria.RightPrice;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import amsi.dei.estg.ipleiria.RightPrice.Admin.Aceitar_Cliente_admin_Fragment;
import amsi.dei.estg.ipleiria.RightPrice.Admin.Lista_Cliente_admin_Fragment;
import amsi.dei.estg.ipleiria.RightPrice.Admin.MainActivity_admin;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Lista_Clientes_Fornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.MainActivity_fornecedor;
import amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Produto_Fornecedor_Fragment;
import amsi.dei.estg.ipleiria.RightPrice.Fragmentos.PerfilUtilizador;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Adicionar_Fornecedor_Instalador;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Clientes_Instalador;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Conhecer_Fornecedor_Fragment;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.MainFragment_instalador;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Obras_Fragment;


public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private DrawerLayout drawerInstalador, drawerFornecedor, drawerAdministrador;
    private FragmentManager fragmentManager;
    private static final String numConta = "numConta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBarDrawerToggle toggle = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_instalador);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerInstalador = findViewById(R.id.drawer_layout_instalador);
        drawerFornecedor = findViewById(R.id.drawer_layout_fornecedor);
        drawerAdministrador = findViewById(R.id.drawer_layout_administrador);

        if(numConta == "1") {
            toggle = new ActionBarDrawerToggle(this, drawerInstalador, toolbar, R.string.ndOpen, R.string.ndClose);
            toggle.syncState();
            drawerInstalador.addDrawerListener(toggle);
        }else if(numConta == "2") {
            toggle = new ActionBarDrawerToggle(this, drawerFornecedor, toolbar, R.string.ndOpen, R.string.ndClose);
            toggle.syncState();
            drawerFornecedor.addDrawerListener(toggle);
        }else if(numConta == "3") {
            toggle = new ActionBarDrawerToggle(this, drawerAdministrador, toolbar, R.string.ndOpen, R.string.ndClose);
            toggle.syncState();
            drawerFornecedor.addDrawerListener(toggle);
        }


        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem .getItemId()) {
            case R.id.nav_instalador_estatistica:
                fragment = new MainFragment_instalador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_instalador_cliente:
                fragment = new Clientes_Instalador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_instalador_conta:
                fragment = new PerfilUtilizador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_instalador_adicionar_fornecedor:
                fragment = new Adicionar_Fornecedor_Instalador();
                setTitle("dasda");
                break;
            case R.id.nav_instalador_fornecedor:
                fragment = new Conhecer_Fornecedor_Fragment();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_instalador_obra:
                fragment = new Obras_Fragment();
                setTitle(menuItem.getTitle());
                break;
          /*  case R.id.nav_instalador_sair:
                fragment = new ;
                setTitle(menuItem.getTitle());
                break;*/
            case R.id.nav_fornecedor_conta:
                fragment = new PerfilUtilizador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_fornecedor_estatistica:
                fragment = new MainActivity_fornecedor();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_fornecedor_instalador:
                fragment = new Lista_Clientes_Fornecedor();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_fornecedor_produto:
                fragment = new Produto_Fornecedor_Fragment();
                setTitle(menuItem.getTitle());
                break;
            /*case R.id.nav_fornecedor_sair:
                fragment = new ;
                setTitle(menuItem.getTitle());
                break;*/
            case R.id.nav_administrador_cliente_pendente:
                fragment = new Aceitar_Cliente_admin_Fragment();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_administrador_clientes:
                fragment = new Lista_Cliente_admin_Fragment();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_administrador_conta:
                fragment = new PerfilUtilizador();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_administrador_estatistica:
                fragment = new MainActivity_admin();
                setTitle(menuItem.getTitle());
                break;
            /*case R.id.nav_administrador_sair:
                fragment = new ;
                setTitle(menuItem.getTitle());
                break;*/
        }
        if(fragment != null){
            fragmentManager.beginTransaction().replace(R.id.framelayout1,fragment).commit();
        }
        if(numConta=="1") {
            drawerInstalador.closeDrawer(GravityCompat.START);
        }else if(numConta=="2") {
            drawerFornecedor.closeDrawer(GravityCompat.START);
        }else if(numConta=="3") {
            drawerAdministrador.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}