package src_class.src_class_modele;
import java.sql.Date;
import java.time.LocalDate;

public class Message {

    private String contenu;

    private String nomExpediteur;

    private java.sql.Date date;

    private int nombreLike;

    private int identifiantPost;

    public Message(String contenu, String nomExpediteur, int nombreLike){
        this.contenu = contenu;
        this.nomExpediteur = nomExpediteur;
        this.date = java.sql.Date.valueOf(LocalDate.now());
        this.nombreLike = nombreLike;
    }

    public Message(String contenu, String nomExpediteur, int nombreDeLike, Date date) {
        this.contenu = contenu;
        this.nomExpediteur = nomExpediteur;
        this.date = date;
        this.nombreLike = nombreDeLike;
    }

    // getteur et setteur de la classe message
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getNomExpediteur() {
        return nomExpediteur;
    }

    public void setNomExpediteur(String nomExpediteur) {
        this.nomExpediteur = nomExpediteur;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date2) {
        this.date = date2;
    }

    public int getNombreLike() {
        return nombreLike;
    }

    public void setNombreLike(int nombreLike) {
        this.nombreLike = nombreLike;
    }

    public int getId() {
        return identifiantPost;
    }

    public void setId(int identifiantPost) {
        this.identifiantPost = identifiantPost;
    }

    // méthode métier
    /**
     * cet methode permet d'incrementer de 1 le nombre de like
     */
    public void incrementLike(){
        this.nombreLike++;
    }
    /**
     * cet methode permet d'obtenir le message sous forme de String recevable par le serveur (avec la syntaxe correcte)
     * @return  le message recevable par le serveur
     */
    public String ecritureMessage(){
        String data="message : \n contenu : '"+this.contenu+"' \n expediteur : '"+this.nomExpediteur+"' \n date : '"+this.date+"' \n nombreLike : '"+this.nombreLike+"' \n id : '"+this.identifiantPost+"'";
        return data;
    }

    @Override
    public String toString(){
        return "message : \n contenu : '"+this.contenu+"' \n expediteur : '"+this.nomExpediteur+"' \n date : '"+this.date+"' \n nombreLike : '"+this.nombreLike+"' \n id : '"+this.identifiantPost+"'";
    }
}
