package com.digitalinnovation.junit;

public class Conta {
    private int numeroConta;
    private String tipoConta;
    private String nomeCliente;
    private int saldo;

    public int getSaldo() {
        return saldo;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Conta(int numeroConta, String tipoConta, String nomeCliente) {
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
        this.nomeCliente = nomeCliente;
        this.saldo = 0;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
}
