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
<jsp:include page="adminLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h2>レコードを変更</h2>
            <c:forEach var="record" items="${requestScope.recordList}">
                <form name="form" action="../modityReco/${record.id}" method="get" onsubmit="return yz()">
                    <h6>お名前</h6>
                    <input type="text" name="name" value="${record.name }" style="color: #90111a" readonly
                           class="form-control" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"
                           maxlength="4"/>
                    <h6>日&nbsp;&nbsp;付</h6>
                    <input type="date" name="date" class="form-control" value="${record.date }"/>
                    <h6>出勤時間</h6>
                    <input type="time" name="startime" step="01" class="form-control" id="startime"
                           value="${record.startime }"/>
                    <h6>退勤時間</h6>
                    <input type="time" name="endtime" step="01" class="form-control" id="endtime"
                           value="${record.endtime }"/>
                    <h6>休憩</h6>
                    <input type="text" name="rest" class="form-control" id="rest" value="${record.rest }"/>
                    <h6>設計</h6>
                    <input type="text" name="design" class="form-control"
                           id="design" value="${mission.design }"/>
                    <h6>コード</h6>
                    <input type="text" name="code" class="form-control"
                           id="code" value="${mission.code }"/>
                    <h6>テスト</h6>
                    <input type="text" name="test" class="form-control"
                           id="test" value="${mission.test }"/>
                    <h6>会議</h6>
                    <input type="text" name="meeting" class="form-control"
                           id="meeting" value="${mission.meeting }"/>
                    <h6>勉強</h6>
                    <input type="text" name="study" class="form-control"
                           id="study" value="${mission.study }"/>
                    <h6>実働時間(h)</h6>
                    <input type="text" name="worktime" id="worktime" class="form-control" style="color: #90111a"
                           readonly value="${record.worktime }"/>
                    <h6>特別な場合</h6>
                    <textarea name="situation" class="form-control" cols="20" rows="6">${record.situation }</textarea>
                    <h6>備考</h6>
                    <textarea name="remarks" class="form-control" cols="20" rows="6">${record.remarks }</textarea>

                    <input type="submit" class="btn btn-primary btn-wide login-btn" id="btn1" style="margin-top:2rem"
                           value="修正"/>
                    <br/>
                </form>
            </c:forEach>
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