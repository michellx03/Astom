package modelo;

public class PedidoFinanciado {
    private Long id_pedido;
    private int id_cliente;
    private int id_funcionario;
    private int id_veiculo;
    private Double valor_financiado;
    private Double total_financiado;
    private int parcelas;
    private String vendedor_financiado;
    private int comissao_financiado;
    private int quantidade_veiculo;
    private String mes_venda_financiada;
    
    //GETTER AND SETTERS
    public int getQuantidade_veiculo() {
        return quantidade_veiculo;
    }

    public void setQuantidade_veiculo(int quantidade_veiculo) {
        this.quantidade_veiculo = quantidade_veiculo;
    }

    public String getMes_venda_financiada() {
        return mes_venda_financiada;
    }
    
    public void setMes_venda_financiada(String mes_venda_financiada) {    
        this.mes_venda_financiada = mes_venda_financiada;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public Double getValor_financiado() {
        return valor_financiado;
    }

    public void setValor_financiado(Double valor_financiado) {
        this.valor_financiado = valor_financiado;
    }

    public Double getTotal_financiado() {
        return total_financiado;
    }

    public void setTotal_financiado(Double total_financiado) {
        this.total_financiado = total_financiado;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getVendedor_financiado() {
        return vendedor_financiado;
    }

    public void setVendedor_financiado(String vendedor_financiado) {
        this.vendedor_financiado = vendedor_financiado;
    }

    public int getComissao_financiado() {
        return comissao_financiado;
    }

    public void setComissao_financiado(int comissao_financiado) {
        this.comissao_financiado = comissao_financiado;
    }
}
