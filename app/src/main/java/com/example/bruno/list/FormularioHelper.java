package com.example.bruno.list;
//Classe de apoio ao formulário que ira pegar todas as informações passadas por ele.
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.bruno.modelo.Aluno;

public class FormularioHelper { //Inicio do método

    private final EditText campoNome; //definição do atributo campoNome
    private final EditText campoEndereco; //definição do atributo campoEndereco
    private final EditText campoTelefone; //definição do atributo campoTelefone
    private final EditText campoSite; //definição do atributo campoSite
    private final RatingBar campoNota; //definição do atributo campoNota

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){ //Quando este atributo é chamado, a activity que está chamando é passada como parametro através do "this"
        campoNome = activity.findViewById(R.id.formularioNome); //Atribui ao campo nome o que está sendo adicionado no Id
        campoEndereco = activity.findViewById(R.id.formularioEndereco); //"
        campoTelefone = activity.findViewById(R.id.formularioTelefone);//"
        campoSite = activity.findViewById(R.id.formularioSite); //"
        campoNota = activity.findViewById(R.id.formularioNota); //"

        aluno = new Aluno();
    }

    public Aluno pegaAluno() { //Método para pegar os dados do Aluno

        aluno.setNome(campoNome.getText().toString()); //Atribui o campoNome na forma de texto convertido em tipo String para o setNome
        aluno.setEndereco(campoEndereco.getText().toString()); //Atribui o campoEndereco na forma de texto convertido em tipo String para o setEndereco
        aluno.setTelefone(campoTelefone.getText().toString()); //Atribui o campoTelefone na forma de texto convertido em tipo String para o setTelefone
        aluno.setSite(campoSite.getText().toString()); //Atribui o campoSite na forma de texto convertido em tipo String para o setSite
        aluno.setNota(Double.valueOf(campoNota.getProgress())); //Atribui o campoNota na forma de texto convertido em tipo String para o setNota

        return aluno; //Retorna o aluno com todas as informações setadas.
    }


    public void preencheFormulario(Aluno aluno) {

        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(((int) aluno.getNota()));
        
        this.aluno = aluno;

    }
}
