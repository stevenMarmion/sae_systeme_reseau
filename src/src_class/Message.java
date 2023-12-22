package src_class;
import java.util.Date;

public class Message {

    private String contenu;

    private String nomExpediteur;

    private Date date;

    private int nombreLike;

    private int identifiantPost;

    public Message(String contenu,String nomExpediteur,int nombreLike){
        this.contenu=contenu;
        this.date=new Date();
        this.nombreLike=nombreLike;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    }
