package com.toutiao.web.service.newhouse;

import com.toutiao.web.domain.query.NewHouseQuery;

import java.util.List;
import java.util.Map;

public interface NewHouseService {


    /**
     * 根绝条件筛选新房
     * @param newHouseQuery
     * @return
     */
    Map<String,Object> getNewHouse(NewHouseQuery newHouseQuery);

    /**
     * 获取楼盘详情信息
     * @param buildingId
     * @return
     */
    Map<String,Object>getNewHouseDetails(Integer buildingId);


    /**
     * 获取楼盘全部描述
     * @return
     */
    List<Map<String,Object>>getNewHouseDiscript(Integer id);

}
