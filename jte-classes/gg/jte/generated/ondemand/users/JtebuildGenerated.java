package gg.jte.generated.ondemand.users;
public final class JtebuildGenerated {
	public static final String JTE_NAME = "users/build.jte";
	public static final int[] JTE_LINE_INFO = {36,36,36,36,36,36,36,36,36,36,36};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"ru\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Название вашего сайта</title>\n</head>\n<body>\n    <form action=\"/users\" method=\"post\">\n      <div>\n        <label>\n          <p>Имя</p>\n          <input type=\"text\" name=\"name\" />\n        </label>\n      </div>\n      <div>\n        <label>\n          <p>Email</p>\n          <input type=\"email\" required name=\"email\" />\n        </label>\n      </div>\n      <div>\n        <label>\n          <p>Пароль</p>\n          <input type=\"password\" required name=\"password\" />\n        </label>\n      </div>\n      <div>\n        <label>\n          <p>Подтверждение пароля</p>\n          <input type=\"password\" required name=\"passwordConfirmation\" />\n        </label>\n      </div>\n      <input type=\"submit\" value=\"Зарегистрировать\" />\n    </form>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
