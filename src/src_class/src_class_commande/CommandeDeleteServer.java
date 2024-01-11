package src_class.src_class_commande;

public class CommandeDeleteServer implements Commande {
    private String nom;
    private String code;

    public CommandeDeleteServer(String nom, String code){
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

    @Override
    public String agis(String param, String username) {
      return null;
    }
}
