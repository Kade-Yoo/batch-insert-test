package kr.kade.batchinserttest.controller;

import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.service.JdbcBatchInsertService;
import kr.kade.batchinserttest.service.JpaBatchInsertService;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final MybatisBatchInsertService mybatisBatchInsertService;
    private final JdbcBatchInsertService jdbcBatchInsertService;
    private final JpaBatchInsertService jpaBatchInsertService;

    @PostMapping("/jpa-save")
    public int saveForJpa(@RequestBody List<OrderRequest> requests) {
        return jpaBatchInsertService.batchInsert(requests);
    }

    @PostMapping("/jdbc-save")
    public int saveForJdbc(List<OrderRequest> requests) {
        return jdbcBatchInsertService.batchInsert(requests);
    }

    @PostMapping("/mybatis-single-save")
    public int singleSaveForMybatis(List<OrderRequest> requests) {
        return mybatisBatchInsertService.singleBatchSave(requests);
    }

    @PostMapping("/mybatis-multi-save")
    public int multiSaveForMybatis(List<OrderRequest> requests) {
        return mybatisBatchInsertService.multiBatchSave(requests);
    }
}
