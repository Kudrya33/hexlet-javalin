@import gg.jte.Content
@param Content content

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Название вашего сайта</title>
</head>
<body>
    <header>
        <nav>
            <h1>Courses</h1>
            <ul>
                <li><a href="/courses">Главная</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>Добро пожаловать на наш сайт!</h2>
        <p>Здесь будет основное содержимое страницы.</p>
    </main>


    <footer>
        ${content}
    </footer>
</body>
</html>
