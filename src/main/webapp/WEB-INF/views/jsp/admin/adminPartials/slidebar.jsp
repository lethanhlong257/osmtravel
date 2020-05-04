<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" data-color="purple" data-background-color="white" data-image="/admin/assets/img/sidebar-1.jpg">
    <!--
      Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

      Tip 2: you can also add an image using data-image tag
  -->
    <div class="logo"><a href="#" class="simple-text logo-normal">
        PROJECT NAME
    </a></div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li class="nav-item active  ">
                <a class="nav-link" href="/admin">
                    <i class="material-icons">dashboard</i>
                    <p>Dashboard</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="#">
                    <i class="material-icons">person</i>
                    <p>User Management</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/place-list">
                    <i class="material-icons">content_paste</i>
                    <p>Places List</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/create-new-place">
                    <i class="material-icons">location_ons</i>
                    <p>Add New Place</p>
                </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="#">
                    <i class="material-icons">notifications</i>
                    <p>Notifications</p>
                </a>
            </li>
        </ul>
    </div>
</div>
