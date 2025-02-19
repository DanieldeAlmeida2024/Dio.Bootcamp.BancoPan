package com.digitalinnovation.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    void validarCenarioDeExcecaoNaTransferencia(){
        Conta contaOrigem = new Conta(111,"corrente","Daniel");
        Conta contaDestino = new Conta(111,"corrente","Daniel");
        TransferenciaEntreContas transferencia = new TransferenciaEntreContas();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                transferencia.transfere(contaOrigem,contaDestino,-1));
    }
}
