package com.cognexa.soulScript.service;

import com.cognexa.soulScript.dto.JournalEntryRequestDTO;
import com.cognexa.soulScript.dto.JournalEntryResponseDTO;
import com.cognexa.soulScript.entity.JournalEntry;
import com.cognexa.soulScript.entity.UserData;
import com.cognexa.soulScript.exception.JournalNotFoundException;
import com.cognexa.soulScript.mapper.JournalEntryMapper;
import com.cognexa.soulScript.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    private final JournalEntryRepository repository;
    private final UserDataService userDataService;

    @Autowired
    public JournalEntryServiceImpl(
            JournalEntryRepository repository,
            UserDataService userDataService
    ) {
        this.repository = repository;
        this.userDataService = userDataService;
    }

    @Override
    public List<JournalEntryResponseDTO> getAllJournalEntries() {
        return repository.findAll()
                .stream()
                .map(JournalEntryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JournalEntryResponseDTO getJournalEntryById(Long id) {
        JournalEntry entry = repository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException(
                        "Request journal does not exists or has been deleted"));
        return JournalEntryMapper.toDto(entry);
    }

    @Override
    public JournalEntryResponseDTO saveJournalEntry(JournalEntryRequestDTO dto, Long userId) {
        UserData userData = userDataService.findUserDataById(userId);
        JournalEntry entry = JournalEntryMapper.toEntity(dto);
        entry.setUserData(userData);
        return JournalEntryMapper.toDto(repository.save(entry));
    }

    @Override
    public JournalEntryResponseDTO updateJournalEntry(Long id, Map<String, Object> updates) {
        JournalEntry entry = repository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal Entry not found"));

        updates.forEach((key, value) -> {
            if (value == "" || value.toString().trim().isEmpty()) return;

            switch (key) {
                case "journalTitle":
                    entry.setJournalTitle(value.toString());
                    break;

                case "journalContent":
                    entry.setJournalContent(value.toString());
                    break;

                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return JournalEntryMapper.toDto(repository.save(entry));
    }


    @Override
    public void deleteJournalEntryById(Long id) {
        if (!repository.existsById(id)) {
            throw new JournalNotFoundException("Request journal does not exists or has already been deleted");
        }
        repository.deleteById(id);
    }

    @Override
    public Page<JournalEntryResponseDTO> getPagedEntries(
            int page,
            int size,
            String sortBy,
            String sortOrder) {
        Sort sort;
        if (sortOrder.toLowerCase().equals("ascending")) {
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable).map(JournalEntryMapper::toDto);
    }
}
