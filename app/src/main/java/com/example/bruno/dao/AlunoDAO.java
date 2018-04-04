package com.example.bruno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.bruno.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends SQLiteOpenHelper { //Herdando o banco de dados SQLite

    public AlunoDAO(Context context) { // Criando o metodo AlunoDAO passando como parametro a activity que está chamando ele
        super(context, "Agenda", null, 1); //Passando como parametro o nome do banco de dados e a versão
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Cria o banco de dados e passa instruções SQL atravez do execSQL
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //Atualiza o banco de dados caso necessário
        String sql = "DROP TABLE IF EXISTS Alunos"; //Instrução SQL
        db.execSQL(sql);//Executa a instrução SQL
        onCreate(db); //Recria o banco com a versão nova
    }

    public void insere(Aluno aluno) { //Insere o aluno passado como parametro

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        db.insert("Alunos", null, dados); //Insere os dados na tabela de Alunos

    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues(); //Instancia ContentValues para pegar os conteudos
        dados.put("nome", aluno.getNome()); //adiciona o nome atraves do getNome e adiciona na linha de "nome"
        dados.put("endereco", aluno.getEndereco()); //adiciona o endereco atraves do getEndereco e adiciona na linha de "endereco"
        dados.put("telefone", aluno.getTelefone()); //adiciona o telefone atraves do getTelefone e adiciona na linha de "telefone"
        dados.put("site", aluno.getSite()); //adiciona o site atraves do getSite e adiciona na linha de "site"
        dados.put("nota", aluno.getNota());//adiciona a nota atraves do getNota e adiciona na linha de "nota"
        return dados;
    }

    public List<Aluno> buscaAlunos() { //Metodo para buscar os alunos no banco de dados e retornar

        String sql = "SELECT * FROM Alunos;"; //Instrução em SQL
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null); //Cursor que aponta para uma posição na tabela (inicia com uma linha e coluna nula

        List<Aluno> alunos = new ArrayList<Aluno>(); //instancia um aluno do tipo List<Aluno> e cria um ArrayList

        while(c.moveToNext()){ //Enquanto houver linhas == true...

            Aluno aluno = new Aluno(); //Instancia o aluno
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            //Seta no aluno o que está na coluna do Id que está sendo referenciado
            //pelo cursor, pega retorna em tipo Long
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno); //Adiciona o aluno com todas as informações resgatadas para a Lista de Alunos.

        }

        c.close(); //Libera o cursor

        return alunos; //Retorna a Lista de alunos
    }

    public void deleta(Aluno aluno) { //Metodo para deletar o aluno

        SQLiteDatabase db = getWritableDatabase(); //

        String[] params = {aluno.getId().toString()}; //Array de strings que representa o que a interrogação será substituida

        db.delete("Alunos", "id = ?", params); //Chama o metodo delete, chama a tabela, e diz quais ids serão deletados

    }

    public void altera(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoAluno(aluno);
        String[] params = {aluno.getId().toString()};

        db.update("Alunos", dados, "id = ?", params);

    }
}
