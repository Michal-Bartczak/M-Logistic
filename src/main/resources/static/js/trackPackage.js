function checkStatus() {
    const trackingNumber = document.getElementById('trackingNumber').value;
    const errorField = document.getElementById('errorTrackingNumber');
    const statusContainer = document.getElementById('statusContainer');

    if (!/^[0-9]{10}$/.test(trackingNumber)) {
        errorField.innerText = 'Błędny numer paczki. Upewnij się, że wprowadziłeś dokładnie 10 cyfr.';
        errorField.style.display = 'block';
        return;
    } else {
        errorField.style.display = 'none';  // ukryj komunikat o błędzie, jeśli wszystko jest w porządku
    }

    fetch('/track-package/status', {
        method: 'POST',
        body: trackingNumber
    })

        .then(response => {
            if (response.status === 404) {
                errorField.innerText = "Nie znaleziono paczki o numerze: " + trackingNumber;
                errorField.style.display = 'block';
                throw new Error("Nie znaleziono paczki");
            }
            return response.json();
        })
        .then(data => {
            debugger;

            if (data.orderStatus === "MAGAZYN") {
                document.getElementById('status1').style.opacity = 1;
                document.getElementById('status2').style.opacity = 0.3;
                document.getElementById('status3').style.opacity = 0.3;
            } else if(data.orderStatus === "DOSTAWA"){
                document.getElementById('status1').style.opacity = 0.3;
                document.getElementById('status2').style.opacity = 1;
                document.getElementById('status3').style.opacity = 0.3;
            } else if(data.orderStatus === "DOSTARCZONO"){
                document.getElementById('status1').style.opacity = 0.3;
                document.getElementById('status2').style.opacity = 0.3;
                document.getElementById('status3').style.opacity = 1;
            }
            statusContainer.style.display = 'block';
            if (data.orderStatus === null){
                statusContainer.style.display = 'none';
                errorField.innerText = data.errorMessage;
                errorField.style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.getElementById('trackingNumber').addEventListener('input', function () {
    document.getElementById('errorTrackingNumber').style.display = 'none';
});
