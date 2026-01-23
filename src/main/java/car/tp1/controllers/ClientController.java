package car.tp1.controllers;

import car.tp1.models.Client;
import car.tp1.services.ClientService;
import jakarta.servlet.http.HttpSession;
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
    public RedirectView createClient(HttpSession session, @RequestParam String email, @RequestParam String mdp, @RequestParam String nom, @RequestParam String prenom) {
        Client client = this.clientService.createClient(email,nom,prenom,mdp);
        if(client == null)
            return new RedirectView("/store/home");

        session.setAttribute("clientEmail",client.getEmail());

        return new RedirectView("/store/dashboard");
    }

    @PostMapping("/store/client/login")
    public RedirectView createAuteur(HttpSession session, @RequestParam String email, @RequestParam String mdp) {
        Client client = this.clientService.loginClient(email,mdp);
        if(client == null)
            return new RedirectView("/store/home");

        session.setAttribute("clientEmail",client.getEmail());

        return new RedirectView("/store/dashboard");
    }

    @GetMapping("/store/client/logout")
    public RedirectView logout(HttpSession session) {
        session.setAttribute("clientEmail",null);
        session.invalidate();
        return new RedirectView("/store/home");
    }
}
