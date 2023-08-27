<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Edit Customer Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap">
    <link rel="stylesheet" href="/css/rejestracja.css" type="text/css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<jsp:include page="../sidebars/sidebarEmployee.jsp"/>

<div id="main">
    <div class="head">
        <div class="col-div-6">
                <span style="font-size: 30px; cursor: pointer;color: white;"
                      class="nav">&#9776; Dashboard</span>
            <span style="font-size: 30px;cursor: pointer;color: white;"
                  class="nav2"> &#9776; Panel pracownika</span></div>
        <div class="col-div-12">

            <div class="profile">
                <img src="https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y" class="pro-img">
                <p>${employee.username} <span>${employee.role}</span></p>
            </div>

        </div>

        <div class="clearfix"></div>
    </div>
    <div class="container mt-5 ml-3">
        <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Raporty</h2>


    </div>
    <canvas id="myChart"></canvas>


    <footer>
        <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
    </footer>
</div>
<script src="/js/swapProvider.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="/js/orderRaport.js"></script>
</body>
</html>
