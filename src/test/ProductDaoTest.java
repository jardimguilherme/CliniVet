package test;

import model.dao.EmployeeDao;
import model.dao.ProductDao;
import model.db.DbConnection;
import model.entity.Product;

import java.sql.Connection;
import java.util.List;

public class ProductDaoTest {
    public static void main(String[] args) {

        Connection connection  = DbConnection.getInstance();
        ProductDao dao = new ProductDao();
        dao.setConnection(connection);

//        Product product = dao.get(1);
//        System.out.println(product.getName());
//        System.out.println(product.getPrice());

        List<Product> productList = dao.getAll();

        for(Product product : productList){
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println("___________");
        }

    }
}
