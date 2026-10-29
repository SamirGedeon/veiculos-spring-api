package br.com.estudo.veiculosapi;

import java.math.BigDecimal;

public class Veiculo {
    private final Long id;
    private final String marca;
    private final String modelo;
    private final Integer ano;
    private final String placa;
    public Veiculo(Long id, String marca, String modelo, Integer ano, String placa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }
    public Long getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAno() { return ano; }
    public String getPlaca() { return placa; }
}
