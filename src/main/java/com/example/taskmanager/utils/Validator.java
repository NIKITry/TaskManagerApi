package com.example.taskmanager.utils;

import com.example.taskmanager.utils.exceptions.TaskValidationException;
import org.springframework.validation.BindingResult;

public final class Validator {
    public static void validate(BindingResult br) {
        if (br.hasErrors()) throw new TaskValidationException("Неверная модель Task");
    }
}
