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
        function del(id) {
            if (window.confirm("削除を確認しますか？")) {
                $.ajax({
                    type: "post",
                    url: "/employee/AdminHandler/delete?id=" + id,
                    dataType: "json",
                    success: function (result) {
                        alert(result.msg);
                        window.location.reload();
                    }
                })
            } else {
                return false;
            }
        }
    </script>
</head>

<body>
<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="adminLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5>従業員管理</h5>

            <form name="employeequery" action="/employee/AdminHandler/query/1" method="get">
                <div class="row">
                    <div class="col-md-4" style="max-width: 40%">
                        <strong>クエリ条件<br></strong>
                        <select name="serc" class="form-control select select-primary select-block mbl">
                            <option value="all">全部</option>
                            <option value="userName">ユーザー名</option>
                        </select>

                    </div>
                    <div class="col-md-4" style="max-width: 40%">


                        <strong>条件</strong>

                        <div class="form-group has-feedback">

                            <input type="text" name="condition" placeholder="入力値" class="form-control"/>

                        </div>

                    </div>
                    <div class="col-md-3 pos" style="max-width: 20%">
                        <br>
                        <input type="submit" class="btn btn-primary" value="クエリー"/>
                        <input type="button" class="btn btn-primary" onclick="window.location.href='/employee/AdminHandler/addemp'" value="追加"/>
                    </div>
                </div>
            </form>

        </div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <div class="margin:0 15px" style="overflow-x: scroll;">
                        <table style="overflow:scroll;text-align: center" class="table table-striped table-hover animated fadeIn" >
                            <tr>
                                <th>順番</th>
                                <th>ユーザー名</th>
                                <th>お名前</th>
                                <th>パスワード</th>
                                <th>性別</th>
                                <th>修正/削除</th>
                            </tr>
                            <c:forEach var="employee" items="${requestScope.elist}" varStatus="xh">
                                <tr>
                                    <td>${xh.index + 1 }</td>
                                    <td>${employee.userName}</td>
                                    <td>${employee.name }</td>
                                    <td>${employee.password}</td>
                                    <td>${employee.sex == 0?"男":"女"}</td>
                                    <td><a class="btn btn-default"
                                           href="/employee/AdminHandler/modityEmp/${employee.userName}">
                                        修正 </a>
                                        <button class="deleteCss btn btn-default" id="delete"
                                                onclick="del(${employee.id})"
                                        > 削除
                                        </button>
                                    </td>
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
                    <a href="/employee/AdminHandler/query/1?serc=${requestScope.serc == null?"all":requestScope.serc}&condition=${requestScope.condition}">トップページ</a>
                </li>
                <c:if test="${pageInfo.hasPreviousPage }">
                    <li>
                        <a href="/employee/AdminHandler/query/${pageInfo.pageNum-1}?serc=${requestScope.serc == null?"all":requestScope.serc}&condition=${requestScope.condition}"
                           aria-label="Previous"> <span aria-hidden="true">«</span>
                        </a></li>
                </c:if>

                <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                    <c:if test="${page_Num == pageInfo.pageNum }">
                        <li class="active"><a href="#">${page_Num }</a></li>
                    </c:if>
                    <c:if test="${page_Num != pageInfo.pageNum }">
                        <li>
                            <a href="/employee/AdminHandler/query/${page_Num }?serc=${requestScope.serc == null?"all":requestScope.serc}&condition=${requestScope.condition}">${page_Num }</a>
                        </li>
                    </c:if>

                </c:forEach>
                <c:if test="${pageInfo.hasNextPage }">
                    <li>
                        <a href="/employee/AdminHandler/query/${pageInfo.pageNum+1 }?serc=${requestScope.serc == null?"all":requestScope.serc}&condition=${requestScope.condition}"
                           aria-label="Next"> <span aria-hidden="true">»</span>
                        </a></li>
                </c:if>
                <li>
                    <a href="/employee/AdminHandler/query/${pageInfo.pages}?serc=${requestScope.serc == null?"all":requestScope.serc}&condition=${requestScope.condition}">最後のページ</a>
                </li>
            </ul>
        </nav>
    </div>

</div>

<script>
    $("select").select2({dropdownCssClass: 'dropdown-inverse'});
</script>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
</body>
</html>