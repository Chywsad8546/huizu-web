package com.toutiao.web.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.*;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.service.repository.admin.SaveToESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SaveToESMQ implements CommandLineRunner {
    @Autowired
    private SaveToESService saveToESService;
    @Autowired
    private ESClientTools esClientTools;

    @Override
    public void run(String... strings) throws Exception {
        esClientTools.init();

        Properties properties = new Properties();
        // 您在MQ控制台创建的Consumer ID
        properties.put(PropertyKeyConst.ConsumerId, "CID-dts-pg2wap-test");
        // 鉴权用AccessKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey, "LTAIMx964YUDXxwH");
        // 鉴权用SecretKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, "z4w9cd5sIPSbJ8dzsTfDG3QNuXKrsf");
        // 设置 TCP 接入域名（此处以公共云公网环境接入为例）
        properties.put(PropertyKeyConst.ONSAddr,
                "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        // 集群订阅方式 (默认)
        // properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
        // 广播订阅方式
        // properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
        com.aliyun.openservices.ons.api.Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("toutiaofangchan-pg2es-test", "*", new com.aliyun.openservices.ons.api.MessageListener() { //订阅多个Tag
            public Action consume(Message message, ConsumeContext context) {
                try {
                    String jsonStr = new String(message.getBody(), "utf-8");
//                    System.out.println(jsonStr);
                    if (jsonStr != null) {
                        Map map = JSON.parseObject(jsonStr, Map.class);

                        Map projectInfo = (Map) map.get("projectInfo");
                        Map esfHouse = (Map) map.get("esfHouse");
                        Map projectLayout = (Map) map.get("projectLayout");

                        //楼盘
                        if (null != projectInfo) {
                            Integer nOrE = (Integer) projectInfo.get("nOrE");
                            //新房
                            if (nOrE == 0) {

                            }
                            //小区（二手房）
                            if (nOrE == 1 || nOrE == 2) {
                                String jsonString = JSONObject.toJSONString(projectInfo);
                                VillageEntityES villageEntityES = JSON.parseObject(jsonString, VillageEntityES.class);
//                                VillageEntity villageEntity = new VillageEntity();
                                //序号
//                                villageEntity.setId((Integer) projectInfo.get("newcode"));
//                                //小区名称/楼盘名称
//                                villageEntity.setRc((String) projectInfo.get("projname"));
//                                //别名
//                                villageEntity.setAlias((String) projectInfo.get("nickname"));
//                                //小区照片
                                JSONArray projectImage = (JSONArray) map.get("projectImage");
                                if (projectImage != null) {
                                    ArrayList<String> arrayList = new ArrayList<String>();
                                    for (int i = 0; i < projectImage.size(); i++) {
                                        arrayList.add(projectImage.getString(i));
                                    }
                                    String[] str = new String[arrayList.size()];
                                    String[] phone = arrayList.toArray(str);
                                    villageEntityES.setPhoto(phone);
                                }
//                                //区域编号
//                                villageEntity.setAreaId((String) projectInfo.get("districtId"));
//                                //区域
//
//                                //商圈编号
//                                villageEntity.setTradingAreaId((String) projectInfo.get("areaId"));
//                                //地址
//                                villageEntity.setAddress((String) projectInfo.get("address"));
//                                //坐标
                                BigDecimal coordX = (BigDecimal) projectInfo.get("coordX");
                                BigDecimal coordY = (BigDecimal) projectInfo.get("coordY");
//                                Double[] location = {coordX.doubleValue(),coordY.doubleValue()};
//                                villageEntityES.setLocation(location);
                                villageEntityES.setLocation(coordY.toString()+","+coordX.toString());
//                                //地铁线路编号
//                                villageEntity.setSubwayLineId((String[]) projectInfo.get("subwayLineId"));
//                                //地铁站编号
//                                villageEntity.setMetroStationId((String[]) projectInfo.get("metroStationId"));
//                                //地铁线站与小区的距离
                                Map projectExtra = (Map) map.get("projectExtra");
                                String str1 = (String) projectExtra.get("subwayDistince");
                                Map subwayDistince = (Map) JSONObject.parse(str1);
                                villageEntityES.setMetroWithPlotsDistance(subwayDistince);
//                                //交通信息
//
//                                //标签编号
//
//                                //标签
//
//                                //均价
//                                villageEntity.setAvgPrice((Integer) projectInfo.get("esfPrice"));
//                                //总价
//                                villageEntity.setSumPrice((Integer) projectInfo.get("esfTotalPrice"));
//                                //建成年代
//                                villageEntity.setAbbreviatedAge((String) projectInfo.get("finishdate"));
//                                //楼龄
                                String finishdate = (String) projectInfo.get("finishdate");
                                if (finishdate != null) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String format2 = format.format(new Date());
                                String date1 = finishdate.split("-")[0];
                                String date2 = format2.split("-")[0];
                                villageEntityES.setAge(Integer.parseInt(date2) - Integer.parseInt(date1));
                                }
//                                //总栋数
//                                villageEntity.setSumBuilding((String) projectInfo.get("buildCount"));
//                                //总户数
//                                villageEntity.setSumHousehold((String) projectInfo.get("sumHousehold"));
//                                //户均绿化率
                                BigDecimal virescencerate = (BigDecimal) projectInfo.get("virescencerate");
                                BigDecimal sumHousehold = (BigDecimal) projectInfo.get("sumHousehold");
                                if (virescencerate != null && sumHousehold != null && sumHousehold.intValue() != 0) {
                                    Double aDouble = new Double(virescencerate.divide(sumHousehold, 3, BigDecimal.ROUND_HALF_UP).doubleValue());
                                    villageEntityES.setAvgGreeningRate(aDouble);
                                }
//                                //户均电梯
//
//                                //电梯配备
//                                villageEntity.setElevator((String) projectInfo.get("hasLift"));
//                                //车位配比
//                                villageEntity.setCarPositionRatio((Double) projectInfo.get("parkRadio"));
//                                //停车费(车位租价)
//                                villageEntity.setParkingRate((Double) projectInfo.get("carRentPrice"));
//                                //小区详情介绍
//                                villageEntity.setDesc((String) projectInfo.get("projFeature"));
//                                //小区特色
//                                villageEntity.setCharacteristic((String) projectInfo.get("projdesc"));
//                                //开发商
//                                villageEntity.setDevelopers((String) projectInfo.get("developer"));
//                                //物业公司
//                                villageEntity.setProperty((String) projectInfo.get("propertymanage"));
//                                //物业费
//                                villageEntity.setPropertyFee((String) projectInfo.get("propertyfee"));
//                                //物业类型/业态
//                                villageEntity.setPropertyType((String) projectInfo.get("propertyType"));
//                                //产权年限
//                                villageEntity.setYopr((String) projectInfo.get("rightYear"));
//                                //建筑类型编号
//                                villageEntity.setArchitectureTypeId((String[]) projectInfo.get("buildForm"));
//                                //建筑类型
//
//                                //建筑结构
//                                villageEntity.setBuildingStructure((String) projectInfo.get("buildCategory"));
//                                //住宅类型
//                                villageEntity.setResidentialType((String) projectInfo.get("villaStyle"));
//                                //供暖方式
//                                villageEntity.setHeatingMode((String) projectInfo.get("heatingMode"));
//                                //供需关系
//
//                                //楼盘级别
//
//                                //版本
                                villageEntityES.setVersion((Integer) map.get("version"));
//                                //城市编号
//                                villageEntity.setCityId((String) projectInfo.get("cityId"));

                                saveToESService.saveParent(villageEntityES);
                            }
                        }

                        //二手房
                        if (null != esfHouse) {

                        }

                        //布局
                        if (null != projectLayout) {

                        }
                    }
//                    System.out.println("Receive: " + new String(message.getBody(), "utf-8"));
                    System.out.println(jsonStr);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Action.CommitMessage;
            }
        });
//        //订阅另外一个Topic
//        consumer.subscribe("toutiaofangchan-test", "*", new MessageListener() { //订阅全部Tag
//            public Action consume(Message message, ConsumeContext context) {
//                System.out.println("Receive: " + message+"22222222222222222222222");
//                return Action.CommitMessage;
//            }
//        });
        consumer.start();
        System.out.println("Consumer Started!+++++++++++++++++++++++++++++++++++++++");
    }
}
