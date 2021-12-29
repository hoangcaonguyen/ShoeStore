package nguyen.shoestore.Repository;

import nguyen.shoestore.Entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {
    ProductType getByTypeName(String typeName);
}
