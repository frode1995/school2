package com.frode.system.service.impl;

import com.frode.system.dao.PaperDao;
import com.frode.system.domain.PaperDO;
import com.frode.system.service.PaperService;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService{

    @Autowired
    private PaperDao paperMapper;

    @Override
    public PaperDO get(Long paperId) {
        return paperMapper.get(paperId);
    }

    @Override
    public List<PaperDO> list(Map<String, Object> map) {
        //为了content的显示  这波操作可以的
        List<PaperDO> modelList=paperMapper.list(map);
        for(PaperDO paperDO:modelList){
            if("5".equals(paperDO.getIsJournal())){
                paperDO.setIsJournal("是");
            }
            if("6".equals(paperDO.getIsJournal())){
                paperDO.setIsJournal("否");
            }
            if("5".equals(paperDO.getSci())){
                paperDO.setSci("是");
            }
            if("6".equals(paperDO.getSci())){
                paperDO.setSci("否");
            }
            if("5".equals(paperDO.getEij())){
                paperDO.setEij("是");
            }
            if("6".equals(paperDO.getEij())){
                paperDO.setEij("否");
            }
            if("5".equals(paperDO.getEim())){
                paperDO.setEim("是");
            }
            if("6".equals(paperDO.getEim())){
                paperDO.setEim("否");
            }
            if("5".equals(paperDO.getCscd())){
                paperDO.setCscd("是");
            }
            if("6".equals(paperDO.getCscd())){
                paperDO.setCscd("否");
            }
            if("5".equals(paperDO.getCnCore())){
                paperDO.setCnCore("是");
            }
            if("6".equals(paperDO.getCnCore())){
                paperDO.setCnCore("否");
            }
            if("5".equals(paperDO.getCpci())){
                paperDO.setCpci("是");
            }
            if("6".equals(paperDO.getCpci())){
                paperDO.setCpci("否");
            }
            if("5".equals(paperDO.getSsci())){
                paperDO.setSsci("是");
            }
            if("6".equals(paperDO.getSsci())){
                paperDO.setSsci("否");
            }
            if("5".equals(paperDO.getCssci())){
                paperDO.setCssci("是");
            }
            if("6".equals(paperDO.getCssci())){
                paperDO.setCssci("否");
            }
            if("5".equals(paperDO.getAhci())){
                paperDO.setAhci("是");
            }
            if("6".equals(paperDO.getAhci())){
                paperDO.setAhci("否");
            }

        }
            return modelList;
    }
//        return paperMapper.list(map);


    @Override
    public int count(Map<String, Object> map) {
        return paperMapper.count(map);
    }

    @Override
    public int save(PaperDO paper) {
        String full="SCI:"+paper.getSciNum();
        paper.setSciNum(full);
        String full2="EI:"+paper.getEiNum();
        paper.setEiNum(full2);
        String full3="ISSN:"+paper.getIssnNum();
        paper.setIssnNum(full3);
        paper.setPaperNew(new Date());//设置最新时间
        paper.setUserId(paperMapper.getUserIdByName(paper.getPaperAuthor()));//作者转换为userId
        return paperMapper.save(paper);
    }

    @Override
    public int update(PaperDO paper) {
        String full="SCI:"+paper.getSciNum();
        paper.setSciNum(full);
        String full2="EI:"+paper.getEiNum();
        paper.setEiNum(full2);
        String full3="ISSN:"+paper.getIssnNum();
        paper.setIssnNum(full3);
        paper.setPaperNew(new Date());//设置最新时间
        paper.setUserId(paperMapper.getUserIdByName(paper.getPaperAuthor()));//作者转换为userId
        return paperMapper.update(paper);
    }

    @Override
    public int remove(Long paperId) {
        return paperMapper.remove(paperId);
    }

    @Override
    public int batchRemove(Long[] paperIds) {
        return paperMapper.batchRemove(paperIds);
    }

    @Override
    public List<PaperDO> listMyPaper(Map<String, Object> map, Long userId) {
        List<PaperDO> modelList=paperMapper.listMyPaper(map,userId);
        for(PaperDO paperDO:modelList){
            if("5".equals(paperDO.getIsJournal())){
                paperDO.setIsJournal("是");
            }
            if("6".equals(paperDO.getIsJournal())){
                paperDO.setIsJournal("否");
            }
            if("5".equals(paperDO.getSci())){
                paperDO.setSci("是");
            }
            if("6".equals(paperDO.getSci())){
                paperDO.setSci("否");
            }
            if("5".equals(paperDO.getEij())){
                paperDO.setEij("是");
            }
            if("6".equals(paperDO.getEij())){
                paperDO.setEij("否");
            }
            if("5".equals(paperDO.getEim())){
                paperDO.setEim("是");
            }
            if("6".equals(paperDO.getEim())){
                paperDO.setEim("否");
            }
            if("5".equals(paperDO.getCscd())){
                paperDO.setCscd("是");
            }
            if("6".equals(paperDO.getCscd())){
                paperDO.setCscd("否");
            }
            if("5".equals(paperDO.getCnCore())){
                paperDO.setCnCore("是");
            }
            if("6".equals(paperDO.getCnCore())){
                paperDO.setCnCore("否");
            }
            if("5".equals(paperDO.getCpci())){
                paperDO.setCpci("是");
            }
            if("6".equals(paperDO.getCpci())){
                paperDO.setCpci("否");
            }
            if("5".equals(paperDO.getSsci())){
                paperDO.setSsci("是");
            }
            if("6".equals(paperDO.getSsci())){
                paperDO.setSsci("否");
            }
            if("5".equals(paperDO.getCssci())){
                paperDO.setCssci("是");
            }
            if("6".equals(paperDO.getCssci())){
                paperDO.setCssci("否");
            }
            if("5".equals(paperDO.getAhci())){
                paperDO.setAhci("是");
            }
            if("6".equals(paperDO.getAhci())){
                paperDO.setAhci("否");
            }

        }
        return modelList;
    }

    @Override
    public String xlsToDB(MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        String filePath="C:/excel/";//传到d盘
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
                PaperDO paperDO=new PaperDO();
                Long paperId=(long)Integer.parseInt(sheet.getCell(0,i).getContents());
                String paperName=sheet.getCell(1,i).getContents();
                String paperJournal=sheet.getCell(2,i).getContents();
                String paperAuthor=sheet.getCell(3,i).getContents();
                String paperComAuthor=sheet.getCell(4,i).getContents();
                String paperSign=sheet.getCell(5,i).getContents();
                String firAutDept= sheet.getCell(6,i).getContents();
                String myDept=  sheet.getCell(7,i).getContents();
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dateLong)));
//                Date  publishTime=new Date(sheet.getCell(8,i).getContents());
                String isJournal=sheet.getCell(9,i).getContents();
                String sci=sheet.getCell(10,i).getContents();
                String eij=sheet.getCell(11,i).getContents();
                String eim=sheet.getCell(12,i).getContents();
                String cscd=sheet.getCell(13,i).getContents();
                String cnCore=sheet.getCell(14,i).getContents();
                String cpci=sheet.getCell(15,i).getContents();
                String ssci=sheet.getCell(16,i).getContents();
                String cssci=sheet.getCell(17,i).getContents();
                String ahci=sheet.getCell(18,i).getContents();
                String sciNum=sheet.getCell(19,i).getContents();
                String eiNum=sheet.getCell(20,i).getContents();
                String issnNum=sheet.getCell(21,i).getContents();
                String paperInfo=sheet.getCell(22,i).getContents();

                paperDO.setPaperId(paperId);
                paperDO.setPaperName(paperName);
                paperDO.setPaperJournal(paperJournal);
                paperDO.setPaperAuthor(paperAuthor);
                paperDO.setPaperComAuthor(paperComAuthor);
                paperDO.setPaperSign(paperSign);
                paperDO.setFirAutDept(firAutDept);
                paperDO.setMyDept(myDept);
//                paperDO.setPublishTime(publishTime);
                paperDO.setIsJournal(isJournal);
                paperDO.setSci(sci);
                paperDO.setEij(eij);
                paperDO.setEim(eim);
                paperDO.setCscd(cscd);
                paperDO.setCnCore(cnCore);
                paperDO.setCpci(cpci);
                paperDO.setSsci(ssci);
                paperDO.setCssci(cssci);
                paperDO.setAhci(ahci);
                paperDO.setSciNum(sciNum);
                paperDO.setEiNum(eiNum);
                paperDO.setIssnNum(issnNum);
                paperDO.setPaperInfo(paperInfo);
                paperMapper.save(paperDO);//可以判断一下id  如果存在就更新

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "true";
    }
}
