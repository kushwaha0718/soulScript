package com.cognexa.soulScript.mapper;

import com.cognexa.soulScript.dto.JournalEntryRequestDTO;
import com.cognexa.soulScript.dto.JournalEntryResponseDTO;
import com.cognexa.soulScript.dto.UserDataSummaryDTO;
import com.cognexa.soulScript.entity.JournalEntry;
import com.cognexa.soulScript.entity.UserData;

import java.time.LocalDate;
import java.time.LocalTime;

public class JournalEntryMapper {

    public static JournalEntry toEntity(JournalEntryRequestDTO dto) {
        JournalEntry entry = new JournalEntry();
        entry.setJournalTitle(dto.getJournalTitle());
        entry.setJournalContent(dto.getJournalContent());
        entry.setJournalUploadDate(LocalDate.now());
        entry.setJournalUploadTime(LocalTime.now());
        return entry;
    }

    private static UserDataSummaryDTO generateUserSummary(UserData userData){
        return  new UserDataSummaryDTO(
                userData.getId(),
                userData.getUsername()
        );
    }

    public static JournalEntryResponseDTO toDto(JournalEntry entry) {
        return new JournalEntryResponseDTO(
                entry.getJournalId(),
                entry.getJournalTitle(),
                entry.getJournalContent(),
                entry.getJournalUploadDate(),
                entry.getJournalUploadTime(),
                generateUserSummary(entry.getUserData())
        );
    }
}
