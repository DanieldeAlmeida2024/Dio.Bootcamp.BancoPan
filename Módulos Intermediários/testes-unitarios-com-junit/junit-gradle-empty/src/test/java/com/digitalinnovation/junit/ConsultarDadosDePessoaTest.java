package com.digitalinnovation.junit;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class ConsultarDadosDePessoaTest {
    //Executa uma vez antes de todos os testes existentes na classe. No inicio
    @BeforeAll
    static void configurarConexao(){
        BancoDeDados.iniciarConexao();
        System.out.println("rodou configuraConexao");
    }

    //Executa antes de cada teste existente na classe
    @BeforeEach
    void insereDadosParaTeste(){
        BancoDeDados.insereDados(new Pessoa("Joao", LocalDate.of(2000,1,1)));
    }
    //Executa depois de cada teste existente na classe
    @AfterEach
    void removeDadosDoTeste(){
        BancoDeDados.removeDados(new Pessoa("Joao", LocalDate.of(2000,1,1)));
    }


    @Test
    void validarDadosDeRetorno(){
        Assertions.assertTrue(true);
    }

    @Test
    void validarDadosDeRetorno2(){
        Assertions.assertNull(null);
    }

    //Executa depois de todos os testes existentes na classe. Uma vez
    @AfterAll
    static void finalizarConexao(){
        BancoDeDados.finalizarConexao();
        System.out.println("rodou finalizarConexao");
    }
}
