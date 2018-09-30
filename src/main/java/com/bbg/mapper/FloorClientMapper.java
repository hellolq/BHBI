package com.bbg.mapper;

import com.bbg.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by H1N1 on 2018/8/22.
 */
@Mapper
@Repository
public interface FloorClientMapper {

    List<FloorKllDTO> selectFloorKll(FloorClientParam param);//查询客流和销售




}
