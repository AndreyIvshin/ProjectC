package org.example.api;

import org.example.mapper.ReportMapper;
import org.example.service.EntityService;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("api/reports")
public class ReportResource {

    @Autowired
    private ReportService reportService;
    @Autowired
    private EntityService entityService;

    @PostMapping
    public ResponseEntity<ReportMapper.Resp> post(@RequestBody @Valid ReportMapper.Req req) {
        return entityService.find(req.getEntity().getId())
                .map(entity -> ResponseEntity.ok(reportService.getReport(req.getFirst(), req.getLast(), req.getEntity().getId())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("print")
    public ResponseEntity print(@RequestBody @Valid ReportMapper.Req req) {
        return entityService.find(req.getEntity().getId())
                .map(entity -> {
                    Optional<ByteArrayResource> optional = reportService
                            .getExcel(reportService.getReport(req.getFirst(), req.getLast(), req.getEntity().getId()));
                    return optional.map(excel -> ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx")
                            .contentLength(excel.contentLength())
                            .contentType(MediaType.APPLICATION_OCTET_STREAM).body(excel))
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
