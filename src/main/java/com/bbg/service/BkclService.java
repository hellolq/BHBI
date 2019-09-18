package com.bbg.service;

import com.bbg.mapper.bkcl.BkclMapper;
import com.bbg.pojo.BfclDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = (Logger) LoggerFactory.getLogger(BkclService.class);


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

        // 提取优惠信息
        JsonNode orderPmt = data.path("orderPmt");
        List<BfclDTO> orderPmtList = new ArrayList<>();
        if(orderPmt != null && orderPmt.size() > 0){
            for(int i=0;i<orderPmt.size();i++){
                BfclDTO temp_model = new BfclDTO();
                JsonNode temp = orderPmt.get(i);
                //  优惠总金额  pmtuomamt  pmtamt
                double yhje = temp.path("pmtuomamt").asDouble();
                String wareCode = temp.path("wareCode").asText();
                temp_model.setYhje(yhje);
                temp_model.setGoodsId(wareCode);
                orderPmtList.add(temp_model);
            }
        }

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

        if(orderPmtList != null && orderPmtList.size() > 0){
            for(int i=0;i<orderPmtList.size();i++){
                BfclDTO yh_temp = orderPmtList.get(i);
                for(int j=0;j<orders.size();j++){
                    BfclDTO or_temp = orders.get(j);
                    if(yh_temp.getGoodsId().equals(or_temp.getGoodsId())){
                        or_temp.setYhje(yh_temp.getYhje());
                    }
                }
            }
        }

        model.setOrders(orders);
        return model;
    }

}
