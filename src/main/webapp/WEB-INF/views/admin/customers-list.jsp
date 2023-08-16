<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <titl>Pracownik</titl>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/styleZalogowanyAdmin.css" type="text/css"/>

</head>
<style>
    .col-div-3{
        margin-bottom: 10px;
    }
    h1{
        color: white;
        text-decoration: none;
    }
    .list{
        font-size: 14px;
        color: #6c757d;
    }
    .header-list{
        padding-top: 10px;

    }
    button.btn.btn-link {
        text-decoration: none;
    }
    .list-header{
        font-size: large;
        color: white;
    }
    .col-div-3 .btn {
        width: 100%;
        display: block;
    }
    .row {
        margin: 0;
        padding: 0;
    }
    .link-details{
        color: white;
        text-decoration: none;
    }
    .link-details:hover {
        color: green;  /* Możesz również użyć konkretnego kodu koloru, np. #00FF00 dla zielonego */
    }


    .custom-div a {
        color: inherit; /* zachowuje kolor tekstu z nadrzędnego diva */
        text-decoration: none; /* usuwa podkreślenie */
        display: block; /* sprawia, że link zajmuje całą przestrzeń diva */
        height: 100%;
        width: 100%;
    }



</style>
<body>

<jsp:include page="../sidebars/sidebarAdmin.jsp"/>

<div id="main">
    <div class="head">
        <div class="col-div-6">
                <span style="font-size: 30px; cursor: pointer;color: white;"
                      class="nav">&#9776; Dashboard</span>
            <span style="font-size: 30px;cursor: pointer;color: white;"
                  class="nav2"> &#9776; Panel admina</span></div>
        <div class="col-div-6">

            <div class="profile">
                <img src="https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y" class="pro-img">
                <p>${admin.username} <span>${admin.role}</span></p>
            </div>

        </div>

        <div class="clearfix"></div>
    </div>
    <div class="col-div-3 bg-secondary">
        <div class="box">
            <h1 class="header-list">Lista klientów</h1>
        </div>
    </div>

    <c:forEach var="item" items="${customerslist}" varStatus="listStatus">
        <div class="col-div-3">
            <div class="accordion-header">
                <button class="btn btn-link" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${listStatus.index}" aria-expanded="true" aria-controls="collapse${listStatus.index}">
                    <div class="row">
                        <div class="col-3 text-start">
                            <p class="list-header">Id</p>
                            <p class="list">${item.id}</p>
                        </div>
                        <div class="col-3 text-start">
                            <p class="list-header">Dane klient</p>
                            <p class="list">${item.customerDetails.nameCompanySender},${item.customerDetails.zipCodeSender} ${item.customerDetails.citySender}, ${item.customerDetails.citySender} </p>
                        </div>
                        <div class="col-3 text-center">
                            <p class="list-header">Nazwa użytkownika</p>
                            <p class="list">${item.username}</p>
                        </div>
                        <div class="col-3 text-end">
                            <p class="list-header">Email</p>
                            <p class="list">${item.email}</p>
                        </div>




                    </div>
                </button>
            </div>
            <div id="collapse${listStatus.index}" class="collapse" data-bs-parent="#accordionExample">
                <div class="box row">
                    <div class="col-2"></div>
                    <div class="col-2 text-center"></div>

                    <div class="col-2 text-center"></div>
                    <div class="col-2 text-center"><a href="#" class="link-details"></a></div>
                    <div class="col-2 text-center"><a href="#" class="link-details"></a></div>
                    <div class="col-2 text-end"></div>
                </div>
            </div>

        </div>
    </c:forEach>


<jsp:include page="../includes/footer.jsp"/>