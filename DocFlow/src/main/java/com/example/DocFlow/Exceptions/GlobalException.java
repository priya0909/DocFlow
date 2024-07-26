package com.example.DocFlow.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(GlobalException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";

    }

    private Throwable getCause () {
        return null;
    }

}
