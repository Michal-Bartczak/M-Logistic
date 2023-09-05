<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Strona Główna - Pracownik</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/homepageEmployee.css" type="text/css"/>

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
        <div class="col-div-6">

            <div class="profile">
                <img src="https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y" class="pro-img">
                <p>${employee.username} <span>${employee.role}</span></p>
            </div>

        </div>

        <div class="clearfix"></div>
    </div>
    <div class="col-div-3 bg-secondary">
        <div class="box">
            <h1 class="header-list">Lista przesyłek</h1>
        </div>
    </div>
    <div id="content-list-filter">
        <div class="col-div-3 filter-container bg-secondary">
            <div class="row">
                <div class="col-3">
                    <input type="text" name="filter-text" id="filter-text" placeholder="Wpisz numer zamówienia">
                </div>
                <div class="col-3 text-center">DATA
                    <input type="date" name="filter-data" id="filter-data" placeholder="rrrr-mm-dd">
                </div>
                <div class="col-3 text-center">
                    <label for="status">STATUS</label>
                    <select name="status" id="status">
                        <option value="WSZYSTKIE">WSZYSTKIE</option>
                        <option value="MAGAZYN">MAGAZYN</option>
                        <option value="DOSTAWA">DOSTAWA</option>
                        <option value="DOSTARCZONO">DOSTARCZONO</option>
                    </select>
                </div>
                <div class="col-3 text-end">
                    <label class="dimension-filter">RODZAJ</label>
                    <label for="kind-eur">EUR</label>
                    <input type="checkbox" name="kind" id="kind-eur" value="EUR" checked>

                    <label for="kind-hp">HP</label>
                    <input type="checkbox" name="kind" id="kind-hp" value="HP" checked>
                </div>


            </div>
        </div>

        <div id="noOrdersMessage" style="display: none;">Żadna z przesyłek nie spełnia kryteriów</div>

        <div class="content-list">
            <c:forEach var="order" items="${orderList}" varStatus="listStatus">
                <div class="col-div-3">
                    <div class="accordion-header">
                        <button class="btn btn-link" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse${listStatus.index}" aria-expanded="true"
                                aria-controls="collapse${listStatus.index}">
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
                            <div class="col-2 text-center ">
                                <a href="#" data-bs-toggle="modal-lp" id="trackingNumberButton-lp"
                                   onclick="fetchDataLp('${order.trackingNumber}')" class="link-details-lp">List
                                    przewozowy</a>
                            </div>
                            <div class="col-2 text-center">
                                <a href="#" data-bs-toggle="modal" data-bs-target="#shippingLabelModal"
                                   id="trackingNumberButton" onclick="fetchLabelData('${order.trackingNumber}')"
                                   class="link-details">Etykieta</a>
                            </div>
                            <div class="col-2 text-end">
                                <select class="driverSelect" data-order-id="${order.id}">

                                    <option value="${order.provider}">${order.provider}</option>

                                    <c:forEach items="${driversList}" var="driver">
                                        <option value="${driver.id}">${driver.name} ${driver.surname}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                    </div>

                </div>
            </c:forEach>
        </div>
    </div>
</div>
<footer>
    <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
</footer>

<jsp:include page="../modals/shippingLabelModal.jsp"/>
<jsp:include page="../modals/wayBillModal.jsp"/>

<script src="/js/bootstrap/bootstrap.bundle.min.js"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/shippingLabelEmployee.js"></script>
<script src="/js/swapProvider.js"></script>
<script src="/js/filterOrder.js"></script>
</body>
</html>