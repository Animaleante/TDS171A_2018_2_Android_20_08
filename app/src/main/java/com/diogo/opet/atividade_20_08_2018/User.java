package com.diogo.opet.atividade_20_08_2018;

import java.util.Date;

/**
 * Created by opet on 27/08/2018.
 */

public class User {
    private String login;
    private String nome;
    private String nascimento;
    private String graduacao;
    private long ultimoLogin;

    public User() {}

    public User(String login, String nome, String nascimento, String graduacao) {
        this.login = login;
        this.nome = nome;
        this.nascimento = nascimento;
        this.graduacao = graduacao;
        ultimoLogin = new Date().getTime();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getGraduacao() { return graduacao; }

    public void setGraduacao(String graduacao) { this.graduacao = graduacao; }

    public long getUltimoLogin() { return ultimoLogin; }

    public void setUltimoLogin(long ultimoLogin) { this.ultimoLogin = ultimoLogin; }
}
