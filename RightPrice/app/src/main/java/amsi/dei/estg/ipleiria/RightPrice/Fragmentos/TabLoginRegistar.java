package amsi.dei.estg.ipleiria.RightPrice.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.ViewPagerAdapter;
import amsi.dei.estg.ipleiria.RightPrice.FullScreen;
import amsi.dei.estg.ipleiria.RightPrice.R;


public class TabLoginRegistar extends DialogFragment  {
    private FullScreen fullScreen;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitle = new ArrayList<>();
    private Login login;
    private Registar registar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_login_registar, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewpageraa);

        registar = new Registar();
        login = new Login();

        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);

        viewPagerAdapter.addFragment(login,getString(R.string.Login));
        viewPagerAdapter.addFragment(registar,getString(R.string.Registar));
        viewPager.setAdapter(viewPagerAdapter);
        return view;
    }

    public TabLoginRegistar(FullScreen fullScreen) {
        this.fullScreen = fullScreen;
    }

    @Override
    public void onDestroy() {
       fullScreen.noneColor();
        super.onDestroy();
    }
}
