package nguyen.shoestore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "color_id")
    private Integer colorId;
    @Column(name = "size")
    private int size;
    @Column(name = "num_items")
    private int numItems;
    @Column(name = "type_id")
    private Integer typeId;
    @Column(name = "sale")
    private int sale;
    @Column(name = "status")
    private Integer status;
}
