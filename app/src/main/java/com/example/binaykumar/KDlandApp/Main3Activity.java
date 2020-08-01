package com.example.binaykumar.KDlandApp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.binaykumar.KDlandApp.ui.Plot.PlotFragment;
import com.example.binaykumar.KDlandApp.ui.Result.ResultFragment;


public class Main3Activity extends AppCompatActivity implements PlotFragment.OnMessage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_1, R.id.navigation_2,R.id.navigation_4, R.id.navigation_5,R.id.navigation_3)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        getSupportActionBar().setTitle("Keredari coal mining project");




    }

    @Override
    public void onMessageSent(String message) {

        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        resultFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction =getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.container1,resultFragment, null)
                                      .addToBackStack(null);
        fragmentTransaction.commit();

    }
}
