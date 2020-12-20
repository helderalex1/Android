package amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Fornecedor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.FornecedorInstalador.FornecedorInstalador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class ListaClientesFornecedorAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<FornecedorInstalador> fornecedor_instaladores;

    public ListaClientesFornecedorAdapter(Context context, ArrayList<FornecedorInstalador> fornecedor_instaladores) {
        this.context = context;
        this.fornecedor_instaladores = fornecedor_instaladores;
    }

    @Override
    public int getCount() {
        return fornecedor_instaladores.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        viewHolderLista.update(fornecedor_instaladores.get(position));
        return convertView;
    }


    private class ViewHolderLista{
        private final TextView id_for, id_ins;
        public ViewHolderLista(View convertView){
            id_for = convertView.findViewById(R.id.tVNome_header);
            id_ins = convertView.findViewById(R.id.tVEmpresa_header);
        }
        public void update(FornecedorInstalador clientesInstalador){
            id_for.setText(clientesInstalador.getId_fornecedor());
            id_ins.setText(""+clientesInstalador.getId_instalador());
        }
    }
}
