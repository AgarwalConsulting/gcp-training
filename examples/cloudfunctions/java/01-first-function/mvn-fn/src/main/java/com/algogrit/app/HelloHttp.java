/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2021 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package com.algogrit.app;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.io.InputStreamReader;
import java.io.*;
import java.util.logging.Logger;

// import com.google.cloud.functions.HttpFunction;
// import com.google.cloud.functions.HttpRequest;
// import com.google.cloud.functions.HttpResponse;
import com.google.cloud.functions.*;

// import com.google.gson.Gson;
// import com.google.gson.JsonElement;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParseException;
import com.google.gson.*;

public class HelloHttp implements HttpFunction {
  private static final Logger logger = Logger.getLogger(HelloHttp.class.getName());

  private static final Gson gson = new Gson();

  @Override
  public void service(HttpRequest request, HttpResponse response)
      throws IOException {
    // Check URL parameters for "name" field
    // "world" is the default value
    String name = request.getFirstQueryParameter("name").orElse("world");

    // Parse JSON request and check for "name" field
    try {
      // var in = request.getInputStream();
      // var reader = new InputStreamReader(in, "UTF-8");
      // JsonElement requestParsed = gson.fromJson(reader, JsonElement.class);

      JsonElement requestParsed = gson.fromJson(request.getReader(), JsonElement.class);


      JsonObject requestJson = null;

      if (requestParsed != null && requestParsed.isJsonObject()) {
        logger.info(requestParsed.toString());
        requestJson = requestParsed.getAsJsonObject();
      }

      if (requestJson != null && requestJson.has("name")) {
        name = requestJson.get("name").getAsString();
      }
    } catch (JsonParseException e) {
      logger.severe("Error parsing JSON: " + e.getMessage());
    }

    var writer = new PrintWriter(response.getWriter());
    writer.printf("Hello %s!", name);
  }
}
