package subnautica.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subnautica.model.Categoria;
import subnautica.model.Item;
import subnautica.repository.CategoriaRepository;
import subnautica.repository.ItemRepository;

import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoriaRepository categoriaRepository;

    public ItemService(ItemRepository itemRepository, CategoriaRepository categoriaRepository) {
        this.itemRepository = itemRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Item> listar(String categoria) {
        return categoria == null || categoria.isBlank()
                ? itemRepository.findAllByOrderByNomeAsc()
                : itemRepository.findByCategoria_CodigoOrderByNomeAsc(categoria);
    }

    @Transactional(readOnly = true)
    public List<Item> pesquisar(String termo, String categoria) {
        String filtro = termo == null ? "" : termo.trim().toLowerCase(java.util.Locale.ROOT);
        return listar(categoria).stream().filter(item -> filtro.isEmpty()
                || (item.getNome() + " " + item.getDescricao() + " " + item.getReceita() + " " + item.getLocalizacao())
                .toLowerCase(java.util.Locale.ROOT).contains(filtro)).toList();
    }

    @Transactional(readOnly = true)
    public long contar() { return itemRepository.count(); }

    @Transactional(readOnly = true)
    public List<Categoria> listarCategorias() { return categoriaRepository.findAllByOrderByNomeAsc(); }

    @Transactional(readOnly = true)
    public boolean categoriaExiste(Long id) { return id != null && categoriaRepository.existsById(id); }

    @Transactional(readOnly = true)
    public Item buscar(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public ItemForm formulario(Long id) {
        Item item = buscar(id);
        ItemForm form = new ItemForm();
        form.setNome(item.getNome()); form.setIcone(item.getIcone()); form.setDescricao(item.getDescricao());
        form.setReceita(item.getReceita()); form.setLocalizacao(item.getLocalizacao());
        form.setDesbloqueio(item.getDesbloqueio()); form.setCategoriaId(item.getCategoria().getId());
        return form;
    }

    public Item criar(ItemForm form) {
        verificarDuplicidade(form, null);
        return itemRepository.save(preencher(new Item(), form));
    }

    public Item atualizar(Long id, ItemForm form) {
        verificarDuplicidade(form, id);
        return itemRepository.save(preencher(buscar(id), form));
    }

    public void excluir(Long id) { itemRepository.delete(buscar(id)); }

    private Item preencher(Item item, ItemForm form) {
        Categoria categoria = categoriaRepository.findById(form.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("A categoria selecionada não existe."));
        item.setNome(form.getNome().trim()); item.setIcone(form.getIcone().trim());
        item.setDescricao(form.getDescricao().trim()); item.setReceita(form.getReceita().trim());
        item.setLocalizacao(form.getLocalizacao().trim()); item.setDesbloqueio(form.getDesbloqueio().trim());
        item.setCategoria(categoria);
        return item;
    }

    public void verificarDuplicidade(ItemForm form, Long id) {
        boolean duplicado = id == null
                ? itemRepository.existsByNomeIgnoreCaseAndCategoria_Id(form.getNome().trim(), form.getCategoriaId())
                : itemRepository.existsByNomeIgnoreCaseAndCategoria_IdAndIdNot(form.getNome().trim(), form.getCategoriaId(), id);
        if (duplicado) throw new ItemDuplicadoException("Já existe um item com esse nome nesta categoria.");
    }
}
