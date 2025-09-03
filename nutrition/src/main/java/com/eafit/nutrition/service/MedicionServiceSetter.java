package com.eafit.nutrition.service;

import com.eafit.nutrition.model.Medicion;
import com.eafit.nutrition.repository.MedicionRepository;
import com.eafit.nutrition.repository.PacienteRepository;
import com.eafit.nutrition.repository.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicionServiceSetter {

    private MedicionRepository medicionRepository;
    private PacienteRepository pacienteRepository;
    private NutricionistaRepository nutricionistaRepository;

    @Autowired
    public void setMedicionRepository(MedicionRepository medicionRepository) {
        this.medicionRepository = medicionRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Medicion> findLastMedicionByPacienteId(Long pacienteId) {
        return medicionRepository.findFirstByPacienteIdOrderByFechaDesc(pacienteId);
    }


    @Autowired
    public void setPacienteRepository(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Autowired
    public void setNutricionistaRepository(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }

    @Transactional(readOnly = true)
    public List<Medicion> findAll() { return medicionRepository.findAll(); }

    @Transactional(readOnly = true)
    public Optional<Medicion> findById(Long id) { return medicionRepository.findById(id); }
}
