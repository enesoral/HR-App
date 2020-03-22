package com.enesoral.simplehr.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception) {
        return generateErrorModel(exception, "SOMETHING WENT WRONG");
    }

    private ModelAndView generateErrorModel(Exception exception, String response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("response", response);
        mav.addObject("message", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
