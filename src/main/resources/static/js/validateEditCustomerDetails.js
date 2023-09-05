document.addEventListener("DOMContentLoaded", function () {
    const nameCompanySender = document.getElementById('nameCompanySender');
    const zipCodeSender = document.getElementById('zipCodeSender');
    const citySender = document.getElementById('citySender');
    const streetSender = document.getElementById('streetSender');
    const contactSender = document.getElementById('contactSender');

    nameCompanySender.addEventListener('input', function () {
        if (this.value.length < 5 || this.value.length > 30) {
            this.nextElementSibling.textContent = 'Nazwa firmy musi być od 5 do 30 znaków';
        } else {
            this.nextElementSibling.textContent = '';
        }
    });

    zipCodeSender.addEventListener('input', function () {
        const regex = /^[0-9]{2}-[0-9]{3}$/; // Prosty wzorzec dla kodu pocztowego XX-XXX
        if (!regex.test(this.value)) {
            this.nextElementSibling.textContent = 'Nieprawidłowy kod pocztowy';
        } else {
            this.nextElementSibling.textContent = '';
        }
    });

    citySender.addEventListener('input', function () {
        if (this.value.length < 3) {
            this.nextElementSibling.textContent = 'Nazwa miasta musi się składać conajmniej z 3 liter';
        } else {
            this.nextElementSibling.textContent = '';
        }
    });

    streetSender.addEventListener('input', function () {
        if (this.value.length < 3) {
            this.nextElementSibling.textContent = 'Nazwa ulicy musi się składać conajmniej z 3 liter';
        } else {
            this.nextElementSibling.textContent = '';
        }
    });

    contactSender.addEventListener('input', function () {
        // Przykładowy wzorzec dla numeru telefonu
        const regex = /^[0-9]{9}$/; // Dziewięć cyfr
        if (!regex.test(this.value)) {
            this.nextElementSibling.textContent = 'Nieprawidłowy numer telefonu';
        } else {
            this.nextElementSibling.textContent = '';
        }
    });

    document.getElementById('customerDetailsForm').addEventListener('submit', function (e) {
        const errorMessages = document.querySelectorAll('.error');
        for (let msg of errorMessages) {
            if (msg.textContent !== '') {
                e.preventDefault();
                return;
            }
        }
    });
});
