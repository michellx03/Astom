package modelo;

public class Cliente {
    
    private Long id_cliente;
    private String nome_cliente;
    private String cpf_cliente;
    private String data_nascimento_cliente;
    private String sexo_cliente;
    private String endereco_cliente;
    private String bairro_cliente;
    private String complemento_cliente;
    private String telefone_residencial_cliente;
    private String telefone_celular_cliente;
    private int numero_casa_cliente;
    private String cidade;
    
    //GETTERS AND SETTERS
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getData_nascimento_cliente() {
        return data_nascimento_cliente;
    }
    
    public void setData_nascimento_cliente(String data_nascimento_cliente) {    
        this.data_nascimento_cliente = data_nascimento_cliente;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getSexo_cliente() {
        return sexo_cliente;
    }

    public void setSexo_cliente(String sexo_cliente) {
        this.sexo_cliente = sexo_cliente;
    }

    public String getEndereco_cliente() {
        return endereco_cliente;
    }

    public void setEndereco_cliente(String endereco_cliente) {
        this.endereco_cliente = endereco_cliente;
    }

    public String getBairro_cliente() {
        return bairro_cliente;
    }

    public void setBairro_cliente(String bairro_cliente) {
        this.bairro_cliente = bairro_cliente;
    }

    public String getComplemento_cliente() {
        return complemento_cliente;
    }

    public void setComplemento_cliente(String complemento_cliente) {
        this.complemento_cliente = complemento_cliente;
    }

    public String getTelefone_residencial_cliente() {
        return telefone_residencial_cliente;
    }

    public void setTelefone_residencial_cliente(String telefone_residencial_cliente) {
        this.telefone_residencial_cliente = telefone_residencial_cliente;
    }

    public String getTelefone_celular_cliente() {
        return telefone_celular_cliente;
    }

    public void setTelefone_celular_cliente(String telefone_celular_cliente) {
        this.telefone_celular_cliente = telefone_celular_cliente;
    }

    public int getNumero_casa_cliente() {
        return numero_casa_cliente;
    }

    public void setNumero_casa_cliente(int numero_casa_cliente) {
        this.numero_casa_cliente = numero_casa_cliente;
    }       
}
