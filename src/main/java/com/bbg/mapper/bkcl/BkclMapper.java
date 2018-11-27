package com.bbg.mapper.bkcl;

import com.bbg.pojo.BfclDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by H1N1 on 2018/11/27.
 */
@Mapper
@Repository
public interface BkclMapper {

    int selectApp_dhd(BfclDTO source);

    int insertIntoAPP_DHD(BfclDTO source);

    int insertIntoAPP_DHDITEM(List<BfclDTO> res);



}
