document.querySelectorAll('.driverSelect').forEach(selectElement => {
    selectElement.addEventListener('change', function() {
        let orderId = this.getAttribute('data-order-id');
        let driverId = this.value;

        updateOrderDriver(orderId, driverId);
    });
});

function updateOrderDriver(orderId, driverId) {
    fetch(`/employee/updateOrderDriver/${orderId}/${driverId}`, {
        method: 'POST',
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Aktualizacja powiodła się
            } else {
                // Wystąpił błąd
            }
        });
}