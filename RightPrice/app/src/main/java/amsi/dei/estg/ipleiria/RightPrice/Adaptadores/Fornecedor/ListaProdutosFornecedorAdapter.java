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

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

//Adaptador para carregar a lista de de Seus Produtos.
//Adaptador utilizado pelo Fornecedor

public class ListaProdutosFornecedorAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Produto> produtosFornecedors;

    public ListaProdutosFornecedorAdapter(Context context, ArrayList<Produto> produtosFornecedors) {
        this.context = context;
        this.produtosFornecedors = produtosFornecedors;
    }

    @Override
    public int getCount() {
        return produtosFornecedors.size();
    }

    @Override
    public Object getItem(int position) {
        return produtosFornecedors.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_lista_produto_fornecedor, null);
        }
        ListaProdutosFornecedorAdapter.ViewHolderLista viewHolderLista = (ListaProdutosFornecedorAdapter.ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null) {
            viewHolderLista = new ListaProdutosFornecedorAdapter.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(produtosFornecedors.get(position));
        return convertView;
    }


    private class ViewHolderLista {
        private final TextView nome_produto, referencia, preco;
        private final ImageView imagemProduto;

        public ViewHolderLista(View convertView) {
            nome_produto = convertView.findViewById(R.id.tVNomeProdutoFornecedor);
            referencia = convertView.findViewById(R.id.tVReferenciaProdutoFornecedor);
            preco = convertView.findViewById(R.id.tVPrecoProdutoFornecedor);
            imagemProduto= convertView.findViewById(R.id.ImgProdutoFornecedor);
        }

        public void update(Produto produtosFornecedor) {
            nome_produto.setText(produtosFornecedor.getNome());
            referencia.setText("" + produtosFornecedor.getReferencia());
            preco.setText("" + produtosFornecedor.getPreco());
            Glide.with(context)
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+produtosFornecedor.getImagem())
                    .placeholder(R.mipmap.ic_produto)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemProduto);

        }
    }
}

