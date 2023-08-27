fetch('/driver/effectivenessRaport')  // Zamień URL na odpowiedni endpoint
    .then(response => response.json())
    .then(data => {
        createPieChart(data);
        document.getElementById('totalPackages').innerText = (data.delivered + data.notDelivered).toString();
        document.getElementById('deliveredPackages').innerText = data.delivered.toString();
        document.getElementById('notDeliveredPackages').innerText = data.notDelivered.toString();
        document.getElementById('effectiveness').innerText = data.effectivenessInPercent.toFixed(1);


    })
    .catch(error => {
        console.error('Error fetching the delivery report:', error);
    });

function createPieChart(data) {
    const ctxPie = document.getElementById('myChart').getContext('2d');  // używamy 'myChart' z twojego pierwotnego kodu

    new Chart(ctxPie, {
        type: 'pie',
        data: {
            labels: ['Dostarczone', 'Nie dostarczone'],
            datasets: [{
                data: [data.delivered, data.notDelivered],
                backgroundColor: [
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        }
    });
}
