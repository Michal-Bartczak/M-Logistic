<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer>
    <p><i class="fa fa-truck"></i> &copy; 2023 M-Logistic. Wszelkie prawa zastrzeżone.</p>
</footer>

</div>
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
                        <div class="modalTable_col-3" id="text1">PL</div>
                        <div class="modalTable_col-6" id="text2">Tekst 2</div>
                        <div class="modalTable_col-3" id="text3">Tekst 3</div>
                    </div>
                    <div class="modalTable_row">
                        <div class="modalTable_col-6" id="text4">Tekst 4

                        </div>
                        <div class="modalTable_col-6" id="text5">Tekst 5</div>
                    </div>
                    <div class="modalTable_row modalTable_barcode" id="text6"></div>
                    <div class="modalTable_row modalTable_footer">
                        <div class="modalTable_col-6" id="footer-modal">Stopka 1</div>
                        <div class="modalTable_col-6"><span>M</span>-Logistic</div>
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


<script src="/js/bootstrap/bootstrap.bundle.min.js"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function fetchLabelData(trackingNumber) {
        fetch('/customer/shippingLabel/' + trackingNumber)
            .then(response => {
                debugger;
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                document.getElementById("text1").innerText = data.trackingNumber;
                document.getElementById("text2").innerText = data.cityRecipient;
                document.getElementById("text3").innerText = data.weigh;
                document.getElementById("text4").innerText = data.nameRecipient;
                document.getElementById("text5").innerText = data.zipCodeRecipient;
                // ... analogicznie dla innych pól ...
            })

                .catch(error => {
                console.log('There was a problem with the fetch operation:', error.message);
            });
    }
    document.getElementById("printButton").addEventListener("click", function () {
        window.print();
    });
</script>
</body>
</html>