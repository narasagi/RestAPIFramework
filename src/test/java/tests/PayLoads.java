package tests;

import net.datafaker.Faker;
import tests.pojos.Pets;
import utils.RandomDataGenerator;
import utils.RandomDataTypeNames;

import java.util.HashMap;
import java.util.Map;

public class PayLoads {

    public static String readJsonDataFromString(String id,String categotyID,String categoryName,
                                                String name,String tagID,String tagName,String status){
        String payLoad = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": "+categotyID+",\n" +
                "    \"name\": \""+categoryName+"\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": "+tagID+",\n" +
                "      \"name\": \""+tagName+"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+status+"\"\n" +
                "}";
        return payLoad;
    }

    public static Map<String, Object> readJsonDataFromMap(String id, String categotyID, String categoryName,
                                                           String name, String tagID, String tagName, String status){

            Map<String,Object> payLoad = new HashMap<>();
            payLoad.put("id",id);
            payLoad.put("category.id",categotyID);
            payLoad.put("category.name",categoryName);
            payLoad.put("name",name);
            payLoad.put("tags.id",tagID);
            payLoad.put("tags.name",tagName);
            payLoad.put("status",status);

    return payLoad;
    }
    public static Map<String, Object> readAutoDataFromMap(){

        Map<String,Object> payLoad = new HashMap<>();
        Faker faker = new Faker();
        payLoad.put("id", RandomDataGenerator.getRandomNumber(10));
        payLoad.put("category.id",RandomDataGenerator.getRandomAlphabets(10));
        payLoad.put("category.name",RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FULL_NAME));
        payLoad.put("name",RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FIRST_NAME));
        payLoad.put("tags.id",RandomDataGenerator.getRandomNumber(5));
        payLoad.put("tags.name",RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.LAST_NAME));
        payLoad.put("status", RandomDataGenerator.getRandomAlphabets(10));

        return payLoad;
    }

    public static Pets getCreatePetsPayloadFromPojo(){
        return  Pets
                .builder()
                .id(Integer.parseInt(RandomDataGenerator.getRandomNumber(6)))
                .name(RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FIRST_NAME))
                .categoryID(RandomDataGenerator.getRandomAlphabets(10))
                .categoryName(RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.FULL_NAME))
                .tagID(Integer.parseInt(RandomDataGenerator.getRandomNumber(5)))
                .tagName(RandomDataGenerator.getRandomDataNamesFor(RandomDataTypeNames.LAST_NAME))
                .status(RandomDataGenerator.getRandomAlphabets(10))
                .build();
    }
}
