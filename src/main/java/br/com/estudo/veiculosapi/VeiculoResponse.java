package br.com.estudo.veiculosapi;

import java.math.BigDecimal;

public record VeiculoResponse(Long id, String marca, String modelo, Integer ano, String placa) {}
