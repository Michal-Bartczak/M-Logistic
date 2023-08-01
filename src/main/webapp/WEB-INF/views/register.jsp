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
    <link rel="stylesheet" href="css/rejestracja.css" type="text/css"/>
</head>
<body>
<jsp:include page="sidebarStronaGlowna.jsp"/>
    <div id="main">
        <div class="container mt-5 ml-3">
            <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Rejestracja</h2>
            <div style="margin-bottom: 25px; width: 30%; display: flex;">
                <button type="button" id="driverButton" onclick="changeForm('driver')" class="btn btn-secondary active">Pracownik</button>
                <button type="button" id="customerButton" onclick="changeForm('customer')" class="btn btn-secondary">Klient</button>
            </div>
            <form action="#" method="post" id="registrationForm">
                <div id="commonFields">
                    <div class="form-group">
                        <label for="username">Nazwa użytkownika:</label>
                        <input type="text" class="form-control" id="username" name="username" required style="width: 30%;">
                    </div>

                    <div class="form-group">
                        <label for="email">Adres email:</label>
                        <input type="email" class="form-control" id="email" name="email" required style="width: 30%;">
                    </div>

                    <div class="form-group">
                        <label for="password">Hasło:</label>
                        <input type="password" class="form-control" id="password" name="password" required style="width: 30%;">
                    </div>
                </div>

                <div id="driverFields" style="display: block;">
                    <div class="form-group">
                        <label for="firstname">Imię:</label>
                        <input type="text" class="form-control" id="firstname" name="firstname" style="width: 30%;">
                    </div>

                    <div class="form-group">
                        <label for="lastname">Nazwisko:</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" style="width: 30%;">
                    </div>
                </div>

                <div id="customerFields" style="display: none;">
                    <!-- Inne pola dla klienta -->
                </div>

                <button type="submit" class="btn btn-secondary">Zarejestruj się</button>
            </form>
        </div>
    </div>
    
    <script>
        function changeForm(usertype) {
            var driverButton = document.getElementById("driverButton");
            var customerButton = document.getElementById("customerButton");
            var driverFields = document.getElementById("driverFields");
            var customerFields = document.getElementById("customerFields");
        
            if (usertype === "driver") {
                driverButton.classList.add("active");
                customerButton.classList.remove("active");
                driverFields.style.display = "block";
                customerFields.style.display = "none";
            } else {
                driverButton.classList.remove("active");
                customerButton.classList.add("active");
                driverFields.style.display = "none";
                customerFields.style.display = "block";
            }
        }
    </script>

<jsp:include page="footer.jsp"/>