package amsi.dei.estg.ipleiria.RightPrice.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import amsi.dei.estg.ipleiria.RightPrice.R;


public class MainFragmentAdmin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main_admin, container, false);
    }

}