package tiposCliente;

import java.io.Serializable;

public class TipoCliente implements Serializable {
    private int    id;
    private String nome;
    private float  valorRefeicao;

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

    public float getValorRefeicao() {
        return valorRefeicao;
    }

    public void setValorRefeicao(float valorRefeicao) {
        this.valorRefeicao = valorRefeicao;
    }
}
