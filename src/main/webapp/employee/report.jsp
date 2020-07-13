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
    <link href="/employee/utils/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="/employee/utils/js/fileinput.js" type="text/javascript"></script>
    <script src="/employee/utils/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/employee/utils/js/locales/zh.js" type="text/javascript"></script>
    <!-- Loading Flat UI JS -->
    <script type="text/javascript" src="/employee/utils/js/flat-ui.min.js"></script>
    <script type='text/javascript' src='/employee/utils/js/particles.js'></script>
    <link href="/employee/utils/css/animate.css" rel="stylesheet">
    <script type="text/javascript">
        /*$(function () {
            $("#file_form").submit(
                function () {
                    //首先验证文件格式
                    var fileName = $('#file_input').val();
                    if (fileName === '') {
                        alert('ファイルを選択してください');
                        return false;
                    }
                    var fileType = (fileName.substring(fileName
                        .lastIndexOf(".") + 1, fileName.length))
                        .toLowerCase();
                    if (fileType !== 'xls' && fileType !== 'xlsx') {
                        alert('ファイルのフォーマットは正しくありませんて、excelファイル！');
                        return false;
                    }
                    return true;
                });

        });*/

        $(function () {
            $("#upload").click(
                function () {
                var fileName = $('#file_input').val();
                if (fileName === '') {
                    alert('ファイルを選択してください');
                    return false;
                }
                var fileType = (fileName.substring(fileName
                    .lastIndexOf(".") + 1, fileName.length))
                    .toLowerCase();
                if (fileType !== 'xls' && fileType !== 'xlsx') {
                    alert('ファイルのフォーマットは正しくありませんて、excelファイル！');
                    return false;
                }
                var fileObj = document.getElementById("file_input").files[0]; // js 获取文件对象
                var formFile = new FormData();
                formFile.append("file", fileObj); //加入文件对象   file为传递给后台的参数，可以模仿form中input的name值。
                var data = formFile;
                var serc = $("#serc").val();
                $.ajax({
                    url: "/employee/ReportHandler/upload/${sessionScope.id}?serc="+serc,
                    data: data,
                    type: "post",
                    dataType: "json",
                    cache: false,//上传文件无需缓存
                    processData: false,//用于对data参数进行序列化处理 这里必须false
                    contentType: false, //必须
                    success: function (result) {
                        alert(result.msg);
                        return result.success;
                    },
                })
            })
        })

        function query() {
            var serc = $("#serc").val();
            window.location.href = '/employee/ReportHandler/queryRep/${requestScope.name}/1?serc=' + serc;
        }

        function del(id) {
            if (window.confirm("削除を確認しますか？")) {
                $.ajax({
                    type: "post",
                    url: "/employee/ReportHandler/deleteRep?id=" + id,
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
<jsp:include page="employeeLeft.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5>勤務記録の管理</h5>
            <div class="row" style="margin-bottom: -75px;">
                <div class="col-md-4" style="max-width: 40%">
                    <strong>クエリ条件<br></strong>
                    <select name="serc" id="serc" class="form-control select select-primary"
                            style="margin-bottom: 1px;" onchange="query()">
                        <option value="thisMonth">本月</option>
                        <option value="lastMonth">先月</option>
                    </select>
                </div>
            </div>
            <div class="row" style="margin-bottom: -75px;margin-left: 350px;">
                <div class="col-md-3 pos" style="margin-left: -140px;">
                    <input type="file" name="file" id="file_input"/>
                    <input id="upload" type="button" class="btn btn-primary" value="アップロード" />
                    <input type="button" id="export" class="btn btn-primary"
                           style="margin-left: 125px;margin-top: -76px;" value="テンプレートダウンロード"
                           title="このテンプレートデータはサインインのデータです。"
                           onclick="window.location.href = '/employee/ReportHandler/export/${requestScope.name}?serc=${requestScope.serc}'"/>
                    <input type="button" id="print" class="btn btn-primary"
                           style="margin-top: -137px;margin-left: 340px;" value="プリント"
                           onclick=" window.open('/employee/ReportHandler/print/${requestScope.name}?serc=${requestScope.serc}', '_blank');"/>
                    <input id="add" type="button" class="btn btn-primary" value="追加"
                           style="margin-left: 435px;margin-top: -199px;"
                           onclick="window.location.href = '/employee/ReportHandler/add?id='+${sessionScope.id}"/>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <div class="margin:0 15px" style="overflow-x: scroll;">
                        <table style="overflow:scroll;text-align: center"
                               class="table table-striped table-hover animated fadeIn">
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
                                <th>修正/削除</th>
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
                                    <td><a class="btn btn-default"
                                           href="/employee/ReportHandler/modityRep/${record.id}">修正 </a>
                                        <button class="btn btn-default" onclick="del(${record.id})">削除</button>
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
                    <a href="/employee/ReportHandler/queryRep/${requestScope.name}/1?serc=${requestScope.serc}">トップページ</a>
                </li>
                <c:if test="${pageInfo.hasPreviousPage }">
                    <li>
                        <a href="/employee/ReportHandler/queryRep/${requestScope.name}/${pageInfo.pageNum-1}?serc=${requestScope.serc}"
                           aria-label="Previous"> <span aria-hidden="true">«</span>
                        </a></li>
                </c:if>

                <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                    <c:if test="${page_Num == pageInfo.pageNum }">
                        <li class="active"><a href="#">${page_Num }</a></li>
                    </c:if>
                    <c:if test="${page_Num != pageInfo.pageNum }">
                        <li>
                            <a href="/employee/ReportHandler/queryRep/${requestScope.name}/${page_Num }?serc=${requestScope.serc}">${page_Num }</a>
                        </li>
                    </c:if>

                </c:forEach>
                <c:if test="${pageInfo.hasNextPage }">
                    <li>
                        <a href="/employee/ReportHandler/queryRep/${requestScope.name}/${pageInfo.pageNum+1 }?serc=${requestScope.serc}"
                           aria-label="Next"> <span aria-hidden="true">»</span>
                        </a></li>
                </c:if>
                <li>
                    <a href="/employee/ReportHandler/queryRep/${requestScope.name}/${pageInfo.pages}?serc=${requestScope.serc}">最後のページ</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

</div>
<script>
    $("#serc").find("option[value='${requestScope.serc}']").attr("selected", 'selected');
    $("select").select2({dropdownCssClass: 'dropdown-inverse'});
</script>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
</body>
</html>