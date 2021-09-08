import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import retrofit.api.MiniMarketApi;
import retrofit.dto.Category;
import retrofit.dto.ProductDto;
import retrofit.utils.RetrofitGetter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.List;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;

import org.opentest4j.AssertionFailedError;


public class ApiTest {

    private final MiniMarketApi api;

    public ApiTest() throws IOException {
        Retrofit retrofit = new RetrofitGetter().getInstance();
        api = retrofit.create(MiniMarketApi.class);
    }

    @Test
    @DisplayName("Create product")
    void testCreateProduct() throws IOException {

        ProductDto dto = ProductDto.builder()
                .title("Candies")
                .categoryTitle(Category.FOOD.getTitle())
                .price(50L)
                .build();

        Response<ProductDto> response = api.createProduct(dto).execute();
        Long id = response.body().getId();

        ProductDto actually = api.getProduct(id).execute().body();

        assertEquals(dto.getTitle(), actually.getTitle());
        assertEquals(dto.getPrice(), actually.getPrice());
        assertEquals(dto.getCategoryTitle(), actually.getCategoryTitle());

        api.deleteProduct(id).execute();
    }

    @Test
    @DisplayName("Getting product info")
    void testGetProduct() throws IOException {

        ProductDto dto = ProductDto.builder()
                .id(7)
                .title("Meat balls")
                .categoryTitle(Category.FOOD.getTitle())
                .price(15L)
                .build();

        Response<ProductDto> response = api.getProduct(dto.getId()).execute();
        Long id = response.body().getId();

        ProductDto actually = api.getProduct(id).execute().body();

        assertEquals(dto.getTitle(), actually.getTitle());
        assertEquals(dto.getPrice(), actually.getPrice());
        assertEquals(dto.getCategoryTitle(), actually.getCategoryTitle());

    }

    @Test
    @DisplayName("Updating product info")
    void testUpdateProduct() throws IOException {

        ProductDto dto = ProductDto.builder()
                .id(8)
                .title("Eggs")
                .categoryTitle(Category.FOOD.getTitle())
                .price(3L)
                .build();

        Response<ProductDto> response = api.updateProduct(dto).execute();
        Long id = response.body().getId();

        ProductDto actually = api.getProduct(id).execute().body();

        assertEquals(dto.getPrice(), actually.getPrice());
    }

    @Test
    @DisplayName("Delete product")
    void testDeleteProduct() throws IOException {

        ProductDto dto = ProductDto.builder()
                .title("Meat balls")
                .categoryTitle(Category.FOOD.getTitle())
                .price(15L)
                .build();

        Response<ProductDto> response = api.createProduct(dto).execute();

        Long id = response.body().getId();

        api.deleteProduct(id).execute();

        api.getProduct(id).execute();

        new AssertionError("There is no product with id: " + dto.getId());
    }
}