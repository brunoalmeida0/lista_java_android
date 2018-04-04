package com.example.bruno.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bruno.dao.AlunoDAO;
import com.example.bruno.modelo.Aluno;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listaAlunos; //Atributo listaAlunos do tipo ListView para armazenar a lista de alunos.

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate cria a activity

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);//Aciona a activity activity_list

        listaAlunos = findViewById(R.id.lista_alunos); //Atribui o Id lista_alunos ao atributo listaAlunos

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("aluno", aluno);
                startActivity(intentVaiProFormulario);


            }
        });


        Button novoAluno = findViewById(R.id.btnNovoAluno); //Instancia o botão de adicionar novo aluno.
        novoAluno.setOnClickListener(new View.OnClickListener(){ //Identifica evento de clique no botão novoAluno

            @Override
            public void onClick(View v) { //Adiciona ação ao evento de clique
                Intent intentVaiProFormulario = new Intent(ListActivity.this, FormularioActivity.class);
                //Intent vai pro formulário e passa como parametro o contexto (propria activity) e a activity que está sendo chamada
                startActivity(intentVaiProFormulario); //start activity passa como parametro o intent que irá levar para a nova activity
            }

        });

        registerForContextMenu(listaAlunos); //Adiciona o menu de contexto aos itens da listaAlunos

    }

    private void carregaLista() { //Método para popular a lista

        AlunoDAO dao = new AlunoDAO(this); //Instancia a classe AlunoDAO que irá fazer a parte do banco de dados
        List<Aluno> alunos = dao.buscaAlunos(); //Atribui a lista de aluno a busca do DAO que irá identificar cada item do banco de dados
        dao.close(); //Fecha o contato com o banco de dados

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter); //Passa o <Aluno> para a listaAlunos

    }

    @Override
    protected void onResume() { //Evento que ocorre após o aplicativo executar o onFinish, ou seja, quando sai do primeiro plano.

        super.onResume();
        carregaLista(); //Chama o metodo carregaLista para apresentar as atualizações

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) { //Criação do menu de contexto

        MenuItem deletar = menu.add("Deletar"); //Criação manual do item "Deletar" no menu de contexto e cria uma referencia no "deletar"

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { //Mostrando interesse no evento de clique do item deletar

            @Override
            public boolean onMenuItemClick(MenuItem item) { //Pega o evento de clique do menu no item desejado

                AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) menuInfo; //menuInfo é um adapter
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position); //Informa a posição do item clicado lá dentro

                AlunoDAO dao = new AlunoDAO(ListActivity.this); //Instanciando o DAO passando a Activity como contexto
                dao.deleta(aluno);
                dao.close();

                carregaLista();

                return false;

            }

        });

    }
}
