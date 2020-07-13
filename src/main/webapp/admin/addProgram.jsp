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
    <script type="text/javascript">
        $(document).ready(
            function () {
                $("#pname").change(
                    function () {
                        $.post("/employee/AjaxHandler/existPname?pname="
                            + $("#pname").val(), function (data, status) {
                            $("#countsid").html(data);
                        })
                    })
            })
    </script>
    <script type='text/javascript' src='/employee/utils/js/particles.js'></script>
    <link href="/employee/utils/css/animate.css" rel="stylesheet">
</head>
<body>
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="adminLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5>プロジェクトを追加</h5>
            <form action="/employee/ProgramHandler/addProgram" method="post" name="form1">
                <div class="row">
                    <div class="col-md-6">
                        <h6>プロジェクト名</h6>
                        <input type="text" name="name" id="name" class="form-control"
                               maxlength="25"/>
                        <span id="countsid" style="color: #ff0000;"></span>
                        <h6>勤務先住所</h6>
                        <input type="text" name="adress" class="form-control" onkeyup="value=value.replace(/[\d]/g,'') "
                               onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))"
                               maxlength="100"/>
                        <h6>プロジェクト開始日</h6>
                        <input type="date" name="startDate" class="form-control"/>
                        <h6>プロジェクト終了日</h6>
                        <input type="date" name="endDate" class="form-control"/>
                        <h6>出勤時間</h6>
                        <input type="time" name="startime" step="01" class="form-control" value="09:00:00"/>
                        <h6>退勤時間</h6>
                        <input type="time" name="endtime" step="01" class="form-control" value="18:00:00"/>
                        <h6>休憩</h6>
                        <input type="text" name="rest" class="form-control" value="1.0"/>
                    </div>
                    <div class="col-md-12">
                        <br> <input type="submit"
                                    class="btn btn-primary btn-wide login-btn" value="添加"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
<script type="text/javascript">
    $("select").select2({
        dropdownCssClass: 'dropdown-inverse'
    });
</script>
</body>
</html>