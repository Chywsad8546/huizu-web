package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.service.repository.admin.SaveToESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SaveToESController {
    @Autowired
    private SaveToESService saveToESService;

    @RequestMapping("/saveToES")
    public void setSaveToESService(String index, String type, VillageEntity village, Model model){
        VillageEntity village1 = new VillageEntity();
        village1.setId(001);
        village1.setAreaId("002");
        village1.setArea("朝阳");
        village1.setAreaNameId("001");
        village1.setAreaName("朝阳门");
        String[] mt = {"001","002"};
        village1.setMetroStationId(mt);
        String[] mtStation ={"朝阳东","朝阳"};
        village1.setMetroStation(mtStation);
        String[] li ={"001","002"};
        village1.setSubwayLineId(li);
        String[] liName ={"1号线","2号线"};
        village1.setSubwayLine(liName);
        Map<String,String> map = new HashMap<String,String>();
        map.put(village1.getSubwayLineId()[0]+"_"+village1.getMetroStationId()[0],"10");
        map.put(village1.getSubwayLineId()[1]+"_"+village1.getMetroStationId()[1],"20");
        village1.setMetroWithPlotsDistance(map);
        String[] la = {"001","002"};
        village1.setLabelId(la);
        String[] laName = {"标签1","标签2"};
        village1.setLabel(laName);
        village1.setAvgPrice(59999);
        village1.setAge(16);
        village1.setElevator("有");
        Double[] loca = {10.0,10.0};
        village1.setLocation(loca);
        village1.setLevel(1);

        Boolean flag = null;
        try {
            flag = saveToESService.save(index, type, village1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("flag",flag);
    }
}
