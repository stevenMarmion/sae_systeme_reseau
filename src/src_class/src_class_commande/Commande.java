package src_class.src_class_commande;

public interface Commande {
    public String getNom();

    public String getCode();

    public String agis(String param, String username);
}