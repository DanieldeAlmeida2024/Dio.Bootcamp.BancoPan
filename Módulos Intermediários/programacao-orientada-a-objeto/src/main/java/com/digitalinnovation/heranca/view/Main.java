package com.digitalinnovation.heranca.view;
import com.digitalinnovation.heranca.model.Carro;

public class Main {
    public static void main(String[] args){
        Carro carro1 = new Carro();
        carro1.setCor("Vermelho");
        carro1.setModelo("BMW Serie 3");
        carro1.setCapacidadeTanque(55);
        carro1.andarParaFrente();

        System.out.println(carro1.getModelo());
        System.out.println(carro1.getCor());
        System.out.println("Capacidade do tanque: "+carro1.getCapacidadeTanque()+"L");
        System.out.println("Preço para encher o tanque: R$ "+carro1.totalValorTanque(6.44));

    }
}
