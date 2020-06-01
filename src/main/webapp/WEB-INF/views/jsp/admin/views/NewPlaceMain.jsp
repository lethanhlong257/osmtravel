<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:include page="adminPartials/NewPlaceMainPanel.jsp" />
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
