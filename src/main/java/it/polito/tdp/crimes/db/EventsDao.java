package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.crimes.model.Adiacenza;
import it.polito.tdp.crimes.model.Event;



public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> listAllAnni(){
		String sql = "SELECT  distinct YEAR(reported_date) as anno  FROM events  Order by anno " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<Integer>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("anno"));
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> listAllMese(){
		String sql = "SELECT  distinct MONTH(reported_date)  as mese FROM events  Order By mese " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<Integer>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("mese"));
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
		
		public List<Integer>listAllgiorno(){
			String sql = "SELECT  distinct DAY(reported_date) AS giorno FROM  events  order by giorno " ;
			try {
				Connection conn = DBConnect.getConnection() ;

				PreparedStatement st = conn.prepareStatement(sql) ;
				
				List<Integer> list = new ArrayList<Integer>() ;
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						list.add(res.getInt("giorno"));
					} catch (Throwable t) {
						t.printStackTrace();
						
					}
				}
				
				conn.close();
				return list ;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
		}
		
		
		public List<Adiacenza>vertici(int anno)
		{
			String sql = "SELECT DISTINCT e.district_id, e.geo_lat AS Lat, e.geo_lon AS Lng "
					+ "FROM events e "
					+ "WHERE YEAR(e.reported_date) = ? "
					+ "GROUP BY e.district_id "
					+ "ORDER BY e.district_id " ;
			try {
				Connection conn = DBConnect.getConnection() ;
			

				PreparedStatement st = conn.prepareStatement(sql) ;
			    List<Adiacenza> list = new ArrayList<>() ;
				st.setInt(1,anno);
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						list.add(new Adiacenza(res.getInt("district_id"),new LatLng(res.getDouble("Lat"), res.getDouble("Lng"))));
					} catch (Throwable t) {
						t.printStackTrace();
					
					}
				}
				
				conn.close();
				return list ;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
		
		
		}
		
		
		public List<Integer>distretti()
		{
			String sql = "SELECT DISTINCT e.district_id "
					+ "FROM events e "
					+ "ORDER BY e.district_id " ;
			try {
				Connection conn = DBConnect.getConnection() ;
			

				PreparedStatement st = conn.prepareStatement(sql) ;
			    List<Integer> list = new ArrayList<>() ;
				
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						list.add((res.getInt("district_id")));
					} catch (Throwable t) {
						t.printStackTrace();
					
					}
				}
				
				conn.close();
				return list ;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
		
		
		}
		
	
	}
	
	
	
	
	
	
	
	



