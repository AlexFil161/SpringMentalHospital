package com.example.springmentalhospital.dao;

import com.example.springmentalhospital.model.Patient;
import com.example.springmentalhospital.model.Status;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientDao {

    private final JdbcTemplate jdbcTemplate;

    public PatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Patient> indexPatient() {
        return jdbcTemplate.query("SELECT * FROM Patient", new BeanPropertyRowMapper<>(Patient.class));
    }

    public Patient showPatient(int id) {
        return jdbcTemplate.query("SELECT * FROM Patient WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Patient.class)).stream().findAny().orElse(null);
    }

    public void savePatient(Patient patient) {
        jdbcTemplate.update("INSERT INTO Patient(name, age, diagnosis, status_id) VALUES(?, ?, ?, ?)",
                patient.getName(), patient.getAge(), patient.getDiagnosis(), patient.getStatus_id());
    }

    public void updatePatient( int id, Patient patient) {
        jdbcTemplate.update("UPDATE Patient SET name=?, age=?, diagnosis=? WHERE id=?",
                patient.getName(), patient.getAge(), patient.getDiagnosis(), id);
    }

    public void deletePatient(int id) {
        jdbcTemplate.update("DELETE FROM Patient WHERE id=?", id);
    }

    public Status getDiagnosisByPatient(int id) {
        return jdbcTemplate.query("SELECT S.statusname FROM Patient P JOIN Status S ON (P.status_id=S.id) WHERE P.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Status.class)).stream().findAny().orElse(null);
    }

    public void assign(int id, Status selectedStatus) {
        jdbcTemplate.update("UPDATE Patient SET status_id=? WHERE  id=?", selectedStatus.getId(), id);
    }
}
