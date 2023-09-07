document.addEventListener('DOMContentLoaded', () => {
    function renderPagination(totalPages, currentPage) {
        console.log('Total Pages:', totalPages);
        console.log('Current Page:', currentPage);
        let paginationContainer = document.getElementById('pagination');
        paginationContainer.innerHTML = '';

        for (let i = 0; i < totalPages; i++) {
            let btn = document.createElement('button');
            btn.innerText = i + 1;
            btn.dataset.pageNumber = i; // Przypisanie wartości i jako atrybutu danych

            if (i === currentPage) {
                btn.classList.add('pagination-btn-active');
            } else {
                btn.classList.add('pagination-btn');
            }

            btn.addEventListener('click', function() {
                let page = parseInt(this.dataset.pageNumber); // Pobranie wartości i z atrybutu danych
                filterOrders(page, 10, false, currentPage); // Wywołanie filterOrders z prawidłową wartością strony
            });
            paginationContainer.appendChild(btn);
        }
    }

    function filterOrders(page = 0, size = 10, forceResetPage = false, currentPage) {
        console.log('Filter Orders - Page:', page);
        console.log('Filter Orders - Size:', size);
        console.log('Filter Orders - Force Reset Page:', forceResetPage);
        if (forceResetPage) {
            page = 0;
        }
        let filterText = document.getElementById('filter-text').value;
        let filterData = document.getElementById('filter-data').value;
        let status = document.getElementById('status').value;
        let rodzajEurCheckbox = document.getElementById('kind-eur');
        let rodzajHpCheckbox = document.getElementById('kind-hp');

        let kindEur = rodzajEurCheckbox.checked ? rodzajEurCheckbox.value : null;
        let kindHp = rodzajHpCheckbox.checked ? rodzajHpCheckbox.value : null;

        let params = {
            filterText: filterText,
            filterData: filterData,
            status: status,
            kindEur: kindEur,
            kindHp: kindHp,
            page: page,
            size: size
        };

        fetch(`/employee/filter?page=${params.page}&size=${params.size}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(params)
        })
            .then(response => response.json())
            .then(data => {

                let contentListContainer = document.querySelector('.content-list');

                contentListContainer.innerHTML = '';

                if (data.message) {
                    document.getElementById("noOrdersMessage").style.display = "block";
                } else {
                    document.getElementById("noOrdersMessage").style.display = "none";


                    contentListContainer.innerHTML = data.orders.map((order, index) => `
                     <div class="col-div-3">
                        <div class="accordion-header">
                            <button class="btn btn-link" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse${index}" aria-expanded="true"
                                    aria-controls="collapse${index}">
                                <div class="row">
                                    <div class="col-3 text-start">
                                        <p class="list-header">Numer zamówienia</p>
                                        <p class="list">${order.trackingNumber}</p>
                                    </div>
                                    <div class="col-3 text-center">
                                        <p class="list-header">Dane odbiorcy</p>
                                        <p class="list">${order.nameRecipient},${order.zipCodeRecipient} ${order.cityRecipient},
                                            ${order.streetRecipient} </p>
                                    </div>
                                    <div class="col-3 text-center">
                                        <p class="list-header">Data utworzenia</p>
                                        <p class="list">${order.creationDate}</p>
                                    </div>
                                    <div class="col-3 text-end">
                                        <p class="list-header">Status</p>
                                        <p class="list">${order.status}</p>
                                    </div>
                                </div>
                            </button>
                        </div>
                        <div id="collapse${index}" class="collapse" data-bs-parent="#accordionExample">
                            <div class="box row">
                                <div class="col-2">${order.dimensions}</div>
                                <div class="col-2 text-center">${order.price} zł</div>
                                <div class="col-2 text-center">${order.weigh} kg</div>
                                <div class="col-2 text-center ">
                                    <a href="#" data-bs-toggle="modal-lp" id="trackingNumberButton-lp"
                                       onClick="fetchDataLp('${order.trackingNumber}')" class="link-details-lp">List
                                        przewozowy</a>
                                </div>
                                <div class="col-2 text-center">
                                    <a href="#" data-bs-toggle="modal" data-bs-target="#shippingLabelModal"
                                       id="trackingNumberButton" onClick="fetchLabelData('${order.trackingNumber}')"
                                       class="link-details">Etykieta</a>
                                </div>
                                <div class="col-2 text-end">
                                    <select class="driverSelect" data-order-id="${order.id}">
                                        <option value="${order.provider}">${order.provider}</option>
                                        ${data.drivers.map(driver =>
                        `<option value="${driver.id}">${driver.name} ${driver.surname}</option>`).join('')}
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                `).join('');
                    document.querySelectorAll('.driverSelect').forEach(selectElement => {
                        selectElement.addEventListener('change', function() {
                            let orderId = this.getAttribute('data-order-id');
                            let driverId = this.value;

                            updateOrderDriver(orderId, driverId);
                        });
                    });
                    renderPagination(data.totalPages, page);
                }
            });
    }

    document.getElementById('filter-text').addEventListener('input', () => filterOrders(0, 10, true, 0));
    document.getElementById('filter-data').addEventListener('input', () => filterOrders(0, 10, true, 0));
    document.getElementById('status').addEventListener('change', () => filterOrders(0, 10, true, 0));
    document.getElementById('kind-eur').addEventListener('change', () => filterOrders(0, 10, true, 0));
    document.getElementById('kind-hp').addEventListener('change', () => filterOrders(0, 10, true, 0));

    filterOrders(0, 10, true, 0);
});
