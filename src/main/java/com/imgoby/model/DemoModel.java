package com.imgoby.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DemoModel implements Serializable {
    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    @ColumnWidth(25)
    private String mobile;

    @ExcelProperty("运营商")
    @ColumnWidth(25)
    private Integer operators;
}
