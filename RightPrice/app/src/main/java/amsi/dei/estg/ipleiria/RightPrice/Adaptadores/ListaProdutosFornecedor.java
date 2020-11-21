package amsi.dei.estg.ipleiria.RightPrice.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Fornecedor_instalador;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.ProdutosFornecedor;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class ListaProdutosFornecedor extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ProdutosFornecedor> produtosFornecedors;
    public ListaProdutosFornecedor(Context context, ArrayList<ProdutosFornecedor> produtosFornecedors) {
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
            convertView = layoutInflater.inflate(R.layout.item_list_produto_fornecedor, null);
        }
        ListaProdutosFornecedor.ViewHolderLista viewHolderLista = (ListaProdutosFornecedor.ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ListaProdutosFornecedor.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(produtosFornecedors.get(position));
        return convertView;
    }


    private class ViewHolderLista{
        private final TextView nome_produto, referência, descrição, preço;
        public ViewHolderLista(View convertView){
            nome_produto = convertView.findViewById(R.id.tVNome_Produto);
            referência = convertView.findViewById(R.id.tVReferencia);
            descrição = convertView.findViewById(R.id.tVDescrição);
            preço = convertView.findViewById(R.id.tVPreço);
        }
        public void update(ProdutosFornecedor produtosFornecedor){
            nome_produto.setText(produtosFornecedor.getNome());
            referência.setText(""+produtosFornecedor.getReferência());
            descrição.setText(produtosFornecedor.getDescrição());
            preço.setText(""+produtosFornecedor.getPreço());
        }
    }
}
