package com.cognexa.soulScript.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "journal_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    @Column(nullable = false)
    private String journalTitle;

    @Column(columnDefinition = "TEXT")
    private String journalContent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate journalUploadDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime journalUploadTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("journalEntries")
    private UserData userData;
}
