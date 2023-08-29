<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="page.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >
    <link rel="stylesheet" href="css/styleStronaGlowna.css" type="text/css"/>
</head>
<body>
<div id="mySidenav" class="sidenav">
    <p class="logo"><span>M</span><spring:message code="menu.logo"/></p>
    <a href="/" class="icon-a"><spring:message code="menu.home"/></a>
    <a href="/register" class="icon-a"><spring:message code="menu.register"/></a>
    <a href="/login" class="icon-a"><spring:message code="menu.login"/></a>
    <a href="/login?logout=true" id="sendPackageButton" class="icon-a"><spring:message code="menu.sendPackage"/></a>
    <a href="/track-package" class="icon-a"><spring:message code="menu.trackPackage"/></a>
    <a href="/contact" class="icon-a"><spring:message code="menu.contact"/></a>
    <a href="/FAQ" class="icon-a"><spring:message code="menu.faq"/></a>
</div>
<div id="main">
    <div class="container text-center mt-5" style="width: 100%;">
        <h1 class="display-4 text-white" style="font-family: system-ui; font-weight: 500;"><spring:message code="main.welcome.heading"/></h1>
        <p class="lead text-white"><spring:message code="main.welcome.subheading"/></p>

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="p-2 fixed-height">
                        <i class="fa fa-cogs fa-3x"></i>
                        <h4><spring:message code="feature.effectiveManagement.title"/></h4>
                        <p><spring:message code="feature.effectiveManagement.description"/></p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-2 fixed-height">
                        <i class="fa fa-map fa-3x"></i>
                        <h4><spring:message code="feature.deliveryPlanning.title"/></h4>
                        <p><spring:message code="feature.deliveryPlanning.description"/></p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-2 fixed-height">
                        <i class="fa fa-globe fa-3x"></i>
                        <h4><spring:message code="feature.internationalShipments.title"/></h4>
                        <p><spring:message code="feature.internationalShipments.description"/></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="p-2 fixed-height">
                        <i class="fa fa-archive fa-3x"></i>
                        <h4><spring:message code="feature.inventoryManagement.title"/></h4>
                        <p><spring:message code="feature.inventoryManagement.description"/></p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-2 fixed-height">
                        <i class="fa fa-line-chart fa-3x"></i>
                        <h4><spring:message code="feature.operationalDataAnalysis.title"/></h4>
                        <p><spring:message code="feature.operationalDataAnalysis.description"/></p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-2 fixed-height">
                        <i class="fa fa-undo fa-3x"></i>
                        <h4><spring:message code="feature.returnsHandling.title"/></h4>
                        <p><spring:message code="feature.returnsHandling.description"/></p>
                    </div>
                </div>
            </div>

            <div class="row mt-5">
                <div class="col-12">
                    <a href="/register" class="btn btn-secondary btn-lg"><spring:message code="cta.register"/></a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp"/>
</body>
</html>
