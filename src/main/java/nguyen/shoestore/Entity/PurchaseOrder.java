package nguyen.shoestore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Purchase_Order")
public class PurchaseOrder {
    @Id
    @Column(name = "code_order")
    private Integer codeOrder;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "id_staff")
    private Integer idStaff;
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "purchase_quantity")
    private int purchaseQuantity;
    @Column(name = "process_id")
    private Integer processId;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "money")
    private double money;
    @Column(name = "status")
    private Integer status;
}
