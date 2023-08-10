<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>M-Logistic</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >


    <link rel="stylesheet" href="css/logowanie.css" type="text/css"/>
</head>
<body>
<jsp:include page="sidebarStronaGlowna.jsp"/>
<div id="main">
    <div class="container mt-5 ml-3">
        <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Logowanie</h2>

        <c:if test="${param.registered == 'true'}">
            <div class="alert alert-success" style="width: 30%" >
                <p>Rejestracja przebiegła pomyślnie!</p>
                <p>Teraz możesz się zalogować</p>
            </div>
        </c:if>

        <c:if test="${param.logout=='true'}">
            <div class="alert alert-danger" style="width: 30%; color: white"  >
                <p>Ta opcja jest tylko dla zalogowanych użytkowników, zaloguj się !</p>

            </div>
        </c:if>
        <form action="/login" method="POST" id="logForm">
            <div class="form-group">
                <label for="username">Nazwa użytkownika</label>
                <input type="text" class="form-control" id="username" name="username" required style="width: 30%;">
            </div>
            <div class="form-group">
                <label for="password">Hasło</label>
                <input type="password" class="form-control" id="password" name="password" required style="width: 30%;">
            </div>
            <button type="submit" class="btn btn-secondary">Zaloguj</button>
        </form>

        <div class="mt-3">
            <p>Nie masz jeszcze konta? <a href="/register">Zarejestruj się</a></p>
        </div>
    </div>
</div>



<script src="/js/script.js"/>
<jsp:include page="footer.jsp"/>