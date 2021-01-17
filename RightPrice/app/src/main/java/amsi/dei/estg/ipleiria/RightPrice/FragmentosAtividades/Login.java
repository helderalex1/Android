package amsi.dei.estg.ipleiria.RightPrice.FragmentosAtividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;


//Classe de login
public class Login extends Fragment {
    private Button login;
    private EditText edit_username;
    private EditText edit_password_login;

    private String username = null;
    private String password = null;

//carrega o formulario de login
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.btnLogin);
        edit_username = view.findViewById(R.id.editUsername);
        edit_password_login = view.findViewById(R.id.editPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username= edit_username.getText().toString();
                password=edit_password_login.getText().toString();
                SingletonGerirOrcamentos.getInstance(getContext()).loginAPI(username,password,0,getContext());
            }
        });



    return view;
    }








}