package com.discgolf.disc_golf_backend.controllers;

import com.discgolf.disc_golf_backend.models.Hole;
import com.discgolf.disc_golf_backend.services.HoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holes")
@CrossOrigin(origins = "http://172.20.10.3:3000")
public class HoleController {

    private final HoleService holeService;

    public HoleController(HoleService holeService) {
        this.holeService = holeService;
    }

    @GetMapping
    public List<Hole> getAllHoles() {
        return holeService.getAllHoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hole> getHoleById(@PathVariable int id) {
        Optional<Hole> hole = holeService.getHoleById(id);
        return hole.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hole createHole(@RequestBody Hole hole) {
        return holeService.createHole(hole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hole> updateHole(@PathVariable int id, @RequestBody Hole updatedHole) {
        Hole hole = holeService.updateHole(id, updatedHole);
        if (hole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHole(@PathVariable int id) {
        boolean deleted = holeService.deleteHole(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}