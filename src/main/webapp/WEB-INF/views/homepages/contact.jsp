<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>M-Logistic</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >

    <style>
    
    </style>
    <link rel="stylesheet" href="css/kontakt.css" type="text/css"/>
</head>
<body>
<jsp:include page="../sidebars/sidebarHomepage.jsp"/>
    
    <div id="main" >
        <div class="container mt-5 ml-3">
            <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Skontaktuj się z nami</h2>
            <form action="#" method="#">
                <div class="form-group" style="width: 30%;">
                    <label for="name">Imię i nazwisko:</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>

                <div class="form-group" style="width: 30%;">
                    <label for="email">Adres email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>

                <div class="form-group" style="width: 30%;">
                    <label for="message">Wiadomość:</label>
                    <textarea id="message" name="message" rows="4" class="form-control" required></textarea>
                </div>

                <button type="submit" class="btn btn-secondary">Wyślij</button>
            </form>
        <div class="contact" style="margin-top: 50px;">
        <h6>Nasz adres:</h6>
        <p class="p-contact" style="color: #8e8e8e;"> ul. Przykładowa 1, 00-000 Miasto</p>
      <h6>Email:</h6>  
        <p class="p-contact" style="color: #8e8e8e;"> kontakt@przykladowy-email.com</p>
        <h6>Telefon:</h6> 
        <p class="p-contact" style="color: #8e8e8e;"> +48 123 456 789</p>
    </div>
      </div>

<jsp:include page="../includes/footer.jsp"/>