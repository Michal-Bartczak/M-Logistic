package pl.coderslab.magazyn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.magazyn.exception.OrderNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView handleOrderNotFoundException(OrderNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("/error/404"); //
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
