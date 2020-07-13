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
            <h5>従業員情報を変更</h5>
            <c:forEach var="employee" items="${requestScope.employeeList}">
                <form action="../modityempl/${employee.userName}" method="get">
                    <div class="row">
                        <div class="col-md-6">
                            <h6>お名前</h6>
                            <input type="text" name="name" value="${employee.name }" class="form-control"
                                   onkeyup="value=value.replace(/[\d]/g,'') "
                                   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))"
                                   maxlength="4"></input>
                            <h6>性別</h6>
                            <c:choose>
                                <c:when test="${employee.sex == 0 }">
                                    <label class="radio" for="radio1">
                                        <input type="radio" name="sex" value="0" checked id="radio1" data-toggle="radio"
                                               class="custom-radio">
                                        <span class="icons">
			               				<span class="icon-unchecked"></span>
			               				<span class="icon-checked"></span>
			               			</span>
                                        <font style="vertical-align: inherit;">
                                            <font style="vertical-align: inherit;">
                                                男
                                            </font>
                                        </font>
                                    </label>
                                    <label class="radio" for="radio2">
                                        <input type="radio" name="sex" value="1" id="radio2" data-toggle="radio"
                                               class="custom-radio">
                                        <span class="icons">
			               				<span class="icon-unchecked"></span>
			               				<span class="icon-checked"></span>
			               			</span>
                                        <font style="vertical-align: inherit;">
                                            <font style="vertical-align: inherit;">
                                                女
                                            </font>
                                        </font>
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <label class="radio" for="radio1">
                                        <input type="radio" name="sex" value="0" id="radio1" data-toggle="radio"
                                               class="custom-radio">
                                        <span class="icons">
			               				<span class="icon-unchecked"></span>
			               				<span class="icon-checked"></span>
			               			</span>
                                        <font style="vertical-align: inherit;">
                                            <font style="vertical-align: inherit;">
                                                男
                                            </font>
                                        </font>
                                    </label>
                                    <label class="radio" for="radio2">
                                        <input type="radio" name="sex" value="1" checked id="radio2" data-toggle="radio"
                                               class="custom-radio">
                                        <span class="icons">
			               				<span class="icon-unchecked"></span>
			               				<span class="icon-checked"></span>
			               			</span>
                                        <font style="vertical-align: inherit;">
                                            <font style="vertical-align: inherit;">
                                                女
                                            </font>
                                        </font>
                                    </label>

                                </c:otherwise>
                            </c:choose>
                            <h6>パスワード</h6>
                            <input type="password" name="password" value="${employee.password }" class="form-control"
                                   onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');" maxlength="16"></input>
                        </div>
                        <div class="col-md-12">
                            <input type="submit" class="btn btn-primary btn-wide login-btn" style="margin-top:2rem"
                                   value="修正"/>
                        </div>
                    </div>
                </form>
            </c:forEach>

        </div>
    </div>
</div>
<script type="text/javascript" src="/employee/utils/js/flat-ui.js"></script>
<script src="/employee/utils/js/bganimation.js"></script>
<script type="text/javascript">
    $("select").select2({
        dropdownCssClass: 'dropdown-inverse'
    });
</script>
</body>
</html>