package tests.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.RandomDataGenerator;
import utils.RandomDataTypeNames;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pets {
   // private String gender = Stream.of("male","female","other").findAny().get();
    private String gender = Arrays.asList("male","female","other").get(RandomDataGenerator.getRandomNumber(0,3));
   // private Gender gender;
    //private Gender gender = Arrays.stream(Gender.values()).findAny().get();
    private int id = Integer.parseInt(RandomDataGenerator.getRandomNumber(6));
    private String name = RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FULL_NAME);
    private String categoryID = RandomDataGenerator.getRandomAlphabets(10);
    private String categoryName = RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FIRST_NAME);
    private int tagID = Integer.parseInt(RandomDataGenerator.getRandomNumber(4));
    private String tagName = RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.LAST_NAME);
    private String status = RandomDataGenerator.getRandomAlphabets(10);

}
