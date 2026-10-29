package br.com.estudo.veiculosapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("memoria")
public class VeiculoModelMemoria extends ArmazenamentoEmMemoria {  }
