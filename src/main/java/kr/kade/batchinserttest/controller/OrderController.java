package kr.kade.batchinserttest.controller;

import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.service.JdbcBatchInsertService;
import kr.kade.batchinserttest.service.JpaBatchInsertService;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/jpa-save")
    public int saveForJpa(List<OrderRequest> requests) {
        return jpaBatchInsertService.batchInsert(requests);
    }

    @GetMapping("/jdbc-save")
    public int saveForJdbc(List<OrderRequest> requests) {
        return jdbcBatchInsertService.batchInsert(requests);
    }

    @GetMapping("/mybatis-single-save")
    public int singleSaveForMybatis(List<OrderRequest> requests) {
        return mybatisBatchInsertService.singleBatchSave(requests);
    }

    @GetMapping("/mybatis-multi-save")
    public int multiSaveForMybatis(List<OrderRequest> requests) {
        return mybatisBatchInsertService.multiBatchSave(requests);
    }
}
