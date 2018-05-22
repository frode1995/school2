package com.frode.system.service.impl;

import com.frode.system.dao.TestDao;
import com.frode.system.domain.TestDO;
import com.frode.system.service.TestService;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testMapper;

    @Override
    public int save(TestDO testDO) {
        return testMapper.save(testDO);
    }

    @Override
    public String xlsToDB(MultipartFile file)  {
        if (file.isEmpty()) {
            System.out.println("文件为空");
            return "false";
        }
        String fileName = file.getOriginalFilename();
        String filePath="D:/excel/";//传到d盘
        String str=filePath + fileName;//上传路径
        File dest = new File(str);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
                file.transferTo(dest);//文件存入目的地  //实例化文件类
                FileInputStream fis=new FileInputStream(new File(str));//实例化文件输入流
                Workbook book=Workbook.getWorkbook(fis);//获取工作簿对象
                Sheet sheet = book.getSheet(0);//获取工作表对象
                int rows = sheet.getRows();//获取工作表中的数据行数
                for(int i=1;i<rows;i++){//rows为4  1-3行 //循环Excel工作表的行，并读取单元格数据  第一行为列名
                    TestDO testDO=new TestDO();//必须在里面实例化对象  否则只输出最后一行
                    //new Long((long)i)   sheet.getCell(2, i)  第i行第2列
                    Long id=(long)Integer.parseInt(sheet.getCell(0,i).getContents());
                    String content=sheet.getCell(1,i).getContents();//0列和1列就够了
                    testDO.setId(id);
                    testDO.setContent(content);
                    testMapper.save(testDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("true");
        return "true";
    }




    /*int i=2;i<rows;i++//循环Excel工作表的行，并读取单元格数据  第一行为科目等*/
    //Image img = sheet.getDrawing(0);//获取工作表中的图片对象
    //db_subject [] ds = new db_subject[rows]; //写在外面生命周期长  改成类数组避免只存一行
    //System.out.println("行数");
    //System.out.println(rows);
    //db_subject ds = new db_subject();

}
