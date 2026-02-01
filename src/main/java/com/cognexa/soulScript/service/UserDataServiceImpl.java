package com.cognexa.soulScript.service;

import com.cognexa.soulScript.dto.UserDataRequestDTO;
import com.cognexa.soulScript.dto.UserDataResponseDTO;
import com.cognexa.soulScript.entity.UserData;
import com.cognexa.soulScript.exception.UserAlreadyExistsException;
import com.cognexa.soulScript.exception.UserNotFoundException;
import com.cognexa.soulScript.mapper.UserDataMapper;
import com.cognexa.soulScript.repository.UserDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserData findUserDataById(Long id) {
        UserData userData = userDataRepository.findById(id).orElse(null);
        if (userData == null) {
            throw new UserNotFoundException("UserData not found");
        }
        return userData;
    }
    @Override
    public boolean verifyUserDataByUsername(String username) {
        UserData userData = userDataRepository.findUserDataByUsername(username);
        return userData == null;
    }

    @Override
    public UserData findUserDataByUserName(String username) {
        UserData userData = userDataRepository.findUserDataByUsername(username);
        if (userData == null) {
            throw new UserNotFoundException("UserData not found");
        }
        return userData;
    }

    @Override
    @Transactional
    public UserDataResponseDTO saveUserData(UserDataRequestDTO userDataRequestDTO){
        if (!verifyUserDataByUsername(userDataRequestDTO.getUsername())) {
            throw new UserAlreadyExistsException("Existing user detected");
        }
        UserData userData = UserDataMapper.toUserData(userDataRequestDTO);
        userDataRepository.save(userData);
        return UserDataMapper.toUserDataResponseDTO(userData);
    }
}
