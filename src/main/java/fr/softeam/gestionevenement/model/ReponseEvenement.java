package fr.softeam.gestionevenement.model;

public class ReponseEvenement {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReponseEvenement(String message) {
        this.message = message;
    }
}
