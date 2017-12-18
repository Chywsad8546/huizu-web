<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/plot-detail.css">
    <title>小区详情</title>
</head>
<body>
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/esf/esxq_banner1.png" data-src="${staticurl}/images/esf/esxq_banner1.png" alt="">
            </li>
        </ul>
        <div class="banner-title">
            <#--<div class="banner-house-number">房源编号：${build['building_name']}</div>-->
            <div class="swiper-pagination pictrue-index"></div>
        </div>
    </div>
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
            <div class="pswp__ui pswp__ui--hidden">
                <div class="pswp__top-bar">
                    <div class="pswp__counter"></div>
                    <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                    <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                    <div class="pswp__preloader">
                        <div class="pswp__preloader__icn">
                            <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                    <div class="pswp__share-tooltip"></div>
                </div>
                <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
                <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
                <div class="pswp__caption">
                    <div class="pswp__caption__center"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="module-bottom-fill">
    <section class="plot-primary-header">
        <div class="plot-primary-text">
            <h2>新世纪丽樽</h2>
            <p>[顺义-商圈] 中央别墅区 顺语路57号</p>
            <p>距离地铁马泉营站 [15号线] 3.8km</p>
            <div class="house-labelling gray">
                <span>满五</span>
                <span>满二</span>
                <span>随时看房</span>
            </div>
        </div>
        <div class="plot-primary-map-box"></div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>推荐小区好房</h3>
            <a href="#" class="more-arrows">查看全部房源<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>市场行情</h3>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>基本信息</h3>
            <a href="#" class="more-arrows">查看更多<i class="arrows-right"></i></a>
        </div>
        <div class="basic-information">
            <div class="column item-only-one">
                <div class="info-card-item">首城国际，<em class="high-light-red">2008</em>年建成住宅，共<em class="high-light-red">18</em>栋（2558户）<br><em class="high-light-red">板楼/板塔结合</em></div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-1"></i>
                    <div class="info-item-text">
                        <p>人均绿化</p>
                        <em>19平方米</em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-2"></i>
                    <div class="info-item-text">
                        <p>车位配比</p>
                        <em>1.5车位/户</em>
                    </div>
                </div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-3"></i>
                    <div class="info-item-text">
                        <p>户均电梯</p>
                        <em>暂无</em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-4"></i>
                    <div class="info-item-text">
                        <p>空气质量</p>
                        <em>暂无</em>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>交通信息</h3>
        </div>
        <div class="basic-information">
            <div class="column item-column-three">
                <div class="info-card-item">
                    <i class="item-three-1"></i>
                    <em>公交</em>
                    <p>四惠公交站</p>
                    <span>24条线路</span>
                </div>
                <div class="info-card-item">
                    <i class="item-three-2"></i>
                    <em>地铁</em>
                    <p>国贸站[1号线]</p>
                    <span>0.5km</span>
                </div>
                <div class="info-card-item">
                    <i class="item-three-3"></i>
                    <em>自驾</em>
                    <p>三环主路</p>
                    <span>0.7km</span>
                </div>
            </div>
        </div>
    </section>
</div>

<div id="mapMessage">地图</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>医疗配套</h3>
            <a href="javascript:;" class="more-arrows expand-btn"><em>展开</em><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <ul class="result-data-expand">
                <li>
                    <p>
                        <i class="expand-icon medical-treatment"></i>
                        <span class="expand-name">北医三院</span>
                    </p>
                    <span class="expand-distance">1.8km内</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon medical-treatment"></i>
                        <span class="expand-name">北京解放军医院</span>
                    </p>
                    <span class="expand-distance">1.8km内</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon medical-treatment"></i>
                        <span class="expand-name">海淀医院</span>
                    </p>
                    <span class="expand-distance">1.8km内</span>
                </li>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>生活成本</h3>
            <a href="javascript:;" class="more-arrows expand-btn"><em>展开</em><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <ul class="result-data-expand">
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">水费</span>
                        <span class="expand-price">3元/吨</span>
                    </p>
                    <span class="expand-distance tips">居民用水价格范围为1-4元/吨</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">电费</span>
                        <span class="expand-price">3元/度</span>
                    </p>
                    <span class="expand-distance tips">居民用电价格范围为1-4元/度</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">物业费</span>
                        <span class="expand-price">4元/㎡·月</span>
                    </p>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">停车费</span>
                        <span class="expand-price">30元/月</span>
                    </p>
                </li>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a href="#" class="more-arrows">配套详情<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>待售房源</h3>
            <a href="#" class="more-arrows">查看全部待售<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>看了本楼盘的用户还看了</h3>
        </div>
        <ul class="tilelist">
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
        </ul>
    </section>
</div>
<section>
    <div class="module-header-message">
        <h3>新房推荐</h3>
    </div>
    <ul class="tilelist">
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
    </ul>
</section>
<section class="detail-contact-box" id="detailContactState">
    <div class="detail-contact-content">
        <a href="#" class="contact-share"><i></i>分享</a>
        <a href="#" class="contact-collect"><i></i>收藏</a>
        <a href="tel:1234789" class="contact-telephone-counseling">咨询售楼处</a>
    </div>
</section>

<script src="${staticurl}/js/zepto.min.js"></script>
<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<script>

    $.get('http://api.map.baidu.com/place/v2/search?query=银行&location=39.915,116.404&radius=2000&output=json&ak=qecR0qeVFD5yOk8NvT5aDLNjgWiKHbaf', function(response){
        console.log(response);
    });
</script>
</body>
</html>