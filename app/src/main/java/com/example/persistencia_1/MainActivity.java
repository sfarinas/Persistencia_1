package com.example.persistencia_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
        preferences = getSharedPreferences("dadosUsuario", MODE_PRIVATE);
        iniciarDadosUsuario();
    }

    private void iniciarDadosUsuario() {
        if (preferences.contains("nome")){
            edNome.setText(preferences.getString("nome", ""));
        }
        else {
            edNome.setText("");
        }

        if (preferences.contains("peso")){
            edPeso.setText(String.valueOf(preferences.getFloat("peso", 0.0f)));
        }
        else {
            edPeso.setText("");
        }
        if (preferences.contains("altura")){
            edAltura.setText(String.valueOf(preferences.getFloat("altura", 0.0f)));
        }
        else {
            edAltura.setText("");
        }

        if (preferences.contains("sexo")){
            if (preferences.getBoolean("sexo", true)){
                rbFeminino.setChecked(true);
            }
            else {
                rbMasculino.setChecked(true);
            }
        }
        else {
            rbFeminino.setChecked(true);
        }
    }

    public void salvar(View view){
        SharedPreferences.Editor prefUsuario = preferences.edit();
        prefUsuario.putFloat("peso", Float.parseFloat(edPeso.getText().toString()));
        prefUsuario.putFloat("altura", Float.parseFloat(edAltura.getText().toString()));

        if (rbFeminino.isChecked()){
            prefUsuario.putBoolean("sexo", true);
        }
        else {
            prefUsuario.putBoolean("sexo", true);
        }

        prefUsuario.commit();

        Toast.makeText(getApplicationContext(), "Configuracoes salvas", Toast.LENGTH_LONG).show();
    }

    public void limpar(View view){
        SharedPreferences.Editor prefUsuario = preferences.edit();
        prefUsuario.apply();
        iniciarDadosUsuario();
        Toast.makeText(getApplicationContext(), "Limpas as informacoes", Toast.LENGTH_LONG).show();
    }
    public void sair(View v){
        finish();
    }

}
