package br.com.estudo.veiculosapi;

public final class VeiculoMapper {
    private VeiculoMapper() {}
    public static Veiculo toEntity(VeiculoRequest r, Long id) { return new Veiculo(id, r.marca().strip(), r.modelo().strip(), r.ano(), r.placa().strip()); }
    public static VeiculoResponse toResponse(Veiculo item) { return new VeiculoResponse(item.getId(), item.getMarca(), item.getModelo(), item.getAno(), item.getPlaca()); }
}
