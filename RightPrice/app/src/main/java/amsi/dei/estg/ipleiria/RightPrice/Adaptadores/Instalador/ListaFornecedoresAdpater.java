package amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Categoria;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

//Adaptador para carregar a lista de Fornecedores do Instalador.
//Adaptador utilizado pelo Instalador


public class ListaFornecedoresAdpater extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Utilizador> utilizadores;

    public ListaFornecedoresAdpater(Context context, ArrayList<Utilizador> utilizadores){
        this.context = context;
        this.utilizadores = utilizadores;
    }
    @Override
    public int getCount() {
        return utilizadores.size();
    }

    @Override
    public Object getItem(int position) {
        return utilizadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lista_fornecedores_do_instalador, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(utilizadores.get(position));
        return convertView;
    }

   private class ViewHolderLista{
        private final TextView nome, empresa, email, categoria;
        private final ImageView imagemFornecedor;
        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tVNomeFornecedorInstalador);
            empresa = convertView.findViewById(R.id.tvEmpresaFornecedorInstalador);
            email = convertView.findViewById(R.id.tvEmailFornecedorInstalador);
            categoria = convertView.findViewById(R.id.tvCategoriaFornecedorInstalador);
            imagemFornecedor = convertView.findViewById(R.id.IMGFornecedorDoInstaldor);
        }
        public void update(Utilizador utilizadores){
            nome.setText(utilizadores.getUsername());
            empresa.setText(utilizadores.getNome_empresa());
            email.setText(utilizadores.getEmail());
            Categoria catergoriaDados = SingletonGerirOrcamentos.getInstance(context).getCategoria(utilizadores.getCategoria_id());
            if(catergoriaDados!=null){
                categoria.setText(catergoriaDados.getNome_categoria());
            }else{
                categoria.setText(R.string.Erro_Carregar_categoria);
            }
            Glide.with(context)
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+utilizadores.getImagem())
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemFornecedor);
        }
    }
}
