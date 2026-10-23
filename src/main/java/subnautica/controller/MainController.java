package subnautica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import subnautica.service.ItemService;

@Controller
public class MainController {
    private final ItemService itemService;

    public MainController(ItemService itemService) { this.itemService = itemService; }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalItens", itemService.contar());
        return "index";
    }

    @GetMapping("/mapa")
    public String mapa() { return "interative_map"; }

    @GetMapping("/criaturas")
    public String criaturas() { return "criaturas"; }
}
