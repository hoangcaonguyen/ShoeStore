package nguyen.shoestore.Service;


import nguyen.shoestore.Dto.OrderDTO;
import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Entity.Item;
import nguyen.shoestore.Entity.PurchaseOrder;
import nguyen.shoestore.Repository.*;
import nguyen.shoestore.common.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ItemRepo itemRepo;
    private final ProcessRepo processRepo;

    public PurchaseOrderService(PurchaseOrderRepo orderRepo, UserRepo userRepo, ItemRepo itemRepo, ProcessRepo processRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.processRepo = processRepo;
    }
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return orderRepo.findAll();
    }
    public Optional<PurchaseOrder> findPurchaseOrderById(Integer codeOrder) {
        return orderRepo.findById(codeOrder);
    }
    public List<PurchaseOrder> findPurchaseOrderByUser(String user) {
        return orderRepo.getByUser(user);
    }
    public List<PurchaseOrder> findPurchaseOrderByStaff(String staff) {
        return orderRepo.getByStaff(staff);
    }
    public List<PurchaseOrder> findPurchaseOrderByItemId(Integer itemId) {
        return orderRepo.getByItemId(itemId);
    }

    public PurchaseOrder findPurchaseOrderByCreateTime(LocalDateTime dateTime){
        return orderRepo.getByCreateTime(dateTime);
    }
    public List<PurchaseOrder> findPurchaseOrderByProcess(String process){
        return orderRepo.getByProcess(process);
    }
    @Transactional
    public ResponseDTO AddOder (OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        Assert.notNull(userRepo.getByCostumersName(orderDTO.getUser()),
                MessageUtils.getMessage("error.notfound",orderDTO.getUser()));
        Assert.notNull(userRepo.getByStaffsName(orderDTO.getStaff()),
                MessageUtils.getMessage("error.notfound",orderDTO.getStaff()));
        Item item = itemRepo.getById(orderDTO.getItemId());
        Assert.notNull(item, MessageUtils.getMessage("error.notfound",orderDTO.getItemId()));
        Assert.notNull(processRepo.getByProcessName(orderDTO.getProcess()),
                MessageUtils.getMessage("error.notfound",orderDTO.getProcess()));
        setOrder(orderDTO, purchaseOrder);
        int oldNumber = item.getNumItems();
        item.setNumItems(oldNumber-orderDTO.getQuantity());
        itemRepo.save(item);
        orderRepo.save(purchaseOrder);
        return responseDTO;
    }

    public void setOrder(OrderDTO orderDTO,PurchaseOrder purchaseOrder ) {
        purchaseOrder.setIdUser(userRepo.getByCostumersName(orderDTO.getUser()).getId());
        purchaseOrder.setIdUser(userRepo.getByStaffsName(orderDTO.getUser()).getId());
        purchaseOrder.setItemId(orderDTO.getItemId());
        purchaseOrder.setPurchaseQuantity(orderDTO.getQuantity());
        purchaseOrder.setMoney(orderDTO.getMoney());
        purchaseOrder.setProcessId(processRepo.getByProcessName(orderDTO.getProcess()).getProcessId());
    }

    @Transactional
    public ResponseDTO DeleteOrder(Integer orderId) {
        ResponseDTO responseDTO = new ResponseDTO();
        PurchaseOrder purchaseOrder = orderRepo.getById(orderId);
        Assert.notNull(purchaseOrder, MessageUtils.getMessage("error.notfound",orderId));
        orderRepo.delete(purchaseOrder);
        return responseDTO;
    }
}
