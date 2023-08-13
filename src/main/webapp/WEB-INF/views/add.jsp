<!DOCTYPE html>
<html>
<head>
    <title>Formularz pracownika</title>
</head>
<body>

<form action="/path_to_your_endpoint" method="post">

    <!-- Pole nazwa użytkownika -->
    <div>
        <label for="username">Nazwa użytkownika:</label>
        <input type="text" id="username" name="username" required minlength="5" maxlength="15" />
    </div>

    <!-- Pole email -->
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required />
    </div>

    <!-- Pole hasło -->
    <div>
        <label for="password">Hasło:</label>
        <input type="password" id="password" name="password" required minlength="5" />
    </div>

    <!-- Pole imię -->
    <div>
        <label for="name">Imię:</label>
        <input type="text" id="name" name="name" required minlength="3" />
    </div>

    <!-- Pole nazwisko -->
    <div>
        <label for="surname">Nazwisko:</label>
        <input type="text" id="surname" name="surname" required minlength="3" maxlength="15" />
    </div>

    <!-- Przycisk do wysłania formularza -->
    <div>
        <button type="submit">Zapisz pracownika</button>
    </div>

</form>

</body>
</html>
