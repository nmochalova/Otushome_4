package stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CourseStub {
  private final String basePath = "/cource/get";

  {
    getCources();
  }

  private void getCources() {
    String jsonString = "[\n"
            +            "  {\n"
            +            "    \"name\": \"QA java\",\n"
            +            "    \"price\": 15000\n"
            +            "  },\n"
            +            "  {\n"
            +            "    \"name\": \"Java\",\n"
            +            "    \"price\": 12000\n"
            +            "  }\n"
            +            "]";


    //Если будет get на {basePath}/store, то мы возвращаем json-объект и статус 200
    stubFor(get(urlEqualTo(String.format("%s/all", basePath)))
            .willReturn(aResponse()
                    .withBody(jsonString)
                    .withStatus(200)));
  }
}
