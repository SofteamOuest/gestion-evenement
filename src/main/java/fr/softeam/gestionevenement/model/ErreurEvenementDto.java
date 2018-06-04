package fr.softeam.gestionevenement.model;

public class ErreurEvenementDto {
    private String code;
    private String messageErreur;

    public ErreurEvenementDto() {
    }

    public ErreurEvenementDto(String code, String messageErreur) {
        this.code = code;
        this.messageErreur = messageErreur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessageErreur() {
        return messageErreur;
    }

    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }
}
