package amsi.dei.estg.ipleiria.RightPrice.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.ClientesInstalador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class ListaClientesInstaladorAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ClientesInstalador> clientesInstaladores;
    public ListaClientesInstaladorAdapter(Context context, ArrayList<ClientesInstalador> clientesInstaladores){
        this.context = context;
        this.clientesInstaladores = clientesInstaladores;
    }
    @Override
    public int getCount() {
        return clientesInstaladores.size();
    }

    @Override
    public Object getItem(int position) {
        return clientesInstaladores.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_list_clientes_instalador, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(clientesInstaladores.get(position));
        return convertView;
    }
    private class ViewHolderLista{
        private final TextView nome, telemovel, nif, email;
        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tVClienteInstalador);
            telemovel = convertView.findViewById(R.id.tVTelClienteInstalador);
            nif = convertView.findViewById(R.id.tVNifClienteInstalador);
            email = convertView.findViewById(R.id.tVEmailClienteInstalador);
        }
        public void update(ClientesInstalador clientesInstalador){
            nome.setText(clientesInstalador.getNome());
            telemovel.setText(""+clientesInstalador.getTelemovel());
            nif.setText(""+clientesInstalador.getNif());
            email.setText(clientesInstalador.getEmail());
        }
    }
}