package src_exception;

public class ExceptionUnfoundMessage extends Exception{
    public ExceptionUnfoundMessage(String idMessage){
        System.out.println("Le message identifiant "+idMessage+" n'a pas été trouver");
    }
    
}
