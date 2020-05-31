<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="slider">
    <div class="owl-carousel slider-carousel owl-theme">
        <div class="item"><a href="#"><img src="../img/slider-1.png"/></a></div>
        <div class="item"><a href="#"><img src="../img/slider-1.png"/></a></div>
    </div>
    <div class="search">
        <div class="container">
            <h1>SEARCHING TOURISTS DESTINATION!</h1>
            <form id="form-search-points" action="${pageContext.request.contextPath}/search" method="get">
                <input id="search-map-key-word" name="keyword" type="text" placeholder="Nhập từ khóa . . ."/>
                <select>
                    <option>HCM city</option>
                </select>
                <select>
                    <option>District</option>
                    <option>Tan Binh</option>
                    <option>Quận 1</option>
                    <option>Quận 2</option>
                    <option>Quận 3</option>
                    <option>Quận 4</option>
                    <option>Quận 5</option>
                    <option>Quận 6</option>
                    <option>Quận 7</option>
                    <option>Quận 8</option>
                    <option>Quận 9</option>
                    <option>Quận 10</option>
                    <option>Quận 11</option>
                    <option>Quận 12</option>
                    <option>Go Vap</option>
                </select>
                <button id="btn-search-point" >Search</button>
            </form>
            <br>
            <form id="form-advanced-search" action="javascript:void(0)" method="get">
                <input id="input-advanced-search" name="keyword" type="text" placeholder="Nhập từ khóa . . ."/>
                <input id="button-advanced-search" type="submit" value="Advanced Search" />
            </form>

            <form class="map-text" id="form-routing" action="/routing" method="get">
                <input name="from" id="input-routing-from" class="text-special" type="text" placeholder="FROM"/>
                <input name="to" id="input-routing-to" class="text-special" type="text" placeholder="TO"/>
                <input id="input-routing-submit-button"  type="submit" value="Chỉ Đường" />
            </form>
        </div>
    </div>
</section>
<script>
</script>