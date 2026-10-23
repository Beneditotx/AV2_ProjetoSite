package subnautica.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import subnautica.service.ItemDuplicadoException;
import subnautica.service.ItemForm;
import subnautica.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) { this.itemService = itemService; }

    @GetMapping
    public String listar(@RequestParam(required = false) String q,
                         @RequestParam(required = false) String categoria, Model model) {
        model.addAttribute("itens", itemService.pesquisar(q, categoria));
        model.addAttribute("categorias", itemService.listarCategorias());
        model.addAttribute("busca", q == null ? "" : q);
        model.addAttribute("categoriaSelecionada", categoria == null ? "" : categoria);
        return "items";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        prepararFormulario(model, "Novo item", "/items", false);
        return "item-form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("itemForm") ItemForm form, BindingResult result,
                        Model model, RedirectAttributes redirect) {
        validarDuplicidade(form, result, null);
        if (result.hasErrors()) {
            prepararFormulario(model, "Novo item", "/items", false);
            return "item-form";
        }
        var item = itemService.criar(form);
        redirect.addFlashAttribute("mensagem", "Item cadastrado com sucesso.");
        return "redirect:/items/" + item.getId();
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.buscar(id));
        return "item-detail";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("itemForm", itemService.formulario(id));
        model.addAttribute("idItem", id);
        prepararFormulario(model, "Editar item", "/items/" + id + "/editar", true);
        return "item-form";
    }

    @PostMapping("/{id}/editar")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("itemForm") ItemForm form,
                            BindingResult result, Model model, RedirectAttributes redirect) {
        validarDuplicidade(form, result, id);
        if (result.hasErrors()) {
            prepararFormulario(model, "Editar item", "/items/" + id + "/editar", true);
            model.addAttribute("idItem", id);
            return "item-form";
        }
        itemService.atualizar(id, form);
        redirect.addFlashAttribute("mensagem", "Item atualizado com sucesso.");
        return "redirect:/items/" + id;
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        itemService.excluir(id);
        redirect.addFlashAttribute("mensagem", "Item excluído com sucesso.");
        return "redirect:/items";
    }

    private void validarDuplicidade(ItemForm form, BindingResult result, Long id) {
        if (form.getCategoriaId() != null && !itemService.categoriaExiste(form.getCategoriaId())) {
            result.rejectValue("categoriaId", "categoria.invalida", "Selecione uma categoria válida.");
        }
        if (!result.hasFieldErrors("nome") && form.getCategoriaId() != null) {
            try {
                itemService.verificarDuplicidade(form, id);
            } catch (ItemDuplicadoException exception) {
                result.rejectValue("nome", "item.duplicado", exception.getMessage());
            }
        }
    }

    private void prepararFormulario(Model model, String titulo, String acao, boolean edicao) {
        model.addAttribute("categorias", itemService.listarCategorias());
        model.addAttribute("tituloFormulario", titulo);
        model.addAttribute("acaoFormulario", acao);
        model.addAttribute("edicao", edicao);
    }
}
