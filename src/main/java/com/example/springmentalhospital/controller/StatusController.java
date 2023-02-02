package com.example.springmentalhospital.controller;

import com.example.springmentalhospital.dao.PatientDao;
import com.example.springmentalhospital.dao.StatusDao;
import com.example.springmentalhospital.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatusController {
    private final StatusDao statusDao;

    private final PatientDao patientDao;


    public StatusController(StatusDao statusDao, PatientDao patientDao) {
        this.statusDao = statusDao;
        this.patientDao = patientDao;
    }

    @GetMapping("/status")
    public String indexStatus(Model model) {
        model.addAttribute("status", statusDao.indexStatus());
        return "/indexStatus";
    }
}
