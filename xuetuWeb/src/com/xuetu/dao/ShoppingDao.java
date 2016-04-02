package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.ShoppintDaoInter;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyCoupon;
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
				int int1 = query.getInt("cou_id");
				coupon.setCouID(int1);
				coupon.setShiyongNum(queryMyCouponNum(int1));
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

	public int queryMyCouponNum(int couponID){
		Connection conn = DBconnection.getConnection();
		String sql = "select count(*) as 'num' from mycoupon where  cou_id = ?";
		ResultSet query=null;
		PreparedStatement statement=null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, couponID);
			query = statement.executeQuery();
			if(query.next()){
				int num =query.getInt("num");
				return num;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDb.close(conn, query, statement);
		}
		
		
		
		return 0;
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

	@Override
	public boolean insertFavoritesCoupons(FavoritesCoupons fa) {
		Connection conn = DBconnection.getConnection();
		PreparedStatement statement = null;
		String sql="insert into favoritescoupons (stu_id,cou_id,cou_date) values(?,?,?)";
		try {
			  statement = conn.prepareStatement(sql);
			  statement.setInt(1, fa.getStudent().getStuId());
			  statement.setInt(2, fa.getCoupon().getCouID());
			  statement.setTimestamp(3, new Timestamp(fa.getCreateDate().getTime()));
			  int executeUpdate = statement.executeUpdate();
			  if(executeUpdate>0){
				  return true;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean queryIssavefavorites(int coupID, int studentid) {
		Connection conn = DBconnection.getConnection();
		String sql= "select * from favoritescoupons where stu_id=? and cou_id=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, studentid);
			statement.setInt(2, coupID);
			ResultSet querly = statement.executeQuery();
			if(querly.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public boolean insertMycoupon(MyCoupon mycoupon) {
		Connection conn = DBconnection.getConnection();
		String sql="insert into   mycoupon  (cou_id, stu_id,mycou_exchange_time) values(?,?,?)";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, mycoupon.getCoupon().getCouID());
			statement.setInt(2, mycoupon.getStudent().getStuId());
			statement.setTimestamp(3, new Timestamp(mycoupon.getMycouExchangeTime().getTime()));
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
