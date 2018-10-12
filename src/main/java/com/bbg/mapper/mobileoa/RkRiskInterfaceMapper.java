package com.bbg.mapper.mobileoa;

import com.bbg.pojo.RK_RISK_INTERFACE;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by H1N1 on 2018/10/12.
 */
@Mapper
@Repository
public interface RkRiskInterfaceMapper {

    List<RK_RISK_INTERFACE> selectAll();

}
