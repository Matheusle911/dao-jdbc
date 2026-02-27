import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Test 1: seller findById =======");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(3));
        System.out.println("=== Test 2: seller findByDepartment =======");
        Department deparment = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(deparment);
        for(Seller obj : list){
            System.out.println(obj);
        }

    }
}