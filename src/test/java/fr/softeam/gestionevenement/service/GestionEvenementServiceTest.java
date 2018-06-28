package fr.softeam.gestionevenement.service;

import fr.softeam.gestionevenement.dao.EvenementDao;
import fr.softeam.gestionevenement.exception.GestionEvenementException;
import fr.softeam.gestionevenement.model.EvenementDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestionEvenementServiceTest {

    @Mock
    private EvenementDao evenementDao;

    @InjectMocks
    private GestionEvenementService gestionEvenementService;

    @Test
    public void given_cycle_true_et_valeurRecurrence_vide_when_create_then_throw_exception() throws GestionEvenementException, ParseException {

        // given
        EvenementDto evenementDto = new EvenementDto();
        evenementDto.setIdEvenement(2);
        evenementDto.setNom("Anniversaire");
        String date = "02/10/2018";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        evenementDto.setDateEvenement(date);
        evenementDto.setCycle(true);
        Mockito.when(evenementDao.creerEvenement(Mockito.any())).thenReturn(evenementDto);

        // when
        final Throwable thrown = catchThrowable(() -> gestionEvenementService.creerEvenement(evenementDto));

        // then
        assertThat(thrown).isInstanceOf(GestionEvenementException.class);
        final GestionEvenementException gee = (GestionEvenementException) thrown;
        assertThat(gee.getErreurEvenementDto().getMessageErreur()).isEqualTo("Cycle présent mais les champs de type de récurrence ou valeur de récurrence sont vides.");
        assertThat(gee).isNotNull();
    }

    /*
    @Test
    public void given_dateEvenement_pas_dans_le_futur_when_create_then_throw_exception() throws GestionEvenementException, ParseException {
        //given
        EvenementDto evenementDto = new EvenementDto();
        evenementDto.setIdEvenement(2);
        evenementDto.setNom("Anniversaire");
        String date = "01/01/2018";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        evenementDto.setDateEvenement(date);
        Mockito.when(evenementDao.creerEvenement(Mockito.any())).thenReturn(evenementDto);

        //when
        final Throwable thrown = catchThrowable(() -> gestionEvenementService.creerEvenement(evenementDto));

        //then
        assertThat(thrown).isInstanceOf(GestionEvenementException.class);
        final GestionEvenementException gee = (GestionEvenementException) thrown;
        assertThat(gee.getMessage()).isEqualTo("La date d'évènement n'est pas dans le futur.");
        assertThat(gee).isNotNull();
    }
    */
}
