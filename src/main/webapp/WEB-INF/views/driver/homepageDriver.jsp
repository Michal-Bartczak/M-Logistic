<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Strona Główna - Kierowca</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/logInAdmin.css" type="text/css"/>

</head>

<body>

<jsp:include page="../sidebars/sidebarDriver.jsp"/>

<div id="main">
    <div class="head">
        <div class="col-div-6">
                <span style="font-size: 30px; cursor: pointer;color: white;"
                      class="nav">&#9776; Dashboard</span>
            <span style="font-size: 30px;cursor: pointer;color: white;"
                  class="nav2"> &#9776; Panel kierowcy</span></div>
        <div class="col-div-6">

            <div class="profile">
                <img src="https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y" class="pro-img">
                <p>${driver.username} <span>${driver.role}</span></p>
            </div>

        </div>

        <div class="clearfix"></div>
    </div>
    <div class="col-div-3 bg-secondary">
        <div class="box">
            <h1 class="header-list">Lista przesyłek</h1>
        </div>
    </div>

    <c:forEach var="order" items="${orderlist}" varStatus="listStatus">
        <div class="col-div-3" id="item-order">
            <div class="accordion-header">
                <button class="btn btn-link" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${listStatus.index}" aria-expanded="true" aria-controls="collapse${listStatus.index}">
                    <div class="row">
                        <div class="col-3 text-start">
                            <p class="list-header">Numer zamówienia</p>
                            <p class="list">${order.trackingNumber}</p>
                        </div>
                        <div class="col-3 text-center">
                            <p class="list-header">Dane odbiorcy</p>
                            <p class="list">${order.nameRecipient},${order.zipCodeRecipient} ${order.cityRecipient}, ${order.streetRecipient} </p>
                        </div>
                        <div class="col-3 text-center">
                            <p class="list-header">Data utworzenia</p>
                            <p class="list">${order.creationDate}</p>
                        </div>
                        <div class="col-3 text-end">
                            <p class="list-header">Status</p>
                            <p class="list">${order.status}</p>
                        </div>




                    </div>
                </button>
            </div>
            <div id="collapse${listStatus.index}" class="collapse" data-bs-parent="#accordionExample">
                <div class="box row">
                    <div class="col-2">${order.dimensions}</div>
                    <div class="col-2 text-center">${order.price} zł</div>

                    <div class="col-2 text-center">${order.weigh} kg</div>
                    <div class="col-2 text-center">
                        <button type="button" class="btn btn-success" data-order-id="${order.id}">DOSTARCZONO</button>
                    </div>
                    <div class="col-2 text-center">
                        <button type="button" class="btn btn-danger" data-order-id="${order.id}">NIE DOSTARCZONO</button>
                    </div>

                    <div class="col-2 text-end">${order.provider}</div>
                </div>
            </div>

        </div>
    </c:forEach>




    <footer>
        <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="/js/driverOrderProvide.js"></script>

</body>
</html>
