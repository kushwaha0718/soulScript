package com.cognexa.soulScript.mapper;

import com.cognexa.soulScript.dto.JournalEntryResponseDTO;
import com.cognexa.soulScript.dto.UserDataRequestDTO;
import com.cognexa.soulScript.dto.UserDataResponseDTO;
import com.cognexa.soulScript.dto.UserDataSummaryDTO;
import com.cognexa.soulScript.entity.JournalEntry;
import com.cognexa.soulScript.entity.UserData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserDataMapper {

    public static UserData toUserData(UserDataRequestDTO userDataRequestDTO) {
        UserData userData = new UserData();
        userData.setUsername(userDataRequestDTO.getUsername());
        userData.setPassword(userDataRequestDTO.getPassword());
        userData.setAccountCreationDate(LocalDate.now());
        userData.setAccountCreationTime(LocalTime.now());

        return userData;
    }
    public static UserDataResponseDTO toUserDataResponseDTO(UserData userData) {
        UserDataResponseDTO userDataResponseDTO = new UserDataResponseDTO();
        userDataResponseDTO.setUsername(userData.getUsername());
        List<JournalEntryResponseDTO> journalEntryResponseDTOList = new ArrayList<>();
        if (userData.getJournalEntries() != null) {
            for(JournalEntry e:userData.getJournalEntries()){
                journalEntryResponseDTOList.add(JournalEntryMapper.toDto(e));
            }
        }
        userDataResponseDTO.setJournalEntryResponseDTOList(journalEntryResponseDTOList);
        userDataResponseDTO.setUserId(userData.getId());
        return userDataResponseDTO;
    }

    public static UserDataSummaryDTO toUserDataSummaryDTO(UserData userData) {
        return new UserDataSummaryDTO(
                userData.getId(), userData.getUsername()
        );
    }
}
