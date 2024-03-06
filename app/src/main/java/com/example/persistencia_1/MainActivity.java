package com.example.persistencia_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText edNome, edPeso, edAltura;
    RadioButton rbFeminino,rbMasculino;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.edtNome);
        edPeso = findViewById(R.id.edtPeso);
        edAltura = findViewById(R.id.edtAltura);
        rbFeminino = findViewById(R.id.rbFeminino);
        rbMasculino = findViewById(R.id.rbMasculino);

        //Recuperar Dados

        preferences = getSharedPreferences("dadosUsuario", 0);
        iniciarDadosUsuario();
    }
    public void salvar(View view){
        SharedPreferences.Editor perfUsuario = preferences.edit();

        if (edNome.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Digite um nome", Toast.LENGTH_LONG).show();
        }else {
            perfUsuario.putString("nome", edNome.getText().toString());
        }
        if (edPeso.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Digite um peso", Toast.LENGTH_LONG).show();
        }else {
            perfUsuario.putFloat("peso", Float.parseFloat(edPeso.getText().toString()));
        }
        if (edAltura.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Digite um altura", Toast.LENGTH_LONG).show();
        }else {
            perfUsuario.putFloat("altura", Float.parseFloat(edAltura.getText().toString()));
        }

        if (rbFeminino.isChecked()){
            perfUsuario.putBoolean("sexo", true);
        } else {
            perfUsuario.putBoolean("sexo", false);
        }

        perfUsuario.commit();
        Toast.makeText(getApplicationContext(), "Salvas", Toast.LENGTH_LONG).show();
    }

    private void iniciarDadosUsuario(){

        if (preferences.contains("nome")){
            edNome.setText(preferences.getString("nome",""));
            Toast.makeText(getApplicationContext(), "Nome", Toast.LENGTH_LONG).show();
        } else {
            edNome.setText("");
        }
        if (preferences.contains("peso")) {
            edPeso.setText(String.valueOf(preferences.getFloat("peso", 0.0f))) ;
            Toast.makeText(getApplicationContext(), "peso", Toast.LENGTH_LONG).show();
        }else {
            edPeso.setText("");
        }
        if (preferences.contains("altura")) {
            edAltura.setText(String.valueOf(preferences.getFloat("altura", 0.0f)));
            Toast.makeText(getApplicationContext(), "altura", Toast.LENGTH_LONG).show();
        }else {
            edAltura.setText("");
        }
        if (preferences.contains("sexo")){
            if (preferences.getBoolean("sexo", true)){
                rbFeminino.setChecked(true);
            } else {
                rbMasculino.setChecked(true);
            }

        }
    }

    public void limpar(View view){
        SharedPreferences.Editor perfUsuario = preferences.edit();
        perfUsuario.clear();
        perfUsuario.apply();
        iniciarDadosUsuario();
        Toast.makeText(getApplicationContext(), "Dados Limpos", Toast.LENGTH_LONG).show();
    }

    public void sair(View view){
        finish();
    }
}
