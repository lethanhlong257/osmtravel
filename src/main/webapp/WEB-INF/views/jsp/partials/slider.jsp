<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="slider">
    <div class="owl-carousel slider-carousel owl-theme">
        <div class="item"><a href="#"><img src="../img/slider-1.png"/></a></div>
        <div class="item"><a href="#"><img src="../img/slider-1.png"/></a></div>
    </div>
    <div class="search">
        <div class="container">
            <h1>SEARCHING TOURISTS DESTINATION!</h1>
            <form id="form-search-points" action="${pageContext.request.contextPath}/find" method="get">
                <input id="search-map-key-word" name="keyword" type="text" placeholder="`Nhập từ khóa . . .`"/>
                <button id="btn-search-point" >Search</button>
            </form>
            <br>
        </div>
    </div>
</section>
<script>
</script>