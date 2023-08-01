
    let sendPackageButton = document.getElementById("sendPackageButton");
    sendPackageButton.addEventListener('click', function (e){
    e.preventDefault();
    let additionalText = document.getElementById("additionalText");
    additionalText.innerText = "Aby wysłać paczkę, musisz się zalogować !";
    additionalText.style.color = "#f00";
})
