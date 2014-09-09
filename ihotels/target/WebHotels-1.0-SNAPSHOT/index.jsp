<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="htmlHead.jsp"/>
</head>

<body>
    
<TABLE border="0" cellpadding="0" cellspacing="0" style="position: absolute; top: 0px; margin:0px;" width="99%">
<script>
    $.backstretch([
        "./images/bkg/img3.jpg", 
        "./images/bkg/img1.jpg",
        "./images/bkg/img2.jpg",
        "./images/bkg/img4.jpg",
        "./images/bkg/img5.jpg",
        "./images/bkg/img7.jpg",
        "./images/bkg/img6.jpg"],
        {duration: 3000, fade: 750});
</script>
<TR>
<jsp:include page="canonball.jsp"/>
</TR>
</TABLE>
</body>
</html>
