package com.cognexa.soulScript.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionModel {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;

}
