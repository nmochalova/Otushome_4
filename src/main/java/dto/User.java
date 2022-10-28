package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class User {
  @JsonProperty("name")
  private String name;

  @JsonProperty("cource")
  private String cource;

  @JsonProperty("email")
  private String email;

  @JsonProperty("age")
  private int age;
}
