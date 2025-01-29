/*
 * Dado as seguintes informações sobre as séries,
 * crie um conjunto e ordene esse conjunto exibindo:
 * (nome - genero - tempo de episodio)
 *
 * Serie 1: Nome: GoT, Genero: fantasia, tempoEpisodio: 60
 * Serie 2: Nome: Dark, Genero: drama, tempoEpisodio: 60
 * Serie 3: Nome: That 70's show, Genero: comedia, tempoEpisodio: 25
 */

import java.util.*;

public class ExemploOrdenacaoSet {
    public static void main(String[] args){
        System.out.println("*--\t Ordem Aleatória \t--*");
        Set<Serie> minhasSeries = new HashSet<>();
        minhasSeries.add(new Serie("GoT", "fantasia", 60));
        minhasSeries.add(new Serie("Dark", "drama", 60));
        minhasSeries.add(new Serie("That '70s show", "comedia", 25));

        for(Serie serie:minhasSeries)
            System.out.println(
                    serie.getNome() +
                    " - " +
                    serie.getGenero() +
                    " - " +
                    serie.getTempoEpisodio());

        System.out.println("*--\tOrdem de Inserção\t--*");
        Set<Serie> minhasSeries1 = new LinkedHashSet<>();
        minhasSeries1.add(new Serie("GoT", "fantasia", 60));
        minhasSeries1.add(new Serie("Dark", "drama", 60));
        minhasSeries1.add(new Serie("That '70s show", "comedia", 25));

        for(Serie serie:minhasSeries1)
            System.out.println(
                    serie.getNome() +
                            " - " +
                            serie.getGenero() +
                            " - " +
                            serie.getTempoEpisodio());

        System.out.println("*--\tOrdem Natural (TempoEpisodio)\t--*");
        Set<Serie> minhasSeries3 = new TreeSet<>(new CompareToNomeGeneroTempoEpisodio());
        minhasSeries3.addAll(minhasSeries);
        for(Serie serie:minhasSeries3)
            System.out.println(
                    serie.getNome() +
                            " - " +
                            serie.getGenero() +
                            " - " +
                            serie.getTempoEpisodio());
    }
}

class Serie implements Comparable<Serie>{
    private String nome;
    private String genero;
    private int tempoEpisodio;

    public Serie(String nome, String genero, int tempoEpisodio) {
        this.nome = nome;
        this.genero = genero;
        this.tempoEpisodio = tempoEpisodio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTempoEpisodio() {
        return tempoEpisodio;
    }

    public void setTempoEpisodio(int tempoEpisodio) {
        this.tempoEpisodio = tempoEpisodio;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", tempoEpisodio=" + tempoEpisodio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return tempoEpisodio == serie.tempoEpisodio && Objects.equals(nome, serie.nome) && Objects.equals(genero, serie.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero, tempoEpisodio);
    }

    @Override
    public int compareTo(Serie o) {
        int tempoEpisodio =  Integer.compare(this.getTempoEpisodio(), o.getTempoEpisodio());
        if(tempoEpisodio != 0) return tempoEpisodio;
        return this.getGenero().compareTo(o.getGenero());
    }
}

class CompareToNomeGeneroTempoEpisodio implements Comparator<Serie>{

    @Override
    public int compare(Serie o1, Serie o2) {
        int nome = o1.getNome().compareTo(o2.getNome());
        if(nome != 0) return nome;
        int genero = o1.getGenero().compareTo(o2.getGenero());
        if(genero != 0) return genero;
        return Integer.compare(o1.getTempoEpisodio(), o2.getTempoEpisodio());
    }
}
