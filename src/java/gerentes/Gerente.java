package gerentes;

import java.io.Serializable;

public class Gerente implements Serializable {
    private int    id;
    private String nome;
    private String cpf;
    //Endereço
    private String cep;
    private int    estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int    numeroEndereco;
    private String complemento;
    //Telefone
    private int    codigoAreaTelefone;
    private String numeroTelefone;
    private int    codigoAreaCelular;
    private String numeroCelular;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getFormatedCpf() {
        String formatedCpf = cpf.substring(0, 3) + ".";
        formatedCpf += cpf.substring(3, 6) + ".";
        formatedCpf += cpf.substring(6, 9) + "-";
        formatedCpf += cpf.substring(9);
        return formatedCpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getCodigoAreaTelefone() {
        return codigoAreaTelefone;
    }

    public void setCodigoAreaTelefone(int codigoAreaTelefone) {
        this.codigoAreaTelefone = codigoAreaTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }
    
    public String getTelefone() {
        String telefone = numeroTelefone;        
        if (telefone != null && telefone.length() > 7) {
            String codigoArea = String.valueOf(this.codigoAreaTelefone);
            return codigoArea + telefone;
        }
        return telefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public int getCodigoAreaCelular() {
        return codigoAreaCelular;
    }

    public void setCodigoAreaCelular(int codigoAreaCelular) {
        this.codigoAreaCelular = codigoAreaCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }
    
    public String getCelular() {
        String celular = numeroCelular;        
        if (celular != null && celular.length() > 7) {
            String codigoArea = String.valueOf(this.codigoAreaCelular);
            return codigoArea + celular;
        }
        return celular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
    
}
