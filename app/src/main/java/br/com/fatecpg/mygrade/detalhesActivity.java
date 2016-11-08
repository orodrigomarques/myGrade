package com.app.mygrades.mygrades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;



public class DetalhesActivity extends AppCompatActivity {

    private String disciplina;
    private TextView etNota1;
    private TextView etNota2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        TextView tvDisciplina = (TextView) findViewById(R.id.tvDisciplina);
        etNota1 = (TextView) findViewById(R.id.etNota1);
        etNota2 = (TextView) findViewById(R.id.etNota2);


        Intent i = getIntent();
        disciplina = i.getStringExtra("arquivo");
        tvDisciplina.setText(disciplina);

        File diretorio = getFilesDir();

        String saida;
        String nota1s = "Nota 1: 0";
        String nota2s = "Nota 2: 0";
        String nota1 = "0";
        String nota2 = "0";
        String[] nota = new String[2];

        try {
            BufferedReader in = new BufferedReader(new FileReader(diretorio.toString() + "/" + disciplina));
            String line;
            while ((line = in.readLine()) != null) {
                saida = line;
                nota = saida.split("/");
                if (nota[0].equals("Nota1")) {
                    nota1s = "Nota 1: " + nota[1];
                    nota1 = nota[1];
                } else if (nota[0].equals("Nota2")) {
                    nota2s = "P2: " + nota[1];
                    nota2 = nota[1];
                }
            }
            in.close();
        } catch (Exception ex) {
            System.out.println("ERROOR" + ex.getMessage());
        }
        etNota1.setText(nota1s);
        etNota2.setText(nota2s);

        double media = (Double.parseDouble(nota1) + Double.parseDouble(nota2)) / 2;

        TextView mmedia = (TextView) findViewById(R.id.tvMedia);
        mmedia.setText("Media: " + media);
    }

    public void atualizar(View view) {
        String nota1 = etNota1.getText().toString().substring(4);
        String nota2 = etNota2.getText().toString().substring(4);
        System.out.println("aki");
        EditText etN1 = (EditText) findViewById(R.id.etNota1);
        if (etN1.getText().toString().trim().isEmpty()) {
            etN1.setError("Sem atualização nas notas");
        } else if ((Double.parseDouble(etN1.getText().toString()) > 10)) {
            etN1.setError("Somente notas de 0 á 10");
        } else {
            nota1 = etN1.getText().toString();
            atualizarNota1(nota1);
        }

        EditText etN2 = (EditText) findViewById(R.id.etNota2);
        if (etN2.getText().toString().trim().isEmpty()) {
            etN2.setError("Sem atualização nas notas");
        } else if ((Double.parseDouble(etN2.getText().toString()) > 10)) {
            etN2.setError("Somente notas de 0 á 10");
        } else {
            nota2 = etN2.getText().toString();
            atualizarNota2(nota2);
        }
        double media = (Double.parseDouble(nota1) + Double.parseDouble(nota2)) / 2;
        TextView tvMedia = (TextView) findViewById(R.id.tvMedia);
        tvMedia.setText("Media: " + media);
    }


    public void atualizarNota1(String nota1) {
        etNota1 = (TextView) findViewById(R.id.etNota1);
        String out = nota1;
        etNota1.setText(out);

        FileOutputStream output;
        try {
            output = openFileOutput(disciplina, Context.MODE_APPEND);
            output.write("Nota 1/".getBytes());
            output.write(nota1.getBytes());
            output.write("\n".getBytes());
            output.close();
        } catch (Exception ex) {
            Toast.makeText(
                    this,
                    "Erro ao gravar a nota 1: " + ex.getLocalizedMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void atualizarNota2(String nota2) {
        etNota2 = (TextView) findViewById(R.id.etNota2);
        String out = nota2;
        etNota2.setText(out);

        FileOutputStream output;
        try {
            output = openFileOutput(disciplina, Context.MODE_APPEND);
            output.write("Nota 2/".getBytes());
            output.write(nota2.getBytes());
            output.write("\n".getBytes());
            output.close();
        } catch (Exception ex) {
            Toast.makeText(
                    this,
                    "Erro ao gravar a nota 2: " + ex.getLocalizedMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void voltar(View view) {

        finish();
    }
}
