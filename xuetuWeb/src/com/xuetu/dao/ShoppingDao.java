package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.ShoppintDaoInter;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StoreName;
import com.xuetu.utils.CloseDb;
import com.xuetu.utils.DBconnection;

public class ShoppingDao implements ShoppintDaoInter {
	StoreNameDao2 dao2 = new StoreNameDao2();

	@Override
	public List<Coupon> queryCouponlimmit(int page, int num) {
		Connection conn = DBconnection.getConnection();
		PreparedStatement statement=null;
		String sql = "select * from coupon   ORDER BY cou_create_time DESC  limit ?,?" ;
		
		ResultSet query = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, page*num);
			statement.setInt(2, num);
			query = statement.executeQuery();
			List<Coupon> list = new ArrayList<>();
			Coupon coupon = null;;
			while(query.next()){
				coupon = new Coupon();
				coupon.setCouID(query.getInt("cou_id"));
				StoreName storeName = dao2.getStoreNameById(query.getInt("sto_id"));
				coupon.setStoreName(storeName);
				coupon.setCouInfo(query.getString("cou_info"));
				coupon.setConNum(query.getInt("cou_num"));

				coupon.setConValidity(query.getDate("cou_Validity"));
				coupon.setCoouRedeemPoints(query.getInt("cou_redeem_points"));
				coupon.setCouName(query.getString("cou_name"));
				coupon.setCouPrice(query.getInt("cou_price"));
				
				
				
				
				list.add(coupon);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			CloseDb.close(conn, query, statement);
		}
		
	}

	@Override
	public List<Coupon> queryCouponall(int stoID) {
		Connection conn = DBconnection.getConnection();
		PreparedStatement statement=null;
		String sql = "select * from coupon  where sto_id=? ;";
		ResultSet query = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, stoID);
			query = statement.executeQuery();
			List<Coupon> list = new ArrayList<>();
			Coupon coupon = null;;
			while(query.next()){
				coupon = new Coupon();
				coupon.setCouID(query.getInt("cou_id"));
				StoreName storeName = dao2.getStoreNameById(query.getInt("sto_id"));
				coupon.setStoreName(storeName);
				coupon.setCouInfo(query.getString("cou_info"));
				coupon.setConNum(query.getInt("cou_num"));

				coupon.setConValidity(query.getDate("cou_Validity"));
				coupon.setCoouRedeemPoints(query.getInt("cou_redeem_points"));
				coupon.setCouName(query.getString("cou_name"));
				coupon.setCouPrice(query.getInt("cou_price"));
				
				
				
				
				list.add(coupon);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			CloseDb.close(conn, query, statement);
		}
		
	}

}
