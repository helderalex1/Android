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

import amsi.dei.estg.ipleiria.RightPrice.Modelo.Orcamento;
import amsi.dei.estg.ipleiria.RightPrice.R;

//Adaptador para carregar a lista de Orçamentos do Cliente.
//Adaptador utilizado pelo Instalador

public class ListaOrcamentoAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Orcamento> OrcmamentosInstaldor;

    public ListaOrcamentoAdapter(Context context, ArrayList<Orcamento> orcamentos){
        this.context = context;
        this.OrcmamentosInstaldor = orcamentos;
    }
    @Override
    public int getCount() {
        return OrcmamentosInstaldor.size();
    }

    @Override
    public Object getItem(int position) {
        return OrcmamentosInstaldor.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_lista_orcamento_instalador, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(OrcmamentosInstaldor.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private final TextView nome, data, margem, total;
        private final ImageView imagemOrcamento;

        public ViewHolderLista(View convertView) {
            nome = convertView.findViewById(R.id.tVNomeOrcamentoInstalador);
            data = convertView.findViewById(R.id.tVDataOrcamentoInstalador);
            margem = convertView.findViewById(R.id.tVMargemOrcamentoInstalador);
            total = convertView.findViewById(R.id.tvTotalOrcamentoInstalador);
            imagemOrcamento = convertView.findViewById(R.id.IMGOrcamento);
        }
        public void update(Orcamento OrcamentoInstalador){
            nome.setText(OrcamentoInstalador.getNome());
            data.setText(OrcamentoInstalador.getData_orcamento());
            margem.setText(""+OrcamentoInstalador.getMargem());
            total.setText(String.valueOf(OrcamentoInstalador.getTotal()));
            Glide.with(context)
                    .load(R.mipmap.ic_orcamento)
                    .placeholder(R.mipmap.ic_orcamento)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagemOrcamento);
        }
    }
}
