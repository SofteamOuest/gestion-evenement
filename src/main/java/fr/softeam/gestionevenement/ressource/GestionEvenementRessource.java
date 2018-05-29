package fr.softeam.gestionevenement.ressource;

import fr.softeam.gestionevenement.exception.GestionEvenementException;
import fr.softeam.gestionevenement.model.EvenementDto;
import fr.softeam.gestionevenement.service.GestionEvenementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GestionEvenementRessource {


    private final GestionEvenementService gestionEvenementService;

    static final Logger LOG = LoggerFactory.getLogger(GestionEvenementService.class);

    public GestionEvenementRessource(GestionEvenementService gestionEvenementService) {
        this.gestionEvenementService = gestionEvenementService;
    }

    @GetMapping(path = "/evenement")
    public List<EvenementDto> getAllEvenements() {
        return gestionEvenementService.getAllEvent();
    }

    @GetMapping(path = "/evenement/limite")
    public List<EvenementDto> getEvenementsAfterDate(@RequestParam("limite") String dateLimite) {
        return gestionEvenementService.getEvenementsAfterDate(dateLimite);
    }

    @PostMapping(path = "/evenement")
    @ResponseStatus(HttpStatus.CREATED)
    public EvenementDto ajouterEvenement(@RequestBody @Valid EvenementDto evenementDto) throws GestionEvenementException {
        return gestionEvenementService.creerEvenement(evenementDto);
    }

    @GetMapping(path = "/evenement/{idEvenement}")
    public EvenementDto getEvenementById(@PathVariable Integer idEvenement) throws GestionEvenementException {
        return gestionEvenementService.recupererEvenement(idEvenement);
    }

    @PutMapping(path = "/evenement/{idEvenement}")
    public EvenementDto updateEvenement(@PathVariable Integer idEvenement,
                                        @RequestBody @Valid EvenementDto evenementDto) throws GestionEvenementException {
        return gestionEvenementService.modifierEvenement(idEvenement, evenementDto);
    }

    @DeleteMapping(path = "/evenement/{idEvenement}")
    public ResponseEntity<?> deleteEvenement(@PathVariable Integer idEvenement) throws GestionEvenementException {
        gestionEvenementService.supprimerEvenement(idEvenement);
        return null;
    }
}
