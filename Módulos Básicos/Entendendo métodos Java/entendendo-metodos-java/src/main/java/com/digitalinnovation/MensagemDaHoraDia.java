package com.digitalinnovation;

public class MensagemDaHoraDia {
    public static void mensagemHora(int hora){
        switch (hora){
            case 5:
                System.out.println("Bom dia !");
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                System.out.println("Boa tarde !");
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                System.out.println("Boa noite !");
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                System.out.println("Hora inválida !");
                break;
        }
    }
}
