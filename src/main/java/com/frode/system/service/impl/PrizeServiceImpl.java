package com.frode.system.service.impl;

import com.frode.system.dao.PrizeDao;
import com.frode.system.domain.PrizeDO;
import com.frode.system.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeDao prizeMapper;

    @Override
    public PrizeDO get(Long prizeId) {
        return prizeMapper.get(prizeId);
    }

    @Override
    public List<PrizeDO> list(Map<String, Object> map) {//不用字典
        return prizeMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return prizeMapper.count(map);
    }

    @Override
    public int save(PrizeDO prize) {
        prize.setPrizeNew(new Date());
        prize.setUserId(prizeMapper.getUserIdByName(prize.getAwardee()));
        return prizeMapper.save(prize);
    }

    @Override
    public int update(PrizeDO prize) {
        prize.setPrizeNew(new Date());
        prize.setUserId(prizeMapper.getUserIdByName(prize.getAwardee()));
        return prizeMapper.update(prize);
    }

    @Override
    public int remove(Long prizeId) {
        return prizeMapper.remove(prizeId);
    }

    @Override
    public int batchRemove(Long[] prizeIds) {
        return prizeMapper.batchRemove(prizeIds);
    }

    @Override
    public Long[] listAllPrize() {
        return prizeMapper.listAllPrize();
    }

    @Override
    public List<PrizeDO> listMyPrize(Map<String, Object> map, Long userId) {
        return prizeMapper.listMyPrize(map,userId);
    }
}
