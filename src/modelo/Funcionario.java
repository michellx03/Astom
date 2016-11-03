package modelo;

public class Funcionario {
    private Long id_funcionario;
    private String nome_funcionario;
    private String sobrenome_funcionario;
    private String data_nascimento_funcionario;
    private String endereco_funcionario;
    private String bairro_funcionario;
    private int numero_casa_funcinario;
    private String complemento_funcionario;
    private String cargo_funcionario;
    private double salario_funcionario;
    private String usuario;
    private String senha;

    //GETTER AND SETTER
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {    
        this.senha = senha;
    }

    public Long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getSobrenome_funcionario() {
        return sobrenome_funcionario;
    }

    public void setSobrenome_funcionario(String sobrenome_funcionario) {
        this.sobrenome_funcionario = sobrenome_funcionario;
    }

    public String getData_nascimento_funcionario() {
        return data_nascimento_funcionario;
    }

    public void setData_nascimento_funcionario(String data_nascimento_funcionario) {
        this.data_nascimento_funcionario = data_nascimento_funcionario;
    }

    public String getEndereco_funcionario() {
        return endereco_funcionario;
    }

    public void setEndereco_funcionario(String endereco_funcionario) {
        this.endereco_funcionario = endereco_funcionario;
    }

    public String getBairro_funcionario() {
        return bairro_funcionario;
    }

    public void setBairro_funcionario(String bairro_funcionario) {
        this.bairro_funcionario = bairro_funcionario;
    }

    public int getNumero_casa_funcinario() {
        return numero_casa_funcinario;
    }

    public void setNumero_casa_funcinario(int numero_casa_funcinario) {
        this.numero_casa_funcinario = numero_casa_funcinario;
    }

    public String getComplemento_funcionario() {
        return complemento_funcionario;
    }

    public void setComplemento_funcionario(String complemento_funcionario) {
        this.complemento_funcionario = complemento_funcionario;
    }

    public String getCargo_funcionario() {
        return cargo_funcionario;
    }

    public void setCargo_funcionario(String cargo_funcionario) {
        this.cargo_funcionario = cargo_funcionario;
    }

    public double getSalario_funcionario() {
        return salario_funcionario;
    }

    public void setSalario_funcionario(double salario_funcionario) {
        this.salario_funcionario = salario_funcionario;
    }    
}
