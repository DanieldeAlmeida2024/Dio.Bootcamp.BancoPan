package com.digitalinnovation.heranca.model;



public class Carro extends Veiculo {
    String cor;
    String modelo;
    int capacidadeTanque;

    public Carro() {
    }

    public Carro(String cor, int capacidadeTanque, String modelo) {
        this.cor = cor;
        this.capacidadeTanque = capacidadeTanque;
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public void setCapacidadeTanque(int capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    public double totalValorTanque(double valorCombustivel){
        if(capacidadeTanque != 0) {
            return capacidadeTanque * valorCombustivel;
        }
        return 0;
    }
}
