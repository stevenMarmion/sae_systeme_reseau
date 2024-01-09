package src_class.src_class_modele;

import java.sql.Date;

/**
 * La classe Message représente un message avec des attributs tels que l'identifiant du message,
 * son contenu, le nombre de likes et la date de création.
 */
public class Message {
    private int idMessage;          // L'identifiant du message
    private String contenu;         // Le contenu du message
    private int nombreDeLike;       // Le nombre de likes du message
    private Date date;              // La date de création du message

    /**
     * Constructeur de la classe Message pour initialiser les attributs.
     * 
     * @param idMessage     L'identifiant du message
     * @param contenu       Le contenu du message
     * @param nombreDeLike  Le nombre de likes du message
     * @param date          La date de création du message
     */
    public Message(int idMessage, String contenu, int nombreDeLike, Date date) {
        this.idMessage = idMessage;
        this.contenu = contenu;
        this.nombreDeLike = nombreDeLike;
        this.date = date;
    }

    /**
     * Obtient l'identifiant du message.
     * 
     * @return L'identifiant du message
     */
    public int getIdMessage() {
        return idMessage;
    }

    /**
     * Obtient le contenu du message.
     * 
     * @return Le contenu du message
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Obtient le nombre de likes du message.
     * 
     * @return Le nombre de likes du message
     */
    public int getNombreDeLike() {
        return nombreDeLike;
    }

    /**
     * Obtient la date de création du message.
     * 
     * @return La date de création du message
     */
    public Date getDate() {
        return date;
    }

    /**
     * Définit l'identifiant du message.
     * 
     * @param idMessage Le nouvel identifiant du message
     */
    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    /**
     * Définit le contenu du message.
     * 
     * @param contenu Le nouveau contenu du message
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * Définit le nombre de likes du message.
     * 
     * @param nombreDeLike Le nouveau nombre de likes du message
     */
    public void setNombreDeLike(int nombreDeLike) {
        this.nombreDeLike = nombreDeLike;
    }

    /**
     * Définit la date de création du message.
     * 
     * @param date La nouvelle date de création du message
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de l'objet Message.
     * 
     * @return Une représentation sous forme de chaîne de caractères de l'objet Message
     */
    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", contenu='" + contenu + '\'' +
                ", nombreDeLike=" + nombreDeLike +
                ", date=" + date +
                '}';
    }
}
