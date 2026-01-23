package car.tp1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private String id;

    private String nom;

    @OneToMany
    private List<LigneCommande> lignesCommandes;

    public Commande(String nom) {
        this.nom = nom;
    }

    public Commande() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<LigneCommande> getLignesCommandes() {
        return lignesCommandes;
    }

    public void setLignesCommandes(List<LigneCommande> lignesCommandes) {
        this.lignesCommandes = lignesCommandes;
    }

    public void addLigneCommande(LigneCommande ligneCommande) {
        this.lignesCommandes.add(ligneCommande);
    }
}
