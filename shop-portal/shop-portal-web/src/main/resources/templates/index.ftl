<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>蚂蚁商城-首页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../res/layui/css/layui.css">
  <link rel="stylesheet" href="../res/static/css/index.css">
</head>
<body>

<div class="house-header">
  <div class="layui-container">
    <div class="house-nav">
      <span class="layui-breadcrumb" lay-separator="|">
     <#if desensMobile??>
       <a href="">${desensMobile}</a>
       <a href="">我的订单</a>
       <a href="/logout">退出</a>
     <#else >
       <a href="login">登录</a>
     </#if>

        <a href="http://wpa.qq.com/msgrd?v=3&uin=483966038&site=qq&menu=yes">在线客服</a>
      </span>
      <span class="layui-breadcrumb house-breadcrumb-icon" lay-separator=" ">
        <a id="search"><i class="layui-icon layui-icon-house-find"></i></a>
        <a href="login.html"><i class="layui-icon layui-icon-username"></i></a>
        <a href="usershop.html"><i class="layui-icon layui-icon-house-shop"></i></a>
      </span>
    </div>
    <div class="house-banner layui-form">
      <a class="banner" href="index.html">
        <img src="http://static.itmayiedu.com/12312312312312da.png" alt="家居商城">
      </a>
      <div class="layui-input-inline">
        <input type="text" placeholder="搜索好物" class="layui-input"><i class="layui-icon layui-icon-house-find"></i>
      </div>
      <a class="shop" href="usershop.html"><i class="layui-icon layui-icon-house-shop"></i><span class="layui-badge">1</span></a>
    </div>
    <ul class="layui-nav close">
      <li class="layui-nav-item layui-this"><a href="index.html">首页</a></li>
      <li class="layui-nav-item"><a href="list.html">居家用品</a></li>
      <li class="layui-nav-item"><a href="list.html">小家电</a></li>
      <li class="layui-nav-item"><a href="list.html">洗护</a></li>
      <li class="layui-nav-item"><a href="list.html">厨具</a></li>
      <li class="layui-nav-item"><a href="list.html">日用品</a></li>
    </ul>
    <button id="switch">
      <span></span><span class="second"></span><span class="third"></span>
    </button>
  </div>
</div>

<div class="layui-fulid">
  <div class="layui-carousel house-carousel" id="house-carousel">
    <div carousel-item>
      <div ><a href="http://www.mayikt.com" target="_blank" ><img src="../res/static/img/us_banner.jpg"></a></div>
      <div><a href="http://www.mayikt.com" target="_blank" ><img src="../res/static/img/us_banner.jpg""></a></div>
    </div>
  </div>
</div>

<div class="layui-container">
  <div class="hot-cate">
    <p class="house-title">热门分类</p>
    <div class="layui-row">
      <div class="layui-col-xs4 cateFir cate">
        <a href="list.html">
          <img src="../res/static/img/home_img1.png">
          <div>
            <p>家居用品</p>
            <span>18元起</span>
          </div>
        </a>
      </div>
      <div class="layui-col-xs4 cateSec cate">
        <a href="list.html">
          <img src="../res/static/img/home_img2.png">
          <div>
            <p>小家电</p>
            <span>59元起</span>
          </div>
        </a>
      </div>
      <div class="layui-col-xs4 cateThir cate">
        <a href="list.html">
          <img src="../res/static/img/home_img3.png">
          <div>
            <p>洗护用品</p>
            <span>18元起</span>
          </div>
        </a>
      </div>
    </div>
  </div>
  <div class="hot-sell">
    <p class="house-title">热销推荐<a href="">更多优品  &gt;</a></p>
    <div class="layui-row layui-col-space20">
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img4.jpg"></div>
        <p>一盏好看的台灯</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img5.jpg"></div>
        <p>时尚瓷碗7件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img6.jpg"></div>
        <p>智能电吹风</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img7.jpg"></div>
        <p>一盏好看的台灯</p>
        <p class="price">￥200</p>
      </a>
    </div>
  </div>
  <div class="hot-sell">
    <p class="house-title">新品推荐<a href="">更多优品  &gt;</a></p>
    <div class="layui-row layui-col-space20">
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img8.jpg"></div>
        <p>蒂芙尼蓝化妆刷</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img9.jpg"></div>
        <p>玻璃洗漱三件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img10.jpg"></div>
        <p>清洁力强劲浴球</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img11.jpg"></div>
        <p>瘦脸仪</p>
        <p class="price">￥200</p>
      </a>
    </div>
  </div>
</div>

<div class="layui-fulid" id="goodsAll"></div>

<div class="layui-container">
  <div class="hot-sell">
    <p class="house-title">猜您喜欢<a href="">更多新品  &gt;</a></p>
    <div class="layui-row layui-col-space20">
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img12.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img13.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img14.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img15.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img12.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img13.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img14.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img15.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img12.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img13.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img14.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img15.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img12.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img13.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img14.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
      <a href="detail.html" class="layui-col-xs3 text">
        <div><img src="../res/static/img/home_img15.jpg"></div>
        <p>森系小清新四件套</p>
        <p class="price">￥200</p>
      </a>
    </div>
  </div>
</div>

<div class="layui-fulid" id="champ">
  <div class="layui-row">
    <a href="list.html" class="layui-col-xs3">
      <img src="../res/static/img/home_img16.jpg">
    </a>
    <a href="list.html" class="layui-col-xs6">
      <img src="../res/static/img/home_img17.jpg">
    </a>
    <a href="list.html" class="layui-col-xs3">
      <img src="../res/static/img/home_img20.jpg">
    </a>
    <a href="list.html" class="layui-col-xs3">
      <img src="../res/static/img/home_img18.jpg">
    </a>
    <a href="list.html" class="layui-col-xs3">
      <img src="../res/static/img/home_img19.jpg">
    </a>
    <a href="list.html" class="layui-col-xs3">
      <img src="../res/static/img/home_img21.jpg">
    </a>
  </div>
</div>
<div class="house-footer">
  <div class="layui-container">
    <div class="intro">
			<span class="first"><i
                      class="layui-icon layui-icon-house-shield"></i>7天无理由退换货</span> <span
              class="second"><i class="layui-icon layui-icon-house-car"></i>满99元全场包邮</span>
      <span class="third"><i
                class="layui-icon layui-icon-house-diamond"></i>100%品质保证</span> <span
              class="last"><i class="layui-icon layui-icon-house-tel"></i>客服400-2888-966</span>
    </div>
    <div class="about">
			<span class="layui-breadcrumb" lay-separator="|"> <a
                      href="http://www.mayikt.com" target="_blank">关于我们</a> <a
                      href="http://www.mayikt.com" target="_blank">帮助中心</a> <a
                      href="http://www.mayikt.com" target="_blank">售后服务</a> <a
                      href="http://www.mayikt.com" target="_blank">配送服务</a> <a
                      href="http://www.mayikt.com" target="_blank">关于货源</a>
			</span>
      <p>上海每特教育科技有限公司版权所有- &copy; 2018-2019</p>
    </div>
  </div>
</div>

<script src="../res/layui/layui.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
  layui.config({
    base : '../res/static/js/'
  }).use('house');
</script>

</body>

</html>
