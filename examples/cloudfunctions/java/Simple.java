import java.io.IOException;
import java.io.PrintWriter;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

public class Simple implements HttpFunction {
  @Override
  public void service(HttpRequest request, HttpResponse response) throws IOException {
    var writer = new PrintWriter(response.getWriter())
    writer.printf("Hello from simple function!")
  }
}
