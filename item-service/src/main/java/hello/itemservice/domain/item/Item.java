package hello.itemservice.domain.item;

import lombok.Data;

@Data//lombok을 쓸 수 있게 해줌, 동적으로 쓰일때는 좋지 않다.
public class Item {
    private Long id;
    private String itemName;
    private Integer price;//int는 NULL이 안되지만 Integer는 NULL이 된다.
    private Integer quantity;

    public Item(){}

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
