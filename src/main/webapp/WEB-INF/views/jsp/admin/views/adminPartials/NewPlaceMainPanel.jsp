<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-panel">
    <!-- Navbar -->
    <jsp:include page="navbar.jsp" />
    <!-- End Navbar -->
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <c:set var="isPlaced" value="${isPlaced}" />
                    <c:if test='${isPlaced != null}'>
                        <c:choose>
                            <c:when test="${isPlaced == true}">
                                <div class="alert alert-success">
                                    <strong>Success!</strong> Saving successfully.
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger">
                                    <strong>Failed!</strong>.<span>${message}</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <div class="card">
                        <div class="card-header card-header-primary">
                            <h4 class="card-title">Add new place to map</h4>
                            <p class="card-category">Latitude and longtitude should be in second exactly</p>
                            <p class="card-category">Using Quick add to find the location quickly</p>
                        </div>
                        <div class="card-body">
                            <form id="form-quick-add">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="input-quickadd" placeholder="Address or Name of your location" name="name" required>
                                            <input type="submit" value="Quick check" />
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <hr />
                            <form:form action="/admin/place/add" method="post" modelAttribute="Point">
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Location name</label>
                                            <form:input id="input-place-name" type="text" class="form-control" path="name" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Latitude in second</label>
                                            <form:input id="input-place-lat" type="text" class="form-control" path="lat" />
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Longtitude in second</label>
                                            <form:input id="input-place-lon" type="text" class="form-control" path="lon" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Country</label>
                                            <form:input id="input-place-country" type="text" class="form-control" path="country" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">City</label>
                                            <form:input id="input-place-city" type="text" class="form-control" path="city" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">ward</label>
                                            <form:input id="input-place-ward" type="text" class="form-control" path="ward" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">District</label>
                                            <form:input id="input-place-district" type="text" class="form-control" path="district" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Street</label>
                                            <form:input id="input-place-street" type="text" class="form-control" path="street" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Postal Code</label>
                                            <form:input id="input-place-zipcode" type="text" class="form-control" path="zipCode" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Upload Image</label>
                                            <form:input class="input-upload-img" accept="image/png, image/jpeg" type="file" id="myFile" path="img" />
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Phone number</label>
                                            <form:input id="input-place-phone" class="form-control" type="text" path="phone" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Descripton</label>
                                            <div class="form-group">
                                                <label class="bmd-label-floating">Description the position</label>
                                                <form:textarea class="form-control" rows="5" path="desc"></form:textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary pull-right">Add Place</button>
                                <div class="clearfix"></div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card card-profile">
                        <div class="card-avatar">
                            <a href="javascript:;">
                                <img class="img" src="/admin/assets/img/faces/marc.jpg" />
                            </a>
                        </div>
                        <div class="card-body">
                            <h6 class="card-category text-gray">CEO / Co-Founder</h6>
                            <h4 class="card-title">Username</h4>
                            <p class="card-description">
                                Don't be scared of the truth because we need to restart the human foundation in truth And I love you like Kanye loves Kanye I love Rick Owens’ bed design but the back is...
                            </p>
                            <a href="javascript:;" class="btn btn-primary btn-round">Edit</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>