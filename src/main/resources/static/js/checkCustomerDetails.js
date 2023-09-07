window.onload = function() {
    fetch('/customer/hasCustomerDetails')
        .then(response => response.json())
        .then(hasDetails => {
            if (!hasDetails) {
                document.getElementById("submitButton").disabled = true;

                // Znajdź kontener i formularz
                const containerDiv = document.querySelector(".container.mt-5.ml-3");
                const form = document.getElementById("detailsPackage");

                // Stwórz nowy div z wiadomością ostrzeżenia
                const alertDiv = document.createElement('div');
                alertDiv.className = 'alert alert-danger';
                alertDiv.style.width = '30%';
                alertDiv.style.color = 'white';
                alertDiv.style.border = 'none';
                alertDiv.style.backgroundColor = '#CB6B4D';

                const alertParagraph = document.createElement('p');
                alertParagraph.innerHTML = 'Bez uzupełnienia swoich danych,<br>nie możesz wysłać paczki!';
                alertDiv.appendChild(alertParagraph);

                // Wstaw nowy div przed formularzem
                containerDiv.insertBefore(alertDiv, form);
            }
        })
        .catch(error => console.error('Wystąpił błąd podczas pobierania szczegółów:', error));
};
