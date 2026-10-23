package subnautica.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemForm {
    @NotBlank(message = "Informe o nome do item.")
    @Size(max = 100, message = "O nome deve ter até 100 caracteres.")
    private String nome;
    @NotBlank(message = "Informe o ícone do item.")
    @Size(max = 12, message = "O ícone deve ter até 12 caracteres.")
    private String icone;
    @NotBlank(message = "Informe uma descrição.")
    @Size(max = 500, message = "A descrição deve ter até 500 caracteres.")
    private String descricao;
    @NotBlank(message = "Informe a receita ou forma de obtenção.")
    @Size(max = 500, message = "A receita deve ter até 500 caracteres.")
    private String receita;
    @NotBlank(message = "Informe onde encontrar o item.")
    @Size(max = 300, message = "A localização deve ter até 300 caracteres.")
    private String localizacao;
    @NotBlank(message = "Informe como desbloquear o item.")
    @Size(max = 300, message = "O desbloqueio deve ter até 300 caracteres.")
    private String desbloqueio;
    @NotNull(message = "Selecione uma categoria.")
    private Long categoriaId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getIcone() { return icone; }
    public void setIcone(String icone) { this.icone = icone; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getReceita() { return receita; }
    public void setReceita(String receita) { this.receita = receita; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getDesbloqueio() { return desbloqueio; }
    public void setDesbloqueio(String desbloqueio) { this.desbloqueio = desbloqueio; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
