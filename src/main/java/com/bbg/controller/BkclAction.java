package com.bbg.controller;

import com.bbg.mapper.bkcl.BkclMapper;
import com.bbg.pojo.BfclDTO;
import com.bbg.pojo.ResponseObj;
import com.bbg.service.BkclService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H1N1 on 2018/11/27.
 */
@Controller
public class BkclAction {

    @Autowired
    private BkclService bkclService;

    @RequestMapping(value = "/bhbkcl",method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj bkcl(BfclDTO source){
        ResponseObj responseObj = new ResponseObj();
        //String source = "{ \"data\": { \"bill\": { \"orderPrice\": 2900, \"wareTotalOrigPrice\": 3990 }, \"merchant_contribute\": 0, \"netAmt\": 2900, \"orderId\": \"183302202504\", \"orderPmt\": [ { \"dmno\": \"0\", \"lineno\": 0, \"pmtamt\": 1090, \"pmtbill\": \"223374036510241\", \"pmtbill2\": \"22336106\", \"pmtmemo\": \"siebel券\", \"pmttype\": \"50\", \"pmtuomamt\": 1090, \"skuCount\": 1, \"wareCode\": \"107340312\" } ], \"orderType\": \"BH\", \"otherPayOrderNo\": \"4200000208201811268072325691\", \"other_contribute\": 0, \"payChannel\": \"WX\", \"payId\": \"183302202504\", \"payOrderNo\": \"18112617002429\", \"payType\": \"1\", \"unitPayOrderNo\": \"201811262100004629063\", \"wares\": [ { \"count\": 1, \"discountAmount\": 2900, \"discountPrice\": 2900, \"lineno\": 0, \"originalPrice\": 3990, \"proQty\": 1, \"wareCode\": \"107340312\", \"warePrice\": 3990, \"weight\": 0 } ] }, \"member\": { \"birth\": \"1979-06-22\", \"birthFlag\": \"Y\", \"cardNo\": \"881100000000257440\", \"point\": \"141\", \"siebelId\": \"1-3EJER13\", \"status\": \"Active\", \"tier\": \"金卡\" }, \"storeCode\": \"120236\", \"traceId\": \"1f02f430-a996-47c6-8a7e-a326823962a2\" }";
        try {
            BfclDTO result = bkclService.parsonDsJson(source.getOrderJson());
            bkclService.insertIntoTable(result);
            responseObj.setMessage("成功");
            responseObj.setStatus("200");
            responseObj.setObj(result.getTraceId());
        } catch (Exception e) {
            //e.printStackTrace();
            responseObj.setMessage(e.getMessage());
            responseObj.setStatus("400");
        }finally {

        }

        return responseObj;
    }






}
