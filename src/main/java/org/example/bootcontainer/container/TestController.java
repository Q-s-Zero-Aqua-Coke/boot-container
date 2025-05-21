package org.example.bootcontainer.container;

import lombok.RequiredArgsConstructor;
import org.example.bootcontainer.dto.AnimalDTO;
import org.example.bootcontainer.entity.Animal;
import org.example.bootcontainer.repository.AnimalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final AnimalRepository animalRepository;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/animals")
    public ResponseEntity<List<AnimalDTO>> getAnimal() {
        return ResponseEntity.ok(
                animalRepository.findAll().stream().map(
                        el -> new AnimalDTO(el.getName())
                ).toList());
    }

    @PostMapping("/animals")
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setName(animalDTO.name());
        animalRepository.save(animal);
        return ResponseEntity.ok(animal);
    }
}
