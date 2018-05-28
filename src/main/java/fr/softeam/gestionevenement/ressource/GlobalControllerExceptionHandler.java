package fr.softeam.gestionevenement.ressource;

import fr.softeam.gestionevenement.exception.GestionEvenementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(GestionEvenementException.class)
    public String handleGestionEvenementException(GestionEvenementException exc){
        return exc.getMessage();
    }
}
