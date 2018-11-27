package com.bbg;

import com.bbg.pojo.BfclDTO;
import com.bbg.pojo.DataDealDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by H1N1 on 2018/11/16.
 */
public class NormalTest {

    @Test
    public void test2() throws IOException {
        BfclDTO model = new BfclDTO();
       String source = "{ \"data\": { \"bill\": { \"orderPrice\": 2900, \"wareTotalOrigPrice\": 3990 }, \"merchant_contribute\": 0, \"netAmt\": 2900, \"orderId\": \"183302202504\", \"orderPmt\": [ { \"dmno\": \"0\", \"lineno\": 0, \"pmtamt\": 1090, \"pmtbill\": \"223374036510241\", \"pmtbill2\": \"22336106\", \"pmtmemo\": \"siebel券\", \"pmttype\": \"50\", \"pmtuomamt\": 1090, \"skuCount\": 1, \"wareCode\": \"107340312\" } ], \"orderType\": \"BH\", \"otherPayOrderNo\": \"4200000208201811268072325691\", \"other_contribute\": 0, \"payChannel\": \"WX\", \"payId\": \"183302202504\", \"payOrderNo\": \"18112617002429\", \"payType\": \"1\", \"unitPayOrderNo\": \"201811262100004629063\", \"wares\": [ { \"count\": 1, \"discountAmount\": 2900, \"discountPrice\": 2900, \"lineno\": 0, \"originalPrice\": 3990, \"proQty\": 1, \"wareCode\": \"107340312\", \"warePrice\": 3990, \"weight\": 0 } ] }, \"member\": { \"birth\": \"1979-06-22\", \"birthFlag\": \"Y\", \"cardNo\": \"881100000000257440\", \"point\": \"141\", \"siebelId\": \"1-3EJER13\", \"status\": \"Active\", \"tier\": \"金卡\" }, \"storeCode\": \"120236\", \"traceId\": \"1f02f430-a996-47c6-8a7e-a326823962a2\" }";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(source);
        String traceId = rootNode.path("traceId").asText();
        model.setTraceId(traceId);
        String shopId =  rootNode.path("storeCode").asText();
        model.setShopId(shopId);

        JsonNode data = rootNode.path("data");
        String dhd = data.path("orderId").asText();
        model.setDhd(dhd);

        JsonNode wares = data.path("wares");
        List<BfclDTO> orders = new ArrayList<>();
        for(int i=0;i<wares.size();i++){
            BfclDTO temp_model = new BfclDTO();
            JsonNode temp = wares.get(i);
            int dhsl = temp.path("count").asInt();
            temp_model.setDhsl(dhsl);
            double discountPrice = temp.path("discountPrice").asDouble();
            temp_model.setDhje(discountPrice);
            String wareCode = temp.path("wareCode").asText();
            temp_model.setGoodsId(wareCode);
            orders.add(temp_model);

        }

        System.out.println(model.toString());

    }



}
