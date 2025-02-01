import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExemploStreamApi {
    public static void main(String[] args) {
        // Lista de números inteiros
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Exemplo 1: Filtrando números pares
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + evenNumbers);

        // Exemplo 2: Multiplicando cada número por 2 (map)
        List<Integer> doubledNumbers = numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("Números multiplicados por 2: " + doubledNumbers);

        // Exemplo 3: Encontrar o maior número
        Optional<Integer> maxNumber = numbers.stream()
                .max(Integer::compareTo);
        maxNumber.ifPresent(n -> System.out.println("Maior número: " + n));

        // Exemplo 4: Soma de todos os números (reduce)
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Soma dos números: " + sum);

        // Exemplo 5: Contar os números ímpares
        long oddCount = numbers.stream()
                .filter(n -> n % 2 != 0)
                .count();
        System.out.println("Quantidade de números ímpares: " + oddCount);
    }
}
