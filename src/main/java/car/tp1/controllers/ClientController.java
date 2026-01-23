package car.tp1.controllers;

import car.tp1.models.Client;
import car.tp1.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/store/home")
    public String home() {
        return "home";
    }

    @PostMapping("/store/client/create")
    public RedirectView createAuteur(@RequestParam String email, @RequestParam String mdp, @RequestParam String nom, @RequestParam String prenom) {
        Client client = this.clientService.createClient(email,nom,prenom,mdp);
        if(client == null)
            return new RedirectView("/store/home");
        RedirectView dashboard = new RedirectView("/store/dashboard");
        dashboard.addStaticAttribute("clientEmail", client.getEmail());
        return dashboard;
    }

    @PostMapping("/store/client/login")
    public RedirectView createAuteur(@RequestParam String email, @RequestParam String mdp) {
        Client client = this.clientService.loginClient(email,mdp);
        if(client == null)
            return new RedirectView("/store/home");

        RedirectView dashboard = new RedirectView("/store/dashboard");
        dashboard.addStaticAttribute("clientEmail", client.getEmail());
        return dashboard;
    }

    @GetMapping("/store/client/logout")
    public RedirectView logout() {
        return new RedirectView("/store/home");
    }
}
