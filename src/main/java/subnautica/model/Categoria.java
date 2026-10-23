package subnautica.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String codigo;

    @Column(nullable = false, length = 80)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Item> itens = new ArrayList<>();

    protected Categoria() { }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public List<Item> getItens() { return itens; }
    public void setNome(String nome) { this.nome = nome; }
}
