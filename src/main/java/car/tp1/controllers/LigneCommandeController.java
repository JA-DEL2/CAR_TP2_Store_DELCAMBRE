package car.tp1.controllers;

import car.tp1.models.Commande;
import car.tp1.models.LigneCommande;
import car.tp1.services.CommandeService;
import car.tp1.services.LigneCommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class LigneCommandeController {

    private LigneCommandeService ligneCommandeService;
    private CommandeService commandeService;

    public LigneCommandeController(LigneCommandeService ligneCommandeService, CommandeService commandeService) {
        this.ligneCommandeService = ligneCommandeService;
        this.commandeService = commandeService;
    }

    @GetMapping("/store/commande/lignes")
    public ModelAndView getLignesCommande(HttpSession session, @RequestParam String commandeId) {

        String clientEmail = (String) session.getAttribute("clientEmail");
        if(clientEmail == null)
            return new ModelAndView("home");

        List<LigneCommande> lignes = this.commandeService.getLignesCommandeByCommandeId(commandeId);
        Optional<Commande> commande = this.commandeService.getCommandeById(commandeId);

        ModelAndView model = new ModelAndView("commandeDetail");
        model.addObject("lignes",lignes);
        model.addObject("commandeName",commande.get().getNom());
        model.addObject("commandeId",commandeId);
        return model;
    }

    @PostMapping("/store/commande/lignes")
    public RedirectView createLigne(HttpSession session, @RequestParam String libelle, @RequestParam int quantite, @RequestParam float prixU,
                                      @RequestParam String commandeId) {

        String clientEmail = (String) session.getAttribute("clientEmail");
        if(clientEmail == null)
            return new RedirectView("/store/home");

        LigneCommande ligne = this.ligneCommandeService.createLigneCommande(libelle,quantite,prixU);
        this.commandeService.addLigneCommande(ligne,commandeId);

        StringBuilder url = new StringBuilder("/store/commande/lignes");
        url.append("?commandeId=").append(commandeId);

        return new RedirectView(url.toString());
    }

    @PostMapping("/store/commande/lignes/delete")
    public RedirectView deleteLigne(HttpSession session, @RequestParam String ligneId, @RequestParam String commandeId) {
        String clientEmail = (String) session.getAttribute("clientEmail");
        if(clientEmail == null)
            return new RedirectView("/store/home");

        this.ligneCommandeService.deleteLigneCommande(ligneId,commandeId);

        StringBuilder url = new StringBuilder("/store/commande/lignes");
        url.append("?commandeId=").append(commandeId);

        return new RedirectView(url.toString());
    }
}
