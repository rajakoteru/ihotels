/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihotels.server;


import com.ihotels.cassandra.beans.Hotel;
import com.ihotels.cassandra.dao.CassandraDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.prettyprint.cassandra.serializers.StringSerializer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Madan KN
 */

@Controller
@RequestMapping(value={"/list.do"})
public class HotelListController {
    
    private static final StringSerializer SE = StringSerializer.get();
    
    private CassandraDAO cassandraDAO;

    ////////////////////////////////////////////////////////////////////////////
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView doGET(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return doProcess(request, response);
    }
    ////////////////////////////////////////////////////////////////////////////
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return doProcess(request, response);
    }
    ////////////////////////////////////////////////////////////////////////////
    private ModelAndView doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String sDestination = request.getParameter("destination")==null?"":request.getParameter("destination");
        String sNoOfRooms   = request.getParameter("noofrooms")==null?"":request.getParameter("noofrooms");
        String sNoOfGuests  = request.getParameter("noofguests")==null?"":request.getParameter("noofguests");
        String sCinDate     = request.getParameter("cindate"); 
        String sCoutDate    = request.getParameter("coutdate"); 
        
        //hotelDetailsDAO.getContent("en");
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar         cal = Calendar.getInstance();        
        if(sCinDate==null){
           sCinDate = sdf.format(cal.getTime());
        }   
   
        if(sCoutDate==null){
           cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);
           sCoutDate = sdf.format(cal.getTime());
        } 
   
        ModelAndView view   = new ModelAndView("list");
        List<Hotel> results = cassandraDAO.getHotelDetails(cassandraDAO.getHotelIds(sDestination).getHotelIds(), sNoOfRooms, sNoOfGuests, sCinDate, sCoutDate);
        
        String    sLocale = StringUtils.isEmpty(request.getParameter("lang"))==true?"":request.getParameter("lang");
        String  sLables[] = new String[]{ "byh", "dst", "cid" , "cod" , "nor" , "nog"};
        Map<String, String> map = cassandraDAO.getContent("mainpage", sLables, sLocale);
        view.addObject("cmap", map);

        view.addObject("hotels", results);
        return view;
        
    }
    ////////////////////////////////////////////////////////////////////////////

    public CassandraDAO getCassandraDAO() {
        return cassandraDAO;
    }


    @Autowired
    public void setCassandraDAO(CassandraDAO dao) {
        this.cassandraDAO = dao;
    }

}
