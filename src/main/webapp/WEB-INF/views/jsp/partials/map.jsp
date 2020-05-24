<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="container">
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