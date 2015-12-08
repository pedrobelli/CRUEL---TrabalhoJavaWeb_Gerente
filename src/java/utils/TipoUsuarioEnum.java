package utils;

public class TipoUsuarioEnum {
    public enum TipoUsuario {
        GERENTE("Gerente", 1),
        NUTRICIONISTA("Nutricionista", 2),
        ATENDENTE("Atendente", 3);
        
        private int cod;
        private String nome;

        TipoUsuario(String nome, int cod){
            this.nome = nome;
            this.cod = cod;
        }

        public int getCod(){
            return this.cod;
        }
        
        public String getNome(){
            return this.nome;
        }
    }
}
