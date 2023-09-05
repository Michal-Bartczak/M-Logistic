<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Śledzenie przesyłki</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >


    <link rel="stylesheet" href="/css/trackPackage.css" type="text/css"/>
</head>
<body>
<jsp:include page="../sidebars/sidebarHomepage.jsp"/>
<div id="main">
    <div class="container mt-5 ml-3">
        <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Śledzenie przesyłki</h2>
        <h4>
            Wprowadź numer przesyłki poniżej, aby sprawdzić jej aktualny status.
        </h4>
        <form id="trackingForm">
            <div class="form-group">
                <label for="trackingNumber">Numer przesyłki:</label>
                <input type="text" class="form-control" id="trackingNumber" placeholder="Wpisz numer przesyłki" style="width: 30%" pattern="^[0-9]{10}$" maxlength="10">
                <small id="errorTrackingNumber" class="text-danger" style="display:none;"></small>


            </div>
            <button type="button" class="btn btn-secondary" onclick="checkStatus()">Sprawdź status</button>
        </form>
        <div id="statusContainer" style="display: none;">
            <h4 class="status-package">Aktualny status:</h4>
            <ul>
                <li class="status" id="status1"><i class="fa fa-times-circle"></i>Twoja przesyłka jest w naszym magazynie. Niedługo ruszy do Ciebie !</li>
                <li class="status" id="status2"><i class="fa fa-truck"></i>Twoja przesyłka jest już w drodze do Ciebie !</li>
                <li class="status" id="status3"><i class="fa fa-check-circle"></i>Twoja przesyłka została już dostarczona ! </li>
            </ul>
        </div>



    </div>
</div>


<footer>
    <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="/js/trackPackage.js"></script>
</body>
</html>