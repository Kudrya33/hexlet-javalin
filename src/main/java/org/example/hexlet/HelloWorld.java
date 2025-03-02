package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");
            if (name == null || name.isEmpty()) {
                ctx.result("Hello, World!");
            } else {
                ctx.result("Hello, " + name + "!");
            }

        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var id = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");

            ctx.result("Users: " + id + " Post ID: " + postId);

        });

        app.start(7070);
    }
}
