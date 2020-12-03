package amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Instalador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class ListaFornecedores extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Utilizador> utilizadores;
    public ListaFornecedores(Context context, ArrayList<Utilizador> utilizadores){
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
            convertView = layoutInflater.inflate(R.layout.item_lista_clientes_do_fornecedor, null);
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
        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tVNomeClienteFornecedor);
            empresa = convertView.findViewById(R.id.tVEmpresaClienteFornecedor);
            email = convertView.findViewById(R.id.tvEmailClienteFornecedor);
            categoria = convertView.findViewById(R.id.tvCategoriaClienteFornecedor);
        }
        public void update(Utilizador utilizadores){
            nome.setText(utilizadores.getUsername());
            empresa.setText(utilizadores.getNome_empresa());
            email.setText(utilizadores.getEmail());
            categoria.setText(""+utilizadores.getCategoria_id());
        }
    }
}
