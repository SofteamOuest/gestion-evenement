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
            evenementDto.setDateEvenement(resultSet.getString("date_evenement"));
            evenementDto.setDateValidation(resultSet.getString("date_validation"));
            evenementDto.setType(resultSet.getString("type"));
            evenementDto.setCycle(resultSet.getBoolean("cycle"));
            evenementDto.setValeurRecurrence(resultSet.getInt("valeur_recurrence"));
            evenementDto.setTypeRecurrence(resultSet.getString("type_recurrence"));
            return evenementDto;
        });
    }

    public List<EvenementDto> getEvenementsAfterDate(String dateLimite) {
        String sql = "select * from evenement where to_date(date_evenement,'DD/MM/YYYY') >= to_date(?,'YYYYMMDD')";
        return jdbcTemplate.query(sql,new Object[]{dateLimite}, new BeanPropertyRowMapper<>(EvenementDto.class));
    }

    public EvenementDto creerEvenement(EvenementDto evenementDto) {
        String sql = "insert into evenement (nom,description,date_evenement,date_validation,type,cycle,valeur_recurrence,type_recurrence) values (?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, evenementDto.getNom());
            ps.setObject(2, evenementDto.getDescription());
            ps.setObject(3, evenementDto.getDateEvenement());
            ps.setObject(4, evenementDto.getDateValidation() );
            ps.setObject(5, evenementDto.getType());
            ps.setObject(6, evenementDto.getCycle());
            ps.setObject(7, evenementDto.getValeurRecurrence());
            ps.setObject(8, evenementDto.getTypeRecurrence());
            return ps;
        }, keyHolder);
        evenementDto.setIdEvenement((Integer) keyHolder.getKeys().get("id_evenement"));
        return evenementDto;
    }

    public EvenementDto modifierEvenement(int idEvenement, EvenementDto evenementDto) {
        String sql = "update evenement set nom = ?, description = ?, date_evenement = ?, date_validation = ?, type = ?, cycle = ?, valeur_recurrence = ?, type_recurrence = ? where id_evenement = ?";
        jdbcTemplate.update(sql,
                evenementDto.getNom(),
                evenementDto.getDescription(),
                evenementDto.getDateEvenement(),
                evenementDto.getDateValidation(),
                evenementDto.getType(),
                evenementDto.getCycle(),
                evenementDto.getValeurRecurrence(),
                evenementDto.getTypeRecurrence(),
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
