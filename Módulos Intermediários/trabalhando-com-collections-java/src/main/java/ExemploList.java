import java.util.*;

public class ExemploList {
    public static void main(String[] args){
        //Dada uma lista com 7 notas de um aluno [7, 8.5, 9.3, 5, 7, 0, 3.6], faça:

        //List notas = new Arraylist();
        //List<Double> notas = new ArrayList<Double>();  //Generics(jdk 5) - Diamond Operator
        //ArrayList<Double> notas = new ArrayList<>():
        //List<Double> notas = new ArrayList<>(Array.asList(7d, 8.5, 9.3, 5d, 7d, 0d, 3.6));
        /*List<Double> notas = Array.asList(7d, 8.5, 9.3, 5d, 7d, 0d, 3.6);
        notas.add(10d);
        System.out.println(notas);
        */
        /*
        List<Double> notas = List.of(7d, 8.5, 9.3, 5d, 7d, 0d, 3.6);
        notas.add(1d); //não funciona pois a lista é imutavel
        notas.remove(5d);//não funciona
        System.out.println(notas);
         */
        System.out.println("Crie uma lista e adicione as sete notas: ");
        List<Double> notas = new ArrayList<>();
        notas.add(7d);
        notas.add(8.5);
        notas.add(9.3);
        notas.add(5d);
        notas.add(7d);
        notas.add(0d);
        notas.add(3.6);
        System.out.println(notas.toString());

        System.out.println("Exiba a posição da nota 5.0: "+ notas.indexOf(5d));
        notas.add(4,8d);
        System.out.println(notas);

        System.out.println("Substitua a nota 5.0 pela  nota 6.0: ");
        notas.set(notas.indexOf(5d),6d);
        System.out.println(notas);

        System.out.println("Confira se a nota 5.0 está na lista: " +notas.contains(5d));

        System.out.println("Exiba todas as notas na ordem em que foram informadas: ");
        for(Double nota:notas) System.out.println(nota);

        System.out.println("Exiba a terceira nota adicionada: "+ notas.get(2));

        System.out.println("Exiba a menor nota: "+ Collections.min(notas));
        System.out.println("Exiba a maior nota: "+ Collections.max(notas));

        System.out.print("Exiba a  soma dos valores: ");
        Iterator<Double> iterator = notas.iterator();
        Double soma = 0d;
        while(iterator.hasNext()){
            soma += iterator.next();
        }
        System.out.print(soma);

        System.out.println("Exiba a média das notas: "+ (soma/notas.size()));
        System.out.println("Remova a nota 0");
        notas.remove(notas.indexOf(0d));
        System.out.println(notas.toString());
    }
}
