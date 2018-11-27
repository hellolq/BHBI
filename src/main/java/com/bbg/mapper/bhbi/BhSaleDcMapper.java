package com.bbg.mapper.bhbi;

import com.bbg.pojo.DataDealDTO;
import com.bbg.pojo.SaleAndClient;
import com.bbg.pojo.SaleAndClientRequireParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by H1N1 on 2018/11/15.
 */
@Mapper
@Repository
public interface BhSaleDcMapper {

    /*
    * 根据ID 查询 wx_send_message
    * */
    public List<DataDealDTO> getWxSendMessage(DataDealDTO model);
    public int updateWxSendMessage(DataDealDTO model);

    /*
    * 查询 xsdc_jk 表 获取没有推送的，达成信息
    * */
    public List<DataDealDTO> getXsdcJkOne();

    /*
    * 根据ID 查询门店信息
    * */
    public DataDealDTO getShopByShopId(@Param("shopId")String  shopId);

    /*
    * 根据类型获取门店达成信息
    * */
    public int getDcNum(@Param("jkType")String  jkType);

    /*
    * 插入信息进消息推送表
    * */
    public int insertIntoMessagePush(DataDealDTO model);

    /*
    * 更新  xsdc_jk  的flag 状态
    * */
    public int updateXsdc_jkById(@Param("id")String id);

}
