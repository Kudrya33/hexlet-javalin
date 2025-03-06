@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

@if(page.getErrors() != null)
      <ul>
          @for(var validator : page.getErrors().values())
              @for(var error : validator)
                  <li>${error.getMessage()}</li>
              @endfor
          @endfor
      </ul>
@endif

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Название вашего сайта</title>
</head>
<body>
    <form action="/users" method="post">
      <div>
        <label>
          <p>Имя</p>
          <input type="text" name="name" />
        </label>
      </div>
      <div>
        <label>
          <p>Email</p>
          <input type="email" required name="email" />
        </label>
      </div>
      <div>
        <label>
          <p>Пароль</p>
          <input type="password" required name="password" />
        </label>
      </div>
      <div>
        <label>
          <p>Подтверждение пароля</p>
          <input type="password" required name="passwordConfirmation" />
        </label>
      </div>
      <input type="submit" value="Зарегистрировать" />
    </form>
</body>
</html>