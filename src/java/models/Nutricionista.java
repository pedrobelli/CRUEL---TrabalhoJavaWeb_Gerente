package models;

import java.io.Serializable;

public class Nutricionista implements Serializable {
    private int    id;
    private String nome;
    private String cpf;
    private String crn;
    private String email;
    private String senha;
    private int    tipoUsuario;
    //Endereço
    private String zipCode;
    private int    estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numeroEndereco;
    private String complemento;
    //Telefone
    private int    codigoAreaTelefone;
    private String numeroTelefone;
    private int    codigoAreaCelular;
    private String numeroCelular;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCrn() {
        return crn;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getCodigoAreaTelefone() {
        return codigoAreaTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public int getCodigoAreaCelular() {
        return codigoAreaCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }
    
}
