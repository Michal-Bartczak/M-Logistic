
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="shippingLabelModal" tabindex="-1" aria-labelledby="shippingLabelModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="shippingLabelModalLabel">Etykieta wysyłkowa</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalContentToPrint">
                <div class="modalTable_container">
                    <div class="modalTable_row">
                        <div class="modalTable_col-3 big-text" id="text1">EUR</div>
                        <div class="modalTable_col-6 big-text"  id="text2">
                            <span id="trackingNumber">NUMER</span>
                        </div>



                        <div class="modalTable_col-3 big-text" id="text3">WAGA</div>
                    </div>
                    <div class="modalTable_row">
                        <div class="modalTable_col-6 medium-text" id="text4">ODBIORCA

                        </div>
                        <div class="modalTable_col-6 medium-text" id="text5">NADAWCA</div>
                    </div>
                    <div class="modalTable_row modalTable_barcode" id="text6"></div>
                    <div class="modalTable_row modalTable_footer">
                        <div class="modalTable_col-6" id="footer-modal">DATA UTWORZENIA</div>
                        <div class="modalTable_col-6">© 2023  <span>M</span>-Logistic</div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Zamknij</button>
                <button type="button" class="btn btn-primary" id="printButton">Wydrukuj</button>
            </div>
        </div>
    </div>
</div>