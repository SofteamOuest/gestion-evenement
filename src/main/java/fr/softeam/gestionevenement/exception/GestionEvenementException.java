package fr.softeam.gestionevenement.exception;

import fr.softeam.gestionevenement.model.ErreurEvenementDto;

public class GestionEvenementException extends Exception{

    private ErreurEvenementDto erreurEvenementDto;

    public GestionEvenementException(ErreurEvenementDto erreurEvenementDto){
        this.erreurEvenementDto = erreurEvenementDto;
    }

    public GestionEvenementException(String message){
        this.erreurEvenementDto = new ErreurEvenementDto("000",message);
    }

    public ErreurEvenementDto getErreurEvenementDto() {
        return erreurEvenementDto;
    }

    public void setErreurEvenementDto(ErreurEvenementDto erreurEvenementDto) {
        this.erreurEvenementDto = erreurEvenementDto;
    }
}
