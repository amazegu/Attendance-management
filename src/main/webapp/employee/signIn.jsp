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
        function tod() {
            var time = new Date();
            var day = ("0" + time.getDate()).slice(-2);
            var month = ("0" + (time.getMonth() + 1)).slice(-2);
            var today = time.getFullYear() + "-" + (month) + "-" + (day);
            $("#date").attr('value', today);
        }

        $(function () {
            $("#startime").bind("input propertychange", function () {//监控输入框1
                var a = $("#startime").val();//获取输入框1的值
                alert(a);
                if (a = "") {            //若输入框1的内容不为空，则输入框2不可用
                    $("#endtime").attr("disabled", "disabled");  //设置输入框2不可用
                } else if (a == "") {
                    $("#endtime").removeAttr("disabled");    //移除不可用的属性
                }
            });
        })

    </script>
    <script type='text/javascript' src='/employee/utils/js/particles.js'></script>
    <link href="/employee/utils/css/animate.css" rel="stylesheet">
</head>

<body onload="tod()">
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="employeeLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5>SignIn</h5>
            <form action="/employee/EmployeeHandler/signIn" method="post" name="form1">
                <div class="row">
                    <div class="col-md-6">
                        <h6>お名前</h6>
                        <input type="text" name="name" id="name" class="form-control" required readonly
                               value="${name }"></input>
                        <span id="countsid" style="color: #ff0000;"></span>
                        <h6>日付</h6>
                        <input type="date" name="date" class="form-control" required id="date"></input>
                        <h6>出勤時間</h6>
                        <input type="time" name="startime" onkeyup="yz()" id="startime" class="form-control">
                        <h6>退勤時間</h6>
                        <input type="time" name="endtime" onkeyup="yz()" id="endtime" class="form-control">
                        <h6>実働時間(h)</h6>
                        <input type="text" name="worktime" class="form-control" readonly value="${record.worktime }">
                        <h6>特別な場合</h6>
                        <textarea name="situation" onkeyup="yz()" cols="20" rows="6" class="form-control"></textarea>
                    </div>
                    <div class="col-md-12">
                        <br> <input type="submit"
                                    class="btn btn-primary btn-wide login-btn" id="btn1" value="追加"/>
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
    var btn1 = document.getElementById("btn1");

    function yz() {
        if (document.form1.endtime.value != "" || document.form1.situation.value != "") {
            btn1.disabled = false;
        } else {
            btn1.disabled = true;
        }
    }
</script>
</body>
</html>