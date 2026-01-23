package models;

public class Operation {

    private String date;
    private TypeOperation type;
    private double montant;
    private Categorie categorie;
    private String libelle;

    public Operation() {}

    public Operation(String date, TypeOperation type, double montant, Categorie categorie, String libelle) {
        this.date = date;
        this.type = type;
        this.montant = montant;
        this.categorie = categorie;
        this.libelle = libelle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TypeOperation getType() {
        return type;
    }

    public void setType(TypeOperation type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
