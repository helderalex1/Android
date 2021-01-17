package amsi.dei.estg.ipleiria.RightPrice.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.MainAdminFragmentListener;


//funcao main do instalador
public class MainFragmentAdmin extends Fragment implements MainAdminFragmentListener {
    private TextView textViewlink;
    private TextView text_quantidade_categorias;
    private TextView text_quantidades_pendentes;
    private TextView text_quantidade_utilizadores;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_main_admin, container, false);
        textViewlink=view.findViewById(R.id.textViewtextolink);
        text_quantidade_categorias= view.findViewById(R.id.textViewTotalCategorias);
        text_quantidades_pendentes= view.findViewById(R.id.textViewTotalUtilizadoresPendentes);
        text_quantidade_utilizadores= view.findViewById(R.id.textViewTotalUtilizadores);

        SingletonGerirOrcamentos.getInstance(getContext()).setmainAdminFragmentListener(this);
        SingletonGerirOrcamentos.getInstance(getContext()).getAllCategoriasAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllUtilizadoresAdminAPI(getContext());
        SingletonGerirOrcamentos.getInstance(getContext()).getAllFuncoesAdminAPI(getContext());


        //funcao de clique da text_view
        textViewlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    goToUrl("https://dontkillmyapp.com/");
                }catch (Exception e){
                    Snackbar.make(getView(),"Erro a abrir o link. Tente mais tarde",Snackbar.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }





    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    //carrega os dados da estatistica
    @Override
    public void onRefreshdadocategoriaAdminMain(int valor) {
        text_quantidade_categorias.setText(String.valueOf(valor));
    }

    @Override
    public void onRefreshdadoutilizadorAdminMain(int valor) {
        text_quantidade_utilizadores.setText(String.valueOf(valor));

    }

    @Override
    public void onRefreshdadopendentesAdminMain(int valor) {
        text_quantidades_pendentes.setText(String.valueOf(valor));
    }
}

