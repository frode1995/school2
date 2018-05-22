package com.frode.system.service;

import com.frode.system.domain.TestDO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface TestService {
    int save(TestDO testDO);

    String xlsToDB(MultipartFile file);

}
