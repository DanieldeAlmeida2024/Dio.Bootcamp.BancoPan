package dio.springboot;

import org.springframework.stereotype.Component;

@Component
public class Calculadora {
    // Método para somar dois números
    public double somar(double num1, double num2) {
        return num1 + num2;
    }

    // Método para subtrair dois números
    public double subtrair(double num1, double num2) {
        return num1 - num2;
    }

    // Método para multiplicar dois números
    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    // Método para dividir dois números
    public double dividir(double num1, double num2) throws ArithmeticException {
        if (num2 == 0) {
            throw new ArithmeticException("Não é possível dividir por zero");
        }
        return num1 / num2;
    }

}
