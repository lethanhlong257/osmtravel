<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Place" %>
<div class="main-panel">
    <!-- Navbar -->
    <jsp:include page="/admin/adminPartials/navbar.jsp" />
    <!-- End Navbar -->
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header card-header-primary">
                            <h4 class="card-title ">Simple Table</h4>
                            <p class="card-category"> Here is a subtitle for this table</p>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class=" text-primary">
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Country</th>
                                    <th>City</th>
                                    <th>District</th>
                                    <th>Ward</th>
                                    <th>Street</th>
                                    <th>Coordinate</th>
                                    </thead>
                                    <tbody>
                                    <% List places = (List) request.getAttribute("listPlace"); %>

                                    <% for (Object placeObj: places) {
                                        Place place = new Place();
                                        place = (Place) placeObj;
                                    %>
                                        <tr>
                                            <td><%=place.getId() %></td>
                                            <td><%=place.getName() %></td>
                                            <td><%=place.getCountry() %></td>
                                            <td><%=place.getCity() %></td>
                                            <td><%=place.getDistrict() %></td>
                                            <td><%=place.getWard() %></td>
                                            <td><%=place.getStreet1() %></td>
                                            <td><%=place.getLatitude_second() %>,<%=place.getLongtitude_second() %></td>
                                        </tr>
                                    <%} %>

                                    <%--<c:set var="places" value="${request.getAttribute('listPlace')}" />
                                    <c:out value="${places.toString()}" />
                                    <c:forEach items="places2" var="p" >
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td><c:out value="${places2.size()}" /></td>
                                            <td></td>
                                            <td>Oud-Turnhout</td>
                                            <td>Oud-Turnhout</td>
                                            <td>Oud-Turnhout</td>
                                            <td>Oud-Turnhout</td>
                                        </tr>
                                    </c:forEach>--%>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>