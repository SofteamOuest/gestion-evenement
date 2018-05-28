package fr.softeam.gestionevenement.dao;

import fr.softeam.gestionevenement.exception.GestionEvenementException;
import fr.softeam.gestionevenement.model.EvenementDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class EvenementDao {

    private JdbcTemplate jdbcTemplate;

    public EvenementDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EvenementDto> loadAllEvenement() {
        String sql = "select * from evenement";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            EvenementDto evenementDto = new EvenementDto();
            evenementDto.setIdEvenement(resultSet.getInt("id_evenement"));
            evenementDto.setNom(resultSet.getString("nom"));
            evenementDto.setDescription(resultSet.getString("description"));
            evenementDto.setDateEvenement(resultSet.getDate("date_evenement"));
            evenementDto.setDateValidation(resultSet.getDate("date_validation"));
            evenementDto.setType(resultSet.getString("type"));
            evenementDto.setCycle(resultSet.getBoolean("cycle"));
            evenementDto.setValeurRecurrence(resultSet.getInt("valeur_reccurence"));
            evenementDto.setTypeRecurrence(resultSet.getString("type_reccurence"));
            evenementDto.setIdAuteur(resultSet.getString("id_auteur"));
            return evenementDto;
        });
    }

    public EvenementDto creerEvenement(EvenementDto evenementDto) {
        String sql = "insert into evenement (nom,description,date_evenement,date_validation,type,cycle,valeur_reccurence,type_reccurence,id_auteur) values (?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, evenementDto.getNom());
            ps.setString(2, evenementDto.getDescription());
            java.sql.Date dateEvenementSQL = null;
            if(evenementDto.getDateEvenement() != null){
                dateEvenementSQL = new java.sql.Date(evenementDto.getDateEvenement().getTime());
            }
            ps.setDate(3, dateEvenementSQL);
            java.sql.Date dateValidationSQL = null;
            if(evenementDto.getDateValidation() != null){
                dateValidationSQL = new java.sql.Date(evenementDto.getDateValidation().getTime());
            }
            ps.setDate(4, dateValidationSQL);
            ps.setString(5, evenementDto.getType());
            ps.setBoolean(6,evenementDto.getCycle());
            ps.setInt(7, evenementDto.getValeurRecurrence());
            ps.setString(8, evenementDto.getTypeRecurrence());
            ps.setString(9, evenementDto.getIdAuteur());
            return ps;
        }, keyHolder);
        evenementDto.setIdEvenement((Integer) keyHolder.getKeys().get("id_evenement"));
        return evenementDto;
    }

    public EvenementDto modifierEvenement(int idEvenement, EvenementDto evenementDto) {
        String sql = "update evenement set nom = ?, description = ?, date_evenement = ?, date_validation = ?, type = ?, cycle = ?, valeur_reccurence = ?, type_reccurence = ?, id_auteur = ? where id_evenement = ?";
        jdbcTemplate.update(sql,
                evenementDto.getNom(),
                evenementDto.getDescription(),
                evenementDto.getDateEvenement(),
                evenementDto.getDateValidation(),
                evenementDto.getType(),
                evenementDto.getCycle(),
                evenementDto.getValeurRecurrence(),
                evenementDto.getTypeRecurrence(),
                evenementDto.getIdAuteur(),
                idEvenement);
        evenementDto.setIdEvenement(idEvenement);
        return evenementDto;
    }

    public EvenementDto findByIdEvenement(int idEvenement){
        String sql = "select * from evenement where id_evenement = ?";
        return jdbcTemplate.queryForObject(
                sql, new Object[]{idEvenement},
                new BeanPropertyRowMapper<>(EvenementDto.class));
    }

    public void suppressionEvenementById(int idEvenement) throws GestionEvenementException {
        String sql = "delete from evenement where id_evenement = ?";
        if(jdbcTemplate.update(sql, idEvenement) == 0){
            throw new GestionEvenementException("Impossible de supprimer l'evenement "+idEvenement);
        }
    }
}
