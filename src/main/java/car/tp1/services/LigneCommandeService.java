package car.tp1.services;

import car.tp1.models.LigneCommande;
import car.tp1.repositories.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
public class LigneCommandeService {

    private LigneCommandeRepository ligneCommandeRepository;
    private CommandeService commandeService;

    @Autowired
    public LigneCommandeService(LigneCommandeRepository ligneCommandeRepository, CommandeService commandeService) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.commandeService = commandeService;
    }

    public LigneCommande createLigneCommande(String libelle, int quantite, float prixU) {
        return this.ligneCommandeRepository.save(new LigneCommande(libelle,quantite,prixU));
    }

    public void deleteLigneCommande(String id, String commandeId) {
        Optional<LigneCommande> ligneCommande = this.ligneCommandeRepository.findById(id);
        System.out.println("here we are");
        ligneCommande.ifPresent(ligne -> {
            this.commandeService.detachLigneCommande(ligne, commandeId);
            this.ligneCommandeRepository.delete(ligne);
        });
    }

}
