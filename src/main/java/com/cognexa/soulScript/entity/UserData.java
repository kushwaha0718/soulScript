package com.cognexa.soulScript.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate accountCreationDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime accountCreationTime;

    @OneToMany(mappedBy = "userData",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("userData")
    private List<JournalEntry> journalEntries;
}
