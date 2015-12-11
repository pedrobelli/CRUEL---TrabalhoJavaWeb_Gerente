package tiposCliente;

import java.io.Serializable;

public class TipoCliente implements Serializable {
    private int    id;
    private String nome;
    private double  valorRefeicao;

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

    public double getValorRefeicao() {
        return valorRefeicao;
    }

    public String getValorRefeicaoWithTwoDecimals() {
        String valorRefeicaoString = String.format("%.2f", this.valorRefeicao); 
        return valorRefeicaoString;
    }

    public void setValorRefeicao(double valorRefeicao) {
        this.valorRefeicao = valorRefeicao;
    }
}
