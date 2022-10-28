package stubs;

import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ScoreStub {
  private final String basePath = "/user/get";
  private int id;

  {
    getScore();
  }

  private void getScore() {
    Map<String,String> map = new HashMap<>();
    map.put("name","Test user");
    map.put("score","78");

    //Ответ на get запрос <base_url>/user/get/2
    stubFor(get(urlEqualTo(String.format("%s/%d",basePath,2)))
            .willReturn(aResponse()
                    .withBody(new JSONObject(map).toJSONString())
                    .withStatus(200)));
  }
}
