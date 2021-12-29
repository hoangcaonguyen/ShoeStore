package nguyen.shoestore.Service;

import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Entity.Color;
import nguyen.shoestore.Entity.Item;
import nguyen.shoestore.Repository.ColorRepo;
import nguyen.shoestore.Repository.ItemRepo;
import nguyen.shoestore.Repository.PurchaseOrderRepo;
import nguyen.shoestore.common.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepo colorRepo;
    private final ItemRepo itemRepo;
    private final PurchaseOrderRepo purchaseOrderRepo;

    public ColorService(ColorRepo colorRepo, ItemRepo itemRepo, PurchaseOrderRepo purchaseOrderRepo) {
        this.colorRepo = colorRepo;
        this.itemRepo = itemRepo;
        this.purchaseOrderRepo = purchaseOrderRepo;
    }
    public List<Color> getAllColors() {
        return colorRepo.findAll();
    }
    public List<Color> getAllColorActive() {
        return colorRepo.findByStatus(1);
    }
    public List<Color> getAllColorsUnActive() {
        return colorRepo.findByStatus(0);
    }
    public Optional<Color> findColorById(Integer colorId) {
        return colorRepo.findById(colorId);
    }
    public Color findColorByName(String colorName){
        return colorRepo.getByColorName(colorName);
    }
    @Transactional
    public ResponseDTO AddColor(String colorName){
        ResponseDTO responseDTO = new ResponseDTO();
        Color color = colorRepo.getByColorName(colorName);
        Assert.isNull(color, MessageUtils.getMessage("error.notfound",colorName));
        colorRepo.save(color);
        return responseDTO;
    }
    @Transactional
    public ResponseDTO DeleteColor(Integer colorId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Color color = colorRepo.getById(colorId);
        Assert.notNull(color, MessageUtils.getMessage("error.notfound",colorId));
        List<Item> items = itemRepo.getByColorId(colorId);
        for(Item i : items){
            i.setStatus(0);
            itemRepo.save(i);
        }
        colorRepo.delete(color);
        return responseDTO;
    }
}
