package br.com.progiv.sistemageo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.os.StrictMode;

import java.io.IOException;
import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void carregarLocalizacao(String ip) throws JSONException, IOException {
        Localizacao localizacao = ClienteGeoIp.localizar(ip);
        TextView pais = (TextView) findViewById(R.id.pais);
        TextView estado = (TextView) findViewById(R.id.estado);
        pais.setText(localizacao.getCountryName());
        estado.setText(localizacao.getCountryState());
    }

    public void btnLocalizarOnClick(View v){
        EditText txtIP = (EditText) findViewById(R.id.txtIP);
        String ip = txtIP.getText().toString();
        try{
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            else{
                carregarLocalizacao(ip);
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

   /* public void btnLocalizarOnClick(View v) throws JSONException, IOException {
        EditText txtIP = (EditText) findViewById(R.id.txtIP);
        String ip = txtIP.getText().toString();
        carregarLocalizacao(ip);
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    EditText txtIP = (EditText) findViewById(R.id.txtIP);
                    String ip = txtIP.getText().toString();
                    try {
                        carregarLocalizacao(ip);
                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Sem funcionamento", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}

