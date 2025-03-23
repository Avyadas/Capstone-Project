package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private static final String FILE_PATH = "C:\\Users\\avyab\\eclipse-workspace\\Luma\\LoginData.xlsx";

    public static String[] getLoginData() {
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(1);
            Row row = sheet.getRow(1); // First row (excluding headers)

            String email = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            return new String[]{email, password};

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"", ""}; // Default empty values
    }
}
