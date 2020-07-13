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
        function load() {
            var date_now = new Date();
            var year = date_now.getFullYear();
            var month = date_now.getMonth()+1;
            var monthEndDate = new Date(year, month, 0);
            if(month<10){
                var start = year+"-0"+month+"-01";
                var end = year+"-0"+month+"-"+monthEndDate.getDate();
            }else{
                var start = year+"-"+month+"-01";
                var end = year+"-"+month+"-"+monthEndDate.getDate();
            }
            $("#date").attr("min", start);
            $("#date").attr("max", end);
        }
        function dateCheck() {
            $.ajax({
                url : "/employee/ReportHandler/existRecord",
                data:{
                    "name" : $("#name").val(),
                    "date" :　$("#date").val()
                },
                dataType:"json",
                type:"post",
                success: function(data){
                    if(!data.success){
                        alert(data.msg);
                    }
                }
            });
        }
    </script>
    <script type='text/javascript' src='/employee/utils/js/particles.js'></script>
    <link href="/employee/utils/css/animate.css" rel="stylesheet">
</head>
<body onload="load()">
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="employeeLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h2>追加</h2>
            <form name="form" action="/employee/ReportHandler/addReport" method="post" onsubmit="return yz()">
                <h6>お名前</h6>
                <input type="text" name="name" id="name" value="${sessionScope.name }" style="color: #90111a" readonly
                       class="form-control"/>
                <h6>日&nbsp;&nbsp;付</h6>
                <input type="date" name="date" id="date" class="form-control" value="" required onchange="dateCheck()"/>
                <h6>出勤時間</h6>
                <input type="time" name="startime" step="01" class="form-control" id="startime"
                       value="${program.startime}"/>
                <h6>退勤時間</h6>
                <input type="time" name="endtime" step="01" class="form-control" id="endtime"
                       value="${program.endtime}"/>
                <h6>休憩</h6>
                <input type="text" name="rest" class="form-control" id="rest" value="${program.rest}"/>
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
                <h6>実働時間(h)</h6>
                <input type="text" name="worktime" id="worktime" class="form-control" style="color: #90111a"
                       readonly value="7.0"/>
                <h6>特別な場合</h6>
                <textarea name="situation" class="form-control" cols="20" rows="6"></textarea>
                <h6>備考</h6>
                <textarea name="remarks" class="form-control" cols="20" rows="6"></textarea>

                <input type="submit" class="btn btn-primary btn-wide login-btn" id="btn1" style="margin-top:2rem"
                       value="追加"/>
                <br/>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
<script type="text/javascript">
    function yz() {
        if (document.form.endtime.value != "" || document.form.situation.value != "") {
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
        } else {
            alert("時間と特殊な場合は同時に空にすることはできません！");//弹出提示框
            document.form.situation.focus(); //获取鼠标焦点
            return (false);//返回一个值
        }
    }

    $(function () {
        $("#startime").bind("input propertychange", function () {//监控输入框1
            var a = $("#startime").val();//获取输入框1的值
            if (a == "") {            //若输入框1的内容为空，则输入框2不可用
                $("#endtime").attr("disabled", "disabled");  //设置输入框2不可用
            } else if (a != "") {
                $("#endtime").removeAttr("disabled");
                var b = $("#endtime").val();//获取输入框2的值
                if (b != "") {
                    var a = $("#startime").val();
                    var d1 = new Date('2020/01/01 '.concat(a));
                    var d2 = new Date('2020/01/01 '.concat(b));
                    var s1 = d1.getTime();
                    var s2 = d2.getTime();
                    if (s1 > s2) {
                        alert("退勤時間は出勤時間より早くしてはいけません!");
                        $("#startime").val("");
                    }
                    var r = $("#rest").val();
                    if (r == null || r == "") {
                        r = "0.0"
                    }
                    ;
                    var rest = parseFloat(r);
                    var worktime = (parseFloat((s2 - s1) / 1000 / 3600) - rest).toFixed(1);
                    worktime = worktime > 0.0 ? worktime : 0.0;
                    $("#worktime").val(worktime);
                }
            }
        });
    })
    $(function () {
        $("#endtime").bind("input propertychange", function () {//监控输入框2
            var b = $("#endtime").val();//获取输入框2的值
            if (b != "") {
                var a = $("#startime").val();
                var d1 = new Date('2020/01/01 '.concat(a));
                var d2 = new Date('2020/01/01 '.concat(b));
                var s1 = d1.getTime();
                var s2 = d2.getTime();
                if (s1 > s2) {
                    alert("退勤時間は出勤時間より早くしてはいけません!");
                    $("#endtime").val("");
                }
                var r = $("#rest").val();
                if (r == null || r == "") {
                    r = "0.0"
                }
                ;
                var rest = parseFloat(r);
                var worktime = (parseFloat((s2 - s1) / 1000 / 3600) - rest).toFixed(1);
                worktime = worktime > 0.0 ? worktime : 0.0;
                $("#worktime").val(worktime);
            }
        });
    })
    $(function () {
        $("#rest").bind("input propertychange", function () {//监控输入框2
            var b = $("#endtime").val();//获取输入框2的值
            if (b != "") {
                var a = $("#startime").val();
                var d1 = new Date('2020/01/01 '.concat(a));
                var d2 = new Date('2020/01/01 '.concat(b));
                var s1 = d1.getTime();
                var s2 = d2.getTime();
                var r = $("#rest").val();
                if (r == null || r == "") {
                    r = "0.0"
                }
                ;
                var rest = parseFloat(r);
                var worktime = (parseFloat((s2 - s1) / 1000 / 3600) - rest).toFixed(1);
                worktime = worktime > 0.0 ? worktime : 0.0;
                $("#worktime").val(worktime);
            }
        });
    })
</script>
</body>
</html>