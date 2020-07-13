<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="/employee/utils/image/favicon.ico"
          type="/employee/utils/image/x-icon"/>
    <meta charset="UTF-8">
    <title>勤怠管理システム</title>

    <script type="text/javascript" src="/employee/utils/js/jquery-3.3.1.min.js"></script>

    <!-- Loading Bootstrap -->
    <link href="/employee/utils/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI Pro -->
    <link href="/employee/utils/css/flat-ui.css" rel="stylesheet">

    <!-- Loading Flat UI Pro -->
    <link href="/employee/utils/css/animate.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
          rel='stylesheet' type='text/css'>
    <script type='text/javascript' src='/employee/utils/js/particles.js'></script>
    <!--<script type='text/javascript' src='scripts/jquery.particleground.js'></script>-->
    <script src="/employee/utils/js/bootstrap.min.js"></script>
    <style>
        .login-plane {
            padding: 2rem;
            margin-top: 3rem;
        }

        .center-block {
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .center-carousel {
            width: 33%;
            margin-left: auto;
            margin-right: auto;
        }

        .login-btn {
            width: 100%;
            margin-top: 1rem;
        }

        .container {
            padding-top: 4rem;
        }

        body {
            /*background: url(image/bj.jpg)*/

        }
    </style>

    <link href="/employee/utils/css/animate.css" rel="stylesheet">
</head>
<body style="display: flex;">


<div id="particles-js">
    <canvas class="particles-js-canvas-el" width="1322" height="774"
            style="width: 100%; height: 100%;">
    </canvas>
</div>
<div class="container" id="particles"
     style="position: relative; top: -4rem; margin: auto;">
    <div class="row">
        <div class="col-md-12">
            <h4 class="text-center animated bounceInDown">勤怠管理システム</h4>

        </div>
        <div class="col-md-12">
            <div class="row login-plane animated fadeIn">
                <div class="col-md-4 center-block">
                    <form method="post" name="login" id="login" action=""
                          onsubmit="return submitHandler()">
                        <strong>アカウント情報</strong>
                        <div class="form-group has-feedback">
                            <input name="" type="text" id="userid" value=""
                                   placeholder="userName" class="form-control" m="userName"
                                   onkeyup="value=value.replace(/[\u4e00-\u9fa5]|(^\s+)|(\s+$)/ig,'')"
                                   maxlength="12"/> <span class="form-control-feedback fui-user"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input name="" type="password" value="" id="password"
                                   placeholder="password" class="form-control" m="password"
                                   onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');"
                                   maxlength="16"/> <span class="form-control-feedback fui-lock"></span>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <strong>キャラクター情報</strong>
                            </div>
                            <div class="col-md-4" style="left: 20px;">
                                <label class="radio" for="radio1"> <input type="radio"
                                                                          name="optionsRadios1" value="employee"
                                                                          checked="checked"
                                                                          id="radio1" data-toggle="radio"
                                                                          class="custom-radio">
                                    <span class="icons"> <span class="icon-unchecked"></span>
											<span class="icon-checked"></span>
									</span> <font style="vertical-align: inherit;"> <font
                                            style="vertical-align: inherit;"> 従業員 </font>
                                    </font>
                                </label>
                            </div>
                            <div class="col-md-4" style="left: 110px;">
                                <label class="radio" for="radio2"> <input type="radio"
                                                                          name="optionsRadios1" value="admin"
                                                                          id="radio2"
                                                                          data-toggle="radio" class="custom-radio">
                                    <span
                                            class="icons"> <span class="icon-unchecked"></span> <span
                                            class="icon-checked"></span>
									</span> <font style="vertical-align: inherit;"> <font
                                            style="vertical-align: inherit;"> 管理者 </font>
                                    </font>
                                </label>
                            </div>
                        </div>

                        <input class="btn btn-primary btn-wide login-btn" value="LOGIN"
                               type="submit"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var useridEle = document.getElementById("userid");
    var passwordEle = document.getElementById("password");
    var roleRadio = document.getElementsByName("optionsRadios1");

    /*
     * 点击登录执行的逻辑
     */
    function submitHandler() {

        if (!vali()) {
            return false;
        }

        var role = "employee";

        for (var i = 0; i < roleRadio.length; i++) {
            if (roleRadio[i].checked) {
                role = roleRadio[i].value;
            }
        }

        var action = null;
        if (role == "employee") {
            action = "/employee/LoginHandler/employeelogin";
            useridEle.name = "userName";
            passwordEle.name = "password";
        } else {
            action = "/employee/LoginHandler/adminlogin";
            useridEle.name = "aname";
            passwordEle.name = "apassword";
        }
        document.getElementById("login").action = action;

        return true;
    }

    function vali() {
        var valiObjs = createInputMsgObj(['userid', 'password'])

        for (var i = 0; i < valiObjs.length; i++) {
            console.log(valiObjs[i].el.val())
            if (valiObjs[i].el.val() == null || valiObjs[i].el.val() == '') {
                valiObjs[i].el.css({
                    "border-color": "red"
                })
                console.log(valiObjs[i].el)
                var alertEl = $("<div style=\"position:fixed;top:1rem;right:1rem;\" class=\"alert alert-warning\">"
                    + valiObjs[i].msg + "記入していません！" + "</h5></div>")
                $("body").append(alertEl);
                setTimeout(function () {
                    alertEl.remove();
                }, 1000)
                return false;
            }
        }
        return true;
    }

    function createInputMsgObj(ids) {
        var objs = [];

        for (var i = 0; i < ids.length; i++) {

            var obj = {
                el: $("#" + ids[i]),
                msg: $("#" + ids[i]).attr('m')
            }
            objs.push(obj);
        }

        return objs;
    }
</script>
<script src="/employee/utils/js/bganimation.js"></script>
</body>
</html>