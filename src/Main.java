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
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("");
        System.out.println("=== Test 2: seller findByDepartment =======");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){
            System.out.println(obj);
        }
        System.out.println("");
        System.out.println("=== Test 3: seller findAll =======");
        list = sellerDao.findAll();
        for(Seller obj : list){
            System.out.println(obj);
        }
       /* System.out.println("");
        System.out.println("=== Test 4: seller insert =======");
        Seller newseller = new Seller(null,"Jeffrey","Jeffrey@gmail.com",new Date(),4400.00, department);
        sellerDao.insert(newseller);
        System.out.println("Inserted! New id = "+ newseller.getId());

        */

        System.out.println("");
        System.out.println("=== Test 5: seller update =======");
        seller = sellerDao.findById(1);
        seller.setName("Martha Wayne");
        seller.setEmail("MarthaWayne@gmail.com");
        sellerDao.update(seller);
        System.out.println("Update completed!");
    }
}