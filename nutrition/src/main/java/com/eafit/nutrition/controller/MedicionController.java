package com.eafit.nutrition.controller;

import com.eafit.nutrition.model.Medicion;
import com.eafit.nutrition.service.MedicionServiceConstructor;
import com.eafit.nutrition.service.MedicionServiceAutowired;
import com.eafit.nutrition.service.MedicionServiceSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {

    // Inyección por constructor
    private final MedicionServiceConstructor constructorService;

    // Inyección por campo
    @Autowired
    private MedicionServiceAutowired autowiredService;

    // Inyección por setter
    private MedicionServiceSetter setterService;

    public MedicionController(MedicionServiceConstructor constructorService) {
        this.constructorService = constructorService;
    }

    @Autowired
    public void setSetterService(MedicionServiceSetter setterService) {
        this.setterService = setterService;
    }

    @GetMapping("/constructor")
    public ResponseEntity<List<Medicion>> getAllMedicionesConstructor() {
        return ResponseEntity.ok(constructorService.findAll());
    }

    @GetMapping("/autowired")
    public ResponseEntity<List<Medicion>> getAllMedicionesAutowired() {
        return ResponseEntity.ok(autowiredService.findAll());
    }

    @GetMapping("/setter")
    public ResponseEntity<List<Medicion>> getAllMedicionesSetter() {
        return ResponseEntity.ok(setterService.findAll());
    }

    @GetMapping("/compare/{id}")
    public ResponseEntity<Map<String, Object>> compareMedicionById(@PathVariable Long id) {
        Optional<Medicion> a = constructorService.findById(id);
        Optional<Medicion> b = autowiredService.findById(id);
        Optional<Medicion> c = setterService.findById(id);

        Map<String, Object> r = new HashMap<>();
        r.put("constructorService", a.orElse(null));
        r.put("autowiredService", b.orElse(null));
        r.put("setterService", c.orElse(null));
        return ResponseEntity.ok(r);
    }

    @PostMapping("/constructor/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Medicion> createMedicionConstructor(
            @PathVariable Long pacienteId,
            @PathVariable Long nutricionistaId,
            @RequestBody Medicion medicion) {
        Medicion created = constructorService.createMedicion(pacienteId, nutricionistaId, medicion);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
