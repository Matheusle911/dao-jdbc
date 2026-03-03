package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("Insert Into department (Name) Values (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());
            int rowsaffected = st.executeUpdate();
            if(rowsaffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
            st.setString(1,obj.getName());
            st.setInt(2,obj.getId());
            int rowsaffected = st.executeUpdate();
            if(rowsaffected == 0){
                throw new DbException("No Department Found!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1,id);
            int rowsaffected = st.executeUpdate();
            if(rowsaffected == 0){
                throw new DbException("No Department Found!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Department obj = new Department();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                return obj;

            }
            DB.closeResultSet(rs);
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department ORDER BY Id");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            while(rs.next()){
                Department obj = new Department();
                obj.setName(rs.getString("Name"));
                obj.setId(rs.getInt("Id"));
                list.add(obj);
            }
            return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
