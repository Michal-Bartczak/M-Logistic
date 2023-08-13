<!DOCTYPE html>
<html>
<head>
    <title>Formularz pracownika</title>
</head>
<body>

<form action="/159406" method="post">
    <!-- Nazwa użytkownika -->
    <label for="username">Nazwa użytkownika:</label>
    <input type="text" id="username" name="username" required minlength="5" maxlength="15">
    <br>

    <!-- E-mail -->
    <label for="email">E-mail:</label>
    <input type="email" id="email" name="email" required>
    <br>

    <!-- Hasło -->
    <label for="password">Hasło:</label>
    <input type="password" id="password" name="password" required minlength="5">
    <br>

    <!-- Imię -->
    <label for="name">Imię:</label>
    <input type="text" id="name" name="name" required minlength="3">
    <br>

    <!-- Nazwisko -->
    <label for="surname">Nazwisko:</label>
    <input type="text" id="surname" name="surname" required minlength="3" maxlength="15">
    <br>

    <!-- Rola (ukryta, domyślnie "EMPLOYEE") -->
    <input type="hidden" name="role" value="EMPLOYEE">

    <!-- Przycisk do wysłania formularza -->
    <input type="submit" value="Dodaj pracownika">
</form>

</body>
</html>
