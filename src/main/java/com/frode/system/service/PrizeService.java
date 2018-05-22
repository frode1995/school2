package com.frode.system.service;

import com.frode.system.domain.PrizeDO;

import java.util.List;
import java.util.Map;

public interface PrizeService {
    PrizeDO get(Long prizeId);//get都是从id得对象

    List<PrizeDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(PrizeDO prize);

    int update(PrizeDO prize);

    int remove(Long prizeId);

    int batchRemove(Long[] prizeIds);

    Long[] listAllPrize();

    List<PrizeDO> listMyPrize(Map<String,Object> map,  Long userId);
}
