package library.domain.entidades;

public class Livro {
    private int id;
    private int id_autor;
    private int id_categoria;
    private int id_estudante;
    private int id_volume;
    private String nome;
    private String descricao;
    private String nacionalidade;
    private int numero_edicao;
    private String estante;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_estudante() {
        return id_estudante;
    }

    public void setId_estudante(int id_estudante) {
        this.id_estudante = id_estudante;
    }

    public int getId_volume() {
        return id_volume;
    }

    public void setId_volume(int id_volume) {
        this.id_volume = id_volume;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getNumero_edicao() {
        return numero_edicao;
    }

    public void setNumero_edicao(int numero_edicao) {
        this.numero_edicao = numero_edicao;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

}
