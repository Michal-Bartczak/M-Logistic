document.addEventListener("DOMContentLoaded", function (){
    document.querySelectorAll(".btn-success").forEach(button => {
        button.addEventListener("click" , function (e){
            const orderId = e.target.getAttribute("data-order-id");
            sendToServer(orderId,"DOSTARCZONO");
        });
    });

    document.querySelectorAll(".btn-danger").forEach(button => {
        button.addEventListener("click", function (e){
            const orderId = e.target.getAttribute("data-order-id");
            sendToServer(orderId,"NIE DOSTARCZONO");
        });
    });
});

function sendToServer(orderId, status){
    fetch(`/driver/editStatus/${orderId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: status

    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            const orderContainer = document.querySelector(`#item-order`);
            if (orderContainer) {
                orderContainer.remove();
            }
        })
        .catch(error => {
            console.error(error);
        });
}
