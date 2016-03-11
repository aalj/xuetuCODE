<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="./Style/skin.css" />

<script src="jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript">
	function imgsub() {
		var imgForm = document.getElementsByName("imgForm")[0];
		imgForm.action = "/xuetuWeb/ImgServlet";
		imgForm.submit();
	}

	function Submit() {
		alert("ok");
		var registerForm = document.getElementsByName("registerForm")[0];
		registerForm.action = "/xuetuWeb/RegisterServlet";
		registerForm.submit();
	}
	/* function Confirm(){
		var insertForm = document.getElementsByName("insertForm")[0];
		insertForm.action ="/studentweb/insert";
		insertForm.submit();
	} */
	/* <input class="btn" type="button" value="提交" name="sub"/> */
	//验证用户名不唯一
	$(document).ready(function(e) {
		$(":button[name=val_sto_name]").click(function(e) {
			var valForm = document.getElementsByName("valForm")[0];
			valForm.action = "/xuetuWeb/ValiStoName";
			alert("ok");
			valForm.submit();
		});
	});


	///点击验证码刷新图片
	function changeImg(obj) {
		obj.src = "/xuetuWeb/ValiImage?time=" + new Date().getTime();
	}

	//参数obj为input file对象
	var realPath = getPath(document.getElementById("sto_img"));
	/*  file.select();
	 var realPath = document.selection.createRange().text; */
	function showImage() {
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

	//获取上传图片的真实路径
	function getPath(obj) {
		if (obj) {
			if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
				alert(document.selection.createRange().text);
				obj.select();
				return document.selection.createRange().text;
			} else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
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
	

	//执行一些方法，然后判断整个页面执行的是否完整
	function mycheck() {
		if (isNull(registerForm.sto_user_name.value)) {
			alert("店家名字不能为空！");
			return false;
		}

		if (document.registerForm.sto_pwd.value != document.registerForm.sto_pwdConfirm.value) {
			if (isNull(registerForm.sto_pwd.value)) {
				alert("密码不能为空！");
				return false;
			} else {
				alert("您两次输入的新密码不一致！请重新输入！");
				return false;

			}
		}
		if (isNull(registerForm.sto_tel.value)) {

			alert("电话号码不能为空！");
			return false;
		}
		if (!verifiTel(registerForm.sto_tel.value)) {
			alert("输入的不是手机号码");
			return false;
		}

		if (isNull(registerForm.sto_address.value)) {
			alert("店家店铺地址不能为空！");
			return false;
		}

		if (isNull(registerForm.sto_introduction.value)) {
			alert("店家介绍不能为空！");
			return false;
		}

		if (isNull(registerForm.valiimage.value)) {
			alert("验证码不能为空！");
			return false;
		}

	}
	//验证输入的是否是手机号
	function verifiTel(val) {
		var pattern = /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;

		if (pattern.test(val)) {
			return true;
		} else {
			return false;
		}
	}
	//验证驶入狂是否为空
	function isNull(str) {
		if (str == "")
			return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
	
	
</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<!-- 头部开始 -->
		<tr>
			<td width="17" valign="top" background="./Images/mail_left_bg.gif">
				<img src="./Images/left_top_right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="./Images/content_bg.gif">
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" background="././Images/content_bg.gif">
					<tr>
						<td height="31">
							<div class="title">添加栏目</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="./Images/mail_right_bg.gif"><img
				src="./Images/nav_right_bg.gif" width="16" height="29" /></td>
		</tr>
		<!-- 中间部分开始 -->
		<tr>
			<!--第一行左边框-->
			<td valign="middle" background="./Images/mail_left_bg.gif">&nbsp;</td>
			<!--第一行中间内容-->
			<td valign="top" bgcolor="#F7F8F9">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<!-- 空白行-->
					<tr>
						<td colspan="2" valign="top">&nbsp;</td>
						<td>&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4">
							<table>
								<tr>
									<td width="100" align="center"><img
										src="./Images/mime.gif" /></td>
									<td valign="bottom">
										<h3 style="letter-spacing: 1px;">&nbsp;</h3>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<!-- 一条线 -->
					<tr>
						<td height="40" colspan="4">
							<table width="100%" height="1" border="0" cellpadding="0"
								cellspacing="0" bgcolor="#CCCCCC">
								<tr>
									<td></td>
								</tr>
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
										<form name="registerForm" action="/xuetuWeb/RegisterServlet"
											method="post" onSubmit="return mycheck()">
											<table width="100%" class="cont">
												<tr>
													<td>&nbsp;</td>
													<td>用户名：</td>
													<!-- 															<form name="valForm" method="post"> -->

													<td><input
														onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
														class="text" type="text" name="sto_user_name" /></td>
													<td><input type="button" value="验证用户名是否唯一"
														name="val_sto_name" /> <c:if test="${flag==0}">用户名已存在！</c:if>
														<c:if test="${flag==1}">用户名可用！</c:if></td>
													<!-- 															</form> -->
													<td>&nbsp;</td>
												</tr>

												<tr>
													<td width="2%">&nbsp;</td>
													<td>密码：</td>
													<td width="20%"><input class="text" type="password"
														name="sto_pwd" value="" /></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>确认密码：</td>
													<td width="20%"><input class="text" type="password"
														name="sto_pwdConfirm" onKeyUp="validate()" /></td>
													<td>
														<%-- 																<c:if test=""><span id="pwdConfirmText" style="color:red">两次输入不一致！</span></c:if> --%>
													</td>
													<td width="2%">&nbsp;</td>
												</tr>

												<tr>
													<td width="2%">&nbsp;</td>
													<td>联系电话：</td>
													<td width="20%"><input
														onkeyup="value=value.replace(/[^\d]/g,'')" class="text"
														type="text" name="sto_tel" value="" /></td>
													<td>例：1369521243</td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>店铺名：</td>
													<td width="20%"><input class="text" type="text"
														name="sto_name" value="" /></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>店铺地址地址：</td>
													<td width="20%">
													<textarea name="sto_address"></textarea>
													
													</td>
													<td>如XX省XX市XX路</td>
													<td width="2%">&nbsp;</td>
												</tr>



												<tr>
													<td width="2%">&nbsp;</td>
													<td>店家介绍</td>
													<td width="20%"><textarea name="sto_introduction"></textarea>
													</td>
													<td>关于店家或优惠券的说明</td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>验证码：</td>
													<td><input type="text" id="val" name="valiimage"
														value="" style="width: 80px;" onBlur="okok()" /><img
														src="/xuetuWeb/ValiImage" onclick="changeImg(this)"
														style="cursor: pointer;" /></td>
													<td></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td colspan="3"><input class="btn" type="submit"
														value="提交" name="sub" />
														&nbsp;&nbsp;
														<a
														href="/xuetuWeb/index.jsp"><img
															src="Images/back.png"></a></td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</form>
									</td>
								</tr>
							</table>
						</td>
						<td width="2%">&nbsp;</td>
					</tr>
					<!-- 添加栏目结束 -->
					<tr>
						<td height="40" colspan="4">
							<table width="100%" height="1" border="0" cellpadding="0"
								cellspacing="0" bgcolor="#CCCCCC">
								<tr>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="2%">&nbsp;</td>
						<td width="51%" class="left_txt"><img
							src="./Images/icon_phone.gif" width="17" height="14" />
							客服电话：1111-1111-1111</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
			<td background="./Images/mail_right_bg.gif">&nbsp;</td>
		</tr>
		<!-- 底部部分 -->
		<tr>
			<td valign="bottom" background="./Images/mail_left_bg.gif"><img
				src="./Images/buttom_left.gif" width="17" height="17" /></td>
			<td background="./Images/buttom_bgs.gif"><img
				src="./Images/buttom_bgs.gif" width="17" height="17"></td>
			<td valign="bottom" background="./Images/mail_right_bg.gif"><img
				src="./Images/buttom_right.gif" width="16" height="17" /></td>
		</tr>
	</table>
</body>

</html>