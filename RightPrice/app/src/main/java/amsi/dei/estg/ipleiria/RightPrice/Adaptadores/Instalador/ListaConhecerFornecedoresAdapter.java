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

//Adaptador para carregar a lista de Fornecedores que o instalador pode conhecer.
//Adaptador utilizado pelo Instalador


public class ListaConhecerFornecedoresAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater layoutInflater;
        private ArrayList<Utilizador> ConhecerFornecedor;

        public ListaConhecerFornecedoresAdapter(Context context, ArrayList<Utilizador> ConhecerFornecedor){
            this.context = context;
            this.ConhecerFornecedor = ConhecerFornecedor;
        }
        @Override
        public int getCount() {
            return ConhecerFornecedor.size();
        }

        @Override
        public Object getItem(int position) {
            return ConhecerFornecedor.get(position);
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
                convertView = layoutInflater.inflate(R.layout.item_lista_conhecer_fornecedor_instalador, null);
            }
            ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
            if(viewHolderLista == null){
                viewHolderLista = new ViewHolderLista(convertView);
                convertView.setTag(viewHolderLista);
            }
            viewHolderLista.update(ConhecerFornecedor.get(position));
            return convertView;
        }

        private class ViewHolderLista{
            private final TextView nome, empresa, email, categoria;
            private final ImageView imagemFornecedor;

            public ViewHolderLista(View convertView) {
                nome = convertView.findViewById(R.id.tVNomeInstaladorConhecerFornecedor);
                empresa = convertView.findViewById(R.id.tvEmpresaInstaldorConhecerFornecedor);
                email = convertView.findViewById(R.id.tvEmailInstaladorConhecerForncedor);
                categoria = convertView.findViewById(R.id.tvCategoriaInstaladorConhecerFornecedor);
                imagemFornecedor = convertView.findViewById(R.id.IMGInstaladorConhecerFornecedor);
            }
            public void update(Utilizador FornecdorConhecerInstalador){
                nome.setText(FornecdorConhecerInstalador.getNome());
                empresa.setText(FornecdorConhecerInstalador.getNome_empresa());
                email.setText(FornecdorConhecerInstalador.getEmail());
                Categoria catergoriaDados = SingletonGerirOrcamentos.getInstance(context).getCategoria(FornecdorConhecerInstalador.getCategoria_id());
                if(catergoriaDados!=null){
                    categoria.setText(catergoriaDados.getNome_categoria());
                }else{
                    categoria.setText(R.string.Erro_Carregar_categoria);
                }
                Glide.with(context)
                        .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+FornecdorConhecerInstalador.getImagem())
                        .placeholder(R.drawable.ic_user)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imagemFornecedor);
            }
        }
}
