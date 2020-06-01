<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>
        place list management
    </title>

    <jsp:include page="views/adminPartials/adminHead.jsp" />
</head>

<body class="">
<div class="wrapper ">
    <jsp:include page="views/adminPartials/slidebar.jsp" />
    <jsp:include page="views/adminPartials/PlaceListMainPanel.jsp" />

</div>
<div class="fixed-plugin">

</div>
<jsp:include page="views/adminPartials/coreJSFiles.jsp" />
<script src="/admin_staic/assets/js/customs.js"></script>

<script>
  $(document).ready(function() {
    // Javascript method's body can be found in assets/js/demos.js
    md.initDashboardPageCharts();

  });
</script>
</body>

</html>
