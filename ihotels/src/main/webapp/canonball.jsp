<%@page import="org.springframework.util.StringUtils"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
   String sDestination = request.getParameter("destination")==null?"":request.getParameter("destination");
   String sNoOfRooms   = request.getParameter("noofrooms")==null?"":request.getParameter("noofrooms");
   String sNoOfGuests  = request.getParameter("noofguests")==null?"":request.getParameter("noofguests");
   String sCinDate     = request.getParameter("cindate"); 
   String sCoutDate    = request.getParameter("coutdate"); 
   
   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   Calendar         cal = Calendar.getInstance();
   
   if(sCinDate==null){
      sCinDate = sdf.format(cal.getTime());
   }   
   
   if(sCoutDate==null){
      cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);
      sCoutDate = sdf.format(cal.getTime());
   }   
   
   
%>  

<TD WIDTH="25">&nbsp;</TD>    
<TD WIDTH="210" style="margin:0px; background: #6c6c6c;">
    <a href="/iHotels/main.do"><img border="0" alt="/" src="./images/logo.gif" itemprop="logo"></a>
    <div style="position: absolute; top: 125px; left: 150px;">
        <select onchange="javascript:document.location.href='/iHotels/main.do?lang='+this.value">
            <option value="" <% if(StringUtils.isEmpty(request.getParameter("lang"))) out.print(" selected ");  %> >English</option>
            <option value="es"  <% if("es".equalsIgnoreCase(request.getParameter("lang"))) out.print(" selected ");  %> >Spanish</option>
        </select>
    </div>
</TD>
<TD class="cB1">
    <form id="thisFrm" method="GET" action="/iHotels/list.do">
    <input type="hidden" name="lang" value='<%if(!StringUtils.isEmpty(request.getParameter("lang")))out.print("es");%>'>    
    <table cellpadding="3" cellspacing="3" class="cB2">
        <tr>
            <td colspan="4" style="line-height: 15px; font-size: 16px; color: #eccc5b;">
                ${cmap['byh']}
            </td>
        </tr>
        <tr>
            <td>${cmap['dst']}</td>
            <td align="left" colspan="3">
                <select name="destination" id="destination" style="width: 460px;">
                    <option value="DFW-USA" <%if(sDestination.equals("DFW-USA")) out.print("selected");%>  >Dallas Fort Worth, USA</option>
                    <option value="NY-USA"  <%if(sDestination.equals("NY-USA")) out.print("selected");%>>New York, USA</option>
                    <option value="BLR-IN"  <%if(sDestination.equals("BLR-IN")) out.print("selected");%>>Bengaluru, India</option>
                    <option value="DL-IN"   <%if(sDestination.equals("DL-IN")) out.print("selected");%>>Delhi, India</option>
                </select>
                <script>
                    $("#destination").kendoDropDownList();
                </script>
            </td>
        </tr>

        <tr>
            <td>${cmap['cid']}</td>    
            <td>
                <input name="cindate" id="cindate"/>
                <script>
                    $("#cindate").kendoDatePicker({
                        format: "MM/dd/yyyy",
                        value: "<%=sCinDate%>"
                    });
                </script>               
            </td>
            <td>${cmap['cod']} </td>
            <td>
                <input name="coutdate" id="coutdate"/>
                <script>
                    $("#coutdate").kendoDatePicker({
                        format: "MM/dd/yyyy",
                        value: "<%=sCoutDate%>"
                    });
                </script>                 
            </td>
        </tr>
        
        <tr>
            <td>${cmap['nor']}</td>    
            <td>
                <select name="noofrooms" id="noofrooms">
                  <option value="1"  <%if(sNoOfRooms.equals("1")) out.print("selected");%> >1</option>  
                  <option value="2"  <%if(sNoOfRooms.equals("2")) out.print("selected");%> >2</option>
                  <option value="3"  <%if(sNoOfRooms.equals("3")) out.print("selected");%> >3</option>
                  <option value="4"  <%if(sNoOfRooms.equals("4")) out.print("selected");%> >4</option>
                  <option value="5"  <%if(sNoOfRooms.equals("5")) out.print("selected");%> >5</option>
                </select>
                <script>
                    $("#noofrooms").kendoDropDownList();
                </script>                
            </td>
            <td>${cmap['nog']}</td>
            <td>
                <select name="noofguests" id="noofguests">
                  <option value="1"  <%if(sNoOfGuests.equals("1")) out.print("selected");%> >1</option>  
                  <option value="2"  <%if(sNoOfGuests.equals("2")) out.print("selected");%> >2</option>
                  <option value="3"  <%if(sNoOfGuests.equals("3")) out.print("selected");%> >3</option>
                  <option value="4"  <%if(sNoOfGuests.equals("4")) out.print("selected");%> >4</option>
                  <option value="5"  <%if(sNoOfGuests.equals("5")) out.print("selected");%> >5</option>
                  <option value="6"  <%if(sNoOfGuests.equals("6")) out.print("selected");%> >1</option>  
                  <option value="7"  <%if(sNoOfGuests.equals("7")) out.print("selected");%> >2</option>
                  <option value="8"  <%if(sNoOfGuests.equals("8")) out.print("selected");%> >3</option>
                  <option value="9"  <%if(sNoOfGuests.equals("9")) out.print("selected");%> >4</option>
                  <option value="10" <%if(sNoOfGuests.equals("10")) out.print("selected");%> >5</option>
                  
                </select>
                <script>
                    $("#noofguests").kendoDropDownList();
                </script>                
                
            </td>
        </tr>

    </table>    
    </form>                   
</TD>
<TD class="cB1" valign="middle" WIDTH="150" align="left">
    <a href="javascript:submitFrm();" class="myButton">Find Hotels</a>
    <script>
        function submitFrm(){
            $("#thisFrm").submit();
        }
    </script>
</TD>
<TD WIDTH="20">&nbsp;</TD>

