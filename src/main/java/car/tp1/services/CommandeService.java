package car.tp1.services;

import car.tp1.models.Client;
import car.tp1.models.Commande;
import car.tp1.models.LigneCommande;
import car.tp1.repositories.ClientRepository;
import car.tp1.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public Optional<Commande> getCommandeById(String commandeId) {
        return this.commandeRepository.findById(commandeId);
    }

    public Commande createCommande(String nom) {
        return this.commandeRepository.save(new Commande(nom));
    }

    public void addLigneCommande(LigneCommande ligne, String idCommande) {
        Optional<Commande> commande = this.commandeRepository.findById(idCommande);
        commande.ifPresent(cmd -> {
            cmd.addLigneCommande(ligne);
            this.commandeRepository.save(cmd);
        });
    }

    public void detachLigneCommande(LigneCommande ligne, String idCommande) {
        Optional<Commande> commande = this.commandeRepository.findById(idCommande);
        commande.ifPresent(cmd -> {
            cmd.removeLigneCommande(ligne);
            this.commandeRepository.save(cmd);
        });
    }

    public List<LigneCommande> getLignesCommandeByCommandeId(String idCommande){
        Optional<Commande> commande = this.commandeRepository.findById(idCommande);
        if(commande.isPresent())
            return commande.get().getLignesCommandes();
        return new ArrayList<>();
    }

}
