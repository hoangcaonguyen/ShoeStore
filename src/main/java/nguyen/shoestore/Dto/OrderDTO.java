package nguyen.shoestore.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Integer codeOrder;
    private String user;
    private String staff;
    private Integer itemId;
    private Integer quantity;
    private double money;
    private String process;
}
