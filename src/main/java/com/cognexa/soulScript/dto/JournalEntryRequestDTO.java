package com.cognexa.soulScript.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class JournalEntryRequestDTO {

    @NotBlank(message = "Journal title cannot be empty")
    private String journalTitle;

    @NotBlank(message = "Journal content cannot be empty")
    private String journalContent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate journalDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime journalTime;
}
