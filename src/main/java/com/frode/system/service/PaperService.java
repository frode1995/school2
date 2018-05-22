package com.frode.system.service;

import com.frode.system.domain.PaperDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PaperService {
    PaperDO get(Long paperId);//get都是从id得对象

    List<PaperDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(PaperDO paper);

    int update(PaperDO paper);

    int remove(Long paperId);

    int batchRemove(Long[] paperIds);

    List<PaperDO> listMyPaper(Map<String,Object> map,Long userId);//查询个人论文

    String xlsToDB(MultipartFile file);


}


