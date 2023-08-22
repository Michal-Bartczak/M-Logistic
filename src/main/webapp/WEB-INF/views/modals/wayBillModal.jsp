
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="modalLp" tabindex="-1" aria-labelledby="modalLpLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-a4">
        <div class="modal-content-lp">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLpLabel">List Przewozowy</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body-lp">
                <div class="wrapper">
                    <div class="header-in-modal">
                        <h2><span>M</span>-Logistic</h2>
                        <div>M-Logistic Przykładowa ulica 11, kontakt: 512-123-123; email: przykładowy@gmail.com </div>
                    </div>
                    <div class="row">
                        <div class="col-6" id="tracking-number-lp" >
                            Numer listu przewozowego:
                            <!-- DANE DO WSTAWIENIA LISTU  -->
                        </div>
                        <div class="col-6" id="barcode-lp">
                            KOD KRESKOWY
                            <!-- KOD KRESKOWY -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6"><h5>Nadawca:</h5>
                            <p class="sender-data">NAZWA NADAWCY</p>


                        </div>
                        <div class="col-6"><H5>Odbiorca:</H5>
                            <p class="recipient-data">NAZWA ODBIORCY</p>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <p class="send-date">   </p> <!-- DATA NADANIA -->
                        </div>
                    </div>
                    <div class="row" id="table-dane">
                        <div class="col-2"></div>
                        <div class="col-2">Rodzaj towaru</div>
                        <div class="col-2">Ilość</div>
                        <div class="col-2">Rodzaj</div>
                        <div class="col-2">Wymiary</div>
                        <div class="col-2">Waga</div>
                    </div>
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-2">

                        </div>
                        <div class="col-2">
                            1
                        </div>
                        <div class="col-2" id="type">
                            <!--TYPE-->
                        </div>
                        <div class="col-2" id="dimension">
                            <%--                        DIMENSION --%>
                        </div>
                        <div class="col-2" id="weigh">
                            <!-- WEIGH -->
                        </div>
                    </div>
                    <div class="row" id="table-sign">
                        <div class="col-6"id="component-table" >Podpis kierowcy</div>
                        <div class="col-6" style="text-align: center;">Potwierdzam odbiór przesyłki bez zastrzerzeń</div>
                    </div>
                    <div class="row letter-row">

                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell"></div>
                        <div class="letter-cell">d</div>
                        <div class="letter-cell">d</div>
                        <div class="letter-cell">m</div>
                        <div class="letter-cell">m</div>
                        <div class="letter-cell">r</div>
                        <div class="letter-cell">r</div>
                        <div class="letter-cell">r</div>
                        <div class="letter-cell">r</div>

                    </div>
                    <div class="row">
                        <div class="col-6">Czytelnie nazwisko</div>
                        <div class="col-6 text-end" >Data odbioru</div>
                    </div>

                </div>
            </div>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij</button>
            <button type="button" id="printButton-lp" class="btn btn-primary">Wydrukuj</button>
        </div>

    </div>
</div>
</div>
