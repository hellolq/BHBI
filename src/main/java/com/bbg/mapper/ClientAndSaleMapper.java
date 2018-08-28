package com.bbg.mapper;

import com.bbg.pojo.SaleAndClient;
import com.bbg.pojo.SaleAndClientRequireParam;
import com.bbg.pojo.ShopInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by H1N1 on 2018/8/22.
 */
@Mapper
@Repository
public interface ClientAndSaleMapper {

    List<SaleAndClient> selectSaleAndClient(SaleAndClientRequireParam result);//查询客流和销售

    List<SaleAndClient> selectSaleAndClientParse(SaleAndClientRequireParam result);//查询客流和销售  对应图表分析

    List<String> selectSqList();//获取省区列表

    List<ShopInfo> selectAllShop();//获取所有门店信息



}
