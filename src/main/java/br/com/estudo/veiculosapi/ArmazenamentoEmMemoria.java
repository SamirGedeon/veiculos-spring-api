package br.com.estudo.veiculosapi;

import java.util.*;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class ArmazenamentoEmMemoria implements VeiculoModel {
    private final Map<Long, Veiculo> itens = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();
    public synchronized Veiculo criar(Veiculo entrada) {
        Long id = sequencia.incrementAndGet();
        Veiculo criado = new Veiculo(id, entrada.getMarca(), entrada.getModelo(), entrada.getAno(), entrada.getPlaca());
        itens.put(id, criado);
        return criado;
    }
    public synchronized List<Veiculo> listar(String marca, String placa) {
        
        return itens.values().stream()
                        .filter(item -> marca == null || marca.isBlank() || item.getMarca().equalsIgnoreCase(marca.strip()))
            .filter(item -> placa == null || placa.isBlank() || item.getPlaca().equalsIgnoreCase(placa.strip()))
            .toList();
    }
    public synchronized Optional<Veiculo> buscar(Long id) { return Optional.ofNullable(itens.get(id)); }
    public synchronized Optional<Veiculo> atualizar(Long id, Veiculo entrada) {
        if (!itens.containsKey(id)) return Optional.empty();
        Veiculo atualizado = new Veiculo(id, entrada.getMarca(), entrada.getModelo(), entrada.getAno(), entrada.getPlaca());
        itens.put(id, atualizado);
        return Optional.of(atualizado);
    }
    public synchronized boolean excluir(Long id) { return itens.remove(id) != null; }
}
