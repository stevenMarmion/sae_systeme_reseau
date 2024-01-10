package src_class.src_class_commande;

public class CommandeRemove implements Commande {
    private String nom;
    private String code;
    
    public CommandeRemove(String nom, String code){
        this.nom=nom;
        this.code=code;
    }

    @Override
    public String getNom() {
      return this.nom;
    }

    @Override
    public String getCode() {
      return this.code;
    }
    
}
