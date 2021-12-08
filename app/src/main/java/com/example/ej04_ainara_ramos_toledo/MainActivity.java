package com.example.ej04_ainara_ramos_toledo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public String web1Send, web2Send;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ListView menuLateral;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esto lo hacemos para poder asignar el botón con las tres rayitas del menú lateral
        drawerLayout = findViewById(R.id.main_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,
                R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menuLateral = findViewById(R.id.menu_lateral);
        menuLateral.setOnItemClickListener(this);

        //Pantalla que se muestra al iniciarse la aplicación
        changeScreen(2);
    }

    //Acciones de la barra se acaba de seleccionar un elemento
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return true;
    }

    //Sincroniza el estado del botón con la barra
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    //Método que indica el índice de que elemento se ha pulsado, según ese elemento vamos a
    //cambiar la vista que se pone en el contenedor principal
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        changeScreen(i);
    }

    //Método para cambiar la pantalla que se muestra nada más iniciar la aplicación y para movernos
    //por la barra lateral
    private void changeScreen (int i){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (i){
            case 0:
                Web1Fragment web1Fragment = new Web1Fragment();
                setTitle(getString(R.string.first_screen));
                transaction.replace(R.id.content, web1Fragment);
                break;
            case 1:
                Web2Fragment web2Fragment = new Web2Fragment();
                setTitle(getString(R.string.second_screen));
                transaction.replace(R.id.content, web2Fragment);
                break;
            case 2:
                setTitle(getString(R.string.third_screen));
                UserFragment userFragment = new UserFragment();
                transaction.replace(R.id.content, userFragment);
                break;
        }
        transaction.commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    //Método para recoger el dato introducido por el usuario y mandarlo a la pantalla correspondiente
    public void sentWeb1 (String web1Send){
        this.web1Send = web1Send;
    }

    //Método para recoger el dato introducido por el usuario y mandarlo a la pantalla correspondiente
    public void sentWeb2 (String web2Send){
        this.web2Send = web2Send;
    }

    //Método que se asigna al botón confirmar para abrir el fragment correspondiente
    public void openFragment1(View view){
            Web1Fragment fragment = new Web1Fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragment.sentWeb1(web1Send);
            FragmentTransaction transaction =fragmentManager.beginTransaction();
            transaction.replace(R.id.content, new Web1Fragment());
            setTitle(R.string.first_screen);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
    }

    //Método que se asigna al botón confirmar para abrir el fragment correspondiente
    public void openFragment2(View view){
        Web2Fragment fragment = new Web2Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment.sentWeb2(web2Send);
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new Web2Fragment());
        setTitle(R.string.second_screen);
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}