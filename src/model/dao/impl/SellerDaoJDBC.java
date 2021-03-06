package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updata(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	//recuperando um funcionario por id
	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			// se tiver linhas para percorrer
			if (rs.next()) {
				
				//pegando o departamento dele
				Department dep = instantiateDepartment(rs);
				
				//instanciando o funcionario
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			ps.setInt(1, department.getId());
			
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			
			// se tiver linhas para percorrer
			while (rs.next()) {
				
				Seller obj = null;
				//se n?o existir um derpartamento
				if(department.getId().equals(null)) {
					department = instantiateDepartment(rs);
					obj = instantiateSeller(rs, department);
					list.add(obj);
				}else {
					Department dep = new Department(department.getId(), null);
					
					obj = instantiateSeller(rs, dep);
					list.add(obj);
				}
				
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//pegando o departamento dele
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("departmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	
	//instanciando o funcionario
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalery(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartmenty(dep);
		
		return obj;
	}


}
