package car.tp1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue
    private String id;

    private String libelle;
    private int quantite;
    private double prixU;

    public LigneCommande(String libelle, int quantite, double prixU) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.prixU = prixU;
    }

    public LigneCommande() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixU() {
        return prixU;
    }

    public void setPrixU(double prixU) {
        this.prixU = prixU;
    }
}
