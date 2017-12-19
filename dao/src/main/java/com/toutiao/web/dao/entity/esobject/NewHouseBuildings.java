package com.toutiao.web.dao.entity.esobject;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 新房--楼盘es映射对象
 *
 */
@Data
public class NewHouseBuildings {
    /**
     * 城市id
     */
    @JSONField(name = "cityId")
    private Integer cityId;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 区县id
     */
    @JSONField(name = "districtId")
    private Integer districtId;
    /**
     * 区县名称
     */
    private String districtName;
    /**
     * 商圈id
     */
    @JSONField(name = "areaId")
    private Integer areaId;
    /**
     * 商圈名称
     */

    private String areaName;
    /**
     * 地铁线id
     */
    @JSONField(name = "subwayLine")
    private Integer[] subwayLineId;
    /**
     * 地铁线名称
     */
    private String subwayLine;
    /**
     * 地铁站id
     */
    @JSONField(name = "subwayStation")
    private Integer subwayStationId;
    /**
     * 地铁站名称
     */
    private String subwayStation;
    /**
     * 物业类型id
     */
    @JSONField(name = "propertyType")
    private Integer propertyTypeId;
    /**
     * 物业类型名称
     */
    private String propertyType;
    /**
     * 是否有电梯
     */
    @JSONField(name = "propertyType")
    private Integer elevatorFlag;
    /**
     * 建筑类型id
     */
    @JSONField(name = "buildCategory")
    private Integer[] buildingTypeId;
    /**
     * 建筑类型名称
     */
    private String buildingType;
    /**
     * 销售状态id
     */
    @JSONField(name = "saling")
    private Integer saleStatusId;
    /**
     * 销售状态名称
     */
    private String saleStatusName;
    /**
     * 楼盘特色id
     */
    @JSONField(name = "saling")
    private Integer buildingFeatureId;
    /**
     * 楼盘特色名称
     */
    private String buildingFeature;
    /**
     * 装修类型id
     */
    @JSONField(name = "fixstatus")
    private Integer redecorateTypeId;
    /**
     * 装修类型名称
     */
    private String redecorateType;
    /**
     * 楼盘id
     */
    @JSONField(name = "newcode")
    private Integer buildingNameId;
    /**
     * 楼盘名称
     */
    @JSONField(name = "projname")
    private String buildingName;
    /**
     * 均价
     */
    @JSONField(name = "totalPrice")
    private Double averagePrice;
    /**
     * 楼盘标签id
     */
    private Integer buildingTagsId;
    /**
     * 楼盘标签内容
     */
    private String buildingTags;
    /**
     * 活动描述
     */
    @JSONField(name = "activityInfo")
    private String activityDesc;
    /**
     * 大楼图片
     */
    @JSONField(name = "projectImage")
    private String[] buildingImgs;
    /**
     * 大楼别名
     */
    @JSONField(name = "nickname")
    private String buildingNickname;
    /**
     * 楼盘地址
     */
    @JSONField(name = "address")
    private String buildingAddress;
    /**
     * 交通状况
     */

    private String trafficCondition;
    /**
     * 开盘时间
     */
    private Date openedTime;
    /**
     * 交房时间
     */
    private Date deliverTime;
    /**
     * 开发商
     */
    private String developers;
    /**
     * 销售许可证信息
     */
    private String sellLicence;
    /**
     * 产权年限
     */
    private Integer buildingLife;
    /**
     * 车位配比
     */
    private String parkRadio;
    /**
     * 楼盘地理坐标
     */
    private Double location;
    /**
     * 环路
     */
    private Integer roundstation;
    /**
     * 售楼地址
     */
    private String saleAddress;
    /**
     * 占地面积(平方米)
     */
    private Double groundArea;
    /**
     * 建筑面积(平方米)
     */
    private Double purposeArea;
    /**
     * 容积率(%)
     */
    private Double dimension;
    /**
     * 绿化率(%)
     */
    private Double virescencerate;
    /**
     * 总户数
     */
    private String totaldoor;
    /**
     * 停车位数量
     */
    private Integer parkSpace;
    /**
     * 物业管理公司
     */
    private String propertymanage;
    /**
     * 物业管理费
     */
    private Double propertyfee;
    /**
     * 供暖方式名称(0-未知，1-集中供暖，2-自供暖）
     */
    private String heatingType;
    /**
     * 供暖方式id
     *(0-未知，1-集中供暖，2-自供暖）
     */
    private Integer heatingTypeId;
    /**
     * 最小面积
     */
    private Double houseMinArea;
    /**
     * 最大面积
     */
    private Double houseMaxArea;
    /**
     * 附件地铁信息
     */
    private Map<String,String> nearbysubway;
    /**
     * 楼盘等级
     *
     */
    private Integer buildingLevel;

}