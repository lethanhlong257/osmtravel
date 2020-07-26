<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>
        Material Dashboard
    </title>
    <jsp:include page="adminPartials/adminHead.jsp" />
</head>

<body class="">
<div class="wrapper ">
    <jsp:include page="adminPartials/slidebar.jsp" />
    <div class="main-panel">
        <!-- Navbar -->
        <jsp:include page="./adminPartials/navbar.jsp" />
        <!-- End Navbar -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title">Update location</h4>
                                <p class="card-category">Latitude and longtitude should be in second exactly</p>
                                <p class="card-category">Using Quick add to find the location quickly</p>
                            </div>
                            <div class="card-body">
                                <c:if test='${isUpdated != null}'>
                                    <c:choose>
                                        <c:when test="${isUpdated == true}">
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
                                <hr />
                                <form:form action="/admin/location/update/${Point.id}" enctype="multipart/form-data" method="post" modelAttribute="Point">
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
                                                    <%--                                            <form:input name="multipartFile" class="input-upload-img" accept="image/png, image/jpeg" type="file" path="img" />--%>
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
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label data-toggle="tooltip" data-placement="top"
                                                       title="Gia ca"
                                                >Prices:</label>
                                                <form:input class="form-control" type="text" path="price"  />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label data-toggle="tooltip" data-placement="top"
                                                       title="dùng cho phân loại: mỗi tags cách nhau bằng dấu ;. ví dụ: lịch sử;công viên;giải trí"
                                                >Tags:</label>

                                                <form:input class="form-control" type="text" path="tags"  />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="form-group">
                                                <label data-toggle="tooltip" data-placement="top"
                                                       title="link"
                                                >Reference link:</label>
                                                <form:input class="form-control" type="text" path="link"  />
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
                                    <button type="submit" class="btn btn-primary pull-right">Update</button>
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
</div>
<div class="fixed-plugin">

</div>
<jsp:include page="adminPartials/coreJSFiles.jsp" />
<jsp:include page="adminPartials/adminFooter.jsp" />

<script src="/admin_staic/assets/js/customs.js"></script>
<script>
  $(document).ready(function() {
    // Javascript method's body can be found in assets/js/demos.js
    md.initDashboardPageCharts();

  });
</script>
</body>

</html>
