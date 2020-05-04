<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-panel">
    <!-- Navbar -->
    <jsp:include page="/admin/adminPartials/navbar.jsp" />
    <!-- End Navbar -->
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <c:set var="isPlaced" value="${request.getAttribute('isPlaced')}" />
                    <c:if test="${isPlaced == null}">
                        ${isPlaced}
                    </c:if>
                    <c:if test='${isPlaced != null}'>
                        <c:choose>
                            <c:when test="${isPlaced == true}">
                                <div class="alert alert-success">
                                    <strong>Success!</strong> Saving successfully.
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger">
                                    <strong>Failed!</strong>.
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <div class="card">
                        <div class="card-header card-header-primary">
                            <h4 class="card-title">Add new place to map</h4>
                            <p class="card-category">Latitude and longtitude should be in second exactly</p>
                        </div>
                        <div class="card-body">
                            <form action="/create-new-place" method="post">
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Location name</label>
                                            <input type="text" class="form-control" name="name" required>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Latitude in second</label>
                                            <input type="text" class="form-control" name="latitude" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Longtitude in second</label>
                                            <input type="text" class="form-control" name="longtitude" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Country</label>
                                            <input type="text" class="form-control" name="country" required>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">City</label>
                                            <input type="text" class="form-control" name="city" required>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">ward</label>
                                            <input type="text" class="form-control" name="ward" required>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">District</label>
                                            <input type="text" class="form-control" name="district" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Street</label>
                                            <input type="text" class="form-control" name="street" required>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Postal Code</label>
                                            <input type="text" class="form-control" name="zipCode">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Upload Image</label>
                                            <input type="file" id="myFile" name="filename" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Phone number</label>
                                            <input class="form-control" type="text" name="phone" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Descripton</label>
                                            <div class="form-group">
                                                <label class="bmd-label-floating">Description the position</label>
                                                <textarea class="form-control" rows="5" name="description"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary pull-right">Add Place</button>
                                <div class="clearfix"></div>
                            </form>
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