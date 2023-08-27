fetch('/employee/orderStatsForCurrentMonth')
    .then(response => response.json())
    .then(data => {
        const ctx = document.getElementById('myChart').getContext('2d');

        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Wszystkie zamówienia', 'Zamówienia w magazynie', 'Zamówienia w trakcie dostawy', 'Zrealizowane zamówienia', 'Użytkownicy', 'Kierowcy'],
                datasets: [{
                    label: 'Liczba przesyłek w tym miesiącu',
                    data: [data.totalOrders, data.ordersInWarehouse, data.ordersInDelivery, data.ordersDelivered, null, null],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(0,0,0,0)',
                        'rgba(0,0,0,0)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(0,0,0,0)',
                        'rgba(0,0,0,0)'
                    ],
                    borderWidth: 1
                },
                    {
                        label: 'Użytkownicy',
                        data: [null, null, null, null, data.totalUsers, null],
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    },
                    {
                        label: 'Kierowcy',
                        data: [null, null, null, null, null, data.totalDrivers],
                        backgroundColor: 'rgba(255, 159, 64, 0.2)',
                        borderColor: 'rgba(255, 159, 64, 1)',
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                plugins: {
                    legend: {
                        onClick: legendClickHandler
                    }
                }
            }
        });
    })
    .catch(error => {
        console.error('Error fetching the order statistics:', error);
    });

function legendClickHandler(event, legendItem, chart) {
    const index = legendItem.datasetIndex;
    const ci = chart.chart;

    // Zmienia wartość "hidden" dla konkretnego zbioru danych
    ci.data.datasets[index].hidden = !ci.data.datasets[index].hidden;

    // Aktualizuje wykres
    ci.update();
}
