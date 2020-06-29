<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="rent">
    <div class="container">
        <div class="title-page">
            <c:choose>
                <c:when test="${isSearchResult == true}"><h3><a>Result search</a></h3></c:when>
                <c:otherwise><h3><a>SỬA LẠI CÁI GÌ NGAY ĐÂY ĐI</a></h3></c:otherwise>
            </c:choose>
        </div>
        <div></div>
        <div class="swiper-container swiper-suggest">
            <div class="swiper-wrapper">
                <c:forEach items="${points}" var="point">
                    <div class="swiper-slide">
                        <div class="box-rent">
                            <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2019</div>
                            <div class="box-rent-img">
                                <i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="${point.name}" data-fancybox="gallery"></i>
                                <a href="https://www.tripadvisor.com/Attraction_Review-g293925-d1178970-Reviews-Dam_Sen_Water_Park-Ho_Chi_Minh_City.html">
                                    <c:choose>
                                        <c:when test="${point.img == null || point.img == ''}">
                                            <%
                                                int random = (int)(Math.random() * 6 + 1);
                                            %>
                                            <img src="../img/<%=random%>.png"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${point.img}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="box-rent-detail">
                                <h3><a href="chi-tiet-cho-thue.png">${point.name}</a></h3>
                                <h5> <i class="mdi mdi-map-marker-outline"></i>${point.street}</h5>
                                <div class="box-rent-detail-option"><span>Du lịch</span><span>Giải trí</span><span>Ngoài trời</span></div>
                            </div>
                            <div class="box-rent-price">
                                <h3>${point.price}VND<span>/adult</span></h3>
                                <h3 class="text-prices">Miễn phí<span>/children</span></h3>
                                <h6 class="find-on-map-button" data-selectable="${point.lat}-${point.lon}-${point.name}-${point.street}">Tìm trên bản đồ</h6>
                            </div>
                        </div>
                    </div>
                </c:forEach>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2019</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="../img/damsen.png"></i><a href="https://www.tripadvisor.com/Attraction_Review-g293925-d1178970-Reviews-Dam_Sen_Water_Park-Ho_Chi_Minh_City.html"><img src="../img/damsen.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Đầm Sen Park</a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>3 Hòa Bình, WARD 3, District 11, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>240.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">160.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2020</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="https://www.tripadvisor.com/Attraction_Review-g293925-d2389145-Reviews-Suoi_Tien_Theme_Park-Ho_Chi_Minh_City.html"></i><a href="chi-tiet-cho-thue.png"><img src="../img/suoitien.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Suoi Tien Park</a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>120 Xa lộ Hà Nội,Tân Phú ward, District 9, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>120.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">60.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2020</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="https://en.wikipedia.org/wiki/Notre-Dame_Cathedral_Basilica_of_Saigon"></i><a href="chi-tiet-cho-thue.png"><img src="../img/ducba.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Duc Ba Church</a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>01 Công xã Paris, Bến Nghé, District 1, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>120.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">60.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2020</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="https://dulichfun.com/huong-dan-du-lich-van-thanh-di-lai-vui-choi-va-an-uong.html"></i><a href="chi-tiet-cho-thue.png"><img src="../img/dulichvanthanh.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Van Thanh Tourist Area</a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>48/10 Điện Biên Phủ, Ward 22, Bình Thạnh District, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>120.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">60.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2020</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="https://quan3.hochiminhcity.gov.vn/dia-diem-tham-quan/ho-con-rua-2.html"></i><a href="chi-tiet-cho-thue.png"><img src="../img/hoconrua.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Ho Con Rua </a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>Vòng Xoay Công Trường Quốc Tế, District 3, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>120.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">60.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2020</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="http://www.baotangchungtichchientranh.vn/Main.aspx?L=VN"></i><a href="chi-tiet-cho-thue.png"><img src="../resources/img/museumwar.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Museum War</a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>28 Võ Văn Tần, Ward 6, District 3, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>120.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">60.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="swiper-slide">--%>
<%--                    <div class="box-rent">--%>
<%--                        <div class="box-rent-date"><i class="mdi mdi-calendar-outline"> </i>22/04/2020</div>--%>
<%--                        <div class="box-rent-img"><i class="mdi mdi-image-search-outline" title="Nhấn để phóng to ảnh" data-caption="Căn hộ cao cấp nằm giữa trung tâm Quận 1" data-fancybox="gallery" href="https://en.wikipedia.org/wiki/Landmark_81"></i><a href="chi-tiet-cho-thue.png"><img src="../img/landmark.png"/></a></div>--%>
<%--                        <div class="box-rent-detail">--%>
<%--                            <h3><a href="chi-tiet-cho-thue.png">Landmark</a></h3>--%>
<%--                            <h5> <i class="mdi mdi-map-marker-outline"></i>720A Điện Biên Phủ, Ward 22, Binh ThanhDistrict, TPHCM</h5>--%>
<%--                            <div class="box-rent-detail-option"><span>2 Phòng ngủ</span><span>2 Phòng tắm</span><span>50m2</span></div>--%>
<%--                        </div>--%>
<%--                        <div class="box-rent-price">--%>
<%--                            <h3>120.000VND<span>/adult</span></h3>--%>
<%--                            <h3 class="text-prices">60.000VND<span>/children</span></h3>--%>
<%--                            <h6>ACTIVE</h6>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>
        <div class="rent-all"><a href="cho-thue.html">Xem tất cả</a></div>
    </div>
</section>