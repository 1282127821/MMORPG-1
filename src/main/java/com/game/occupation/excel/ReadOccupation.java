package com.game.occupation.excel;

import com.alibaba.fastjson.JSONObject;
import com.game.annotation.ExcelAnnotation;
import com.game.occupation.bean.Occupation;
import com.game.occupation.manager.OccupationMap;
import com.game.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取Excel文件
 * @author lmb
 * @date 2017-3-15
 *
 */
@Slf4j
@ExcelAnnotation
@Component
public class ReadOccupation {
    /**
     * 文件路径
     */
	private static final String FILEPATH = "src/main/resources/excel/Occupation.xls";

    /**
     * 读取excel文件
     */
	@ExcelAnnotation
	public static void readFromXLSX2007() {
         //Excel文件对象
        File excelFile = null;
         //输入流对象
        InputStream is = null;
         //单元格，最终按字符串处理
        String cellStr = null;
        try {
            // 获取文件输入流
            excelFile = new File(FILEPATH);
            is = new FileInputStream(excelFile);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            // 开始循环遍历行，表头不处理，从1开始
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                // 实例化对象
                Occupation occupation = new Occupation();
                // 获取行对象
            	Row row = sheet.getRow(i);
                // 如果为空，不处理
                if (row == null) {
                    continue;  
                }  
                // 循环遍历单元格(每一列)
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    // 获取单元格对象
                	Cell cell = row.getCell(j);
                    // 单元格为空设置cellStr为空串
                    cellStr =  ExcelUtils.returnCellStr(cell);
                    // 下面按照数据出现位置封装到bean中
                    if(j == 0) {
                        occupation.setId(new Double(cellStr).intValue());
                    }else if (j == 1) {
                        occupation.setName(cellStr);
                    }else if (j == 2) {
                        occupation.setAttract(new Double(cellStr).intValue());
                    }else if (j == 3) {
                        occupation.setDescription(cellStr);
                    }else if (j == 4) {
                        occupation.setProperty(JSONObject.parseObject(cellStr));
                    }

                }
                // 数据装入List
                OccupationMap.getOccupationMap().put(occupation.getId(),occupation);
            }
            log.info("occupation静态数据加载完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            // 关闭文件流
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
}  