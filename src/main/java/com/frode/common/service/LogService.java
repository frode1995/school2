package com.frode.common.service;

import org.springframework.stereotype.Service;

import com.frode.common.domain.LogDO;
import com.frode.common.domain.PageDO;
import com.frode.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
