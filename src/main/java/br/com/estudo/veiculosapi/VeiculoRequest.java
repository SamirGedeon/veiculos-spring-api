package br.com.estudo.veiculosapi;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

// DTO de entrada; ID é gerado no servidor.
public record VeiculoRequest(
    @NotBlank(message="marca é obrigatório") @Size(max=120) String marca,
    @NotBlank(message="modelo é obrigatório") @Size(max=120) String modelo,
    @NotNull @Min(1888) @Max(2100) Integer ano,
    @NotBlank(message="placa é obrigatório") @Size(max=120) @Pattern(regexp="(?i)[A-Z]{3}[0-9][A-Z0-9][0-9]{2}", message="placa inválida: use ABC1234 ou ABC1D23") String placa
) {}
