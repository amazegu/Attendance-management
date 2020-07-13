<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="/employee/utils/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="/employee/utils/image/favicon.ico" type="image/x-icon"/>
    <meta charset="UTF-8">
    <title>勤怠管理システム</title>

    <!-- Loading Bootstrap -->
    <link href="/employee/utils/css/bootstrap.min.css" rel="stylesheet">
    <!-- Loading Flat UI Pro -->
    <link href="/employee/utils/css/flat-ui.css" rel="stylesheet">
    <!-- Loading Flat UI JS -->
    <script type="text/javascript" src="/employee/utils/js/flat-ui.min.js"></script>

    <script type='text/javascript' src='/employee/utils/js/particles.js'></script>
    <link href="/employee/utils/css/animate.css" rel="stylesheet">
</head>

<body>
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="employeeLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <h5>個人情報の管理</h5>
                    <div class="margin:0 15px" style="overflow-x: scroll;">
                        <table style="overflow:scroll;text-align: center;" class="table table-striped table-hover animated fadeIn">

                            <thead>
                            <tr>
                                <th>/</th>
                                <th>情報</th>
                                <th>紹介</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>ユーザー名</td>
                                <td>${sessionScope.userName }</td>
                                <td>ログイン名(一意)</td>
                            </tr>

                            <tr>
                                <td>お名前</td>
                                <td>${employee.name }</td>
                                <td>変更不可(一意)</td>
                            </tr>

                            <tr>
                                <td>性別</td>
                                <td>${employee.sex == 0?"男":"女" }</td>
                                <td>変更不可</td>
                            </tr>

                            <tr>
                                <td>プロジェクト</td>
                                <td>${program}</td>
                                <td>変更不可</td>
                            </tr>

                            <tr>
                                <td>パスワード</td>
                                <td>${employee.password }</td>
                                <td><a class="btn btn-default"
                                       href="/employee/EmployeeHandler/modifypassword/${sessionScope.id }">修正 </a></td>
                            </tr>

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
</body>
</html>