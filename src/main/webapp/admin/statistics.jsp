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
        <div class="col-md-12">
            <h5>個人統計</h5>

            <form name="employeequery"
                  action="/employee/AdminHandler/statistics/1?name=${requestScope.name}&s_date=${requestScope.s_date}&e_date=${requestScope.e_date}"
                  method="get">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group has-feedback">
                            <input type="text" name="name" id="name" value="${name}" placeholder="お名前"
                                   class="form-control"/>
                            <input type="date" name="s_date" id="s_date" value="${s_date}" placeholder="開始時間"
                                   class="form-control"/>
                            <input type="date" name="e_date" id="e_date" value="${e_date}" placeholder="終了時間"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="col-md-3 pos">
                        <br>
                        <input type="submit" id="query" class="btn btn-primary" value="クエリー"/>
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
                                <th>実際時間(本月)</th>
                                <th>总計時間(全部)</th>
                            </tr>
                            <c:forEach var="list" items="${requestScope.list}" varStatus="xh">
                                <tr>
                                    <td>${xh.index + 1 }</td>
                                    <td>${list.name}</td>
                                    <td>${list.wtime}</td>
                                    <td>${list.statime }</td>
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
        <!-- 分页条信息 -->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination bg-primary">
                    <li>
                        <a href="/employee/AdminHandler/statistics/1?name=${requestScope.name}&s_date=${requestScope.s_date}&e_date=${requestScope.e_date}">トップページ</a>
                    </li>
                    <c:if test="${pageInfo.hasPreviousPage }">
                        <li>
                            <a href="/employee/AdminHandler/statistics/${pageInfo.pageNum-1}?name=${requestScope.name}&s_date=${requestScope.s_date}&e_date=${requestScope.e_date}"
                               aria-label="Previous"> <span aria-hidden="true">«</span>
                            </a></li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == pageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num }</a></li>
                        </c:if>
                        <c:if test="${page_Num != pageInfo.pageNum }">
                            <li>
                                <a href="/employee/AdminHandler/statistics/${page_Num }?name=${requestScope.name}&s_date=${requestScope.s_date}&e_date=${requestScope.e_date}">${page_Num }</a>
                            </li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage }">
                        <li>
                            <a href="/employee/AdminHandler/statistics/${pageInfo.pageNum+1 }?name=${requestScope.name}&s_date=${requestScope.s_date}&e_date=${requestScope.e_date}"
                               aria-label="Next"> <span aria-hidden="true">»</span>
                            </a></li>
                    </c:if>
                    <li>
                        <a href="/employee/AdminHandler/statistics/${pageInfo.pages}?name=${requestScope.name}&s_date=${requestScope.s_date}&e_date=${requestScope.e_date}">最後のページ</a>
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