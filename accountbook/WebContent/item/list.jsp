<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ÊÕÖ§ÏîÄ¿¹ÜÀí</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(e){
	$(":input[name=checkbox]").click(function(e){
// 		var c = $(":input[name=all]").prop("checked");
	$(":input[name=favor]").prop("checked",$(":input[name=checkbox]").prop("checked"));
	});
});
	




</script>
</head>

<body topmargin="0" leftmargin="0">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="32" align="left" valign="top" class="text006"><table width="98%"  border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <td>¡¾ ÊÕÖ§ÏîÄ¿¹ÜÀí ¡¿ (¸¸ÏîÄ¿Ãû³Æ)</td>
          <td align="right">
          <a href="addTopItem.jsp"  onMouseOver="MM_swapImage('Image1','','../images/index_15_1.gif',1)" onMouseOut="MM_swapImgRestore()" onMouseDown="MM_swapImage('Image1','','../images/index_15_2.gif',1)" onMouseUp="MM_swapImage('Image1','','../images/index_15_1.gif',1)"><img src="../images/index_15_0.gif" width="85" height="24" border="0" align="top" id="Image1"></a>&nbsp;&nbsp;
          <a href="add.html" target="mainframe" onMouseOver="MM_swapImage('Image2','','../images/index_14_1.gif',1)" onMouseOut="MM_swapImgRestore()" onMouseDown="MM_swapImage('Image2','','../images/index_14_2.gif',1)" onMouseUp="MM_swapImage('Image2','','../images/index_15_1.gif',1)"><img src="../images/index_14_0.gif" width="85" height="24" border="0" align="top" id="Image2"></a>&nbsp;&nbsp;<a href="#" target="mainframe" onMouseOver="MM_swapImage('Image3','','../images/index_13_1.gif',1)" onMouseOut="MM_swapImgRestore()" onMouseDown="MM_swapImage('Image3','','../images/index_13_2.gif',1)" onMouseUp="MM_swapImage('Image3','','../images/index_13_1.gif',1)"><img src="../images/index_13_0.gif" id="Image3" width="75" height="24" border="0"></a>
		  </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="215" valign="top"><table width="98%"  border="0" cellspacing="0" cellpadding="0">
      <tr align="center" class="bg03">
        <td width="13%" height="29">
          <input type="checkbox" name="checkbox" value="checkbox"> 
          <span class="text007">È«Ñ¡</span></td>
        <td width="22%" class="text007"> ÊÕÖ§ÀàÐÍ              </td>
        <td width="20%" class="text007">ÏîÄ¿Ãû³Æ </td>
        <td width="30%" class="text007">²Ù×÷</td>
      </tr>
      <tr align="center">
        <td height="35"><input type="checkbox" name="favor" value="checkbox"></td>
        <td>Ö§³ö</td>
        <td align="left"><a href="list.html">½»Í¨</a></td>
        <td><a href="detail.html"><img src="../images/icon_resault.gif" alt="²é ¿´" width="12" height="15" hspace="10" border="0"></a>
        	<a href="edit.html"><img src="../images/icon_set.gif" alt="±à ¼­" width="14" height="15" border="0"></a><img src="../images/delete.gif" alt="É¾ ³ý" width="13" height="16" hspace="10"></td>
      </tr>
         
        

    </table>
      <table width="98%" border="0" cellpadding="0" cellspacing="0" class="bor001">
        <tr>
          <td height="40" align="left" >&nbsp;</td>
          <td align="right">
          </td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
