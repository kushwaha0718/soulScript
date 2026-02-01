package com.cognexa.soulScript.service;

import com.cognexa.soulScript.dto.JournalEntryRequestDTO;
import com.cognexa.soulScript.dto.JournalEntryResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface JournalEntryService {

    List<JournalEntryResponseDTO> getAllJournalEntries();
    JournalEntryResponseDTO getJournalEntryById(Long id);
    JournalEntryResponseDTO saveJournalEntry(JournalEntryRequestDTO dto,Long userId);
    JournalEntryResponseDTO updateJournalEntry(Long id, Map<String, Object> updates);
    void deleteJournalEntryById(Long id);

    Page<JournalEntryResponseDTO> getPagedEntries(int page, int size,String sortBy,String sortOrder);
}
