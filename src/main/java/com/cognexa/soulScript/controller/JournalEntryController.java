package com.cognexa.soulScript.controller;

import com.cognexa.soulScript.dto.JournalEntryRequestDTO;
import com.cognexa.soulScript.dto.JournalEntryResponseDTO;
import com.cognexa.soulScript.service.JournalEntryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryService service;

    public JournalEntryController(JournalEntryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllJournalEntries());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getJournalEntryById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody JournalEntryRequestDTO dto,
                                    @RequestParam Long userId) {
        return new ResponseEntity<>(service.saveJournalEntry(dto,userId), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(service.updateJournalEntry(id, updates));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteJournalEntryById(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }

    @GetMapping("/page")
    public Page<JournalEntryResponseDTO> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "journalUploadDate") String sortBy,
            @RequestParam(defaultValue = "ascending") String sortOrder
    ) {
        return service.getPagedEntries(page,size,sortBy,sortOrder);
    }
}

