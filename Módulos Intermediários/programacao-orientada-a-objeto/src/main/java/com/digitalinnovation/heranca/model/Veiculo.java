package com.digitalinnovation.heranca.model;

public abstract class Veiculo {
    private int qtdRodas;
    private String cambio;
    private int potencia;

    public void andarParaFrente(){
        System.out.println("Andando para frente !");
    };
    public void darRe(){
        System.out.println("Andando de ré !");
    };
    public void virarDireita(){
        System.out.println("Virando a direita !");
    };
    public void virarEsquerda(){
        System.out.println("Virando a esquerda !");
    };
}
