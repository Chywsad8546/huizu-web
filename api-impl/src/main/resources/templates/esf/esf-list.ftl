<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>二手房列表</title>
</head>
<body>
<header>
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" placeholder="中骏·西山天璟">
    </div>
    <a href="/" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<section class="category-box">
    <ul id="category-nav">
        <li><span><em>位置</em><i></i></span></li>
        <li><span><em>总价</em><i></i></span></li>
        <li><span><em>户型</em><i></i></span></li>
        <li><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none">
        <div class="category-cont">
            <div class="none">
                <ul class="category-parent">
                    <li>区域</li>
                    <li>地铁</li>
                </ul>
                <ul class="category-child"></ul>
                <ul class="category-children"></ul>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>不限</li>
                    <li>200万以下</li>
                    <li>200-250万</li>
                    <li>200-250万</li>
                    <li>250-300万</li>
                    <li>350-400万</li>
                </ul>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>不限</li>
                    <li>一居</li>
                    <li>二居</li>
                    <li>三居</li>
                    <li>四居</li>
                    <li>五居及五居以上</li>
                </ul>
                <div class="button-group">
                    <button type="button" class="reset reset-huxing">重置</button>
                    <button type="button" class="confrim confrim-huxing">确定</button>
                </div>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>
                        <h4>朝向</h4>
                        <div class="more-options">
                            <span>朝东</span>
                            <span>朝西</span>
                            <span>朝南</span>
                            <span>朝北</span>
                            <span>南北通透</span>
                        </div>
                    </li>
                    <li>
                        <h4>面积</h4>
                        <div class="more-options">
                            <span>60以下</span>
                            <span>60-90</span>
                            <span>90-120</span>
                            <span>120以上</span>
                        </div>
                    </li>
                    <li>
                        <h4>标签</h4>
                        <div class="more-options">
                            <span>满两年</span>
                            <span>满五年</span>
                            <span>近地铁</span>
                        </div>
                    </li>
                </ul>
                <div class="button-group">
                    <button type="button" class="reset reset-more">重置</button>
                    <button type="button" class="confrim confrim-more">确定</button>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <ul>
    <#if builds?exists>
        <#list builds as map>
            <li>
                <#assign itemLocation=map['housePlotLocation']>
                <a class="list-item" href="/queryByHouseIdandLocation/${map.houseId}/${itemLocation[0]}/${itemLocation[1]}">
                <input type="hidden" name="houseId" value="${map.houseId}"/>

                <input type="hidden" name="lat" value="${itemLocation[0]}"/>
                <input type="hidden" name="lon" value="${itemLocation[1]}"/>
                <div class="clear">
                    <div class="list-item-img-box">
                        <#assign item=map['housePhoto']>
                        <img src="${staticurl}/images/esf/${item[0]}" alt="${map.houseTitle}">
                    </div>
                    <div class="list-item-cont">
                        <h3 class="cont-block-1">${map.houseTitle}</h3>
                        <p class="cont-block-2">${map.houseArea}㎡/${map.houseType}/${map.houseOrientation}/${map.housePlotName}</p>
                        <p class="cont-block-3 distance"><i class="icon"></i>${map.areaName}[${map.houseBusinessName}]</p>
                        <div class="cont-block-4">
                            <#assign item =  map['houseLabel']>
                            <#list item as itemValue>
                                <span>${itemValue}</span>
                            </#list>
                        </div>
                        <div class="cont-block-price">
                            <em>${map.houseTotalPrices}万</em>
                            <span>${map.houseUnitCost}元/㎡</span>
                        </div>
                    </div>
                </div>
            </a></li>
        </#list>
    </#if>
    </ul>
    <p class="tip-box">有新上房源，我们会及时通知您哦！</p>
</section>
<script src="${staticurl}/js/zepto.min.js"></script>
<script src="${staticurl}/js/categorys.js"></script>



</body>
</html>