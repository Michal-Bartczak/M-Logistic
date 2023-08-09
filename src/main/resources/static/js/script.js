let sendPackageButton = document.getElementById("sendPackageButton");
if (sendPackageButton) {
    sendPackageButton.addEventListener('click', function (e){
        e.preventDefault();
        let additionalText = document.getElementById("additionalText");
        additionalText.innerText = "Aby wysłać paczkę, musisz się zalogować !";
        additionalText.style.color = "#f00";
    });
}

function showRegistrationSuccessMessage() {
    let additionalText = document.getElementById("successLog");
    additionalText.innerText = "Udało Ci się założyć konto, teraz możesz się zalogować!";
    additionalText.style.color = "#000";
}


