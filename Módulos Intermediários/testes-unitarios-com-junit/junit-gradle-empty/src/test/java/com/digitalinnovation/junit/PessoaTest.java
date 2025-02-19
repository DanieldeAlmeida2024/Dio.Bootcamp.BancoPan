package com.digitalinnovation.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PessoaTest {
    @Test
    void deveValidarCalculoIdade(){
        Pessoa pessoa = new Pessoa("Julia", LocalDate.of(2011,10,1));
        Assertions.assertEquals(13, pessoa.getIdade());
    }

    @Test
    void deveRetornarSeEhMaiorDeIdade(){
        Pessoa jessica = new Pessoa("Jessica", LocalDate.of(2005, 1,1));
        Assertions.assertTrue(jessica.verificaSeEhMaiorDeIdade());
    }
}
