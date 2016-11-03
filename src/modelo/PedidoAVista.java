package modelo;

public class PedidoAVista {
    private Long id_pedido;
    private int id_cliente;
    private int id_funcionario;
    private int id_veiculo;
    private Double valor_avista;
    private Double desconto;
    private String vendedor_avista;
    private int comissao_vendedor_avista;
    private int quantidade_veiculo;
    private String mes_venda_avista;

    //GETTER AND SETTERS
    public int getQuantidade_veiculo() {
        return quantidade_veiculo;
    }

    public void setQuantidade_veiculo(int quantidade_veiculo) {
        this.quantidade_veiculo = quantidade_veiculo;
    }

    public String getMes_venda_avista() {
        return mes_venda_avista;
    }

    public void setMes_venda_avista(String mes_venda_avista) {
        this.mes_venda_avista = mes_venda_avista;
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

    public Double getValor_avista() {
        return valor_avista;
    }

    public void setValor_avista(Double valor_avista) {
        this.valor_avista = valor_avista;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getVendedor_avista() {
        return vendedor_avista;
    }

    public void setVendedor_avista(String vendedor_avista) {
        this.vendedor_avista = vendedor_avista;
    }

    public int getComissao_vendedor_avista() {
        return comissao_vendedor_avista;
    }

    public void setComissao_vendedor_avista(int comissao_vendedor_avista) {
        this.comissao_vendedor_avista = comissao_vendedor_avista;
    }       
}
