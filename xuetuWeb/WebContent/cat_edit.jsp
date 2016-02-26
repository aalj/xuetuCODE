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


	// 	执行一些方法，然后判断整个页面执行的是否完整
	function mycheck() {

		if (isNull(form1.cou_name.value)) {
			alert("优惠券名字不能为空！");
			return false;
		}

		if (isNull(form1.cou_price.value)) {

			alert("优惠券折扣不能为空！");
			return false;
		}
		if (isNull(form1.cou_redeem_points.value)) {
			alert("优惠券积分价格不能为空");
			return false;
		}

		if (isNull(form1.cou_num.value)) {
			if (form1.cou_num.value < 0) {
				alert("优惠券数量填写错误！");
				return false;

			} else {
				alert("优惠券布不能为空！");

				return false;
			}
		}

		if (isNull(form1.cou_info.value)) {
			alert("优惠券信息不能为空！");
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
						<td height="31"><div class="title">添加栏目</div></td>
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
										<form action="/xuetuWeb/ChangeCouponManagerServlet"
											name="form1" method="post" onSubmit="return mycheck()">
											<table width="100%" class="cont">
												<tr>

													<td>&nbsp;<input hidden="hidden" name="flags"
														value="2" /> <input hidden="hidden" name="couponID"
														value="${editCoupon.couID }" /> <input hidden="hidden"
														name="storeId" value="${editCoupon.storeName.stoID }" /></td>
													<td>优惠券名称：</td>
													<td><input class="text" type="text" name="cou_name"
														value="${editCoupon.couName }" /></td>
													<td></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>优惠券折扣：</td>
													<td><input
														onkeyup="this.value=this.value.replace(/\D/g,'')"
														onafterpaste="this.value=this.value.replace(/\D/g,'')"
														class="text" type="text" name="cou_price"
														value="${editCoupon.couPrice }" /></td>
													<td></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>兑换积分价格：</td>
													<td><input class="text" type="text"
														name="cou_redeem_points"
														onkeyup="this.value=this.value.replace(/\D/g,'')"
														onafterpaste="this.value=this.value.replace(/\D/g,'')"
														value="${editCoupon.coouRedeemPoints }" /></td>
													<td></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>优惠券数量：</td>
													<td width="20%"><input class="text" type="text"
														name="cou_num" value="${editCoupon.conNum }" /></td>
													<td>举例:55</td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>优惠券到期时间：</td>
													<td width="20%"><input class="text" type="text"
														name="cou_Validity"
														onblur="if(!this.value.match(/^([1-2]{1})([0-9]{3})-(0?[1-9]|10|11|12)-(0?[1-9]|[1-2][0-9]|30|31)$/g)) alert('日期输入不正确')"
														value="${editCoupon.conValidity }" /></td>
													<td>格式:2000-2-15</td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>优惠券信息：</td>
													<td><textarea name="cou_info">${editCoupon.couInfo }</textarea></td>

													<td>&nbsp;50字以内</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td colspan="3"><input class="btn" type="submit"
														value="提交" name="submit" /> &nbsp;&nbsp;&nbsp; <a
														href="/xuetuWeb/CouponListServlet"><img
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