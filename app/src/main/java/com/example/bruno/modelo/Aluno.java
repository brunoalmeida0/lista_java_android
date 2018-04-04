package com.example.bruno.modelo;

import java.io.Serializable;

public class Aluno implements Serializable { //Criação da classe Aluno

    private Long id; //Id do aluno
    private String nome; //Nome do aluno
    private String endereco; //Endereço do aluno
    private String telefone; //Telefone do aluno
    private String site; //Site do aluno
    private double nota; //Nota do aluno

    public Long getId() { //Retorna o Id do aluno
        return id;
    }

    public void setId(Long id) { //Seta o Id do aluno
        this.id = id;
    }

    public String getNome() { //Retorna o Nome do aluno
        return nome;
    }

    public void setNome(String nome) { //Seta o Nome do aluno
        this.nome = nome;
    }

    public String getEndereco() { //Retorna o Endereço do aluno
        return endereco;
    }

    public void setEndereco(String endereco) { //Seta o endereço do aluno
        this.endereco = endereco;
    }

    public String getTelefone() { //Retorna o telefone do aluno
        return telefone;
    }

    public void setTelefone(String telefone) { //Seta o endereço do aluno
        this.telefone = telefone;
    }

    public String getSite() { //Retorna o Site do aluno
        return site;
    }

    public void setSite(String site) { //Seta o site do aluno
        this.site = site;
    }

    public double getNota() { //retorna a nota do aluno
        return nota;
    }

    public void setNota(double nota) { //Seta a nota do aluno
        this.nota = nota;
    }

    @Override
    public String toString() { //Reescreve o método toString para escrever o que desejar quando ele for chamado (ListActivity)
        return getId() + " - " + getNome();
    }
}
