package subnautica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "categoria_id"}))
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 12)
    private String icone;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 500)
    private String receita;

    @Column(nullable = false, length = 300)
    private String localizacao;

    @Column(nullable = false, length = 300)
    private String desbloqueio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Item() { }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getIcone() { return icone; }
    public String getDescricao() { return descricao; }
    public String getReceita() { return receita; }
    public String getLocalizacao() { return localizacao; }
    public String getDesbloqueio() { return desbloqueio; }
    public Categoria getCategoria() { return categoria; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIcone(String icone) { this.icone = icone; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setReceita(String receita) { this.receita = receita; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public void setDesbloqueio(String desbloqueio) { this.desbloqueio = desbloqueio; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
