package edu.daniel.dio.smarttv;

public class SmartTv {
    boolean ligada = false;
    int canal = 1;
    int volume = 25;

    public void ligar(){
        this.ligada = true;
    }

    public void desligar(){
        this.ligada = false;
    }

    public void aumentarVolume(){
        this.volume++;
        System.out.println("Volume aumentado para: "+this.volume);
    }

    public void diminuirVolume(){
        this.volume--;
        System.out.println("Volume diminuido para: "+this.volume);
    }

    public void aumentarCanal(){
        this.canal++;
        System.out.println("Canal atual : "+this.canal);
    }

    public void diminuirCanal(){
        this.canal--;
        System.out.println("Canal atual : "+this.canal);
    }

    public void mudarCanal(int novoCanal){
        this.canal = novoCanal;
        System.out.println("Canal atual : "+this.canal);
    }

    @Override
    public String toString() {
        return "SmartTv{" +
                "ligada=" + ligada +
                ", canal=" + canal +
                ", volume=" + volume +
                '}';
    }
}
