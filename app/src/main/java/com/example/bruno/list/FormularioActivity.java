package com.example.bruno.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bruno.dao.AlunoDAO;
import com.example.bruno.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {
    private FormularioHelper helper; //cria um helper a partir do FormulároHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario); //Cria a activity_formulario

        helper = new FormularioHelper(this); //Instancia o FormulárioHelper passando como parametro este contexto

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno != null){
            helper.preencheFormulario(aluno);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Cria as opções do menu e passa como parametro o proprio menu

        MenuInflater inflater = getMenuInflater(); //Infla o menu (Faz ele aparecer)
        inflater.inflate(R.menu.menu_formulario, menu); //Metodo inflater transforma o XML menu_formulario em um menu de fato

        return super.onCreateOptionsMenu(menu); //retorna o proprio onCreateOptionsMenu com o menu passado por parametro

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Decide o que fazer com o item selecionado no menu

        switch (item.getItemId()){ //Switch com o id do item do formulário para decidir qual ação será tomada
            case R.id.menuFormularioOk: // Caso o id selecionado seja o menuFormularioOk...
                Aluno aluno = helper.pegaAluno(); //Cria um aluno do tipo Aluno e atribui ao metodo do helper "pegaAluno()" que irá retornar todos os dados do aluno
                AlunoDAO dao = new AlunoDAO(this);  //Instancia a classe AlunoDAO que contém o banco de dados

                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else {
                    dao.insere(aluno); //Chama o metodo insere do dao para inserir o aluno no banco de dados
                }

                dao.close(); //Fecha o banco de dados

                //Mostra o Toast com mensagem de aluno salvo;
                Toast.makeText(FormularioActivity.this, "Aluno "+ aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();

                finish();//finaliza a ação
                break; //break do case.
        }

        return super.onOptionsItemSelected(item); //retorna o item selecionado.
    }

}
