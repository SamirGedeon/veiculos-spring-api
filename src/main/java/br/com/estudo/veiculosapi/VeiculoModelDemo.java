package br.com.estudo.veiculosapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("demo")
public class VeiculoModelDemo extends ArmazenamentoEmMemoria { public VeiculoModelDemo() { criar(new Veiculo(null, "Chevrolet", "Vectra", 1997, "ABC1D23")); } }
