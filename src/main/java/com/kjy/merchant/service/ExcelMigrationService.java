package com.kjy.merchant.service;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.exception.BizException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ExcelMigrationService {

    public void migrateMember(String drmType, String tenantType ,MultipartFile file) {
        try{
            processExcelFile(file);
        }catch (Exception e) {
            throw new BizException(Code.ERROR, "마이그레이션 처리중 에러 발생", e);
        }

    }

    public void processExcelFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);

        DataFormatter dataFormatter = new DataFormatter();

        // 모든 시트에 대해 처리
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            // 모든 행과 셀에 대해 텍스트로 변환
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);  // 모든 셀을 텍스트로 변환
                    System.out.print(cellValue + "\t");
                }
                System.out.println();
            }
        }

        workbook.close();
        inputStream.close();
    }
}
