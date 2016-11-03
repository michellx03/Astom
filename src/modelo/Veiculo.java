package modelo;

public class Veiculo {
    private Long id_veiculo;
    private String modelo_veiculo;
    private String cor_veiculo;
    private int ano_veiculo;
    private String placa_veiculo;
    private String estado_veiculo;
    private String marca_veiculo;
    private String observacoes_veiculo;
    private double valor_minimo_veiculo;
    private double valor_medio_veiculo;
    private double valor_maximo_veiculo;
    private int quantidade_veiculo;
    private String tipo_veiculo;
    
    public String getTipo_veiculo() {
        return tipo_veiculo;
    }

    //GETTER AND SETTER
    public void setTipo_veiculo(String tipo_veiculo) {    
        this.tipo_veiculo = tipo_veiculo;
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getCor_veiculo() {
        return cor_veiculo;
    }

    public void setCor_veiculo(String cor_veiculo) {
        this.cor_veiculo = cor_veiculo;
    }

    public int getAno_veiculo() {
        return ano_veiculo;
    }

    public void setAno_veiculo(int ano_veiculo) {
        this.ano_veiculo = ano_veiculo;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }

    public String getEstado_veiculo() {
        return estado_veiculo;
    }

    public void setEstado_veiculo(String estado_veiculo) {
        this.estado_veiculo = estado_veiculo;
    }

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public String getObservacoes_veiculo() {
        return observacoes_veiculo;
    }

    public void setObservacoes_veiculo(String observacao_veiculo) {
        this.observacoes_veiculo = observacao_veiculo;
    }

    public double getValor_minimo_veiculo() {
        return valor_minimo_veiculo;
    }

    public void setValor_minimo_veiculo(double valor_minimo_veiculo) {
        this.valor_minimo_veiculo = valor_minimo_veiculo;
    }

    public double getValor_medio_veiculo() {
        return valor_medio_veiculo;
    }

    public void setValor_medio_veiculo(double valor_medio_veiculo) {
        this.valor_medio_veiculo = valor_medio_veiculo;
    }

    public double getValor_maximo_veiculo() {
        return valor_maximo_veiculo;
    }

    public void setValor_maximo_veiculo(double valor_maximo_veiculo) {
        this.valor_maximo_veiculo = valor_maximo_veiculo;
    }

    public int getQuantidade_veiculo() {
        return quantidade_veiculo;
    }

    public void setQuantidade_veiculo(int quantidade_veiculo) {
        this.quantidade_veiculo = quantidade_veiculo;
    }
    
}
