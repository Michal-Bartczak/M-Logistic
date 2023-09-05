<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aktualizacja danych</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >
    <link rel="stylesheet" href="/css/register.css" type="text/css"/>

</head>

<body>
<jsp:include page="../sidebars/sidebarCustomer.jsp"/>

<div id="main">
    <div class="head">
        <div class="col-div-6">
                <span style="font-size: 30px; cursor: pointer;color: white;"
                      class="nav">&#9776; Dashboard</span>
            <span style="font-size: 30px;cursor: pointer;color: white;"
                  class="nav2"> &#9776; Panel klienta</span></div>
        <div class="col-div-12">

            <div class="profile">
                <img src="https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y" class="pro-img">
                <p>${customer.username} <span>${customer.role}</span></p>
            </div>

        </div>

        <div class="clearfix"></div>
    </div>
    <div class="container mt-5 ml-3">
        <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Dodaj dane swojej firmy</h2>
        <c:if test="${param.update == 'true'}">
            <div class="alert alert-success" style="width: 30%" >
                <p>Twoje dane zostały zmienione</p>

            </div>
        </c:if>
        <form:form modelAttribute="editForm"  action="/customer/editDetails" method="post" id="customerDetailsForm">
            <div class="form-group">
                <label for="nameCompanySender">Nazwa firmy</label>
                <input type="text" class="form-control" id="nameCompanySender" name="nameCompanySender" value="${editForm.nameCompanySender}" style="width: 30%;"/>
                <form:errors path="nameCompanySender" class="error"/>
            </div>

            <div class="form-group">
                <label for="zipCodeSender">Kod pocztowy</label>
                <input type="text" class="form-control" id="zipCodeSender" name="zipCodeSender" value="${editForm.zipCodeSender}" style="width: 30%;" />
                <form:errors path="zipCodeSender" class="error"/>
            </div>

            <div class="form-group">
                <label for="citySender">Miasto</label>
                <input type="text" class="form-control" id="citySender" name="citySender" value="${editForm.citySender}" style="width: 30%;" />
                <form:errors path="citySender" class="error"/>
            </div>

            <div class="form-group">
                <label for="streetSender">Ulica</label>
                <input type="text" class="form-control" id="streetSender" name="streetSender" value="${editForm.streetSender}" style="width: 30%;" />
                <form:errors path="streetSender" class="error"/>
            </div>

            <div class="form-group">
                <label for="contactSender">Numer telefonu</label>
                <input type="text" class="form-control" id="contactSender" name="contactSender" value="${editForm.contactSender}" style="width: 30%;" />
                <form:errors path="contactSender" class="error"/>
            </div>

            <button type="submit" class="btn btn-secondary">Aktualizuj dane</button>
        </form:form>

    </div>

</div>
    <footer>
        <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="/js/validateEditCustomerDetails.js"></script>

</body>
</html>