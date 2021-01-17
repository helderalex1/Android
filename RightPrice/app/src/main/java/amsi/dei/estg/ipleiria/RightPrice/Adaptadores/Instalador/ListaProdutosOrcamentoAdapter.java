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

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

//Adaptador para carregar a lista de Produtos do orcamento.
//Adaptador utilizado pelo Instalador

public class ListaProdutosOrcamentoAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Produto> produtosOrcmamentosInstaldor;

    public ListaProdutosOrcamentoAdapter(Context context, ArrayList<Produto> ProdutosOrcamento){
        this.context = context;
        this.produtosOrcmamentosInstaldor = ProdutosOrcamento;
    }
    @Override
    public int getCount() {
        return produtosOrcmamentosInstaldor.size();
    }

    @Override
    public Object getItem(int position) {
        return produtosOrcmamentosInstaldor.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_lista_produtos_orcamento_instalador, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(produtosOrcmamentosInstaldor.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private final TextView nome, referencia, descricao, preco;
        private final ImageView imagemProdutoOrcamento;

        public ViewHolderLista(View convertView) {
            nome = convertView.findViewById(R.id.tVNomeProdutoOrcamento);
            referencia = convertView.findViewById(R.id.tVReferenciaProdutoOrcamento);
            descricao = convertView.findViewById(R.id.tVDescricaoProdutoOrcamento);
            preco = convertView.findViewById(R.id.tVPrecoProdutoOrcamento);
            imagemProdutoOrcamento = convertView.findViewById(R.id.IMGProdutoOrcamento);
        }
        public void update(Produto ProdutoOrcamento){
            nome.setText(ProdutoOrcamento.getNome());
            referencia.setText(ProdutoOrcamento.getReferencia());
            descricao.setText(ProdutoOrcamento.getDescricao());
            preco.setText(String.valueOf(ProdutoOrcamento.getPreco()));
            Glide.with(context)
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+ProdutoOrcamento.getImagem())
                    .placeholder(R.mipmap.ic_orcamento)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemProdutoOrcamento);
        }
    }
}
