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
<script type="text/javascript">
    function load() {
        var myDate = new Date();
        var endtime = myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();
        var today = myDate.toLocaleDateString();
        var b = myDate.getTime();
        var ntime = new Date(today.toString() + " " + $("#startime").val());
        var a = ntime.getTime();
        var r = $("#rest").val();
        if (r == null || r == "") {
            r = 0.0
        }
        ;
        var rest = parseFloat(r);
        var worktime = (parseFloat((b - a) / 1000 / 3600) - rest).toFixed(1);
        worktime = worktime > 0.0 ? worktime : 0.0;
        $("#worktime").val(worktime);
        $("#endtime").val(endtime);
    }

    $(function () {
        $("#startime").bind("input propertychange", function () {//监控输入框2
            var s = $("#startime").val();
            var myDate = new Date();
            var endtime = myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();
            var today = myDate.toLocaleDateString();
            var b = myDate.getTime();
            var ntime = new Date(today.toString() + " " + $("#startime").val());
            var a = ntime.getTime();
            var r = $("#rest").val();
            if (r == null || r == "") {
                r = "0.0"
            }
            ;
            var rest = parseFloat(r);
            if (s != "") {
                var worktime = (parseFloat((b - a) / 1000 / 3600) - rest).toFixed(1);
                worktime = worktime > 0.0 ? worktime : 0.0;
                $("#worktime").val(worktime);
            } else {
                $("#worktime").val("0.0");
            }
            $("#endtime").val(endtime);
        });
    })
    $(function () {
        $("#rest").bind("input propertychange", function () {//监控输入框2
            var s = $("#startime").val();
            var myDate = new Date();
            var endtime = myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();
            var today = myDate.toLocaleDateString();
            var b = myDate.getTime();
            var ntime = new Date(today.toString() + " " + $("#startime").val());
            var a = ntime.getTime();
            var r = $("#rest").val();
            if (r == null || r == "") {
                r = "0.0"
            }
            ;
            var rest = parseFloat(r);
            if (s != "") {
                var worktime = (parseFloat((b - a) / 1000 / 3600) - rest).toFixed(1);
                worktime = worktime > 0.0 ? worktime : 0.0;
                $("#worktime").val(worktime);
            } else {
                $("#worktime").val("0.0");
            }
            $("#endtime").val(endtime);
        });
    })
</script>
<body onload="load()">
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="employeeLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form method="post" name="form1" id="form1" action="/employee/EmployeeHandler/remarks" onsubmit="return sum()">
                <div class="row">
                    <div class="col-md-6">
                        <input type="text" name="id" class="form-control" id="id" value="${record.id}" hidden/>
                        <input type="text" name="eid" class="form-control" id="eid" value="${sessionScope.id}" hidden/>
                        <input type="time" name="endtime" step="01" class="form-control" id="endtime" hidden/>
                        <h6>お名前</h6>
                        <input type="text" name="name" id="name" class="form-control" style="color: #90111a" required
                               readonly value="${sessionScope.name }"/>
                        <h6>日付</h6>
                        <input type="date" name="date" class="form-control" readonly style="color: #90111a" id="date"
                               value="${date}"/>
                        <h6>出勤時間</h6>
                        <input type="time" name="startime" step="01" class="form-control" id="startime"
                               value="${record.startime}"/>
                        <h6>休憩</h6>
                        <input type="text" name="rest" class="form-control" id="rest" value="1.0"/>
                        <h6>本日の勤務時間</h6>
                        <input type="text" name="worktime" class="form-control" style="color: #90111a" readonly
                               id="worktime"/>
                        <h6>設計</h6>
                        <input type="text" name="design" class="form-control"
                               id="design" value="0.0"/>
                        <h6>コード</h6>
                        <input type="text" name="code" class="form-control"
                               id="code" value="0.0"/>
                        <h6>テスト</h6>
                        <input type="text" name="test" class="form-control"
                               id="test" value="0.0"/>
                        <h6>会議</h6>
                        <input type="text" name="meeting" class="form-control"
                               id="meeting" value="0.0"/>
                        <h6>勉強</h6>
                        <input type="text" name="study" class="form-control"
                               id="study" value="0.0"/>
                        <h6>特別な場合</h6>
                        <textarea name="situation" cols="20" rows="2" class="form-control"
                                  value="${record.situation}"></textarea>
                        <h6>備考</h6>
                        <textarea name="remarks" cols="20" rows="2" class="form-control"
                                  value="${record.situation}"></textarea>
                    </div>
                    <div class="col-md-12">
                        <br> <input type="submit"
                                    class="btn btn-primary btn-wide login-btn" value="サインアウト"/>
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
    function sum() {
        var design = $("#design").val();
        var study = $("#study").val();
        var code = $("#code").val();
        var meeting = $("#meeting").val();
        var test = $("#test").val();
        var worktime = parseFloat($("#worktime").val()).toFixed(1);
        var sum = (parseFloat(design)+parseFloat(study)+parseFloat(code)+parseFloat(meeting)+parseFloat(test)).toFixed(1);
        if (sum == worktime) {
            return true;
        } else {
            alert("各作業内容の時間を確認してください！");//弹出提示框
            $("#design").focus(); //获取鼠标焦点
            return false;
        }
    }
</script>
</body>
</html>