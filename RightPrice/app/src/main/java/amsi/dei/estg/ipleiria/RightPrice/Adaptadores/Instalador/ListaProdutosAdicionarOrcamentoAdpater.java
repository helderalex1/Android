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
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;


//Adaptador para carregar a lista de Produtos para adicionar no orçamento
//Adaptador utilizado pelo Instalador


public class ListaProdutosAdicionarOrcamentoAdpater extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Produto> produtosAdicionarOrcmamentos;

    public ListaProdutosAdicionarOrcamentoAdpater(Context context, ArrayList<Produto> ProdutosAdicionarOrcamento){
        this.context = context;
        this.produtosAdicionarOrcmamentos = ProdutosAdicionarOrcamento;
    }
    @Override
    public int getCount() {
        return produtosAdicionarOrcmamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtosAdicionarOrcmamentos.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_lista_adicionar_produto_instalador, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(produtosAdicionarOrcmamentos.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private final TextView nome, referencia, Nome_Fornecedor, preco;
        private final ImageView imagemProdutoAdicionarOrcamento;

        public ViewHolderLista(View convertView) {
            nome = convertView.findViewById(R.id.tVNomeProdutoAdiconarOrcamento);
            referencia = convertView.findViewById(R.id.tVReferenciaProdutoAdicionarOrcamento);
            Nome_Fornecedor = convertView.findViewById(R.id.tVNomeFornecedorProdutoAdicionarOrcamento);
            preco = convertView.findViewById(R.id.tVPrecoProdutoFornecedor);
            imagemProdutoAdicionarOrcamento = convertView.findViewById(R.id.ImgProdutoAcicionarOrcamento);
        }
        public void update(Produto ProdutoAdicionarOrcamento){
            nome.setText(ProdutoAdicionarOrcamento.getNome());
            referencia.setText(ProdutoAdicionarOrcamento.getReferencia());
            Utilizador fornecedor = SingletonGerirOrcamentos.getInstance(context).getUtilizador(ProdutoAdicionarOrcamento.getId_fornecedor());
            if(fornecedor!=null){
                Nome_Fornecedor.setText(fornecedor.getNome_empresa());
            }else{
                Nome_Fornecedor.setText(R.string.erro_carregar_nome_empresa);
            }
            preco.setText(String.valueOf(ProdutoAdicionarOrcamento.getPreco()));
            Glide.with(context)
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+ProdutoAdicionarOrcamento.getImagem())
                    .placeholder(R.mipmap.ic_orcamento)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemProdutoAdicionarOrcamento);
        }
    }
}
