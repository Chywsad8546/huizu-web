package com.toutiao.web.service.newhouse;

import com.toutiao.web.domain.query.NewHouseQuery;

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
     * 获取附近楼盘
     * @return
     */
     Map<String,Object>getNearHouse(int buildingNameId,String index, String type, double lat, double lon, String distance) throws Exception;


}
