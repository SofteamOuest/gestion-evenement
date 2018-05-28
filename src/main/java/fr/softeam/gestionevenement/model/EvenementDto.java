package fr.softeam.gestionevenement.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class EvenementDto {

    private Integer idEvenement;

    @NotNull
    @Size(min=2,max=100)
    private String nom;

    @Size(min=0,max=200)
    private String description;

    @Size(min=0,max=100)
    private String idAuteur;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateEvenement;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateValidation;

    private String type;

    private Boolean cycle;

    private Integer valeurRecurrence;

    private String typeRecurrence;

    public EvenementDto() {
    }

    public Integer getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCycle() {
        return cycle;
    }

    public void setCycle(Boolean cycle) {
        this.cycle = cycle;
    }

    public Integer getValeurRecurrence() {
        return valeurRecurrence;
    }

    public void setValeurRecurrence(Integer valeurRecurrence) {
        this.valeurRecurrence = valeurRecurrence;
    }

    public String getTypeRecurrence() {
        return typeRecurrence;
    }

    public void setTypeRecurrence(String typeRecurrence) {
        this.typeRecurrence = typeRecurrence;
    }

    public String getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(String idAuteur) {
        this.idAuteur = idAuteur;
    }

    @Override
    public String toString() {
        return "EvenementDto{" +
                "idEvenement=" + idEvenement +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", idAuteur='" + idAuteur + '\'' +
                ", dateEvenement=" + dateEvenement +
                ", dateValidation=" + dateValidation +
                ", type='" + type + '\'' +
                ", cycle=" + cycle +
                ", valeurRecurrence=" + valeurRecurrence +
                ", typeRecurrence='" + typeRecurrence + '\'' +
                '}';
    }
}
