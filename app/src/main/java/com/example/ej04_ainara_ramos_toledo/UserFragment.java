package com.example.ej04_ainara_ramos_toledo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class UserFragment extends Fragment {

    EditText web1ET, web2ET;
    private Button button1, button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        web1ET = (EditText) view.findViewById(R.id.web1ET);
        web2ET = (EditText) view.findViewById(R.id.web2ET);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String web1User = web1ET.getText().toString();
                if(web1User.isEmpty()){
                    //Muestra un snackbar de que el editText está vacío
                    Snackbar.make(v, R.string.snackbar_nothing, Snackbar.LENGTH_INDEFINITE).setAction
                            (R.string.snackbar_nothing_action, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //No pongo nada para que se cierre
                                }
                            }).show();
                } else {
                    //Recoge el string introducido por el usuario para mandarlo al Main y allí mandarlo
                    //al fragment
                    FragmentActivity activity = getActivity();
                    MainActivity mainActivity = (MainActivity) activity;

                    mainActivity.sentWeb1(web1User);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String web2User = web2ET.getText().toString();
                if(web2User.isEmpty()){
                    Snackbar.make(v, R.string.snackbar_nothing, Snackbar.LENGTH_INDEFINITE).setAction
                            (R.string.snackbar_nothing_action, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //No pongo nada para que se cierre
                                }
                            }).show();
                } else {
                    FragmentActivity activity = getActivity();
                    MainActivity mainActivity = (MainActivity) activity;

                    mainActivity.sentWeb2(web2User);
                }
            }
        });

        return view;
    }

}
