package nguyen.shoestore.Service;


import nguyen.shoestore.Dto.ItemDTO;
import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Entity.Item;
import nguyen.shoestore.Repository.*;
import nguyen.shoestore.common.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepo itemRepo;
    private final ProductRepo productRepo;
    private final ColorRepo colorRepo;
    private final ProductTypeRepo productTypeRepo;

    public ItemService(ItemRepo itemRepo, ProductRepo productRepo, ColorRepo colorRepo, ProductTypeRepo productTypeRepo) {
        this.itemRepo = itemRepo;
        this.productRepo = productRepo;
        this.colorRepo = colorRepo;
        this.productTypeRepo = productTypeRepo;
    }
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }
    public List<Item> getAllItemsActive() {
        return itemRepo.getByStatus(1);
    }
    public List<Item> getAllItemsUnActive() {
        return itemRepo.getByStatus(0);
    }
    public Optional<Item> findById(Integer itemId) {
        return itemRepo.findById(itemId);
    }
    public List<Item> findByProductName(String productName) {
        return itemRepo.getByProductName(productName);
    }
    public List<Item> findByColorName(String colorName) {
        return itemRepo.getByColorName(colorName);
    }
    public List<Item> findBySize(Integer size) {
        return itemRepo.getBySize(size);
    }
    public List<Item> findByNumItems(Integer numItems) {
        return itemRepo.getByNumItems(numItems);
    }
    public List<Item> findByType(String type) {
        return itemRepo.getByType(type);
    }
    public List<Item> findBySale(Integer sale) {
        return itemRepo.getBySale(sale);
    }
    @Transactional
    public ResponseDTO AddItem (ItemDTO itemDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        Item item = new Item();
        Assert.notNull(productRepo.getByProductName(itemDTO.getProductName()),
                MessageUtils.getMessage("error.notfound",itemDTO.getProductName()));
        Assert.notNull(colorRepo.getByColorName(itemDTO.getColorName()),
                MessageUtils.getMessage("error.notfound",itemDTO.getColorName()));
        Assert.notNull(productTypeRepo.getByTypeName(itemDTO.getType()),
                MessageUtils.getMessage("error.notfound",itemDTO.getType()));
        List<Item> items = itemRepo.getByProductIdAndColorIdAndTypeId(
                productRepo.getByProductName(itemDTO.getProductName()).getProductId(),
                colorRepo.getByColorName(itemDTO.getColorName()).getColorId(),
                productTypeRepo.getByTypeName(itemDTO.getType()).getTypeId()
        );
        if(items == null){
            setItem(itemDTO, item);
        }else {
            for(Item i : items){
                if(i.getStatus().equals(1)){
                    Assert.isTrue(itemDTO.getSize()!=i.getSize(),MessageUtils.getMessage("error.input.exist",itemDTO.getSize()));
                    setItem(itemDTO, item);
                }else if (i.getStatus().equals(0)){
                    setItem(itemDTO, item);
                }
            }
        }
        return responseDTO;
    }

    public void setItem(ItemDTO itemDTO, Item item) {
        item.setProductId(productRepo.getByProductName(itemDTO.getProductName()).getProductId());
        item.setColorId(colorRepo.getByColorName(itemDTO.getColorName()).getColorId());
        item.setSize(itemDTO.getSize());
        item.setNumItems(itemDTO.getNumItems());
        item.setTypeId(productTypeRepo.getByTypeName(itemDTO.getType()).getTypeId());
        item.setSale(itemDTO.getSale());
        itemRepo.save(item);
    }

    @Transactional
    public ResponseDTO DeleteItem(Integer itemId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Item item = itemRepo.getById(itemId);
        Assert.notNull(item, MessageUtils.getMessage("error.notfound",itemId));
        item.setStatus(0);
        itemRepo.save(item);
        return responseDTO;
    }
    @Transactional
    public ResponseDTO UpdateItem(ItemDTO itemDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        Item item = itemRepo.getById(itemDTO.getItemId());
        Assert.notNull(item, MessageUtils.getMessage("error.notfound",itemDTO.getItemId()));
        setItem(itemDTO, item);
        return responseDTO;
    }
}
