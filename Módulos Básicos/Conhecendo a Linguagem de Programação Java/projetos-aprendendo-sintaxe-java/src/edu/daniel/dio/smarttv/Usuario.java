package edu.daniel.dio.smarttv;

public class Usuario {
    public static void main(String[] args) {
        SmartTv smartTv = new SmartTv();

        System.out.println("TV Ligada ? "+smartTv.ligada);
        System.out.println("Canal atual ? : "+smartTv.canal);
        System.out.println("Volume atual ? : "+smartTv.volume);

        smartTv.ligar();
        System.out.println("TV Ligada ? "+smartTv.ligada);

        
    }
}
