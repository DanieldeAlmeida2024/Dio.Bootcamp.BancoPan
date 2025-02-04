package com.digitalinnovation.model;

import java.util.Scanner;

public class CalculadoraDeMedidas {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String[] alunos = {"Camila", "Lucas", "Bruna", "Pedro"};

        double media = calculaMediaTurma(alunos, scan);

        System.out.printf("Média da turma %.1f", media);

    }

    private static double calculaMediaTurma(String[] alunos, Scanner scanner){
        double soma = 0;
        for(String aluno: alunos){
            System.out.printf("Nota do aluno %s:", aluno);
            int nota = scanner.nextInt();
            soma += nota;
        }
        return soma/alunos.length;
    }
}
