package br.com.fatecpg.mygrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    File dirFiles[]; //Pasta de aqrquivos
    ArrayList<String> arquivos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        arquivos.clear();
        File dir = getFilesDir();
        dirFiles = dir.listFiles();
        for (int i = 0; i < dirFiles.length; i++) {
            if(!dirFiles[i].isDirectory()) {
                arquivos.add(dirFiles[i].getName());
            }
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arquivos);

        ListView list = (ListView)findViewById(R.id.lvDisciplina);
        list.setAdapter(aa);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nomeArquivo = MainActivity.this.arquivos.get(i);
                Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                intent.putExtra("Arquivo", nomeArquivo);
                startActivity(intent);
            }
        });
    }

    public void adicionarDisciplina(View view){
        Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
        startActivity(i);
    }

}




