package car.tp1.services;

import car.tp1.models.Client;
import car.tp1.models.Commande;
import car.tp1.repositories.ClientRepository;
import car.tp1.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {

    private CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public Commande createCommande(String nom) {
        return this.commandeRepository.save(new Commande(nom));
    }

}
