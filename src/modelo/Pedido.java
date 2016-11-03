package modelo;

public class Pedido {
    private Long id_pedido;
    private int id_cliente;
    private int id_funcionario;
    private int id_veiculo;
    private Double valor_avista;
    private Double desconto;
    private String vendedor_avista;
    private Double valor_financiado;
    private Double total_financiado;
    private String parcelas;
    private String vendedor_financiado;

    //GETTER AND SETTERS
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

    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }

    public String getVendedor_financiado() {
        return vendedor_financiado;
    }

    public void setVendedor_financiado(String vendedor_financiado) {
        this.vendedor_financiado = vendedor_financiado;
    }
    
        
}
