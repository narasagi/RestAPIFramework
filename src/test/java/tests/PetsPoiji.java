package tests;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;
import lombok.ToString;

import java.util.Map;

@ToString
public class PetsPoiji {
    //Poiji java laibrary is used to mapping excel sheet cells to POJO
    @ExcelCell(0)
    private int id;
    @ExcelCellName("Name")
    private String name;
    @ExcelCellName("CategoryID")
    private String categoryID;
    @ExcelCellName("CategoryName")
    private String categoryName;
    @ExcelCellName("TagID")
    private int tagID;
    @ExcelCellName("TagName")
    private String tagName;
    @ExcelCellName("Status")
    private String status;

    //It is using for mapping newly added cells from excel sheet
    @ExcelUnknownCells
    private Map<String,String> unknownCells;
}
