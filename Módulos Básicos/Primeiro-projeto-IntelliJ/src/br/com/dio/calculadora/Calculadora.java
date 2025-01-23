package br.com.dio.calculadora;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        double a = scan.nextDouble();
        System.out.println("Digite o segundo numero: ");
        double b = scan.nextDouble();

        double soma = somar(a,b);
        double subtracao = subtrair(a,b);
        double divisao = dividir(a,b);
        double multiplicacao = multiplicar(a,b);

        System.out.println("Soma: "+soma);
        System.out.println("Subtracao: "+subtracao);
        System.out.println("Divisao: "+divisao);
        System.out.println("Multiplicacao: "+multiplicacao);

    }

    public static double somar(double a, double b){
        return a+b;
    }

    public static double subtrair(double a, double b){
        return a-b;
    }

    public static double multiplicar(double a, double b){
        return a*b;
    }

    public static double dividir(double a, double b){
        return a/b;
    }

}
