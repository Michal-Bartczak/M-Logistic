<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        /* Dowolne dodatkowe style */
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
        <form:form modelAttribute="registrationForm"  action="/register/driver" method="post" id="registrationForm">
            <div id="commonFields">
                <div class="form-group">
                    <label for="username">Nazwa użytkownika:</label>
                    <form:input path="username" type="text" class="form-control" id="username" name="username" required="true" style="width: 30%;" />
                    <form:errors path="username" class="error"/>
                </div>


                <div class="form-group">
                    <label for="email">Adres email:</label>
                    <form:input path="email" type="email" class="form-control" id="email" name="email" required="true" style="width: 30%;"/>
                    <form:errors path="email" class="error"/>
                </div>

                <div class="form-group">
                    <label for="password">Hasło:</label>
                    <form:input path="password" type="password" class="form-control" id="password" name="password" required="true" style="width: 30%;"/>
                            <form:errors path="password" class="error"/>
                </div>
            </div>

            <!-- Dla kierowcy -->
            <div id="driverFields" style="display: block;">
                <div class="form-group">
                    <label for="name">Imię:</label>
                    <form:input path="name" type="text" class="form-control" id="name" name="name" style="width: 30%;"/>
                    <form:errors path="name" class="error"/>
                </div>

                <div class="form-group">
                    <label for="surname">Nazwisko:</label>
                    <form:input path="surname" type="text" class="form-control" id="surname" name="surname" style="width: 30%;"/>
                    <form:errors path="surname" class="error"/>
                </div>
            </div>

            <!-- Dla klienta -->
            <div id="customerFields" style="display: none;">
                <!-- Tutaj dodajemy pola specyficzne dla klienta, jeśli są -->
            </div>

            <input type="hidden" id="userType" name="userType" value="">
            <button type="submit" class="btn btn-secondary">Zarejestruj się</button>
        </form:form>
    </div>
</div>

<script>
    function changeForm(usertype) {
        document.getElementById("userType").value = usertype;
        let driverButton = document.getElementById("driverButton");
        let customerButton = document.getElementById("customerButton");
        let driverFields = document.getElementById("driverFields");
        let customerFields = document.getElementById("customerFields");

        if (usertype === "driver") {
            document.getElementById("registrationForm").action = "/register/driver";
            driverButton.classList.add("active");
            customerButton.classList.remove("active");
            driverFields.style.display = "block";
            customerFields.style.display = "none";
        } else {
            document.getElementById("registrationForm").action = "/register/customer";
            driverButton.classList.remove("active");
            customerButton.classList.add("active");
            driverFields.style.display = "none";
            customerFields.style.display = "block";
        }
    }
</script>

<jsp:include page="footer.jsp"/>
</body>
</html>