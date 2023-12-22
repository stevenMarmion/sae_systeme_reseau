package src_class.src_class_commande;

abstract class Commande {
    protected String nom;
    protected String code;

    public String getNom(){
        return this.nom;
    }

    public String getCode(){
        return this.code;
    }
}