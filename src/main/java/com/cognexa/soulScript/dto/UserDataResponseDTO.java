package com.cognexa.soulScript.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponseDTO {
    private Long userId;
    private String username;
    private List<JournalEntryResponseDTO>  journalEntryResponseDTOList;
}
