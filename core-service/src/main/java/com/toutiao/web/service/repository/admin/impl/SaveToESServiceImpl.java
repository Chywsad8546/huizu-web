package com.toutiao.web.service.repository.admin.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.service.repository.admin.SaveToESService;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Service
public class SaveToESServiceImpl implements SaveToESService {
    @Autowired
    private ESClientTools esClientTools;

    @Override
    public Boolean save(String index, String type, VillageEntity village) {
        TransportClient client = esClientTools.init();
        Boolean flag = true;
        String jsonStr  = JSONObject.toJSONString(village);
        JSONObject json = JSONObject.parseObject(jsonStr);
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        IndexRequest indexRequest = new IndexRequest(index, type, "2")
                .source(json);
        UpdateRequest updateRequest = new UpdateRequest(index, type, "2")
                .doc(json)
                .upsert(indexRequest);
        bulkRequest.add(updateRequest);
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//         如果失败
        if (bulkResponse.hasFailures()) {
            flag = false;
        }
        return flag;
    }
}
