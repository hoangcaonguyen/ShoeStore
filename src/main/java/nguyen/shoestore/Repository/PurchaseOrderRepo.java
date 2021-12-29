package nguyen.shoestore.Repository;

import nguyen.shoestore.Entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> getByIdUser (Integer idUser);
    @Query("select o from PurchaseOrder o where o.idUser in (SELECT id FROM User where roleId = 1 and fullName =: user)")
    List<PurchaseOrder> getByUser (String user);
    List<PurchaseOrder> getByIdStaff (Integer idStaff);
    @Query("select o from PurchaseOrder o where o.idStaff in (SELECT id FROM User where roleId = 2  and fullName =: user)")
    List<PurchaseOrder> getByStaff (String staff);
    List<PurchaseOrder> getByItemId (Integer itemId);
    List<PurchaseOrder> getByProcessId (Integer processId);
    @Query("select o from PurchaseOrder o where o.processId in (SELECT processId FROM Process where processName =: process)")
    List<PurchaseOrder> getByProcess (String process);
    PurchaseOrder getByCreateTime (LocalDateTime createTime);
}
