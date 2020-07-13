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
        function values(pid) {
            $('input[name="employeelist"]').prop("checked", false);
            $('input[name="employeelist"]').prop("disabled", false);
            $('input[name="employeelist"]').removeAttr("title");
            $('#pid').val(pid);
            $.ajax({
                type: "post",
                url: "/employee/ProgramHandler/getProPerson?pid=" + pid,
                dataType: "json",
                success: function (result) {
                    if (result.data.outList.length != 0) {
                        for (var i in result.data.outList) {
                            var eid = result.data.outList[i].eid.toString();
                            $("#" + eid).prop("disabled", true);
                            $("#" + eid).attr("title", "他の任務があります!");
                        }
                    }
                    if (result.data.inList.length == 0) {
                        $('input[name="employeelist"]').prop("checked", false);
                    } else {
                        for (var i in result.data.inList) {
                            var eid = result.data.inList[i].eid.toString();
                            $("#" + eid).prop("checked", true);
                        }
                    }
                }
            })
        }

        function manage() {
            var pid = $('#pid').val();
            var arr = $("#form1").serialize();
            $.ajax({
                type: "post",
                url: "/employee/ProgramHandler/personManage?pid=" + pid,
                data: arr,
                dataType: "json",
                success: function (result) {
                    alert(result.msg);
                    window.location.reload();
                }
            })
        }

        function del(pid) {
            if (window.confirm("削除を確認しますか？")) {
                $.ajax({
                    type: "post",
                    url: "/employee/ProgramHandler/deletePro?pid=" + pid,
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

<%--子窗口--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                </button>
                <h4 class="modal-title" style="margin-right: 150px;">
                    人員管理
                </h4>
            </div>
            <form name="checkPerson" id="form1" action="##" onsubmit="false" method="post">
                <div class="modal-body">
                    <div style="height: 400px">
                        <input id="pid" hidden/>
                        <span>全員:</span><br>
                        <c:forEach var="employees" items="${requestScope.employees}" varStatus="xh">
                            <label><input type="checkbox" name="employeelist" id="${employees.id}"
                                          value="${employees.id}" />&nbsp;&nbsp;${employees.name}&nbsp;&nbsp;</label>
                            <c:if test="${(xh.index+1)%5==0}"><br></c:if>
                        </c:forEach>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">キャンセル
                    </button>
                    <button type="button" class="btn btn-primary" onclick="manage()">
                        確認
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774" style="width: 100%; height: 100%;"></canvas>
</div>
<jsp:include page="adminLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5>プロジェクト管理</h5>

            <form name="programquery" action="/employee/ProgramHandler/query/1" method="get">
                <div class="row">
                    <div class="col-md-4" style="max-width: 25%">
                        <div class="form-group has-feedback">
                            <span>プロジェクト名:</span>
                            <input type="text" name="pname" placeholder="入力値" class="form-control" style="width: 75%"
                                   value="${pname}">
                        </div>
                    </div>
                    <div class="col-md-4" style="max-width: 25%">
                        <div class="form-group has-feedback">
                            <span>プロジェクト開始日:</span>
                            <input type="date" name="startDate" placeholder="入力値" class="form-control"
                                   style="width: 75%" value="${startDate}"/>
                        </div>
                    </div>
                    <div class="col-md-4" style="max-width: 25%">
                        <div class="form-group has-feedback">
                            <span>プロジェクト終了日:</span>
                            <input type="date" name="endDate" placeholder="入力値" class="form-control" style="width: 75%"
                                   value="${endDate}"/>
                        </div>
                    </div>
                    <div class="col-md-3 pos" style="max-width: 10%">
                        <br>
                        <input type="submit" class="btn btn-primary" value="クエリー"/>
                    </div>
                    <div class="col-md-4 " style="max-width: 10%">
                        <br>
                        <input type="button" class="btn btn-primary" value="追加"
                               onclick="window.location.href='/employee/ProgramHandler/addpro'"/>
                    </div>
                </div>
            </form>

        </div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <div class="margin:0 15px" style="overflow-x: scroll;">
                        <table style="overflow:scroll;text-align: center"
                               class="table table-striped table-hover animated fadeIn">
                            <tr>
                                <th>順番</th>
                                <th>プロジェクト名</th>
                                <th>開始日</th>
                                <th>終了日</th>
                                <th>勤務先住所</th>
                                <th>出勤時間</th>
                                <th>退勤時間</th>
                                <th>休憩</th>
                                <th>人員</th>
                                <th>修正/削除</th>
                            </tr>
                            <c:forEach var="p" items="${requestScope.plist}" varStatus="xh">
                                <tr>
                                    <td>${xh.index + 1 }</td>
                                    <td>${p.name }</td>
                                    <td>${p.startDate }</td>
                                    <td>${p.endDate }</td>
                                    <td>${p.adress }</td>
                                    <td>${p.startime }</td>
                                    <td>${p.endtime }</td>
                                    <td>${p.rest }</td>
                                    <td>
                                        <button class="btn btn-default" data-toggle="modal" data-target="#myModal"
                                                onclick="values(${p.id})">
                                            管理
                                        </button>
                                    </td>
                                    <td><a class="btn btn-default"
                                           href="/employee/ProgramHandler/modityPro/${p.id}">
                                        修正 </a>
                                        <button class="deleteCss btn btn-default" id="delete"
                                                onclick="del(${p.id})">
                                            削除
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
                    <a href="/employee/ProgramHandler/query/1?pname=${requestScope.pname}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}">トップページ</a>
                </li>
                <c:if test="${pageInfo.hasPreviousPage }">
                    <li>
                        <a href="/employee/ProgramHandler/query/${pageInfo.pageNum-1}?pname=${requestScope.pname}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}"
                           aria-label="Previous"> <span aria-hidden="true">«</span>
                        </a></li>
                </c:if>

                <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                    <c:if test="${page_Num == pageInfo.pageNum }">
                        <li class="active"><a href="#">${page_Num }</a></li>
                    </c:if>
                    <c:if test="${page_Num != pageInfo.pageNum }">
                        <li>
                            <a href="/employee/ProgramHandler/query/${page_Num }?pname=${requestScope.pname}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}">${page_Num }</a>
                        </li>
                    </c:if>

                </c:forEach>
                <c:if test="${pageInfo.hasNextPage }">
                    <li>
                        <a href="/employee/ProgramHandler/query/${pageInfo.pageNum+1 }?pname=${requestScope.pname}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}"
                           aria-label="Next"> <span aria-hidden="true">»</span>
                        </a></li>
                </c:if>
                <li>
                    <a href="/employee/ProgramHandler/query/${pageInfo.pages}?pname=${requestScope.pname}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}">最後のページ</a>
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