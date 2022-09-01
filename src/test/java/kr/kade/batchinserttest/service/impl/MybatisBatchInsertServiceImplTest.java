package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MybatisBatchInsertServiceImplTest {

    @Autowired
    private MybatisBatchInsertService mybatisBatchInsertService;

    @Test
    void save() {
        int save = mybatisBatchInsertService.save();
    }
}