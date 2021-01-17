package amsi.dei.estg.ipleiria.RightPrice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

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
import amsi.dei.estg.ipleiria.RightPrice.Instalador.Listas.ListaMeusFornecedores;
import amsi.dei.estg.ipleiria.RightPrice.Instalador.MainFragmentInstalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Utils.ServiceRunBackground;
import amsi.dei.estg.ipleiria.RightPrice.listener.AtualizaCabecalhoListener;


//Atividade que carrega os cabeçalhos, fragmentos adequados a cada tipo de login
public class MenuMainActivity extends FullScreen implements NavigationView.OnNavigationItemSelectedListener, AtualizaCabecalhoListener {

    //variaveis para a sharedpreference

    //nomes dos ficheiros
    public static final String PREF_USER = "USER_SAVE";

    //campos dentro do ficheiro User_Save;
    public static final String ID = "ID";
    public static final String NOME = "NOME";
    public static final String NOME_EMPRESA = "NOME_EMPRESA";
    public static final String USERNAME = "USERNAME";
    public static final String TELEMOVEL = "TELEMOVEL";
    public static final String EMAIL= "EMAIL";
    public static final String IMAGEM= "IMAGEM";
    public static final String CATEFORIA = "CATEGORIA";
    public static final String TOKEN = "TOKEN";
    public static final String FUNCAO = "FUNCAO";
    public static final String TipoUtilizador ="TIPOUTILIZADOR";
    public static final String PASSWORD ="PASSWORD";


    private SharedPreferences sharedPreferences;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private Intent intentservice;

    private String tipo_conta=null;
    private MqttHelper mqttHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBarDrawerToggle toggle = null;
        super.onCreate(savedInstanceState);

        //chamar o singleton dos utilizadores
        SingletonGerirOrcamentos.getInstance(getApplicationContext()).setAtualizaCabecalhoListener(this);
        tipo_conta = getIntent().getStringExtra(TipoUtilizador);
        drawer=null;
        switch (tipo_conta.toLowerCase()){
            case "instalador" :
                setContentView(R.layout.activity_menu_instalador);
                drawer = findViewById(R.id.drawer_layout_instalador);
                break;
            case "fornecedor":
                setContentView(R.layout.activity_menu_fornecedor);
                drawer = findViewById(R.id.drawer_layout_fornecedor);
                break;
            case "admin":
                setContentView(R.layout.activity_menu_administrador);
                drawer= findViewById(R.id.drawer_layout_administrador);
                intentservice = new Intent(getApplicationContext(),ServiceRunBackground.class);

                if(SingletonGerirOrcamentos.getInstance(getApplicationContext()).isConnectionInternet(getApplicationContext())==true)
                {
                    startService(intentservice);
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllCategoriasAPI(getBaseContext());
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllUtilizadoresAdminAPI(getBaseContext());
                }

                break;
            default:
                Toast.makeText(this, R.string.erro_da_aplicacao, Toast.LENGTH_SHORT).show();
                onDestroy();
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
        carregarFragmentoInicial(tipo_conta);
        carregarcabecalho();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //carrega dados para o cabeçalho do menu
    //nome, empresa e funcao

    private   void carregarcabecalho() {
        String nome,empresa,funcao;
        sharedPreferences= getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        nome=sharedPreferences.getString(NOME,getString(R.string.sem_nome));
        funcao=sharedPreferences.getString(FUNCAO,getString(R.string.sem_funcao_atribuida));
        empresa= sharedPreferences.getString(NOME_EMPRESA,getString(R.string.sem_empresa_Atribuida));


        View hView = navigationView.getHeaderView(0);
        TextView nav_user = hView.findViewById(R.id.tVNome_header);
        TextView nav_nome_empresa= hView.findViewById(R.id.tVEmpresa_header);
        TextView nav_funcao=hView.findViewById(R.id.TexVi_funcao_header);
        if(nome.equals("null")||nome.equals(" ")||nome.equals("")){
            nav_user.setText(R.string.sem_nome);
        }else{
            nav_user.setText(getString(R.string.nome_com_dois_pontos)+" "+SingletonGerirOrcamentos.getInstance(getApplicationContext()).toTitledCase(nome));
        }

        if(empresa.equals("null")||empresa.equals(" ")||empresa.equals("")){
            nav_nome_empresa.setText(R.string.sem_empresa_Atribuida);
        }else{
            nav_nome_empresa.setText(getString(R.string.empresa)+" "+SingletonGerirOrcamentos.getInstance(getApplicationContext()).toTitledCase(empresa));
        }

        if (funcao.equals("null")||funcao.equals("")||funcao.equals(" ")){
           nav_funcao.setText(R.string.sem_funcao_atribuida);
        }else{
            nav_funcao.setText(getString(R.string.funcao)+": "+SingletonGerirOrcamentos.getInstance(getApplicationContext()).toTitledCase(funcao));
        }
    }


    //carrega a pagina inicial
    private void carregarFragmentoInicial(String tipo_utilizador) {
        Fragment fragment;
        switch (tipo_conta.toLowerCase()) {
            case "instalador":
                fragment = new MainFragmentInstalador();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
                setTitle(getString(R.string.minha_area));
                break;
            case "fornecedor":
                fragment = new MainFragmentFornecedor();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
                setTitle(getString(R.string.minha_area));
                break;
            case "admin":
                fragment = new MainFragmentAdmin();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
                setTitle(getString(R.string.minha_area));
                break;
            default:
                setTitle("Erro a carregar Tela inicial");
                break;
        }
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
            case R.id.nav_instalador_fornecedor:
                fragment = new ListaMeusFornecedores();
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
                sharedPreferences= getBaseContext().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
               switch (tipo_conta) {
                   case "admin":
                        stopService(intentservice);
                       break;
               }
                break;
        }
        if(fragment != null){
            fragmentManager.beginTransaction().replace(R.id.contentFragment,fragment).commit();
        }
            drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    @Override
    public void onAtualizaCabecalho() {
        carregarcabecalho();
    }
}