<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="./Style/skin.css" />
</head>
<script src="jquery-1.11.1.js" type="text/javascript"></script>
<script>

	function Submit() {
		alert("Submit");
		var insertForm = document.getElementsByName("insertForm")[0];
		insertForm.action = "/xuetuWeb/ChangeStoneInfoServlet";
		insertForm.submit();
	}
	//验证驶入狂是否为空
	function isNull(str) {
		if (str == "")
			return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
	function mycheck() {
		if (isNull(insertForm.sto_name.value)) {
			alert("修改店家名字不能为空！");
			return false;
		}

		
		if (isNull(insertForm.sto_tel.value)) {

			alert("店家电话号码不能为空！");
			return false;
		}

		if (isNull(insertForm.sto_address.value)) {
			alert("店家店铺地址不能为空！");
			return false;
		}

		if (isNull(insertForm.sto_info.value)) {
			alert("店家介绍不能为空！");
			return false;
		}

		Submit();
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
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" background="././Images/content_bg.gif">
					<tr>
						<td height="31">
							<div class="title">修改信息</div>
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
										<form name="insertForm" method="post" enctype="multipart/form-data" onSubmit="return mycheck()">
											<table width="100%" class="cont">
												<tr>
													<td width="2%">&nbsp;</td>
													<td width="10%">店名：</td>
													<td width="20%"><input type="text" name="sto_name"
														value="${storename.stoName }" /> <br /></td>

													<td rowspan="5" width="10%" align="center"><img
														src="Images/shuxian.jpg" /></td>
													<td width="10%">店铺图片</td>



												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>电话：</td>
													<td><input type="text" name="sto_tel"
														value="${storename.stoTel }" /></td>

													<td rowspan="4" width="60%"><img
														src="<%=request.getContextPath() %>/${storename.stoImg}"
														width="200" height="300" />
													<td>
<!-- 														<form name="insertForm1" method="post" id="insertFormId" enctype="multipart/form-data"> -->
															<input type="file" name="sto_img" onchange="showImage()" /><br />
															<input type="button" name="imgSub" value="上传" onclick="imgsub()" />
<!-- 														</form> -->
													</td>
													


												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>店铺地址：</td>
													<td width="20%"><input type="text" name="sto_address"
														value="${storename.stoAddress }" /></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>店铺简介：</td>
													<td width="30%"><textarea style="height: 40"
															name="sto_info">${storename.stoIntroduction}</textarea> <a>500字以内</a></td>
													<td width="2%">&nbsp;</td>
												</tr>

												<tr>
													<td>&nbsp;</td>
													<td colspan="3"><input class="btn" type="button"
														value="提交" name="sub" onclick="Submit()" /> <a
														href="/xuetuWeb/ShowStoneNameServlet"><img
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