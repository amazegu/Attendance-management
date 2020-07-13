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
        $(document).ready(
            function () {
                $("#userName").change(
                    function () {
                        $.post("/employee/AjaxHandler/existUserName?userName="
                            + $("#userName").val(), function (data, status) {
                            $("#countsid").html(data);
                        })
                    })
            })
    </script>
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
            <h5>従業員を追加</h5>
            <form action="/employee/AdminHandler/addEmployee" method="post" name="form1">
                <div class="row">
                    <div class="col-md-6">
                        <h6>ユーザー名</h6>
                        <input type="text" name="userName" id="userName" class="form-control"
                               oninput="value=value.replace(/[^a-zA-Z0-9]+$/,'')" maxlength="12"></input>
                        <span id="countsid" style="color: #ff0000;"></span>
                        <h6>お名前</h6>
                        <input type="text" name="name" class="form-control" onkeyup="value=value.replace(/[\d]/g,'') "
                               onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))"
                               maxlength="4"></input>
                        <h6>パスワード</h6>
                        <input type="password" name="password" class="form-control"
                               onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');" maxlength="16"/>
                        <h6>性&nbsp;&nbsp;別</h6>
                        <label class="radio" for="radio1"> <input type="radio"
                                                                  name="sex" value="0" id="radio1" data-toggle="radio"
                                                                  class="custom-radio"> <span class="icons"> <span
                                class="icon-unchecked"></span> <span class="icon-checked"></span>
							</span> <font style="vertical-align: inherit;"> <font
                                style="vertical-align: inherit;"> 男 </font>
                        </font>
                        </label> <label class="radio" for="radio2"> <input type="radio"
                                                                           name="sex" value="1" id="radio2"
                                                                           data-toggle="radio"
                                                                           class="custom-radio"> <span
                            class="icons"> <span
                            class="icon-unchecked"></span> <span class="icon-checked"></span>
							</span> <font style="vertical-align: inherit;"> <font
                            style="vertical-align: inherit;"> 女 </font>
                    </font>
                    </label>
                    </div>
                    <div class="col-md-12">
                        <br> <input type="submit"
                                    class="btn btn-primary btn-wide login-btn" value="添加"/>
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
</script>
</body>
</html>