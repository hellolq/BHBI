package com.bbg.service;

import com.bbg.mapper.bkcl.BkclMapper;
import com.bbg.pojo.BfclDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H1N1 on 2018/11/27.
 */
@Service
public class BkclService {

    @Autowired
    private BkclMapper bkclMapper;

    @Transactional(value = "bkclTransactionManager",rollbackFor=Exception.class)
    public void insertIntoTable(BfclDTO source){
        bkclMapper.insertIntoAPP_DHD(source);
        List<BfclDTO> res = source.getOrders();
        bkclMapper.insertIntoAPP_DHDITEM(res);
    }

    public BfclDTO parsonDsJson(String source) throws IOException {
        BfclDTO model = new BfclDTO();
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
            temp_model.setDhd(dhd);
            JsonNode temp = wares.get(i);
            int dhsl = temp.path("count").asInt();
            temp_model.setDhsl(dhsl);
            double discountPrice = temp.path("discountPrice").asDouble();
            temp_model.setDhje(discountPrice);
            String wareCode = temp.path("wareCode").asText();
            temp_model.setGoodsId(wareCode);
            orders.add(temp_model);

        }
        model.setOrders(orders);
        return model;
    }

}
