package db.dao;

import db.model.Products;
import db.model.ProductsExample;
import java.util.List;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

public interface ProductsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    long countByExample(ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int deleteByExample(ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int insert(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int insertSelective(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    List<Products> selectByExample(ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    Products selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int updateByPrimaryKeySelective(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRODUCTS
     *
     * @mbg.generated Wed Sep 08 16:48:37 MSK 2021
     */
    int updateByPrimaryKey(Products record);
}
