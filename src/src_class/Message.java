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

    public int getIdentifiantPost() {
        return identifiantPost;
    }

    public void setIdentifiantPost(int identifiantPost) {
        this.identifiantPost = identifiantPost;
    }

    public String ecritureMessage(){
        String data="message : \n contenu : '"+this.contenu+"' \n expediteur : '"+this.nomExpediteur+"' \n date : '"+this.date+"' \n nombreLike : '"+this.nombreLike+"' \n id : '"+this.identifiantPost+"'";
        return data;
    }
    }
