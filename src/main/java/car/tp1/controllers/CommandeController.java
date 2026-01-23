package car.tp1.controllers;

import car.tp1.models.Commande;
import car.tp1.services.ClientService;
import car.tp1.services.CommandeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CommandeController {

    private CommandeService commandeService;
    private ClientService clientService;

    public CommandeController(CommandeService commandeService, ClientService clientService) {
        this.commandeService = commandeService;
        this.clientService = clientService;
    }

    @GetMapping("/store/dashboard")
    public ModelAndView getCommandes(@RequestParam String clientEmail) {
        List<Commande> commandes = this.clientService.getCommandesByClientId(clientEmail);

        ModelAndView model = new ModelAndView("dashboard");
        model.addObject("commandes",commandes);
        model.addObject("clientEmail",clientEmail);
        return model;
    }

    @PostMapping("/store/commande/create")
    public RedirectView createCommand(@RequestParam String nom, @RequestParam String clientEmail) {
        Commande commande = this.commandeService.createCommande(nom);
        this.clientService.addCommande(commande,clientEmail);

        StringBuilder url = new StringBuilder("/store/dashboard");
        url.append("?clientEmail=").append(clientEmail);

        return new RedirectView(url.toString());
    }
}
