package gg.jte.generated.ondemand.users;
import org.example.hexlet.NamedRoutes;
public final class JteuserGenerated {
	public static final String JTE_NAME = "users/user.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,12,12,12,12,15,15,16,16,16,16,16,16,17,17,19,19,19,19,19,19,19,19,19,23,23,23,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<org.example.hexlet.model.User> users, String flash) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"ru\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Название вашего сайта</title>\n</head>\n<body>\n    <p style=\"color: green;\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(flash);
		jteOutput.writeContent("</p>\n    <h1>Список пользователей</h1>\n    <ul>\n        ");
		for (var u : users) {
			jteOutput.writeContent("\n            <li>");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(u.getName());
			jteOutput.writeContent(" (");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(u.getEmail());
			jteOutput.writeContent(")</li>\n        ");
		}
		jteOutput.writeContent("\n    </ul>\n    <a");
		var __jte_html_attribute_0 = NamedRoutes.buildUserPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Добавить нового пользователя</a>\n</body>\n</html>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<org.example.hexlet.model.User> users = (java.util.List<org.example.hexlet.model.User>)params.get("users");
		String flash = (String)params.get("flash");
		render(jteOutput, jteHtmlInterceptor, users, flash);
	}
}
