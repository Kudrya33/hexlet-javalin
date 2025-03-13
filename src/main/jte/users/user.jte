@import org.example.hexlet.NamedRoutes
@param java.util.List<org.example.hexlet.model.User> users
@param String flash

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Название вашего сайта</title>
</head>
<body>
    <p style="color: green;">${flash}</p>
    <h1>Список пользователей</h1>
    <ul>
        @for(var u : users)
            <li>${u.getName()} (${u.getEmail()})</li>
        @endfor
    </ul>
    <a href="${NamedRoutes.buildUserPath()}">Добавить нового пользователя</a>
</body>
</html>

