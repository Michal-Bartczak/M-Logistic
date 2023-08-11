<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>M-Logistic</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&display=swap" >

    <style>
    
    </style>
    <link rel="stylesheet" href="css/styleStronaGlowna.css" type="text/css"/>
</head>
<body>
 <jsp:include page="../sidebars/sidebarStronaGlowna.jsp"/>
    
    <div id="main">
        <div class="container text-center mt-5" style="width: 100%;">
            <h1 class="display-4 text-white" style="font-family: system-ui; font-weight: 500;">Witaj w <span style="color: #f7403b;">M</span>-Logistic!</h1>

            <p class="lead text-white">Twój partner w zarządzaniu transportem i logistyką magazynową.</p>

            <div class="container">
                <div class="row">
                    <!-- Pierwszy rząd -->
                    <div class="col-md-4">
                        <div class="p-2 fixed-height">
                            <i class="fa fa-cogs fa-3x"></i>
                           <h4> Efektywne Zarządzanie Procesami </h4><p> Skup się na tym, co najważniejsze dla Twojego biznesu, a M-Logistic zajmie się resztą.
                             Nasza aplikacja umożliwia łatwe zarządzanie procesami, takimi jak zamówienia, dostawy i zwroty, wszystko z jednego, intuicyjnego interfejsu.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="p-2 fixed-height">
                            <i class="fa fa-map fa-3x"></i> 
                         <h4>   Planowanie Tras Dostaw </h4><p>Optymalizuj swoje trasy dostaw dzięki zaawansowanym funkcjom M-Logistic.
                             Nasza aplikacja umożliwia planowanie najbardziej efektywnych tras, co przekłada się na oszczędność czasu i paliwa.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="p-2 fixed-height">
                            <i class="fa fa-globe fa-3x"></i>
                          <h4>Przesyłki Międzynarodowe</h4><p>Przekrocz granice bez wysiłku z M-Logistic. Nasza aplikacja ułatwia wysyłanie paczek do klientów na całym świecie,
                             a dzięki naszej sieci globalnych partnerów logistycznych, możemy zagwarantować, że Twoje przesyłki dotrą bezpiecznie do celu.</p>
                        </div>
                    </div>
                    
                </div>
                
                <div class="row">
                    <!-- Drugi rzęd -->
                    <div class="col-md-4">
                        <div class="p-2 fixed-height">
                            <i class="fa fa-archive fa-3x"></i>
                           <h4>Zarządzanie Zapasami</h4><p>M-Logistic pomaga utrzymać Twoje zapasy na odpowiednim poziomie. Dzięki naszej aplikacji możesz łatwo monitorować poziom zapasów,
                             a nasze inteligentne powiadomienia zapewniają, że nigdy nie zostaniesz zaskoczony brakiem towaru.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="p-2 fixed-height">
                            <i class="fa fa-line-chart fa-3x"></i> 
                           <h4>Analiza Danych Operacyjnych </h4><p>Wykorzystaj moc danych z M-Logistic do optymalizacji swojego biznesu. Nasza aplikacja 
                            umożliwia analizę kluczowych wskaźników efektywności operacyjnej, co pozwala na podejmowanie świadomych decyzji biznesowych.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="p-2 fixed-height">
                            <i class="fa fa-undo fa-3x"></i>
                            <h4>Bezproblemowe Obsługiwanie Zwrotów</h4><p> M-Logistic sprawia, że proces obsługi zwrotów jest łatwy i bezproblemowy.
                                 Dzięki naszej aplikacji, możesz szybko przetwarzać zwroty, co przekłada się na wyższe zadowolenie klientów.</p>
                        </div>
                    </div>
                    
                </div>
            </div>

            <div class="row mt-5">
                <div class="col-12">
                    <a href="/register" class="btn btn-secondary btn-lg">Zarejestruj się już dziś!</a>
                </div>
            </div> 
        </div>


        <script src="/js/script.js"/>
<jsp:include page="../includes/footer.jsp"/>