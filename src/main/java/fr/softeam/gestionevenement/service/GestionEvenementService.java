package fr.softeam.gestionevenement.service;

import fr.softeam.gestionevenement.dao.EvenementDao;
import fr.softeam.gestionevenement.exception.GestionEvenementException;
import fr.softeam.gestionevenement.model.EvenementDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class GestionEvenementService {

    private EvenementDao evenementDao;

    public GestionEvenementService(EvenementDao evenementDao){
        this.evenementDao = evenementDao;
    }

    public List<EvenementDto> getAllEvent(){
        return evenementDao.loadAllEvenement();
    }

    public List<EvenementDto> getEvenementsAfterDate(String dateLimite){
        return evenementDao.getEvenementsAfterDate(dateLimite);
    }

    public EvenementDto creerEvenement(EvenementDto evenementDto) throws GestionEvenementException {
        controleDateFormat(evenementDto.getDateEvenement());
        controleDateEvenementDansLeFutur(evenementDto);
        controleCycle(evenementDto);
        return evenementDao.creerEvenement(evenementDto);
    }

    public EvenementDto recupererEvenement(int idEvenement){
        return evenementDao.findByIdEvenement(idEvenement);
    }

    public EvenementDto modifierEvenement(int idEvenement, EvenementDto evenementDto){
        return evenementDao.modifierEvenement(idEvenement,evenementDto);
    }

    public void supprimerEvenement(int idEvenement) throws GestionEvenementException {
        evenementDao.suppressionEvenementById(idEvenement);
    }

    private void controleDateEvenementDansLeFutur(EvenementDto evenementDto) throws GestionEvenementException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(evenementDto.getDateEvenement() == null  || !currentDate.isBefore(LocalDate.parse(evenementDto.getDateEvenement(),formatter))){
            throw new GestionEvenementException("La date d'évènement n'est pas dans le futur.");
        }
    }

    private void controleCycle(EvenementDto evenementDto) throws GestionEvenementException {
        if(evenementDto.getCycle() == null ||
                (evenementDto.getCycle() && ((evenementDto.getTypeRecurrence() == null) && (evenementDto.getValeurRecurrence() == null)))){
            throw new GestionEvenementException("Cycle présent mais les champs de type de récurrence ou valeur de récurrence vide.");
        }
    }

    private void controleDateFormat(String stringDate) throws GestionEvenementException {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(stringDate);
            if (!stringDate.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if(date == null){
            throw new GestionEvenementException("Format de la date doit respecter jj/mm/aaaa");
        }
    }
}
