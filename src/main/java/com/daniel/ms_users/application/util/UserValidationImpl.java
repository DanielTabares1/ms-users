package com.daniel.ms_users.application.util;

import com.daniel.ms_users.domain.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class UserValidationImpl implements UserValidations{
    @Override
    public boolean isAdult(User user) {
        LocalDate birthDate = user.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate currentDate = LocalDate.now();

        int age = Period.between(birthDate, currentDate).getYears();
        return age >= 18;
    }
}
