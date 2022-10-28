package stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserStub {
  private final String basePath = "/user/get";

  {
    getStudent();
  }

  private void getStudent() {
    String jsonString = "[\n"
            +            "  {\n"
            +            "    \"name\": \"Test user\",\n"
            +            "    \"cource\": \"QA\",\n"
            +            "    \"email\": \"test@test.test\",\n"
            +            "    \"age\": 23\n"
            +            "  }\n"
            +            "]";

    stubFor(get(urlEqualTo(String.format("%s/all",basePath)))
            .willReturn(aResponse()
                    .withBody(jsonString)
                    .withStatus(200)));
  }
}
