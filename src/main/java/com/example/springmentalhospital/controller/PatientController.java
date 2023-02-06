package com.example.springmentalhospital.controller;

import com.example.springmentalhospital.dao.PatientDao;
import com.example.springmentalhospital.dao.StatusDao;
import com.example.springmentalhospital.model.Patient;
import com.example.springmentalhospital.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PatientController {

    private final PatientDao patientDao;

    private final StatusDao statusDao;

    public PatientController(PatientDao patientDao, StatusDao statusDao) {
        this.patientDao = patientDao;
        this.statusDao = statusDao;
    }

    @GetMapping("/patient")
    public String indexPatient(Model model) {
        model.addAttribute("patients", patientDao.indexPatient());
        return "/index";
    }

    @GetMapping("/patient/{id}")
    public String showPatient(@PathVariable("id") int id, Model model, @ModelAttribute("status") Status status) {
        model.addAttribute("patient", patientDao.showPatient(id));
        model.addAttribute("status", patientDao.getDiagnosisByPatient(id));
        model.addAttribute("statusList", statusDao.indexStatus());
        return "/show";
    }

    @GetMapping("/patient/new")
    public String newPatient(Model model, @ModelAttribute("patient") Patient patient) {
        model.addAttribute("statusList", statusDao.indexStatus());
        return "/new";
    }

    @PostMapping("/patient")
    public String createPatient(@ModelAttribute("patient") Patient patient) {
        patientDao.savePatient(patient);
        return "redirect:/patient";
    }

    @GetMapping("/patient/{id}/edit")
    public String editPatient(@PathVariable("id") int id, Model model) {
        model.addAttribute("patient", patientDao.showPatient(id));
        return "/edit";
    }

    @PatchMapping("/patient/{id}")
    public String updatePatient(@PathVariable("id") int id, @ModelAttribute("patient") Patient patient) {
        patientDao.updatePatient(id, patient);
        return "redirect:/patient/" + id;
    }

    @DeleteMapping("/patient/{id}")
    public String deletePatient(@PathVariable("id") int id) {
        patientDao.deletePatient(id);
        return "redirect:/patient";
    }

    @PatchMapping("/patient/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("status") Status selectedStatus) {
        patientDao.assign(id, selectedStatus);
        return "redirect:/patient/" + id;
    }
}
