package com.toutiao.web.dao.entity.admin;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.*;

public class TestMapping {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        //testIk(client, "house123", "house1234");
        buildRobotMapping(client);
        //queryByHouseId( "a", "b",1, client);
        //buildIndexMapping(client);
        //buildIndexMappingIk(client);
        //save("house123","house1234",client);
        //queryList("a", "b", client, null);
        //System.out.println("=============================");
        //queryRang("projhouseinfo", "projhouse", client, null);
        //query2("projhouseinfo", "projhouse", client, "房山v");
        /*Map<String, Object> map = queryByHouseId("a", "b", 12, client);
        Set<String> set = map.keySet();
		for (int i=0;i<set.size();i++){

			System.out.println(map.get(i));

		}


		System.out.println(map.get("data_house"));
		System.out.println(map.size());*/
    }

    protected static void buildIndexMapping(TransportClient client) throws Exception {
        /**
         .startObject("product_name").field("type", "string")
         .field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
         */
        client.admin().indices().prepareCreate("a").execute().actionGet();
        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject("b")
                .startObject("houseId").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("houseTitle").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("houseArea").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseType").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseTypeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOrientation").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOrientationId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseTotalPrices").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseUnitCost").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLabel").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLabelId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseFloorId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseFloor").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseYear").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePurposeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePurpose").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLiftId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLift").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseHeatingId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseHeating").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOwnershipId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOwnership").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseRecommendInfo").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePhoto").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePlotPhoto").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBudget").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseTrafficInfo").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseUpdateTime").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseManagementTypeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseManagementType").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBuildingTypeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBuildingType").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseRank").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseProxyPhoto").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseProxyName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseProxyPhone").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseRecommend").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePlotId").field("type", "integer")
                .field("index", "not_analyzed").endObject().startObject("housePlotLocation").field("type", "geo_point")
                .field("index", "not_analyzed").endObject().startObject("housePlotName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePlotInfo").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBusinessNameId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBusinessName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("areaId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("areaName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayLineId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayLineName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayStationId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayStationName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseToSubwayDistance").field("type", "object")
                .endObject().endObject().endObject().endObject();

        PutMappingRequest mappingRequest = Requests.putMappingRequest("a").type("b")
                .source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    protected static void buildIndexMappingIk(TransportClient client) throws Exception {
        /**
         .startObject("product_name").field("type", "string")
         .field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
         */
        client.admin().indices().prepareCreate("house123").execute().actionGet();
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("house1234").startObject("properties")
                .startObject("areaName").field("type", "string").field("analyzer", "ik_smart").endObject()
                .startObject("houseBusinessName").field("type", "string").field("analyzer", "ik_smart").endObject()
                .startObject("housePlotName").field("type", "string").field("analyzer", "ik_smart").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("house123").type("house1234")
                .source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    public static void save(String index, String type, TransportClient client) throws Exception {
        ProjHouseInfo houseInfo = new ProjHouseInfo();
        Random random = new Random();
        int houseId = random.nextInt(20);
        // 房源id
        houseInfo.setHouseId(houseId);
        // 房源名称
        houseInfo.setHouseTitle("二手房名称");
        // 房源面积
        houseInfo.setHouseArea("100");
        // 房源户型
        houseInfo.setHouseType("二室二厅");
        // 房源户型id
        houseInfo.setHouseTypeId("1");
        // 房源朝向
        houseInfo.setHouseOrientation("东");
        // 房源朝向id
        houseInfo.setHouseOrientationId("1");
        // 房源总价
        houseInfo.setHouseTotalPrices("520");
        // 房源单价(52365元/㎡)
        houseInfo.setHouseUnitCost("52365");
        String[] lab = {"满二", "近地铁", "满五"};
        // 房源标签(满二)
        houseInfo.setHouseLabel(lab);
        String[] labId = {"1", "2", "3"};
        // 房源标签(满二)
        houseInfo.setHouseLabelId(labId);
        // 房源楼层
        houseInfo.setHouseFloorId("27");
        // 房源楼层
        houseInfo.setHouseFloor("中/27层");
        // 房源楼龄
        houseInfo.setHouseYear("5");
        // 房源用途id
        houseInfo.setHousePurposeId("1");
        // 房源用途
        houseInfo.setHousePurpose("住宅");
        // 房源电梯id
        houseInfo.setHouseLiftId("27");
        // 房源电梯
        houseInfo.setHouseLift("有");
        // 房源供暖id
        houseInfo.setHouseHeatingId("23");
        // 房源供暖
        houseInfo.setHouseHeating("自供暖");
        // 房源权属id
        houseInfo.setHouseOwnershipId("5");
        // 房源权属
        houseInfo.setHouseOwnership("商品房");
        // 房源推荐描述
        houseInfo.setHouseRecommendInfo("南北通透无遮挡 好视野 业主诚意售");
        String[] s = {"/ss", "/dff"};
        // 房源照片
        houseInfo.setHousePhoto(s);
        // 房源预算
        houseInfo.setHouseBudget("参考首付267万，月供5432元/月");
        // 房源交通信息
        houseInfo.setHouseTrafficInfo("距离地铁国贸站[1号线]0.6km");
        // 房源更新时间
        houseInfo.setHouseUpdateTime("2017.09.09");
        // 物业类型id
        houseInfo.setHouseManagementTypeId("1");
        // 物业类型
        houseInfo.setHouseManagementType("链家");
        // 建筑类别id
        houseInfo.setHouseBuildingTypeId("1");
        // 建筑类别
        houseInfo.setHouseBuildingType("板楼");
        // 房源级别(排序)
        houseInfo.setHouseRank("1");
        // 经纪人头像
        houseInfo.setHouseProxyPhone("15254445544");
        // 经纪人姓名
        houseInfo.setHouseProxyName("经纪人姓名");
        // 经纪人电话号
        houseInfo.setHouseProxyPhoto("/dds经纪人头像");
        // 房源介绍
        houseInfo.setHouseRecommend("主卧室南向");
        // 房源小区id
        houseInfo.setHousePlotId(1);
        Double[] d = {34.0, 38.0};
        // 房源小区地理
        houseInfo.setHousePlotLocation(d);

        // 房源小区名称
        houseInfo.setHousePlotName("版本");

        String[] photo = {"小区照片", "小区照片"};

        houseInfo.setHousePlotPhoto(photo);

        // 房源小区信息 首城国际
        houseInfo.setHousePlotInfo("啊啊");
        // 商圈名称
        houseInfo.setHouseBusinessNameId("1");

        // 商圈名称
        houseInfo.setHouseBusinessName("朝阳111");
        // 区域id
        houseInfo.setAreaId("12");
        // 区域名称(朝阳)
        houseInfo.setAreaName("东城1");
        String[] LineId = {"001", "003"};
        // 地铁线id
        houseInfo.setSubwayLineId(LineId);
        String[] LineName = {"一号线", "三号线"};
        // 地铁线名称
        houseInfo.setSubwayLineName(LineName);

        String[] StationId = {"001", "002"};
        // 地铁站id
        houseInfo.setSubwayStationId(StationId);
        // 地铁站名称
        String[] StationName = {"一号站", "三号站"};

        Map<String, String> ha = new HashMap<String, String>();
        ha.put("001,001", "距离您15km");
        ha.put("001,002", "距离您15km");
        ha.put("001,004", "距离您15km");
        houseInfo.setHouseToSubwayDistance(ha);
        houseInfo.setSubwayStationName(StationName);
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        JSONObject json = (JSONObject) JSONObject.toJSON(houseInfo);
        IndexRequestBuilder lrb = client.prepareIndex(index, type).setSource(json).setId(String.valueOf(houseId));
        bulkRequest.add(lrb);
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {

            bulkResponse.buildFailureMessage();
        }
        bulkRequest = client.prepareBulk();
    }


    public static void queryList(String index, String type, TransportClient client, ProjHouseInfoQuery houseInfo) {

        // 搜索，创建一个termQuery查询，该查询要求全部匹配才会出结果，如果只要包含关键字里面一部分，可以创建fieldQuery。

        if (houseInfo == null || "".equals(houseInfo)) {
            //SearchRequestBuilder requestBuilder = client.prepareSearch(index).setTypes(type);
            //requestBuilder.setQuery(QueryBuilders.matchAllQuery());
            SearchResponse response = client.prepareSearch(index)
                    .setTypes(type)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.matchAllQuery())
                    //.addSort("age", SortOrder.DESC)//排序
                    .setFrom(0).setSize(10)//分页
                    .execute()
                    .actionGet();
            //SearchResponse response = requestBuilder.execute().actionGet();
            for (SearchHit searchHit : response.getHits().getHits()) {
                System.out.println(searchHit.getSourceAsString());
            }
        }

    }


//	public static void queryRang(String index, String type, TransportClient client, ProjHouseInfoRequest houseInfo) {
//		// 搜索，创建一个termQuery查询，该查询要求全部匹配才会出结果，如果只要包含关键字里面一部分，可以创建fieldQuery。
//		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//				.must(QueryBuilders.termQuery("subwayLineName", "三3号线"))
//				.must(QueryBuilders.termQuery("subwayStationName", "海淀4"));
//		SearchResponse searchResponse = client.prepareSearch(index)
//				.setTypes(type)
//				.setQuery(queryBuilder)
//				.get();
//		for(SearchHit searchHit : searchResponse.getHits().getHits()) {
//			System.out.println(searchHit.getSourceAsString());
//		}
//
//
//	}


    public static void query2(String index, String type, TransportClient client, String info) {
        QueryBuilder queryBuilder = null;
        if (info == null) {
            queryBuilder = QueryBuilders.boolQuery()
                    //.must(QueryBuilders.termQuery("areaName1", info));
                    .must(QueryBuilders.termQuery("areaName", "西城ss"));

        } else {
            queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("areaName1", info))
                    .must(QueryBuilders.termQuery("areaName", "西城ss"));
        }
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(queryBuilder)
                .get();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }

    }


    /**
     * 通过二手房id查找房源信息
     *
     * @param houseId
     * @return
     */
    public static Map<String, Object> queryByHouseId(String index, String type, Integer houseId, TransportClient client) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        SearchResponse searchresponse = client.prepareSearch(index).setTypes(type)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();
        SearchHits hits = searchresponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        Map<String, Object> result = new HashMap<>();
        result.put("data_house", searchHists);
        result.put("total_house", hits.getTotalHits());
        return result;
    }


    public Map<String, Object> queryProjHouseByhouseIdandLocation(String projhouseIndex, String projhousetype, double lat, double lon, TransportClient client) {

        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhousetype);
        //从该坐标查询距离为distance      housePlotLocation
        GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("housePlotLocation").point(lat, lon).distance("30000", DistanceUnit.METERS);
        srb.setPostFilter(location1);
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("30000", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        sort.point(lat, lon);
        srb.addSort(sort).setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto", "houseType", "houseArea", "housePlotName"}, null).execute().actionGet();
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits hits = searchResponse.getHits();
        String[] house = new String[(int) hits.getTotalHits()];
        System.out.println("附近的房源(" + hits.getTotalHits() + "个)：");
        ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String, Object> buildings = hit.getSource();
            buildinglist.add(buildings);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data_plot", buildinglist);
        result.put("total_plot", hits.getTotalHits());
        return result;
    }


    public static void testIk(TransportClient client, String index, String type) {
        QueryBuilder queryBuilder = null;
        String content = "朝阳111";//朝阳111  首城国际111  东城1
        AnalyzeResponse response = client.admin().indices()
                .prepareAnalyze(content)//内容
                .setAnalyzer("ik_smart")//指定分词器
                //.setTokenizer("standard")
                .execute().actionGet();//执行
        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
        BoolQueryBuilder ww = QueryBuilders.boolQuery();
        for (AnalyzeResponse.AnalyzeToken analyzeToken :
                tokens) {
            System.out.println(analyzeToken.getTerm());
//			queryBuilder =QueryBuilders.boolQuery()
//					.should(QueryBuilders.prefixQuery("areaName",analyzeToken.getTerm()))
//			     .should(QueryBuilders.prefixQuery("houseBusinessName",analyzeToken.getTerm()))
//			     .should(QueryBuilders.prefixQuery("housePlotName",analyzeToken.getTerm()));
            queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termsQuery("areaName", analyzeToken.getTerm()))
                    .should(QueryBuilders.termsQuery("houseBusinessName", analyzeToken.getTerm()))
                    .should(QueryBuilders.termsQuery("housePlotName", analyzeToken.getTerm()));

            ww.should(queryBuilder);
        }
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(ww)
                .get();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
        /*IndicesAdminClient indicesAdminClient = ElasticFactory.getClient().admin().indices();
		AnalyzeRequestBuilder request = new AnalyzeRequestBuilder(indicesAdminClient,"cloud_repair","中华人民共和国国歌");
        // request.setAnalyzer("ik");
		request.setTokenizer("ik");
         // Analyzer（分析器）、Tokenizer（分词器）
		List listAnalysis = request.execute().actionGet().getTokens();
		System.out.println(listAnalysis);
        // listAnalysis中的结果就是分词的结果

		for (AnalyzeResponse.AnalyzeToken term : listAnalysis) {
			System.out.print(term.getTerm());
			System.out.print(',');
			queryBuilder.should(QueryBuilders.queryString(term.getTerm()).field("search_keys_ik"));
			//这里可以用must 或者 should 视情况而定
		}
		System.out.print('\n');*/

    }

    protected static void buildRobotMapping(TransportClient client) throws Exception {
        /**
         .startObject("product_name").field("type", "string")
         .field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
         */

        client.admin().indices().prepareCreate("robot_index").execute().actionGet();
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("robot_type").startObject("properties")
                .startObject("newcode").field("type", "integer").endObject()
                .startObject("n_or_e").field("type", "integer").endObject()
                .startObject("projname").field("type", "text").endObject()
                .startObject("nickname").field("type", "text").endObject()
                .startObject("installment").field("type", "text").endObject()
                .startObject("address").field("type", "text").endObject()
                .startObject("address_info").field("type", "text").endObject()
                .startObject("housefeature").field("type", "text").endObject()
                .startObject("proj_feature").field("type", "text").endObject()
                .startObject("province").field("type", "text").endObject()
                .startObject("city_id").field("type", "integer").endObject()
                .startObject("district_id").field("type", "integer").endObject()
                .startObject("area_id").field("type", "integer").endObject()
                .startObject("round").field("type", "text").endObject()
                .startObject("roundstation").field("type", "text").endObject()
                .startObject("rounddirection").field("type", "text").endObject()
                .startObject("community_id").field("type", "integer").endObject()
                .startObject("projdesc").field("type", "text").endObject()
                .startObject("right_year").field("type", "integer").endObject()
                .startObject("groundarea").field("type", "double").endObject()
                .startObject("purposearea").field("type", "double").endObject()
                .startObject("dimension").field("type", "double").endObject()
                .startObject("virescencerate").field("type", "double").endObject()
                .startObject("buildingdes").field("type", "text").endObject()
                .startObject("totaldoor").field("type", "integer").endObject()
                .startObject("parkdesc").field("type", "text").endObject()
                .startObject("parkspace").field("type", "integer").endObject()
                .startObject("car_rent_price").field("type", "double").endObject()
                .startObject("car_sell_price").field("type", "double").endObject()
                .startObject("propertyfee").field("type", "double").endObject()
                .startObject("propfeetype").field("type", "text").endObject()
                .startObject("propertyaddition").field("type", "text").endObject()
                .startObject("fixstatus").field("type", "text").endObject()
                .startObject("equipment").field("type", "text").endObject()
                .startObject("layout").field("type", "text").endObject()
                .startObject("work_schedule").field("type", "text").endObject()
                .startObject("startdate").field("type", "date").endObject()
                .startObject("finishdate").field("type", "date").endObject()
                .startObject("saledate").field("type", "date").endObject()
                .startObject("saledate_s").field("type", "text").endObject()
                .startObject("livindate").field("type", "date").endObject()
                .startObject("livindate_s").field("type", "text").endObject()
                .startObject("saletelphone").field("type", "text").endObject()
                .startObject("saleaddress").field("type", "text").endObject()
                .startObject("salecard").field("type", "object").endObject()
                .startObject("mortgage_bank").field("type", "text").endObject()
                .startObject("saling").field("type", "integer").endObject()
                .startObject("sail_schedule").field("type", "integer").endObject()
                .startObject("developer").field("type", "text").endObject()
                .startObject("investor").field("type", "text").endObject()
                .startObject("propertymanage").field("type", "text").endObject()
                .startObject("propertyadviser").field("type", "text").endObject()
                .startObject("landscape").field("type", "text").endObject()
                .startObject("construct").field("type", "text").endObject()
                .startObject("agent").field("type", "text").endObject()
                .startObject("sightdesign").field("type", "text").endObject()
                .startObject("conextend").field("type", "text").endObject()
                .startObject("media").field("type", "text").endObject()
                .startObject("is_approve").field("type", "integer").endObject()
                .startObject("webaddress").field("type", "text").endObject()
                .startObject("websaleaddress").field("type", "text").endObject()
                .startObject("complete_fraction").field("type", "text").endObject()
                .startObject("coord_x").field("type", "double").endObject()
                .startObject("coord_y").field("type", "double").endObject()
                .startObject("saling_optime").field("type", "date").endObject()
                .startObject("adminstauts_time").field("type", "date").endObject()
                .startObject("create_time").field("type", "date").endObject()
                .startObject("update_time").field("type", "date").endObject()
                .startObject("deldate").field("type", "date").endObject()
                .startObject("delreason").field("type", "text").endObject()
                .startObject("tel400").field("type", "text").endObject()
                .startObject("pricerate").field("type", "double").endObject()
                .startObject("pricemax").field("type", "double").endObject()
                .startObject("pricemin").field("type", "double").endObject()
                .startObject("esf_district").field("type", "text").endObject()
                .startObject("esf_comarea").field("type", "text").endObject()
                .startObject("pinyin_name").field("type", "text").endObject()
                .startObject("pinyin_initials").field("type", "text").endObject()
                .startObject("esf_address").field("type", "text").endObject()
                .startObject("esf_operastion").field("type", "text").endObject()
                .startObject("esf_show").field("type", "integer").endObject()
                .startObject("esf_is_approve").field("type", "text").endObject()
                .startObject("update_saledate_time").field("type", "date").endObject()
                .startObject("update_livindate_time").field("type", "date").endObject()
                .startObject("right_desc").field("type", "text").endObject()
                .startObject("zip_code").field("type", "text").endObject()
                .startObject("property_tele").field("type", "text").endObject()
                .startObject("property_address").field("type", "text").endObject()
                .startObject("creator_id").field("type", "integer").endObject()
                .startObject("updater_id").field("type", "integer").endObject()
                .startObject("is_del").field("type", "integer").endObject()
                .startObject("price").field("type", "double").endObject()
                .startObject("total_price").field("type", "double").endObject()
                .startObject("esf_price").field("type", "double").endObject()
                .startObject("esf_total_price").field("type", "double").endObject()
                .startObject("heating_mode").field("type", "integer").endObject()
                .startObject("lift_door_radio").field("type", "text").endObject()
                .startObject("park_radio").field("type", "text").endObject()
                .startObject("property_type").field("type", "integer").endObject()
                .startObject("build_form").field("type", "integer").endObject()
                .startObject("build_category").field("type", "integer").endObject()
                .startObject("villa_style").field("type", "integer").endObject()
                .startObject("price_unit").field("type", "text").endObject()
                .startObject("total_price_unit").field("type", "text").endObject()
                .startObject("esf_price_unit").field("type", "text").endObject()
                .startObject("esf_total_price_unit").field("type", "text").endObject()
                .startObject("residential_category").field("type", "integer").endObject()
                .startObject("soufun_newcode").field("type", "text").endObject()
                .startObject("air_quality").field("type", "text").endObject()
                .startObject("plotPhoto").field("type", "text").endObject()
                .startObject("plotTotalricesBegin").field("type", "integer").endObject()
                .startObject("plotTotalricesEnd").field("type", "integer").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("robot_index").type("robot_type")
                .source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }

}
