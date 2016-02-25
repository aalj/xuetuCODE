<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
</head>
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
                                            <form action="" method="">
                                                <table width="100%"  class="cont tr_color">
                                                
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>优惠券名称</th>
                                                        <th>折扣</th>
                                                        <th>剩余数量</th>
                                                        <th>到期时间</th>
                                                        <th>描述</th>
                                                    </tr>
                                                    <c:set var="index" value="0" />
                                                   <c:forEach var='type' items='${listCoupon}'>
                                                   <c:set var="index" value="${index+1}" />
                                                    <tr align="center" class="d">
                                                        <td>${index}</td>
                                                        
														
                                                        <td><a href="#"><c:out value="${type.couName }"></c:out>   </a><p hidden="hidden"><c:out value="${type.couID }"></c:out></p></td>
                                                        <td><c:out value="${type.couPrice }"></c:out></td>
                                                        <td><c:out value="${type.conNum }"></c:out></td>
                                                        <td><c:out value="${type.conValidity }"></c:out></td>
                                                        <td><a href="/xuetuWeb/ShowCouponServlet?strID=${type.couID}">查看
                                                        </a>&nbsp;&nbsp;<a href="/xuetuWeb/EditCouponManagerServlet?strID=${type.couID}&flags=1">编辑
                                                        </a>&nbsp;&nbsp;<a href="/xuetuWeb/DeleteCouponServlet?strID=${type.couID}">删除</a></td>
                                                    </tr>
												</c:forEach>
                                                </table>
                                                <table width='100%' class="tex004">
			<tr>
				<td align="center"><a href='/xuetuWeb/CouponListServlet?coupage=1' onClick='pageQuery(1)'><span>首页</span></a> &nbsp;&nbsp;
					
					<c:choose>
					<c:when test="${page==1 }">
					<a >上一页</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
					<a href='/xuetuWeb/CouponListServlet?coupage=${page-1}'>上一页</a>&nbsp;&nbsp;
					
					</c:otherwise>
					</c:choose>
					<c:choose>
					<c:when test="${page<pages}">
					<a href='/xuetuWeb/CouponListServlet?coupage=${page+1}'><span>下一页</span></a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
					<a >下一页</a>&nbsp;&nbsp;
					</c:otherwise>
					</c:choose>
					<a href='/xuetuWeb/CouponListServlet?coupage=${pages}' onClick="pageQuery(4)">尾页</a></td>
					
				<td align='center'>共&nbsp;${pages }&nbsp;页&nbsp;&nbsp;第&nbsp;${page}&nbsp;页</td>
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