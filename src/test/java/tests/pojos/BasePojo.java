package tests.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePojo {

    @JsonIgnore
    private String scenarioID;
    @JsonIgnore
    private String ScenarioDesc;
    @JsonIgnore
    private int expectedStatusCode;
    @JsonIgnore
    private String expectedErrorMessage;
}
