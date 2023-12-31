package tests.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
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
public class Pets extends CreatePets {
   // private String gender = Stream.of("male","female","other").findAny().get();
    private String gender = Arrays.asList("male","female","other").get(RandomDataGenerator.getRandomNumber(0,3));
   // private Gender gender;
    //private Gender gender = Arrays.stream(Gender.values()).findAny().get();
    private int id = Integer.parseInt(RandomDataGenerator.getRandomNumber(6));

    //Excel sheet mapping with Poiji java library
    @ExcelCell(0)
    @JsonIgnore
    private String idValue;

    @ExcelCellName("Name")
    private String name = RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FULL_NAME);

    @ExcelCellName("CategoryID")
    private String categoryID = RandomDataGenerator.getRandomAlphabets(10);

    @ExcelCellName("CategoryName")
    private String categoryName = RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FIRST_NAME);

    @ExcelCellName("TagID")
    private int tagID = Integer.parseInt(RandomDataGenerator.getRandomNumber(4));

    @ExcelCellName("TagName")
    private String tagName = RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.LAST_NAME);

    @ExcelCellName("Status")
    private String status = RandomDataGenerator.getRandomAlphabets(10);

}
