package com.app.mygrades.mygrades;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;



public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
    }

    public void salvar(View view){
        EditText etIncluirDisciplina = (EditText) findViewById(R.id.etIncluirDisciplina);
        String NovaDisciplina = etIncluirDisciplina.getText().toString();

        FileOutputStream output;
        try{
            output = openFileOutput(NovaDisciplina, Context.MODE_PRIVATE);
            output.close();
        }catch(Exception ex){
            Toast.makeText(
                    this,
                    "Erro ao salvar a disciplina: "+ex.getLocalizedMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }

        finish();
    }
}
