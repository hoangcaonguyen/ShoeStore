package nguyen.shoestore.Service;


import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Entity.Item;
import nguyen.shoestore.Entity.ProductType;
import nguyen.shoestore.Repository.ItemRepo;
import nguyen.shoestore.Repository.ProductTypeRepo;
import nguyen.shoestore.common.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    private final ProductTypeRepo productTypeRepo;
    private final ItemRepo itemRepo;

    public ProductTypeService(ProductTypeRepo productTypeRepo, ItemRepo itemRepo) {
        this.productTypeRepo = productTypeRepo;
        this.itemRepo = itemRepo;
    }
    public List<ProductType> getAllProductTypes() {
        return productTypeRepo.findAll();
    }
    public Optional<ProductType> findProductTypeById(Integer typeId) {
        return productTypeRepo.findById(typeId);
    }
    public ProductType findProductTypeByName(String typeName) {
        return productTypeRepo.getByTypeName(typeName);
    }
    @Transactional
    public ResponseDTO AddProductType(String typeName){
        ResponseDTO responseDTO = new ResponseDTO();
        ProductType productType = productTypeRepo.getByTypeName(typeName);
        Assert.isNull(productType, MessageUtils.getMessage("error.notfound",typeName));
        ProductType productType1 = new ProductType();
        productType1.setTypeName(typeName);
        productTypeRepo.save(productType1);
        return responseDTO;
    }
    @Transactional
    public ResponseDTO DeleteProductType(Integer typeId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductType productType = productTypeRepo.getById(typeId);
        Assert.notNull(productType, MessageUtils.getMessage("error.notfound",typeId));
        List<Item> items = itemRepo.getByTypeId(typeId);
        for(Item i : items){
            i.setStatus(0);
            itemRepo.save(i);
        }
        productTypeRepo.delete(productType);
        return responseDTO;
    }
}
