package edu.ifsul.musart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.musart.R;
import com.example.musart.databinding.ActivityMainBinding;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    // Declaração do AppBarConfiguration

    private AppBarConfiguration appBarConfiguration;

    // Método onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);

        appBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this,
                navController, appBarConfiguration);

    }

    // Método onCreateOptionsMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    // Método onSupportNavigateUp

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);

        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();

    }

}