package nguyen.shoestore.Repository;


import nguyen.shoestore.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Integer> {
    @Query("select i from  Item i where i.status =: status ")
    List<Item> getByStatus(Integer status);
    List<Item> getByProductId (Integer productId);
    @Query("select i from Item i where i.productId in (SELECT productId FROM Product where productName =: productName)")
    List<Item> getByProductName (String productName);
    List<Item> findByColorId (Integer colorId);
    List<Item> getByColorId (Integer colorId);
    @Query("select i from Item i where i.colorId in (SELECT colorId FROM Color where colorName =: colorName)")
    List<Item> getByColorName (String colorName);
    List<Item> getBySize (Integer size);
    List<Item> getByNumItems (Integer numItems);
    List<Item> getByTypeId (Integer typeId);
    @Query("select i from Item i where i.typeId in (SELECT typeId FROM ProductType where typeName =: type)")
    List<Item> getByType (String type);
    List<Item> getBySale (Integer sale);
    List<Item> getByProductIdAndColorIdAndTypeId (Integer productId, Integer colorId, Integer typeId);
}
