package com.algogrit.app;
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
import java.io.IOException;

import com.google.gson.*;

import java.util.logging.*;
import com.google.cloud.functions.*;

public class Rot13Encryption implements HttpFunction {
  public static Logger logger = Logger.getLogger(Rot13Encryption.class.getName());

  private static final Gson gson = new Gson();

  private String rot13Substitution(String message) {
    var response = new StringBuilder();
    for (int i = 0; i < message.length(); i++) {
      char c = message.charAt(i);
      if       (c >= 'a' && c <= 'm') c += 13;
      else if  (c >= 'A' && c <= 'M') c += 13;
      else if  (c >= 'n' && c <= 'z') c -= 13;
      else if  (c >= 'N' && c <= 'Z') c -= 13;
      response.append(c);
    }
    return response.toString();
  }

  public void service(HttpRequest request, HttpResponse response) throws IOException {
    var qp = request.getQueryParameters();
    var listOfValues = qp.get("name");
    String name = "World";

    logger.info("The secret code is set as: " + System.getenv("SECRET_CODE"));

    if (!"POST".equals(request.getMethod())) {
      response.setStatusCode(405);
      return;
    }

    if (listOfValues != null && (listOfValues.size() > 0)) {
      name = listOfValues.get(0);
    }

    var sb = new StringBuilder();
    sb.append("Hello, ").append(name).append("!\n");

    String message = "";

    try {
      JsonElement requestParsed = gson.fromJson(request.getReader(), JsonElement.class);
      JsonObject requestJson = null;

      if (requestParsed != null && requestParsed.isJsonObject()) {
        requestJson = requestParsed.getAsJsonObject();
      }

      if (requestJson != null && requestJson.has("message")) {
        message = requestJson.get("message").getAsString();
      }
    } catch (JsonParseException e) {
      // throw e;
      throw new RuntimeException(e);
      // // response.setStatusCode(400);
      // // var errorMsg = "Error parsing JSON: " + e.getMessage();

      // // response.getWriter().write(errorMsg);
      // // logger.severe(errorMsg);
      // return;
    }

    String responseMessage = "";
    if(message != null && message.length() > 0 ) {
      responseMessage = this.rot13Substitution(message);

    }

    if (responseMessage != null || responseMessage.length() > 0 ) {
      response.getWriter().write(responseMessage);
    } else {
      var writer = response.getWriter();
      writer.write(sb.toString());
    }

    // logger.info("Service was invoked...");
  }
}
