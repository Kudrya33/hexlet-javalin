package gg.jte.generated.ondemand.users;
public final class JteuserGenerated {
	public static final String JTE_NAME = "users/user.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,12,12,12,13,13,13,13,13,13,14,14,21,21,21,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<org.example.hexlet.model.User> users) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"ru\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Название вашего сайта</title>\n</head>\n<body>\n    <h1>Список пользователей</h1>\n    <ul>\n        ");
		for (var u : users) {
			jteOutput.writeContent("\n            <li>");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(u.getName());
			jteOutput.writeContent(" (");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(u.getEmail());
			jteOutput.writeContent(")</li>\n        ");
		}
		jteOutput.writeContent("\n    </ul>\n\n    <a href=\"/users/build\">Добавить нового пользователя</a>\n</body>\n</html>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<org.example.hexlet.model.User> users = (java.util.List<org.example.hexlet.model.User>)params.get("users");
		render(jteOutput, jteHtmlInterceptor, users);
	}
}
