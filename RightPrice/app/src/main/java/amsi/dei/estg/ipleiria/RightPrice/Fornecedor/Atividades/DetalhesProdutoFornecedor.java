package amsi.dei.estg.ipleiria.RightPrice.Fornecedor.Atividades;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;

import amsi.dei.estg.ipleiria.RightPrice.FullScreen;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

public class DetalhesProdutoFornecedor extends FullScreen {
    private EditText nome_produto, referência, descrição, preço;
    public static final String DETALHES_PRODUTO ="produto";
    public static final int ADICIONAR = 1;
    public static final int ACEITAR = 2;
    public static final int EDITAR = 3;
    private Produto produtosFornecedor;
    int id_produto;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_produto_fornecedor);
        nome_produto = findViewById(R.id.edtNomeClienteInstalador);
        referência = findViewById(R.id.edtReferênciaProduto);
        descrição = findViewById(R.id.edtDescrição);
        preço = findViewById(R.id.edtPreçoProduto);

        id_produto = getIntent().getIntExtra(DETALHES_PRODUTO,0);
      //  produtosFornecedor = SingletonGerirOrcamentos.getInstance(getBaseContext()).getProduto(id_produto);
     /*   if (produtosFornecedor!=null){
            setTitle(getString(R.string.detalhes_utilizador_com_dois_pontos)+ produtosFornecedor.getNome());
            nome_produto.setText(produtosFornecedor.getNome());
            referência.setText(""+produtosFornecedor.getReferência());
            descrição.setText(produtosFornecedor.getDescrição());
            preço.setText(""+produtosFornecedor.getPreço());
        }else{
            setTitle("Adicionar produto");
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
        return true;
    }
}
