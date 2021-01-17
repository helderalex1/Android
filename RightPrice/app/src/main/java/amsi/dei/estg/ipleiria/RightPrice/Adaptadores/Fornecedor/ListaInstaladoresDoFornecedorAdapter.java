package amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Fornecedor;

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

//Adaptador para carregar a lista de Instaladores que o Fornecedor tem.
//Adaptador utilizado pelo Fornecedor
public class ListaInstaladoresDoFornecedorAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Utilizador> InstaladoresDoFornecedor;

    public ListaInstaladoresDoFornecedorAdapter(Context context, ArrayList<Utilizador> InstaladoresDoFornecedor) {
        this.context = context;
        this.InstaladoresDoFornecedor = InstaladoresDoFornecedor;
    }

    @Override
    public int getCount() {
        return InstaladoresDoFornecedor.size();
    }

    @Override
    public Object getItem(int position) {
        return InstaladoresDoFornecedor.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_lista_intaladores_do_fornecedor, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(InstaladoresDoFornecedor.get(position));
        return convertView;
    }


    private class ViewHolderLista{
        private final TextView Nome,Empresa,Email,Categoria ;
        private final ImageView imagemInstalador;
        public ViewHolderLista(View convertView){
            Nome = convertView.findViewById(R.id.tVNomeInstaladorDoFornecedor);
            Empresa = convertView.findViewById(R.id.tVEmpresaInstaladorFornecedor);
            Email = convertView.findViewById(R.id.tvEmailInstaladorFornecedor);
            Categoria= convertView.findViewById(R.id.tvCategoriaInstaladorFornecedor);
            imagemInstalador= convertView.findViewById(R.id.ImgInstaladorDoFornecedor);
        }
        public void update(Utilizador InstaladorDoFornecedor){
            Nome.setText(InstaladorDoFornecedor.getNome());
            Empresa.setText(InstaladorDoFornecedor.getNome_empresa());
            Email.setText(InstaladorDoFornecedor.getEmail());
            Categoria catergoriaDados = SingletonGerirOrcamentos.getInstance(context).getCategoria(InstaladorDoFornecedor.getCategoria_id());
            if(catergoriaDados!=null){
                Categoria.setText(catergoriaDados.getNome_categoria());
            }else{
                Categoria.setText(R.string.Erro_Carregar_categoria);
            }
            Glide.with(context)
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+InstaladorDoFornecedor.getImagem())
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemInstalador);
        }
    }
}
