<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pracownik</title>
    <link rel="stylesheet" href="/css/styleZalogowanyAdmin.css" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

    <div id="mySidenav" class="sidenav">
        <p class="logo"><span>M</span>-Logistic</p>
        <a href="#" class="icon-a"><i class="fa fa-users icons"></i>&nbsp;&nbsp; Lista użytkowników</a>
        <a href="#" class="icon-a"><i class="fa fa-briefcase icons"></i>&nbsp;&nbsp; Lista pracowników</a>
        <a href="#" class="icon-a"><i class="fa fa-truck icons"></i>&nbsp;&nbsp; Lista przesyłek</a>
        <a href="#" class="icon-a"><i class="fa fa-key icons"></i>&nbsp;&nbsp; Zmień hasło</a>
        <a href="#" class="icon-a"><i class="fa fa-user-circle-o" aria-hidden="true"></i>&nbsp;&nbsp; Zmień dane</a>
        <a href="#" class="icon-a"><i class="fa fa-folder-open" aria-hidden="true"></i>&nbsp;&nbsp; Raporty</a>
        <a href="/logout" class="icon-a"><i class="fa fa-sign-out icons" aria-hidden="true" style="color: #f7403b;"></i>&nbsp;&nbsp; Wyloguj</a>
    </div>
    
    
    <div id="main">
        <div class="head">
            <div class="col-div-6">
                <span style="font-size: 30px; cursor: pointer;color: white;"
                  class="nav">&#9776; Dashboard</span>
                  <span style="font-size: 30px;cursor: pointer;color: white;"
                     class="nav2"> &#9776; Panel (stanowisko)</span></div>
            <div class="col-div-6">
            
                <div class="profile">
                    <img src="https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y" class="pro-img">
                    <p>DANE UŻYTKOWNIKA <span>STANOWISKO</span></p>
                </div>
                
        </div>

            <div class="clearfix"></div>
        </div>
        <div class="col-div-3">
            <div class="box">
                <h1 class="list">Lista rzeczy do wyświetlenia</h1>
            </div>
        </div> 
        
            <footer>
                <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
            </footer>
        
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/bootstrap/bootstrap.bundle.min/js"></script>
</body>
</html>