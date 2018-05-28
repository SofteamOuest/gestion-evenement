package fr.softeam.gestionevenement.aspect;

import fr.softeam.gestionevenement.model.EvenementDto;
import fr.softeam.gestionevenement.service.GestionEvenementService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class GestionEvenementServiceAspect {

    private GestionEvenementService gestionEvenementService;

    public GestionEvenementServiceAspect(GestionEvenementService gestionEvenementService) {
        this.gestionEvenementService = gestionEvenementService;
    }

    static final Logger LOG = LoggerFactory.getLogger(GestionEvenementService.class);

    @AfterReturning(pointcut = "bean(*Service)", returning = "result")
    public void logAfter(JoinPoint joinPoint){
        LOG.info(joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(value = "execution(* fr.softeam.gestionevenement.service.GestionEvenementService.*(..))",throwing ="e")
    public void logAfterThrowingDowload(JoinPoint thisJoinPoint, Throwable e) {
        LOG.info("Exception : " + e.getMessage());
    }

}
