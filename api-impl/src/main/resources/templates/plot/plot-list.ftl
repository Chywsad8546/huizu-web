<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>小区列表</title>
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
        <li><span><em>均价</em><i></i></span></li>
        <li><span><em>楼龄</em><i></i></span></li>
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
                    <li>5年内</li>
                    <li>10年内</li>
                    <li>15年内</li>
                    <li>20年内</li>
                    <li>20年以上</li>
                </ul>
                <div class="button-group">
                    <button type="button" class="reset reset-year">重置</button>
                    <button type="button" class="confrim confrim-year">确定</button>
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
    <#if villageList?exists>
        <#list villageList as plot>
            <li><a class="list-item" href="">
                <div class="clear">
                    <#if plot['photo']?exists>
                        <div class="list-item-img-box">
                            <#assign photo = plot['photo']>
                            <#if photo?exists>
                                <img src="${staticurl}/images/esf/${photo[0]}" alt="${plot['rc']}">
                            </#if>
                        </div>
                    </#if>
                    <div class="list-item-cont">
                        <h3 class="cont-block-1">${plot['rc']}</h3>
                        <p class="cont-block-2">${plot['abbreviatedAge']}</p>
                        <#if plot['metroWithPlotsDistance']?exists>
                            <#assign map = plot['metroWithPlotsDistance']>
                            <#if plot['key']?exists>
                                <#if map[plot['key']]?exists>
                                    <p class="cont-block-3 distance"><i class="icon"></i>${map[plot['key']]}</p>
                                <#else>
                                    <p class="cont-block-3 distance"><i class="icon"></i>${plot['tradingArea']}</p>
                                </#if>
                            <#else>
                                <p class="cont-block-3 distance"><i class="icon"></i>${plot['tradingArea']}</p>
                            </#if>
                        <#else>
                            <p class="cont-block-3 distance"><i class="icon"></i>${plot['tradingArea']}</p>
                        </#if>
                        <div class="cont-block-4">
                            <#assign item =  plot['label']>
                            <#list item as itemValue>
                                <span>${itemValue}</span>
                            </#list>
                        </div>
                        <div class="cont-block-price plot">
                            <em>${plot['avgPrice']}</em>
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