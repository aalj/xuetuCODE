<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <title>学途店家管理中心</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="店铺宣传一站式后台" />
    <meta name="keywords" content="学途专业后台管理中心" />
    <meta name="Author" content="RainMan" />
    <meta name="CopyRight" content="学途科技" />
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script type="text/javascript">
	function changeImg(obj){
		obj.src = "/xuetuWeb/ValiImage?time="+new Date().getTime();
	}
	
	</script>

</head>
    <body>
        <table width="100%">
            <!-- 顶部部分 -->
            <tr height="41"><td colspan="2" background="./Images/login_top_bg.gif">&nbsp;</td></tr>
            <!-- 主体部分 -->
            <tr style="background:url(./Images/login_bg.jpg) repeat-x;" height="532">
                <!-- 主体左部分 -->
                <td id="left_cont">
                    <table width="100%" height="100%">
                        <tr height="155"><td colspan="2">&nbsp;</td></tr>
                        <tr>
                            <td width="20%" rowspan="2">&nbsp;</td>
                            <td width="60%">
                                <table width="100%"  >
                                    <tr height="70" ><td align="center"><img src="./Images/xuetulogo.png" title="瑞曼科技" alt="瑞曼科技" style="width:100px;height:100px;"/></td></tr>
                                    <tr height="274" >
                                        <td valign="top" align="center">
                                            <ul>
                                                <li>1- 店铺宣传首选方案...</li>
                                                <li>2- 一站通式的整合方式，方便用户使用...</li>
                                                <li>3- 强大的后台系统，管理店铺内容易如反掌...</li>
                                                <li><img src="./Images/icon_demo.gif" />&nbsp;<a href="javascript:void(0)">使用说明</a>&nbsp;&nbsp;<span> <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=609307843&site=qq&menu=yes" onfocus="this.blur()"><img border="0" src="http://wpa.qq.com/pa?p=2:609307843:41" alt="瑞曼为您服务" title="瑞曼科技"></a> </span></li>
                                            </ul>
                                        </td>
                                    </tr>
                                </table>
                            <td width="15%" rowspan="2">&nbsp;</td>
                            </td>
                        </tr>
                        <tr><td colspan="2">&nbsp;</td></tr>
                    </table>
                </td>
                <!-- 主体右部分 -->
                <td id="right_cont">
                    <table height="100%">
                        <tr height="30%"><td colspan="3">&nbsp;</td></tr>
                        <tr>
                            <td width="30%" rowspan="5">&nbsp;</td>
                            <td valign="top" id="form">
                                <form action="/xuetuWeb/LoginServlet" method="post" >
                                    <table valign="top" width="50%">
                                        <tr><td colspan="2"><h4 style="letter-spacing:1px;font-size:16px;">学途  店家管理后台</h4></td></tr>
                                        <tr><td>管理员：</td><td><input type="text" name="username" value="" /></td></tr>
                                        <tr><td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td><td><input type="password" name="userpass" value="" /></td></tr>
                                        
                                        <tr><td>验证码：</td><td><input type="text" name="valiimage" value="" style="width:80px;"/><img src="/xuetuWeb/ValiImage" onclick="changeImg(this)" style="cursor: pointer;"/></td></tr>
                                        <tr ><td></td><td align="right"><a href='/xuetuWeb/register.jsp' style="font-size:12px;">新用户</a></td></tr>
                                        <tr class="bt" align="center"><td>&nbsp;<input type="submit" value="登陆" /></td><td>&nbsp;<input type="reset" value="重填" /></td></tr>
                                    </table>
                                </form>
                            </td>
                            <td rowspan="5">&nbsp;</td>
                        </tr>
                        <tr><td colspan="3">&nbsp;</td></tr>
                    </table>
                </td>
            </tr>
            <!-- 底部部分 -->
            <tr id="login_bot"><td colspan="2"><p>Copyright © 2014-2016 XueTu 网络工作室</p></td></tr>
        </table>
    </body>
</html>