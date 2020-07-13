<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navbar navbar-inverse navbar-expand-lg" role="navigation">
    <a class="navbar-brand" href="/employee/EmployeeHandler/employeepage">勤怠管理システム</a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse-01"></button>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
        <ul class="nav navbar-nav mr-auto">
            <%--		<li><a href="/employee/EmployeeHandler/employeeSign//${sessionScope.id }"><strong>サインイン</strong></a>--%>
            <li><a href="/employee/EmployeeHandler/situation/${sessionScope.id }"><strong>特別な場合</strong></a>
            <li><a href="/employee/EmployeeHandler/managerec/${sessionScope.name }/1"><strong>出席記録</strong></a>
            <li><a href="/employee/EmployeeHandler/querySelf/${sessionScope.id }"><strong>個人情報</strong></a>
            <li><a href="/employee/EmployeeHandler/manageSta/${sessionScope.name }"><strong>個人統計</strong></a>
            <li><a href="/employee/ReportHandler/managerep/${sessionScope.name }/1"><strong>レポート</strong></a>
        </ul>
        <button><a href="/employee/EmployeeHandler/employeeSignIn/${sessionScope.name }"><strong>サインイン</strong></a>
        </button>
        <button><a href="/employee/EmployeeHandler/employeeSignOut/${sessionScope.id }"><strong>サインアウト</strong></a>
        </button>
        <p class="navbar-text navbar-right">welcome！${sessionScope.name}<a href="/employee/LoginHandler/employeelogout"
                                                                           class="navbar-link" style="color: aqua"
                                                                           href="#">ログアウト</a></p>
    </div>
</nav>