package com.cognexa.soulScript.repository;

import com.cognexa.soulScript.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Long> {
    UserData findUserDataByUsername(String username);
}
