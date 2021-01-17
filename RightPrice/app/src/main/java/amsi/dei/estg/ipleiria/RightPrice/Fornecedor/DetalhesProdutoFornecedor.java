package amsi.dei.estg.ipleiria.RightPrice.Fornecedor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import amsi.dei.estg.ipleiria.RightPrice.MenuMainActivity;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.Produto;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;
import amsi.dei.estg.ipleiria.RightPrice.listener.DetalhesProdutoListener;

//Classe de detalhes do produto
public class DetalhesProdutoFornecedor extends AppCompatActivity implements DetalhesProdutoListener {
    private EditText nome_produto, referencia, descricao, preco;
    private FloatingActionButton floatingActionButton;
    private ImageView img;
    public static final String DETALHES_PRODUTO ="produto";
    public static final int ADICIONAR = 1;
    public static final int EDITAR = 3;
    public int valor_save=0;
    private Produto produtosFornecedor;
    int id_produto;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleColor(R.color.black);
        setContentView(R.layout.activity_detalhes_produto_fornecedor);
        nome_produto = findViewById(R.id.edtNomeProduto);
        referencia = findViewById(R.id.edtReferenciaProduto);
        descricao = findViewById(R.id.edtDescricao);
        preco = findViewById(R.id.edtPrecoProduto);
        floatingActionButton= findViewById(R.id.fab_detalhes_produto);
        img=findViewById(R.id.Img_produto);

        id_produto = getIntent().getIntExtra(DETALHES_PRODUTO,0);

        SingletonGerirOrcamentos.getInstance(getBaseContext()).setDesalhesProdutoListener(this);
        produtosFornecedor = SingletonGerirOrcamentos.getInstance(getBaseContext()).getProduto(id_produto);
        //carrrega os dados para as vistas
        if (produtosFornecedor!=null){
            setTitle(getString(R.string.detalhes_produto_com_dois_pontos)+ produtosFornecedor.getNome());
            nome_produto.setText(produtosFornecedor.getNome());
            referencia.setText(""+produtosFornecedor.getReferencia());
            descricao.setText(produtosFornecedor.getDescricao());
            preco.setText(""+produtosFornecedor.getPreco());
            Glide.with(getApplicationContext())
                    .load(SingletonGerirOrcamentos.mURLAPIOrcamentos+"/"+produtosFornecedor.getImagem())
                    .placeholder(R.mipmap.ic_produto)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
            floatingActionButton.setImageResource(R.drawable.ic_guardar);
        }else{
            setTitle("Adicionar Produto");
            floatingActionButton.setImageResource(R.drawable.ic_guardar);
        }

        //funcao de clique do botao adicionar
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferenceuser = getSharedPreferences(MenuMainActivity.PREF_USER, MODE_PRIVATE);
                String id = sharedPreferenceuser.getString(MenuMainActivity.ID, null);

                if(produtosFornecedor !=null){
                    produtosFornecedor.setNome(nome_produto.getText().toString());
                    produtosFornecedor.setDescricao(descricao.getText().toString());
                    produtosFornecedor.setReferencia(referencia.getText().toString());
                    produtosFornecedor.setPreco(Float.parseFloat(preco.getText().toString()));
                    valor_save=1;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).alteraProdutoAPI(produtosFornecedor,getApplicationContext());
                }else{
                    if(nome_produto.getText().toString().equals("")||nome_produto.getText().toString().equals(" ")||referencia.getText().toString().equals("")||referencia.getText().toString().equals(" ")||descricao.getText().toString().equals("")||descricao.getText().toString().equals(" ")||preco.getText().toString().equals("")||preco.getText().toString().equals(" ")){
                        Snackbar.make(view,getString(R.string.preencha_todos_os_campos),Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    Produto auxproduto = new Produto(0,Integer.parseInt(id),null,nome_produto.getText().toString(),referencia.getText().toString(),descricao.getText().toString(),Float.parseFloat(preco.getText().toString()));
                    valor_save=2;
                    SingletonGerirOrcamentos.getInstance(getApplicationContext()).adicionarProdutoAPI(auxproduto,getApplicationContext());
                }
            }
        });

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


    //escuta quanddo os dados foram aceites com sucesso
    @Override
    public void onSucssecProduto() {
        setResult(RESULT_OK);
        finish();
    }

    //funcao de erro 
    @Override
    public void onErroDetalhesprodutos(String message) {
        if(valor_save==1){
            Toast.makeText(this, "Erro a editar o produto. "+message, Toast.LENGTH_LONG).show();
        }else if(valor_save==2){
            Toast.makeText(this, "Erro a guardar o produto. "+message, Toast.LENGTH_LONG).show();
        }

    }

}
