function closeLanguageBox() {
    let box = document.getElementById('languageSelector');
    box.style.display = 'none';
}
function setCookie(name, value, exhours) {
    let d = new Date();
    d.setTime(d.getTime() + (exhours * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

function getCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}
function setLanguageCookie(lang) {
    setCookie("preferredLanguage", lang, 1); // Ustawia ciasteczko na godzinę
}
function setLanguageCookieAndClose(event, lang) {
    event.preventDefault(); // Zapobieganie natychmiastowemu przekierowaniu
    setCookie("preferredLanguage", lang, 1);
    closeLanguageBox();
    window.location.href = "?lang=" + lang; // Przekierowanie po zamknięciu boxu
}
function checkLanguagePreference() {
    let preferredLanguage = getCookie("preferredLanguage");
    if (preferredLanguage) {
        closeLanguageBox();
    }
}
window.onload = checkLanguagePreference;