package com.example.mymemberarea;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DrawerLayout drawerLayout = findViewById(R.id.DrawerLayout);
        NavigationView navigationView = findViewById(R.id.NavigationView);


        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.contact :
                    Toast.makeText(MainActivity.this, "Contact is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.espace :
                    Toast.makeText(MainActivity.this, "Espace VIP is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.compte :
                    Toast.makeText(MainActivity.this, "Compte is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.home :
                    Toast.makeText(MainActivity.this, "Home is clicked", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return true;
        });
    }
}