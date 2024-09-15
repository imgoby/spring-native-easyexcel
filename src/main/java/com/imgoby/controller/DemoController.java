package com.imgoby.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.imgoby.model.DemoModel;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class DemoController {

    @GetMapping("/excel")
    public void excel(HttpServletResponse response) throws Exception {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 11);
        headWriteCellStyle.setWriteFont(headWriteFont);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, (WriteCellStyle) null);

        String filename = "1.xlsx";
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream(), DemoModel.class).registerWriteHandler(horizontalCellStyleStrategy).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("携号转网").build();


            List<DemoModel> exportList =new ArrayList<>();
            exportList.add(new DemoModel("13800001111",1));
            exportList.add(new DemoModel("13800002222",2));
            excelWriter.write(exportList, writeSheet);

        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
