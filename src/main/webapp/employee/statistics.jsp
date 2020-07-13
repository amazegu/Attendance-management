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
            <h5>個人統計</h5>

            <form name="employeequery"
                  action="/employee/EmployeeHandler/statistics/${sessionScope.name}/${requestScope.statime}"
                  method="get">
                <div class="row">
                    <div class="col-md-4">
                        <strong>クエリ条件<br></strong>
                        <select id="serc" name="serc" class="form-control select select-primary select-block mbl">
                            <option value="all"
                                    <c:if test="${'all' eq serc}">selected</c:if> >全部
                            </option>
                            <option value="day"
                                    <c:if test="${'day' eq serc}">selected</c:if> >日付
                            </option>
                            <option value="month"
                                    <c:if test="${'month' eq serc}">selected</c:if> >月
                            </option>
                            <option value="year"
                                    <c:if test="${'year' eq serc}">selected</c:if> >年
                            </option>
                        </select>

                    </div>
                    <div class="col-md-4">


                        <strong>条件</strong>

                        <div class="form-group has-feedback">

                            <input type="text" id="condition" name="condition" onfocus="con()" value="${condition}"
                                   placeholder="入力値" class="form-control"/>

                        </div>

                    </div>
                    <div class="col-md-3 pos">
                        <br>
                        <input type="submit" class="btn btn-primary" value="クエリー"/>
                    </div>
                </div>
            </form>

        </div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <div class="margin:0 15px" style="overflow-x: scroll;">
                        <table style="overflow:scroll;text-align: center" class="table table-striped table-hover animated fadeIn">
                            <tr>
                                <th>お名前</th>
                                <th>実際時間(本日)</th>
                                <th>总計時間(全部)</th>
                            </tr>
                            <tr>
                                <td>${requestScope.name}</td>
                                <td>${requestScope.wtime}</td>
                                <td>${requestScope.statime }</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("select").select2({dropdownCssClass: 'dropdown-inverse'});

    function con() {
        var a = $("#serc").val();//获取输入框1的值
        if (a == "all") {
            $("#condition").attr("placeholder", "入力値");
        } else if (a == "day") {
            $("#condition").attr("placeholder", "2020-01-01");
        } else if (a == "month") {
            $("#condition").attr("placeholder", "2020-01");
        } else if (a == "year") {
            $("#condition").attr("placeholder", "2020");
        }
    }
</script>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
</body>
</html>