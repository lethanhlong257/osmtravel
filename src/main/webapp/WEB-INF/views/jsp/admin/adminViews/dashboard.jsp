<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>
        Material Dashboard by Creative Tim
    </title>

    <jsp:include page="/admin/adminPartials/adminHead.jsp" />
</head>

<body class="">
<div class="wrapper ">
    <jsp:include page="/admin/adminPartials/slidebar.jsp" />
    <jsp:include page="/admin/adminPartials/mainPanel.jsp" />
</div>
<div class="fixed-plugin">

</div>
<jsp:include page="/admin/adminPartials/coreJSFiles.jsp" />
<script src="/admin/assets/js/customs.js"></script>

<script>
  $(document).ready(function() {
    // Javascript method's body can be found in assets/js/demos.js
    md.initDashboardPageCharts();

  });
</script>
</body>

</html>
