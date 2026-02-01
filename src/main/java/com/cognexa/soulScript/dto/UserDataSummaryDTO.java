package com.cognexa.soulScript.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataSummaryDTO {
    private Long userId;
    private String username;
}
