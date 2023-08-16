
function handleEtykietaClick(event) {
    event.preventDefault();

    let trackingNumber = event.target.closest('.accordion-header').querySelector('.list-header').innerText;

    fetch(`/customer/shippingLabel/${trackingNumber}`)
        .then(response => {
            if(!response.ok) {
                throw new Error('Nie udało się pobrać etykiety');
            }
            return response.json();
        })
        .then(data => {
            // Tutaj możesz uzupełnić modal danymi z obiektu data

            let modal = document.getElementById('shippingLabelModal');
            // na przykład:
            modal.querySelector('#text2').innerText = data;
            //... (uzupełnij inne elementy modalu odpowiednimi danymi)


        })
        .catch(error => {
            console.error('Błąd:', error);
        });
}
