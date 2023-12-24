package dao;

import metier.Product;
import metier.User;

import java.util.List;

public interface IProductDao {




    public Product save(Product p);

    public Product selectProduct(int id);

    public List<Product> selectAllProduct();

    public boolean deleteProduct(int id);

    public  boolean updateProduct(Product product);



}
