package com.lgb.function.admin.finance.export.controller;

import com.lgb.arc.excel.Excel;
import com.lgb.arc.excel.ExcelFactory;
import com.lgb.arc.excel.mapper.FinanceExcelMapper;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.export.service.ExportServiceI;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/finance")
public class ExportController {

    @Autowired
    ExportServiceI exportService;

    @RequestMapping("/export")
    public void adminExport(HttpServletResponse response,Finance finance) {
        List<Finance> finances = exportService.exportFinance(finance);
        ExcelFactory<Finance> factory = new ExcelFactory<Finance>();
        File file = new File("finance.xls");

        try {
            WritableWorkbook workbook = factory.createExcel(new FileOutputStream(file),
                    new Excel("财务卡", 0), Arrays.asList("序号", "卡号", "姓名", "来源", "系别", "专业", "课程", "学费", "优惠","实际缴费","操作用户", "操作时间"), finances, new FinanceExcelMapper());
            workbook.write();
            workbook.close();

            response.setContentType("application/x-export");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            int length = 0;
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, length);
            }
            fis.close();
            os.flush();
            os.close();
        } catch (RowsExceededException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
