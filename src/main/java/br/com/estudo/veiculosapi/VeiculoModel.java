package br.com.estudo.veiculosapi;

import java.util.*;
import java.math.BigDecimal;
public interface VeiculoModel {
    Veiculo criar(Veiculo entrada);
    List<Veiculo> listar(String marca, String placa);
    Optional<Veiculo> buscar(Long id);
    Optional<Veiculo> atualizar(Long id, Veiculo entrada);
    boolean excluir(Long id);
}
