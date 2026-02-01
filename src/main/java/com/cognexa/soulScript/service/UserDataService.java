package com.cognexa.soulScript.service;

import com.cognexa.soulScript.dto.UserDataRequestDTO;
import com.cognexa.soulScript.dto.UserDataResponseDTO;
import com.cognexa.soulScript.entity.UserData;

public interface UserDataService {
    UserData findUserDataById(Long id);
    boolean verifyUserDataByUsername(String username);
    UserData findUserDataByUserName(String username);
    UserDataResponseDTO saveUserData(UserDataRequestDTO userDataR);
}
