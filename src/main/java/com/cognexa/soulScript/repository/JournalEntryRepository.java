package com.cognexa.soulScript.repository;

import com.cognexa.soulScript.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry,Long> {
}
