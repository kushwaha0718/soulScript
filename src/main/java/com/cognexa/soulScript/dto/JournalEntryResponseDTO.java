package com.cognexa.soulScript.dto;

import com.cognexa.soulScript.entity.UserData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntryResponseDTO {

    private Long journalId;
    private String journalTitle;
    private String journalContent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate journalDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime journalTime;

    private UserDataSummaryDTO userData;
}
