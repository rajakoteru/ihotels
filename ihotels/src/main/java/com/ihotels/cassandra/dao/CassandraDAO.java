/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihotels.cassandra.dao;

import com.ihotels.cassandra.beans.Destination;
import com.ihotels.cassandra.beans.Hotel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.spring.HectorTemplate;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.MultigetSliceQuery;
import me.prettyprint.hector.api.query.QueryResult;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Madan KN
 */
public class CassandraDAO {
    
    private static final StringSerializer SE = StringSerializer.get();
    private HectorTemplate casTemplate;
    ////////////////////////////////////////////////////////////////////////////
    public List<Hotel> getHotelDetails(String sDestination, String sNoOfRooms, String sNoOfGuests, String sCinDate, String sCoutDate){
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        MultigetSliceQuery<String, String, String> multigetSliceQuery = casTemplate.createMultigetSliceQuery(SE, SE, SE);
        multigetSliceQuery.setColumnFamily("hoteldetails"); 
        
        if(sDestination!=null){
           multigetSliceQuery.setKeys(sDestination.split(","));
        }
        if (!("1".equalsIgnoreCase(sNoOfRooms))){
            sCinDate=sCinDate+"-R"+sNoOfRooms;
        }
        
        if(!("1".equalsIgnoreCase(sNoOfGuests))){
            sCinDate=sCinDate+"-G"+sNoOfGuests;
        }

        multigetSliceQuery.setColumnNames("name", "star", "reviews", "address", sCinDate, sCoutDate);
        
        QueryResult<Rows<String, String, String>> result = multigetSliceQuery.execute();
        Rows<String, String, String> orderedRows = result.get();
        
        for (Row<String, String, String> r : orderedRows) {
            
             if(r!=null){
                Hotel hotel = new Hotel();
                ColumnSlice<String, String> colSlice = r.getColumnSlice();
                hotel.setId(r.getKey());
                if(colSlice.getColumnByName("name")!=null)hotel.setName(colSlice.getColumnByName("name").getValue());
                if(colSlice.getColumnByName("star")!=null)hotel.setStar(colSlice.getColumnByName("star").getValue());
                if(colSlice.getColumnByName(sCinDate)!=null){
                    //System.out.println("sCinDate"+sCinDate.toString()+colSlice.getColumnByName(sCinDate.getValue()));
                  hotel.setPrice(colSlice.getColumnByName(sCinDate).getValue() + ".00 USD");
                }else{
                  hotel.setPrice("Sold Out");  
                }
                    
                if(colSlice.getColumnByName("reviews")!=null)hotel.setReviews(colSlice.getColumnByName("reviews").getValue());
                if(colSlice.getColumnByName("address")!=null)hotel.setAddress(colSlice.getColumnByName("address").getValue());
                if(colSlice.getColumnByName("description")!=null)hotel.setDescription(colSlice.getColumnByName("description").getValue());
                results.add(hotel);
             }   
        }
        return results;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public Map<String, String> getContent(String key, String[] columns,  String lang){
        Map<String, String> map = new HashMap<String, String>();
        
        MultigetSliceQuery<String, String, String> multigetSliceQuery = casTemplate.createMultigetSliceQuery(SE, SE, SE);
        multigetSliceQuery.setColumnFamily("content1"); 
        String sKey =  key;
        if(!StringUtils.isEmpty(lang)){
            sKey = sKey+"-"+lang;
        }
        
        multigetSliceQuery.setKeys(sKey);
        multigetSliceQuery.setColumnNames(columns);
        
        QueryResult<Rows<String, String, String>> result = null;
        Rows<String, String, String>         orderedRows = null;
        
        try{ 
            result = multigetSliceQuery.execute(); 
            orderedRows = result.get();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        if(orderedRows!=null)
        for (Row<String, String, String> r : orderedRows) {
             ColumnSlice<String, String> cs = r.getColumnSlice();
             if(cs!=null){
                List<HColumn<String, String>> colList = cs.getColumns();
                for(HColumn col : colList){
                    //System.out.println((String)col.getName() + "===" +(String)col.getValue());
                    map.put((String)col.getName(), (String)col.getValue());
                }
             }
        }
        return map;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public Destination getHotelIds(String sDestination){
        Destination destination = new Destination();
        ColumnQuery<String, String, String> columnQuery =  casTemplate.createColumnQuery();
        columnQuery.setColumnFamily("hotelsbycity");
        columnQuery.setKey(sDestination);
        columnQuery.setName("hids");
        QueryResult<HColumn<String, String>> result = columnQuery.execute();
        destination.setHotelIds(result.get().getValue());
        System.out.println("destination = " +destination.getHotelIds());
        return destination;
    }    
    /**
     * @return the casTemplate
     */
    private HectorTemplate getCasTemplate() {
        return casTemplate;
    }

    /**
     * @param casTemplate the casTemplate to set
     */
    public void setCasTemplate(HectorTemplate casTemplate) {
        this.casTemplate = casTemplate;
    }
}
