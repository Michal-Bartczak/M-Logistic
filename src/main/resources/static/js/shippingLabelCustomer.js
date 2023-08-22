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
            document.getElementById("trackingNumber").innerText = "Nr.zamówienia" + "\n" + data.trackingNumber;
            document.getElementById("text1").innerText = data.dimensions;
            document.getElementById("text3").innerText = data.weigh;
            document.getElementById("text4").innerText =
                "ODBIORCA" + "\n" +
                data.nameRecipient + "\n" +
                data.zipCodeRecipient + "  " +
                data.cityRecipient + "\n" +
                data.streetRecipient
            document.getElementById("text5").innerText =
                "NADAWCA" + "\n" +
                data.nameCompanySender + "\n" +
                data.zipCodeSender + "  " +
                data.citySender + "\n" +
                data.streetSender;
            document.getElementById("footer-modal").innerText= data.orderCreated;

            let barcodeElement = document.getElementById("text6");


            while (barcodeElement.firstChild) {
                barcodeElement.removeChild(barcodeElement.firstChild);
            }

            let barcodeImage = document.createElement("img");
            barcodeImage.src = "data:image/png;base64," + data.barcode;
            barcodeElement.appendChild(barcodeImage);
        })

        .catch(error => {
            console.log('There was a problem with the fetch operation:', error.message);
        });
}
document.getElementById("printButton").addEventListener("click", function () {
    window.print();
});
document.getElementById("printButton-lp").addEventListener("click", function () {
    window.print();
});
function fetchDataLp(trackingNumber) {
    fetch('/customer/shippingLabel/' + trackingNumber)
        .then(response => {
            if(!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data =>{
            document.getElementById("tracking-number-lp").innerText ='Numer listu przewozowego:' + '\n' + trackingNumber;
            let barcodeElement = document.getElementById("barcode-lp");


            while (barcodeElement.firstChild) {
                barcodeElement.removeChild(barcodeElement.firstChild);
            }

            let barcodeImage = document.createElement("img");
            barcodeImage.src = "data:image/png;base64," + data.barcode;
            barcodeElement.appendChild(barcodeImage);

            document.querySelector(".sender-data").innerText =  data.nameRecipient + "\n" +
                data.zipCodeRecipient + "  " +
                data.cityRecipient + "\n" +
                data.streetRecipient
            document.querySelector(".recipient-data").innerText = data.nameCompanySender + "\n" +
                data.zipCodeSender + "  " +
                data.citySender + "\n" +
                data.streetSender;

            document.querySelector(".send-date").innerText= " Data nadania:   " + data.orderCreated;
            document.getElementById("type").innerText = data.dimensions;
            let dimensionsValue;
            if (data.dimensions ==='EUR' ){
                dimensionsValue = "120x80";
            } else {
                dimensionsValue = "60x80";
            }
            document.getElementById("dimension").innerText = dimensionsValue;
            document.getElementById("weigh").innerText = data.weigh;

        })
        .catch(error=> {
            console.log('There was a problem with the fetch operation:', error.message)
        });
    let modal = new bootstrap.Modal(document.getElementById('modalLp'));
    modal.show();
}