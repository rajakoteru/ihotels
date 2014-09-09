/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihotels.server;


import com.ihotels.cassandra.dao.CassandraDAO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author SG0210591
 */

@Controller
@RequestMapping(value={"/main.do"})
public class HotelMainController {
    
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
        ModelAndView view = new ModelAndView("index");
        String    sLocale = StringUtils.isEmpty(request.getParameter("lang"))==true?"":request.getParameter("lang");
        String  sLables[] = new String[]{ "byh", "dst", "cid" , "cod" , "nor" , "nog"};
        Map<String, String> map = cassandraDAO.getContent("mainpage", sLables, sLocale);
        view.addObject("cmap", map);
        return view;
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     * @return the cassandraDAO
     */
    public CassandraDAO getCassandraDAO() {
        return cassandraDAO;
    }

    /**
     * @param cassandraDAO the hotelByCityDAO to set
     */
    @Autowired
    public void setCassandraDAO(CassandraDAO dao) {
        this.cassandraDAO = dao;
    }



}
