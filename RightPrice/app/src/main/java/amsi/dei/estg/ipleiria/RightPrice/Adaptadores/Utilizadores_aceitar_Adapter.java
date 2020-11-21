package amsi.dei.estg.ipleiria.RightPrice.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Utilizador;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class Utilizadores_aceitar_Adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Utilizador> utilizadors;
    public Utilizadores_aceitar_Adapter(Context context, ArrayList<Utilizador> utilizadors){
        this.context = context;
        this.utilizadors = utilizadors;
    }
    @Override
    public int getCount() {
        return utilizadors.size();
    }

    @Override
    public Object getItem(int position) {
        return utilizadors.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_list_aceitar_cliente_admin, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(utilizadors.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private final TextView nome, empresa, email,categoria;
        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tVNomeAceitarUtilizador);
            empresa = convertView.findViewById(R.id.tvEmpresaAceitarUtilizador);
            email = convertView.findViewById(R.id.tvEmailAceitarUtilizador);
            categoria = convertView.findViewById(R.id.tvCategoriaAceitarUtilizador);
        }
        public void update(Utilizador utilizadores){
            nome.setText(utilizadores.getUsername());
            empresa.setText(utilizadores.getNome_empresa());
            email.setText(utilizadores.getEmail());
            categoria.setText(""+utilizadores.getCategoria_id());
        }
    }
}