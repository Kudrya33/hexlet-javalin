package gg.jte.generated.ondemand;
import org.example.hexlet.dto.MainPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,13,13,13,14,14,14,16,16,20,20,20,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"ru\">\n  <head>\n    <meta charset=\"utf-8\" />\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n    <title>Hello Hexlet!</title>\n  </head>\n  <body>\n    <main>\n      <h1>Привет, Хекслет!</h1>\n      ");
		if (page.getCurrentUser() != null) {
			jteOutput.writeContent("\n        Добро пожаловать, ");
			jteOutput.setContext("main", null);
			jteOutput.writeUserContent(page.getCurrentUser());
			jteOutput.writeContent(".\n        Чтобы разлогиниться, удалите куку JSESSIONID из браузера\n      ");
		}
		jteOutput.writeContent("\n    </main>\n  </body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
