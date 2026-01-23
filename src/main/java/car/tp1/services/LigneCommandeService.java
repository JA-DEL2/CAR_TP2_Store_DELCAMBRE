package car.tp1.services;

import car.tp1.models.LigneCommande;
import car.tp1.repositories.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LigneCommandeService {

    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    public LigneCommandeService(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    public LigneCommande createLigneCommande(String libelle, int quantite, float prixU) {
        return this.ligneCommandeRepository.save(new LigneCommande(libelle,quantite,prixU));
    }

    public void deleteLigneCommande(String id) {
        Optional<LigneCommande> ligneCommande = this.ligneCommandeRepository.findById(id);
        ligneCommande.ifPresent(commande -> this.ligneCommandeRepository.delete(commande));
    }

}
