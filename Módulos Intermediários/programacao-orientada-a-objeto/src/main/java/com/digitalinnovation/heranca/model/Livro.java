package com.digitalinnovation.heranca.model;

public class Livro {
    private int qtdPaginas;
    private String editora;
    private String autor;
    private String titulo;
    private String assunto;
    private int isbn;

    public Livro(){}

    public Livro(int qtdPaginas, String editora, String autor, String titulo, String assunto, int isbn) {
        this.qtdPaginas = qtdPaginas;
        this.editora = editora;
        this.autor = autor;
        this.titulo = titulo;
        this.assunto = assunto;
        this.isbn = isbn;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
