package amsi.dei.estg.ipleiria.RightPrice.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import amsi.dei.estg.ipleiria.RightPrice.MenuMainActivity;
import amsi.dei.estg.ipleiria.RightPrice.R;


public class Login extends Fragment {
Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.btnLogin);

    return view;
    }



}