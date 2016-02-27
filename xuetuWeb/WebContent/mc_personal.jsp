<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="./Style/skin.css" />
</head>
<script src="jquery-1.11.1.js"></script>
<script>
	/* function Submit(){
	 var insertForm = document.getElementsByName("insertForm")[0];
	 insertForm.action ="/studentweb/insert";
	 insertForm.submit();
	 } */
	$(document).ready(function(e) {
		$(":button[name=submit]").click(function(e) {
			alert("2561");
			var points = $(":input[name=cou_redeem_points]").val();
			var num = $(":input[name=cou_num]").val();
			var validity = $(":input[name=cou_Validity]").val();
			var info = $(":input[name=cou_info]").val();
			if (points == "" || num == "" || validity == "" || info == "") {
				alert("请填写必填项！");
			} else {

				alert("增加成功");
			}
		})
	});
</script>
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
						<td height="31"><div class="title">个人信息</div></td>
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
									<td valign="bottom"><h3 style="letter-spacing: 1px;">&nbsp;</h3></td>
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
										<table width="100%" class="cont">
											<tr>
												<td width="2%">&nbsp;</td>
												<td width="10%">店名：</td>
												<td width="20%"><br />${storename.stoName }</td>
												<td rowspan="5" width="10%" align="center"><img
													src="Images/shuxian.jpg" /></td>
												<td width="10%">店铺图片(图片请到个人信息修改页面修改和添加)</td>



											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>电话：</td>
												<td>${storename.stoTel }</td>

												<td rowspan="4" width="60%"><img
													src="<%=request.getContextPath() %>/${storename.stoImg}"
													width="200" height="300" /></td>


											</tr>
											<tr>
												<td width="2%">&nbsp;</td>
												<td>店铺地址：</td>
												<td width="20%">${storename.stoAddress }</td>


											</tr>
											<tr>
												<td width="2%">&nbsp;</td>
												<td>店铺地址：</td>
												<td width="20%">${storename.stoAddress }</td>


											</tr>
											<tr>
												<td width="2%">&nbsp;</td>
												<td>店铺简介：</td>
												<td width="20%">${storename.stoIntroduction}</td>


											</tr>

											<tr>
											
												<td><a href="main.html"><img src="Images/back.png"></td>

											</tr>
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