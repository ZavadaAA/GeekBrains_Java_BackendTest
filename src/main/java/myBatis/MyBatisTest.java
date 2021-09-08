package myBatis;

import db.dao.ProductsMapper;
import db.model.Products;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;

public class MyBatisTest {
    public static void main(String[] args) {
        SqlSessionFactory sessionFactory =
                new SqlSessionFactoryBuilder()
                        .build(MyBatisTest.class.getResourceAsStream("mybatis-config.xml"));

        SqlSession session = sessionFactory.openSession();

        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);

        // Adding the product into Data-base

        Products product = new Products();
        product.setCategoryId(2L);
        product.setId(88L);
        product.setPrice(195);
        product.setTitle("Grapes");
        productsMapper.insert(product);
        session.commit();
        System.out.println(product);

        Products products = productsMapper.selectByPrimaryKey(88L);
        System.out.println(products);

        Assert.assertEquals(products, product);

        // Deleting the product from the data-base

        productsMapper.deleteByPrimaryKey(88L);
        session.commit();

        Products productDelete = productsMapper.selectByPrimaryKey(88L);
        System.out.println(productDelete);

        Assert.assertEquals(null, productDelete);

        session.close();
    }
}