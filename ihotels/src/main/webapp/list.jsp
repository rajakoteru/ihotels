<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="htmlHead.jsp"/>
</head>
<body>



<TABLE border="0" cellpadding="0" cellspacing="0" style="position: absolute; top: 0px; margin:0px;" width="99%">
<TR><jsp:include page="canonball.jsp"/></TR>    

<TR>
<TD WIDTH="25">&nbsp;</TD>    
<TD WIDTH="210" style="margin:0px; background: #ececec;">
</TD>

<TD colspan="2">

    <c:if test='${not empty hotels}'>
    <c:forEach var="hotel" items="${hotels}">
    <table cellspacing="3" cellpadding="3" style="border-bottom: solid 2px #ccc;">    
    <tr height="25" class="row">
    <td width="20%"><img src="/iHotels/images/hotels/${hotel.id}.jpg"/></td>
    <td width="60%">
        <a href="/iHotels/details.do?id=${hotel.id}">${hotel.name}  </a>
        <br/>
        <img src="/iHotels/images/prop_star_rating_${hotel.star}.gif"/> Star Hotel
        <br/>
        <img src="/iHotels/images/prop_cust_reviews_${hotel.reviews}.gif"/> 
        <br/>
        ${hotel.address}
    </td>
    <td width="20%">
        <b style="color: green;"> ${hotel.price}  </b>
        <br/>
        <font style="font-size: 10px; color: #000;">Avg/Night</font>
    </td>
    
    </tr> 
    </table>
    </c:forEach>
    </c:if> 

</TD>
<TD WIDTH="20">&nbsp;</TD>
</TR>
</TABLE>


</body>
</html>
