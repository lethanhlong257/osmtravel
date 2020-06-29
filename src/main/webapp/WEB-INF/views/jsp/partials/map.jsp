<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="container">
    <div class="row">
        <form class="search-map" id="form-advanced-search" action="/search/advanced" method="get">
            <input id="input-advanced-search" name="keyword" type="text" placeholder="Nhập từ khóa . . ."/>
            <input id="input-advanced-search-data" type="hidden" name="data" value="" />
            <input id="button-advanced-search" type="submit" value="Search on Map" />
        </form>
    </div>
    <div class="row">
        <input type="button" id="current-location-button" class="current-location-button" value="my location" />
    </div>
    <div class="row">
        <form class="map-routing" id="form-routing" action="/routing" method="get">
            <input name="from" id="input-routing-from" class="text-special" type="text" placeholder="FROM"/>
            <input name="to" id="input-routing-to" class="text-special" type="text" placeholder="TO"/>
            <input id="input-routing-submit-button"  type="submit" value="Routing" />
        </form>
    </div>
    <span id="alert-from-to" class="alert-shortest-path"></span>
    <br/>
    <span id="alert-shortest-path" class="alert-shortest-path"></span>
    <div id="map"></div>
    <c:if test="${listPoints != null}">
        <div class="search-list-point">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Search result list</h3>
                    <ul class="list-group list-group-flush">
                        <c:forEach items="${listPoints}" var="point">
                            <li class="search-item-list list-group-item text-left" value="${point.lat}-${point.lon}">
                                ${point.name} - (${point.lat}, ${point.lon})
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>
</div>