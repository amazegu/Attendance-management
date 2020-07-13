<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse navbar-expand-lg" role="navigation">
    <a class="navbar-brand" href="/employee/AdminHandler/adminpage">勤怠管理システム</a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse-01"></button>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
        <ul class="nav navbar-nav mr-auto">
            <li><a href="/employee/AdminHandler/manageemp/1"><strong>従業員管理</strong></a>
            <li><a href="/employee/ProgramHandler/manageProgram/1"><strong>プロジェクト管理</strong></a>
            <li><a href="/employee/AdminHandler/managerec/1"><strong>勤怠管理</strong></a>
            <li><a href="/employee/AdminHandler/manageSta/1"><strong>統計</strong></a>
        </ul>
        <p class="navbar-text navbar-right">welcome！${sessionScope.aname}<a href="/employee/LoginHandler/adminlogout"
                                                                            class="navbar-link" style="color: aqua"
                                                                            href="#">ログアウト</a></p>
    </div> <!--/.navbar-collapse -->
</nav>
<!-- /navbar -->



