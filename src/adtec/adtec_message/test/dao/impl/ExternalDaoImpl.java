package adtec.adtec_message.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adtec.adtec_message.test.DBUtils.DBUtils;

import adtec.adtec_message.test.dao.ExternalDao;

public class ExternalDaoImpl implements ExternalDao {
	
	public static final String FIND_USER_BY_ORGID = "select username from accountorgrelation where orgId = ?";
	
	@Override
	public List<Map<String, Object>> queryAccountByorgIdList(List<String> list) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
		try{
			conn = DBUtils.getConnection();
			for(int i=0;i<list.size();i++){
				pstm = conn.prepareStatement(FIND_USER_BY_ORGID);
				pstm.setString(1, list.get(i));
			}
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("username", rs.getString(1));
				rows.add(map);
			}
			
			return rows;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return rows;
		}
		finally{
			DBUtils.close(conn);
		}
		
	}
	
	@Override
	public String queryorgIdByLevel(Map<String, String> map) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String orgIdKey = null;
		try{
			conn = DBUtils.getConnection();
			int size = map.size();	
			
			//拼接sql语句不分
			StringBuilder sql = new StringBuilder()
			.append("select orgId from ta_organization")
			;
			if(size == 1){
				sql.append(" where level1=?");
			}
			if(size == 2){
				sql.append(" where level1=? and level2=?");
			}
			if(size == 3){
				sql.append(" where level1=? and level2=? and level3=?");
			}
			if(size == 4){
				sql.append(" where level1=? and level2=? and level3=? and level4=?");
			}
			if(size == 5){
				sql.append(" where level1=? and level2=? and level3=? and level4=? and level5=?");
			}
			if(size == 6){
				sql.append(" where level1=? and level2=? and level3=? and level4=? and level5=? and level6=?");
			}
			if(size == 7){
				sql.append(" where level1=? and level2=? and level3=? and level4=? and level5=? and level6=? and level7=?");
			}
			pstm = conn.prepareStatement(sql.toString());
			for(int i=0;i<size;i++){
				String levelname="level"+(i+1);
				pstm.setString(i+1, map.get(levelname));
			}
			rs = pstm.executeQuery();
			if(rs.next()){
				orgIdKey = rs.getString(1);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			DBUtils.close(conn);
		}
			
		return orgIdKey;
	}
	
	
}
