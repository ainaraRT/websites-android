package com.example.ej04_ainara_ramos_toledo;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Web2Fragment extends Fragment {

    public static String web;
    public static WebView web2;
    public ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web2, container, false);

        web2 = view.findViewById(R.id.web2);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
        web2.getSettings().setJavaScriptEnabled(true);

        //Estos métodos son para que cuando tenga que cargar la página, aparezca un ProgressBar
        //indicando que está cargando
        web2.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        web2.loadUrl("https://" + web);

        return view;
    }

    //Añado este método para que cuando se cambie de pantalla, la página siga estando
    public View onResume(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web2, container, false);

        web2 = view.findViewById(R.id.web2);
        web2.setWebViewClient(new WebViewClient());
        web2.loadUrl("https://" + web);
        return view;
    }

    //Método que recibe el string introducido por el usuario y se asigna al string que se va a
    //utilizar para indicar la url de la web
    public void sentWeb2 (String web2Send){
        this.web = web2Send;
    }
}
