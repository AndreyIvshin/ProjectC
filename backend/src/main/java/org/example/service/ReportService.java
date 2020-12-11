package org.example.service;

import org.apache.poi.xssf.usermodel.*;
import org.example.mapper.DealMapper;
import org.example.mapper.EntityMapper;
import org.example.mapper.ItemMapper;
import org.example.mapper.ReportMapper;
import org.example.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private DealService dealService;
    @Autowired
    private EntityService entityService;
    @Autowired
    private DealMapper dealMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private EntityMapper entityMapper;

    public ReportMapper.Resp getReport(Date first, Date last, Long id) {
        List<Deal> all = dealService.findAllBySeller_IdOrBuyer_Id(id, id);
        List<DealMapper.DealFull> deals = all.stream().map(dealMapper::mapFull).collect(Collectors.toList());
        ReportMapper.Resp resp = new ReportMapper.Resp();
        resp.setFirst(first);
        resp.setLast(last);
        resp.setEntity(entityMapper.mapFull(entityService.find(id).get()));
        resp.setSellDeals(new ArrayList<>());
        resp.setBuyDeals(new ArrayList<>());
        resp.setSellItems(new ArrayList<>());
        resp.setBuyItems(new ArrayList<>());
        resp.setAllItems(new ArrayList<>());
        resp.setSum(BigDecimal.ZERO);
        resp.setAllSum(BigDecimal.ZERO);

        List<ReportMapper.RespItem> items = new ArrayList<>();

        for (DealMapper.DealFull deal : deals) {
            if ((deal.getDate().after(first) || deal.getDate().equals(first))
                    && (deal.getDate().before(last) || deal.getDate().equals(last)))
            {
                if (deal.getSeller().getId().equals(id)) {
                    resp.getSellDeals().add(deal);
                    resp.setSum(resp.getSum().add(deal.getPrice()));
                    resp.setAllSum(resp.getAllSum().add(deal.getPrice()));
                    ReportMapper.RespItem item = new ReportMapper.RespItem();
                    item.setQuantity(deal.getQuantity());
                    item.setItem(deal.getItem());
                    resp.getSellItems().add(item);
                    item.setQuantity(item.getQuantity().multiply(new BigDecimal(-1)));
                    items.add(item);
                } else {
                    resp.getBuyDeals().add(deal);
                    resp.setSum(resp.getSum().subtract(deal.getPrice()));
                    resp.setAllSum(resp.getAllSum().subtract(deal.getPrice()));
                    ReportMapper.RespItem item = new ReportMapper.RespItem();
                    item.setQuantity(deal.getQuantity());
                    item.setItem(deal.getItem());
                    resp.getBuyItems().add(item);
                    items.add(item);
                }
            } else {
                if (deal.getSeller().getId().equals(id)) {
                    resp.setAllSum(resp.getAllSum().add(deal.getPrice()));
                    ReportMapper.RespItem item = new ReportMapper.RespItem();
                    item.setQuantity(deal.getQuantity());
                    item.setItem(deal.getItem());
                    item.setQuantity(item.getQuantity().multiply(new BigDecimal(-1)));
                    items.add(item);
                } else {
                    resp.setAllSum(resp.getAllSum().subtract(deal.getPrice()));
                    ReportMapper.RespItem item = new ReportMapper.RespItem();
                    item.setQuantity(deal.getQuantity());
                    item.setItem(deal.getItem());
                    items.add(item);
                }
            }
        }
        for (ReportMapper.RespItem item : items) {
            boolean contains = false;
            for (ReportMapper.RespItem respItem : resp.getAllItems()) {
                if (respItem.getItem().getId().equals(item.getItem().getId())) {
                    respItem.setQuantity(respItem.getQuantity().add(item.getQuantity()));
                    contains = true;
                }
            }
            if (!contains) {
                resp.getAllItems().add(item);
            }
        }
        System.out.println(resp.getBuyDeals());
        return resp;
    }

    public Optional<ByteArrayResource> getExcel(ReportMapper.Resp resp) {
        String path = System.getProperty("user.dir") + "/report.xlsx";
        try {
            DateFormat dateFormat = new SimpleDateFormat();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();

            XSSFRow rowDS = sheet.createRow(0);
            rowDS.createCell(0).setCellValue("Information about " +
                    resp.getEntity().getName() + " in date segment between " +
                    dateFormat.format(resp.getFirst()) + " and " + dateFormat.format(resp.getLast()));

            XSSFRow rowSB = sheet.createRow(2);
            rowSB.createCell(1).setCellValue("Segment balance is " + resp.getSum());

            XSSFRow rowB = sheet.createRow(4);
            rowB.createCell(1).setCellValue("Buys");

            int buyRowsCount = 0;

            for (int i = 0; i < resp.getBuyDeals().size(); i++) {
                XSSFRow row = sheet.createRow(5 + i);
                DealMapper.DealFull deal = resp.getBuyDeals().get(i);
                row.createCell(2).setCellValue(
                        deal.getQuantity().toString() + " " + deal.getItem().getUnit().getName() + " of " +
                                deal.getItem().getName() + " (" + dateFormat.format(deal.getDate()) + ")");
                buyRowsCount++;
            }

            int sellRowsCount = 0;

            XSSFRow rowS = sheet.createRow(6 + buyRowsCount);
            rowS.createCell(1).setCellValue("Sells");

            for (int i = 0; i < resp.getSellDeals().size(); i++) {
                XSSFRow row = sheet.createRow(7 + buyRowsCount + i);
                DealMapper.DealFull deal = resp.getSellDeals().get(i);
                row.createCell(2).setCellValue(
                        deal.getQuantity().toString() + " " + deal.getItem().getUnit().getName() + " of " +
                                deal.getItem().getName() + " (" + dateFormat.format(deal.getDate()) + ")");
                sellRowsCount++;
            }

            XSSFRow rowC = sheet.createRow(8 + buyRowsCount + sellRowsCount);
            rowC.createCell(0).setCellValue("Current information about " + resp.getEntity().getName());

            XSSFRow rowCB = sheet.createRow(10 + buyRowsCount + sellRowsCount);
            rowCB.createCell(1).setCellValue("Current balance is " + resp.getAllSum());

            XSSFRow rowI = sheet.createRow(12 + buyRowsCount + sellRowsCount);
            rowI.createCell(1).setCellValue("Items");

            for (int i = 0; i < resp.getAllItems().size(); i++) {
                XSSFRow row = sheet.createRow(13 + buyRowsCount + sellRowsCount + i);
                ReportMapper.RespItem item = resp.getAllItems().get(i);
                row.createCell(2).setCellValue(item.getQuantity().toString() + " " +
                        item.getItem().getName() + " " + item.getItem().getUnit().getName());
            }


            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            return Optional.of(new ByteArrayResource(Files.readAllBytes(Paths.get(path))));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
