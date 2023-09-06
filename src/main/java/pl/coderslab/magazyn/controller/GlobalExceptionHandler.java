package pl.coderslab.magazyn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.magazyn.exception.BarcodeGenerationException;
import pl.coderslab.magazyn.exception.OrderNotFoundException;
import pl.coderslab.magazyn.exception.UnknownUserTypeException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView handleOrderNotFoundException(OrderNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("/error/404"); //
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", ex.getMessage());
        modelAndView.setViewName("error/404");
        return modelAndView;
    }

    @ExceptionHandler(UnknownUserTypeException.class)
    public ModelAndView handleUnknownUserTypeException(UnknownUserTypeException ex) {
        ModelAndView modelAndView = new ModelAndView("/error/404");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(BarcodeGenerationException.class)
    public ModelAndView handleBarcodeGenerationException(BarcodeGenerationException ex) {
        ModelAndView modelAndView = new ModelAndView("/error/404");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
