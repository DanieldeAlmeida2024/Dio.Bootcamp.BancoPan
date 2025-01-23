package com.digitalinnovation;
import java.util.HashMap;


public class Emprestimo {
    public static void calcularEmprestimo(double valor, int parcelas){
        HashMap<String, Double> taxas = new HashMap<String, Double>();
        taxas.put("1x",30.0);
        taxas.put("2x",55.0);
        taxas.put("3x",70.0);

        if(parcelas == 1){
            double valorTaxa = valor * (taxas.get("1x"))/100;
            System.out.println("O valor final do emprestimo é de: R$ "+(valor+valorTaxa)+". Valor da Taxa: R$ "+valorTaxa);
        }else if(parcelas == 2){
            double valorTaxa = valor * (taxas.get("2x")/100);
            System.out.println("O valor final do emprestimo é de: R$ "+(valor+valorTaxa)+". Valor da Taxa: R$ "+valorTaxa);
        }else if(parcelas == 3){
            double valorTaxa = valor * (taxas.get("3x")/100);
            System.out.println("O valor final do emprestimo é de: R$ "+(valor+valorTaxa)+". Valor da Taxa: R$ "+valorTaxa);

        }else{
            System.out.println("Só é permitido parcelar até 3 vezes!");
        }
    }
}
