package utils;

public class EstadoEnum {
    public enum Estado {
        ACRE("Acre", 1),
        ALAGOAS("Alagoas", 2),
        AMAPA("Amapá", 3),
        AMAZONAS("Amazonas", 4),
        BAHIA("Bahia", 5),
        CEARA("Ceará", 6),
        DISTRITO_FEDERAL("Distrito Federal", 7),
        ESPIRITO_SANTO("Espírito Santo", 8),
        GOIAS("Goiás", 9),
        MARANHAO("Maranhão", 10),
        MATO_GROSSO("Mato Grosso", 11),
        MATO_GROSSO_SUL("Mato Grosso do Sul", 12),
        MINAS_GERAIS("Minas Gerais", 13),
        PARA("Pará", 14),
        PARAIBA("Paraíba", 15),
        PARANA("Paraná", 16),
        PERNAMBUCO("Pernambuco", 17),
        PIAUI("Piauí", 18),
        RIO_JANEIRO("Rio de Janeiro", 19),
        RIO_GRANDE_NORTE("Rio Grande do Norte", 20),
        RIO_GRANDE_SUL("Rio Grande do Sul", 21),
        RONDONIA("Rondônia", 22),
        RORAIMA("Roraima", 23),
        SANTA_CATARINA("Santa Catarina", 24),
        SAO_PAULO("São Paulo", 25),
        SERGIPE("Sergipe", 26),
        TOCANTINS("Tocantins", 27);
        
        private int cod;
        private String nome;

        Estado(String nome, int cod){
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
