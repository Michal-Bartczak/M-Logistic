<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Wyślij paczkę</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >
  <link rel="stylesheet" href="/css/sendPackage.css" type="text/css"/>
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
    <h2 style="color: #f7403b; font-size: 3em; margin-bottom: 50px;">Wyślij paczkę</h2>

    <c:if test="${param.sent=='true'}">
    <div class="alert alert-success" style="width: 30%; background-color: rgba(21,82,47,0.22); color: white; border: none"   >
      <p>Twoja paczka została wysłana !</p>
    </div>
    </c:if>

    <form:form modelAttribute="detailsPackage" action="/customer/send" method="post" id="detailsPackage">
      <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label for="nameRecipient">Nazwa odbiorcy</label>
            <input type="text" class="form-control" id="nameRecipient" name="nameRecipient" value="${detailsPackage.nameRecipient}"/>
            <form:errors path="nameRecipient" class="error"/>
          </div>

          <div class="form-group">
            <label for="cityRecipient">Miasto odbiorcy</label>
            <input type="text" class="form-control" id="cityRecipient" name="cityRecipient" value="${detailsPackage.cityRecipient}"/>
            <form:errors path="cityRecipient" class="error"/>
          </div>

          <div class="form-group">
            <label for="streetRecipient">Ulica odbiorcy</label>
            <input type="text" class="form-control" id="streetRecipient" name="streetRecipient" value="${detailsPackage.streetRecipient}"/>
            <form:errors path="streetRecipient" class="error"/>
          </div>

          <div class="form-group">
            <label for="zipCodeRecipient">Kod pocztowy odbiorcy</label>
            <input type="text" class="form-control" id="zipCodeRecipient" name="zipCodeRecipient" value="${detailsPackage.zipCodeRecipient}"/>
            <form:errors path="zipCodeRecipient" class="error"/>
          </div>
        </div>

        <div class="col-md-6">
          <div class="form-group">
            <label for="dimensions">Wymiary przesyłki</label>
            <select class="form-control" id="dimensions" name="dimensions">
              <c:forEach var="dim" items="${dimensions}">
                <option value="${dim}">${dim}</option>
              </c:forEach>
            </select>
          </div>

          <div class="form-group">
            <label for="price">Cena</label>
            <input type="text" pattern="\d+(\.\d{1,2})?" class="form-control" id="price" name="price" value="${detailsPackage.price}"/>
            <form:errors path="price" class="error"/>
          </div>

          <div class="form-group">
            <label for="weigh">Waga</label>
            <input type="text" class="form-control" id="weigh" name="weigh" value="${detailsPackage.weigh}"/>
            <form:errors path="weigh" class="error"/>
          </div>
        </div>
      </div>

      <button type="submit" class="btn btn-secondary" id="submitButton">Wyślij zamówienie</button>
    </form:form>


  </div>

  <!-- ... -->


</div>

<footer>
  <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="/js/swapProvider.js"></script>
<script src="/js/checkCustomerDetails.js"></script>

</body>
</html>