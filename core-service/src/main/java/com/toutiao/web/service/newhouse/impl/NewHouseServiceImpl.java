package com.toutiao.web.service.newhouse.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.HasChildQueryBuilder;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.xmlbeans.impl.store.Public2.test;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
@Transactional
public class NewHouseServiceImpl implements NewHouseService{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.newhouse.index}")
    private String newhouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newhouseType;//索引类型

    /**
     * 根绝新房筛选新房
     * @param newHouseQuery
     * @return
     */
    @Override
    public Map<String,Object> getNewHouse(NewHouseQuery newHouseQuery) {

        //建立连接

        TransportClient client = esClientTools.init();
        //
        SearchResponse searchresponse = new SearchResponse();
        //校验筛选条件，根据晒选条件展示列表
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

        //城市
        if(newHouseQuery.getCityId()!=null && newHouseQuery.getCityId()!=0){
            booleanQueryBuilder.must(termQuery("city_id", newHouseQuery.getDistrictId()));
        }
        //区域
        if(newHouseQuery.getDistrictId()!=null && newHouseQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseQuery.getDistrictId()));
        }
        //商圈
        if(newHouseQuery.getAreaId()!=null && newHouseQuery.getAreaId()!=0){
            booleanQueryBuilder.must(termQuery("area_id", newHouseQuery.getAreaId()));
        }
        //地铁线id
        if(newHouseQuery.getSubwayLineId() !=null && newHouseQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseQuery.getSubwayLineId()}));
        }
        //地铁站id
        if(newHouseQuery.getSubwayLineId()!=null && newHouseQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{newHouseQuery.getSubwayStationId()}));
        }
        ///==============================
        //总价
        if(newHouseQuery.getBeginPrice()!=null && newHouseQuery.getEndPrice()!=0){
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseQuery.getBeginPrice()).lte(newHouseQuery.getEndPrice())));
        }

        //户型
        if(StringUtil.isNotNullString(newHouseQuery.getLayoutId())){
            String[] layoutId = newHouseQuery.getLayoutId().split(",");
            Long[] longs = new Long[layoutId.length];
            for (int i = 0; i < layoutId.length; i++) {
                longs[i] = Long.valueOf(layoutId[i]);
            }
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("layout", QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }

                //面积
        if(StringUtil.isNotNullString(newHouseQuery.getHouseAreaSize())){
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();


            String[] layoutId = newHouseQuery.getHouseAreaSize().split(",");
            for (int i = 0; i < layoutId.length ; i=i+2) {
                if(i+1>layoutId.length){
                    break;
                }
                boolQueryBuilder.should(JoinQueryBuilders.hasChildQuery("layout",QueryBuilders.rangeQuery("building_area").gt(layoutId[i]).lte(layoutId[i+1]) , ScoreMode.None));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }


        ///================================
        //物业类型
        if(StringUtil.isNotNullString(newHouseQuery.getPropertyTypeId())){
            booleanQueryBuilder.must(termsQuery("property_type_id",new int[]{Integer.valueOf(newHouseQuery.getPropertyTypeId())}));
        }

        //电梯
        if(!StringUtils.isEmpty(newHouseQuery.getElevatorFlag())){
            booleanQueryBuilder.must(termQuery("elevator_flag", newHouseQuery.getElevatorFlag()));
        }
        //建筑类型
        if(StringUtil.isNotNullString(newHouseQuery.getBuildingType())){
            booleanQueryBuilder.must(termsQuery("building_type_id", new int[]{Integer.valueOf(newHouseQuery.getBuildingType())}));
        }
//        //销售状态
        if(StringUtil.isNotNullString(newHouseQuery.getSaleType())){
            booleanQueryBuilder.must(termQuery("sale_status_id", newHouseQuery.getSaleType()));
        }
        //楼盘特色
        if(StringUtil.isNotNullString(newHouseQuery.getBuildingFeature())){
            booleanQueryBuilder.must(termsQuery("building_feature_id", new int[]{Integer.valueOf(newHouseQuery.getBuildingFeature())}));
        }
        //装修
        if(StringUtil.isNotNullString(newHouseQuery.getDeliverStyle())){
            booleanQueryBuilder.must(termsQuery("redecorate_type_id", new int[]{Integer.valueOf(newHouseQuery.getDeliverStyle())}));
        }

        int pageNum = 1;
        int pageSize = 10;
        if(newHouseQuery.getPageNum()!=null && newHouseQuery.getPageNum()>1){
            pageNum = newHouseQuery.getPageNum();
        }


        //排序  0--默认（按楼盘级别（广告优先））--1均价升排序--2均价降排序--3开盘时间升排序--4开盘时间降排序
        if(newHouseQuery.getSort()!=null&& newHouseQuery.getSort()==1){

            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                    .setQuery(booleanQueryBuilder).addSort("average_price", SortOrder.ASC).setFetchSource(
                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_imgs","sale_status_name","property_type","location"},
                            null)
                    .setFrom((pageNum-1)*pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }else if(newHouseQuery.getSort()!=null && newHouseQuery.getSort()==2){
            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
             .setQuery(booleanQueryBuilder).addSort("average_price", SortOrder.DESC).setFetchSource(
                    new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                            "district_id","district_name","area_id","area_name","building_imgs","sale_status_name","property_type","location"},
                    null)
                    .setFrom((pageNum-1)*pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }else if(newHouseQuery.getSort()!=null&&newHouseQuery.getSort()==3){
            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                    .setQuery(booleanQueryBuilder).addSort("opened_time", SortOrder.ASC).setFetchSource(
                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_imgs","sale_status_name","property_type","location"},
                            null)
                    .setFrom((pageNum-1)*pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }else if(newHouseQuery.getSort()!=null && newHouseQuery.getSort()==4){
            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                    .setQuery(booleanQueryBuilder).addSort("opened_time", SortOrder.DESC).setFetchSource(
                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_imgs","sale_status_name","property_type","location"},
                            null)
                    .setFrom((pageNum-1)*pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }else {
            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                    .setQuery(booleanQueryBuilder).setFetchSource(
                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_imgs","sale_status_name","property_type","location"},
                            null)
                    .setFrom((pageNum-1)*pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }
        SearchHits hits = searchresponse.getHits();
//        List<String> buildinglist = new ArrayList<>();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {

            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",buildinglist);
        result.put("total", hits.getTotalHits());
        return result;
    }

    @Override
    public Map<String, Object> getNewHouseDetails(Integer buildingId) {

        //建立连接

        TransportClient client = esClientTools.init();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("building_name_id", buildingId));
        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();
        BoolQueryBuilder booleanQueryBuilder1 = QueryBuilders.boolQuery();
        booleanQueryBuilder1.must(JoinQueryBuilders.hasParentQuery("building1",QueryBuilders.termQuery("building_name_id",buildingId) ,false));
        SearchResponse searchresponse1 = client.prepareSearch(newhouseIndex).setTypes("layout")
                .setQuery(booleanQueryBuilder1)
                .execute().actionGet();

        SearchHits layouthits = searchresponse1.getHits();
        String layouts = null;
        SearchHit[] searchLayoutHists = layouthits.getHits();
        for (SearchHit hit : searchLayoutHists) {
            layouts = hit.getSourceAsString();
        }



        SearchHits hits = searchresponse.getHits();

        SearchHit[] searchHists = hits.getHits();
        String buildings = null;

        for (SearchHit hit : searchHists) {
            buildings = hit.getSourceAsString();
        }
        Map<String, Object> maprep = new HashMap<>();
        maprep.put("build",buildings);
        maprep.put("layout",layouts);
        return maprep;
        System.out.println(searchHists);

        /*try {
            houselist.put("nearHouse",getNearHouse(121,"beijing1","building1",11.12,21.23,"30000000"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return houselist;
    }

    @Override
    public Map<String, Object> getNearHouse(int buildingNameId,String index, String type, double lat, double lon, String distance) throws Exception {
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                    .build();
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
        //从该坐标查询距离为distance
        BoolQueryBuilder boolQueryBuilder =boolQuery();
      //  System.out.println(location1);
        boolQueryBuilder.mustNot(termQuery("building_name_id",buildingNameId));
        boolQueryBuilder.filter(QueryBuilders.geoDistanceQuery("location").point(lat,lon).distance(Double.parseDouble(distance), DistanceUnit.METERS));
        srb.setQuery(boolQueryBuilder);
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        sort.point(lat, lon);
        srb.addSort(sort);

        SearchResponse searchResponse = srb.execute().actionGet();
       /* SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String[] house = new String[(int) hits.getTotalHits()];*/

        SearchHits hits = searchResponse.getHits();
//        List<String> buildinglist = new ArrayList<>();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
           // System.out.println(buildings.get("area_name"));
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",buildinglist);
        result.put("total", hits.getTotalHits());
       /* System.out.println("附近的(" + hits.getTotalHits() + "个)：");
        List houseList = new ArrayList();
        for (SearchHit hit : searchHists) {
            Map source = hit.getSource();
            Class<VillageEntity> entityClass = VillageEntity.class;
            VillageEntity instance = entityClass.newInstance();
            System.out.println(instance);
            BeanUtils.populate(instance, source);
            houseList.add(instance);
        }*/
        return result;
    }


}
