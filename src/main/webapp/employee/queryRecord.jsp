<%@ page import="java.util.Calendar" %>
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
    <script type="text/javascript">
        function exp() {
            var s_date = document.getElementById("s_date").value;
            var e_date = document.getElementById("e_date").value;
            window.location.href = "/employee/EmployeeHandler/export/${requestScope.name}?s_date=" + s_date + "&e_date=" + e_date;
        }

        function pri() {
            var s_date = document.getElementById("s_date").value;
            var e_date = document.getElementById("e_date").value;
            window.open("/employee/EmployeeHandler/print/${requestScope.name}?s_date=" + s_date + "&e_date=" + e_date, "_blank");
        }
    </script>
</head>

<body>
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="employeeLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5>勤務記録の管理</h5>
            <form name="recordquery" action="/employee/EmployeeHandler/queryRec/${requestScope.name}/1" method="get">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group has-feedback">
                            <input type="date" name="s_date" id="s_date" value="${s_date}" placeholder="開始時間"
                                   class="form-control"/>
                            <input type="date" name="e_date" id="e_date" value="${e_date}" placeholder="終了時間"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="col-md-3 pos">
                        <br>
                        <input type="submit" id="query" class="btn btn-primary" value="クエリー"/>
                        <input type="button" id="export" class="btn btn-primary" value="エクスポート" onclick="exp()"/>
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
                                <th>順番</th>
                                <th>お名前</th>
                                <th>日付</th>
                                <th>何曜日</th>
                                <th>休日</th>
                                <th>出勤時間</th>
                                <th>退勤時間</th>
                                <th>休憩</th>
                                <th>設計</th>
                                <th>コード</th>
                                <th>テスト</th>
                                <th>会議</th>
                                <th>勉強</th>
                                <th>特別な場合</th>
                                <th>実働時間(h)</th>
                                <th>備考</th>
                                <%--<th>修正</th>--%>
                            </tr>
                            <c:forEach var="record" items="${requestScope.rlist}" varStatus="xh">
                                <tr>
                                    <td>${xh.index + 1 }</td>
                                    <td>${record.name }</td>
                                    <td>${record.date }</td>
                                    <td>${record.weekday }</td>
                                    <td>${record.isRest }</td>
                                    <td>${record.startime }</td>
                                    <td>${record.endtime }</td>
                                    <td>${record.rest }</td>
                                    <td>${record.design }</td>
                                    <td>${record.code }</td>
                                    <td>${record.test }</td>
                                    <td>${record.meeting }</td>
                                    <td>${record.study }</td>
                                    <td>${record.situation}</td>
                                    <td>${record.worktime}</td>
                                    <td>${record.remarks}</td>
                                        <%--<td><c:if test="${record.inMonth == '1'}"><a class="btn btn-default" href="/employee/EmployeeHandler/modityRec/${record.id}">修正 </a></c:if></td>--%>
                                </tr>

                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 显示分页信息 -->
    <div class="row">
        <!--分页文字信息  -->
        <div class="col-md-6">現在 ${pageInfo.pageNum }ページ,总${pageInfo.pages }
            ページ,合計 ${pageInfo.total } 個の記録
        </div>
    </div>
    <!-- 分页条信息 -->
    <div class="col-md-6">
        <nav aria-label="Page navigation">
            <ul class="pagination bg-primary">
                <li>
                    <a href="/employee/EmployeeHandler/queryRec/${requestScope.name}/1?s_date=${requestScope.s_date}&e_date=${requestScope.e_date}">トップページ</a>
                </li>
                <c:if test="${pageInfo.hasPreviousPage }">
                    <li>
                        <a href="/employee/EmployeeHandler/queryRec/${requestScope.name}/${pageInfo.pageNum-1}?s_date=${requestScope.s_date}&e_date=${requestScope.e_date}"
                           aria-label="Previous"> <span aria-hidden="true">«</span>
                        </a></li>
                </c:if>

                <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                    <c:if test="${page_Num == pageInfo.pageNum }">
                        <li class="active"><a href="#">${page_Num }</a></li>
                    </c:if>
                    <c:if test="${page_Num != pageInfo.pageNum }">
                        <li>
                            <a href="/employee/EmployeeHandler/queryRec/${requestScope.name}/${page_Num }?s_date=${requestScope.s_date}&e_date=${requestScope.e_date}">${page_Num }</a>
                        </li>
                    </c:if>

                </c:forEach>
                <c:if test="${pageInfo.hasNextPage }">
                    <li>
                        <a href="/employee/EmployeeHandler/queryRec/${requestScope.name}/${pageInfo.pageNum+1 }?s_date=${requestScope.s_date}&e_date=${requestScope.e_date}"
                           aria-label="Next"> <span aria-hidden="true">»</span>
                        </a></li>
                </c:if>
                <li>
                    <a href="/employee/EmployeeHandler/queryRec/${requestScope.name}/${pageInfo.pages}?s_date=${requestScope.s_date}&e_date=${requestScope.e_date}">最後のページ</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

</div>
<script>
    $("select").select2({dropdownCssClass: 'dropdown-inverse'});
</script>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
</body>
</html>