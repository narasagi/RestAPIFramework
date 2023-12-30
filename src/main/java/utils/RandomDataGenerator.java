package utils;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

public static Faker faker = new Faker();

    public static String getRandomDataNamesFor(RandomDataTypeNames dataTypeNames){
        switch (dataTypeNames) {
            case FIRST_NAME:
                return faker.name().firstName();
            case LAST_NAME:
                return faker.name().lastName();
            case FULL_NAME:
                return faker.name().fullName();
            case COUNTRY:
                return faker.address().country();
            case CITY:
                return faker.address().cityName();
            default:
               return "Data type name is not available";
        }
    }

    public static String getRandomNumber(int count){
        return faker.number().digits(count);
    }

    public static int getRandomNumber(int min,int max){
        return faker.number().numberBetween(min, max);
    }

    public static String getRandomAlphabets(int count){
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getRandomWebsiteName(){
        return "https://"+RandomStringUtils.randomAlphabetic(10)+".com";
    }
}
