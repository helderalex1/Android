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

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Cliente;
import amsi.dei.estg.ipleiria.RightPrice.R;

//Adaptador para carregar a lista de Clientes do Instalador.
//Adaptador utilizado pelo Instalador

public class ListaClientesInstaladorAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
   private ArrayList<Cliente> clientesInstaladores;

    public ListaClientesInstaladorAdapter(Context context, ArrayList<Cliente> clientesInstaladores){
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
            convertView = layoutInflater.inflate(R.layout.item_lista_clientes_instalador, null);
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
        private final ImageView imagemCliente;
        public ViewHolderLista(View convertView) {
            nome = convertView.findViewById(R.id.tVNomeClienteInstalador);
            telemovel = convertView.findViewById(R.id.tVTelClienteInstalador);
            nif = convertView.findViewById(R.id.tVNifClienteInstalador);
            email = convertView.findViewById(R.id.tVEmailClienteInstalador);
            imagemCliente = convertView.findViewById(R.id.IMGClienteInstalador);
        }
        public void update(Cliente clientesInstalador){
            nome.setText(clientesInstalador.getNome());
            telemovel.setText(""+clientesInstalador.getTelemovel());
            nif.setText(""+clientesInstalador.getNif());
            email.setText(clientesInstalador.getEmail());
            Glide.with(context)
                    .load(R.drawable.ic_user)
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemCliente);
        }
    }
}
