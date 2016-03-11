<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
</head>
<script src="jquery-1.11.1.js"></script>
<script>
function imgsub(){
	 var imgForm = document.getElementsByName("imgForm")[0];
	imgForm.action ="/xuetuWeb/ImgServlet";
	imgForm.submit(); 
}
  function Submit(){
	var registerForm = document.getElementsByName("registerForm")[0];
 	registerForm.action ="/xuetuWeb/RegisterServlet";
	registerForm.submit();
}  
/* function Confirm(){
	var insertForm = document.getElementsByName("insertForm")[0];
	insertForm.action ="/studentweb/insert";
	insertForm.submit();
} */
//验证用户名不唯一
$(document).ready(function(e) {
	$(":button[name=val_sto_name]").click(function(e) {
		var valForm = document.getElementsByName("valForm")[0];
		valForm.action="/xuetuWeb/ValiStoName";
		alert("ok");
		valForm.submit();
	});
});
$(document).ready(function(e) {
	$(":button[name=sub]").click(function(e) {
		var sto_user_name = $(":input[name=sto_user_name]").val();
		var sto_tel = $(":input[name=sto_tel]").val();
		var sto_address = $(":input[name=sto_address]").val();
		var sto_name = $(":input[name=sto_name]").val();
		var sto_introduciton = $(":input[name=sto_introduciton]").val();
		var sto_pwd = $(":input[name=sto_pwd]").val();
		var sto_pwdConfirm = $(":input[name=sto_pwdConfirm]").val();
        if(sto_user_name==""||sto_tel==""||sto_address==""||sto_name==""||sto_introduciton==""||sto_pwd==""||sto_pwdConfirm=="")
		{
        	alert("请填写必填项！");
		}
          else if(sto_pwd!=sto_pwdCondirm){
        	var pwdConfirmText = $("span[name=pwdConfirmText]").val();
        	pwdConfirmText.hidden ="false";
        } 
         else  
        	Submit(); 
})
    });
    </script>
    <script type="text/javascript">
	//参数obj为input file对象
	 var realPath = getPath(document.getElementById("sto_img"));
   /*  file.select();
    var realPath = document.selection.createRange().text; */
function showImage()
{
    // 获取文件路径
    var path = getPath(document.getElementById('sto_img'));
    // 显示文件路径
    document.getElementById('imgName').innerHTML = path;
    // 创建 img
    var img = document.createElement('img');
    // 载入图像
    img.src = path;
    // 插入图像到页面中
    document.getElementById('imgPrev').appendChild(img);
}
    //验证两次密码不一致的提示
    function validate() {
              var pw1 = document.getElementById("sto_pwd").value;
              var pw2 = document.getElementById("sto_pwd2").value;
              if(pw1 == pw2) {
                  document.getElementById("pwdConfirmText").innerHTML="<font color='green'>两次密码相同</font>";
                  document.getElementById("sub").disabled = false;
              }
              else {
                  document.getElementById("pwdConfirmText").innerHTML="<font color='red'>两次密码不相同</font>";
                document.getElementById("sub").disabled = true;
              }
          }

    //获取上传图片的真实路径
    function getPath(obj) {
    	
        if (obj) {
            if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
            	alert(document.selection.createRange().text);
                obj.select(); return document.selection.createRange().text;
            }
            else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                if (obj.files) {
                	alert(obj.files.item(0).getAsDataURL());
                    return obj.files.item(0).getAsDataURL();
                }
                alert(obj.value);
                return obj.value;
            }
            alert(obj.value);
            return obj.value;
        }
    } 
</script>
    <body>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <!-- 头部开始 -->
            <tr>
                <td width="17" valign="top" background="./Images/mail_left_bg.gif">
                    <img src="./Images/left_top_right.gif" width="17" height="29" />
                </td>
                <td valign="top" background="./Images/content_bg.gif">
                    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" background="././Images/content_bg.gif">
                        <tr><td height="31"><div class="title">添加栏目</div></td></tr>
                    </table>
                </td>
                <td width="16" valign="top" background="./Images/mail_right_bg.gif"><img src="./Images/nav_right_bg.gif" width="16" height="29" /></td>
            </tr>
            <!-- 中间部分开始 -->
            <tr>
                <!--第一行左边框-->
                <td valign="middle" background="./Images/mail_left_bg.gif">&nbsp;</td>
                <!--第一行中间内容-->
                <td valign="top" bgcolor="#F7F8F9">
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <!-- 空白行-->
                        <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
                        <tr>
                            <td colspan="4">
                                <table>
                                    <tr>
                                        <td width="100" align="center"><img src="./Images/mime.gif" /></td>
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">&nbsp;</h3></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 一条线 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 添加栏目开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form name="registerForm" method="post">
                                                <table width="100%" class="cont">
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>用户名：</td>
                                                        <form name="valForm" method="post">
                                                        
                                                        <td><input class="text" type="text" name="sto_user_name"/></td>
														<td><input type="button" value="验证用户名是否唯一" name="val_sto_name"/><c:if test="${flag==0}">用户名已存在！</c:if><c:if test="${flag==1}">用户名可用！</c:if></td>
                                                        </form>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <form name="imgForm" method="post" enctype="multipart/form-data" >
                                                        <td id="imgName">店铺图片</td>
                                                        <td><input type="file" name="sto_img" onchange="showImage()" /><br/>
                                                        <input type="button" name="imgSub" onclick="imgsub()" value="上传"/>
														</td> 
                                                        <td id="imgPrev" align="center">图片预览位置</td>
                                                        </form>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>密码：</td>
                                                        <td width="20%"><input class="text" type="password" name="sto_pwd" value="" /></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>确认密码：</td>
                                                        <td width="20%"><input class="text" type="password" name="sto_pwd2" onKeyUp="validate()"/></td>
                                                        <td><c:if test=""><span id="pwdConfirmText"  style="color:red">两次输入不一致！</span></c:if></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>联系电话：</td>
                                                        <td width="20%"><input class="text" type="text" name="sto_tel" value="" /></td>
                                                        <td>例：1369521243</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>店家地址：</td>
                                                        <td width="20%"><input class="text" type="text" name="sto_address" value="" /></td>
                                                        <td>如XX省XX市XX路</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>店铺名：</td>
                                                        <td width="20%"><input class="text" type="text" name="sto_name" value="" /></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>店家介绍</td>
                                                        <td width="20%"><textarea name="sto_introduction"></textarea></td>
                                                        <td>关于店家或优惠券的说明</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td colspan="3"><input class="btn" type="button" value="提交" name="sub"/></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                          		  </form>
                                                </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                        <!-- 添加栏目结束 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="51%" class="left_txt"><img src="./Images/icon_phone.gif" width="17" height="14" /> 客服电话：1111-1111-1111</td>
                            <td>&nbsp;</td><td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
                <td background="./Images/mail_right_bg.gif">&nbsp;</td>
            </tr>
            <!-- 底部部分 -->
            <tr>
                <td valign="bottom" background="./Images/mail_left_bg.gif">
                    <img src="./Images/buttom_left.gif" width="17" height="17" />
                </td>
                <td background="./Images/buttom_bgs.gif">
                    <img src="./Images/buttom_bgs.gif" width="17" height="17">
                </td>
                <td valign="bottom" background="./Images/mail_right_bg.gif">
                    <img src="./Images/buttom_right.gif" width="16" height="17" />
                </td>           
            </tr>
        </table>
    </body>
</html>