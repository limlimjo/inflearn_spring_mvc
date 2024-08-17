package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

//@Data 사용하는 것은 위험!
@Getter
@Setter
public class Item {
    //price와 quantity를 Integer로 쓰는 이유는 null값이 있을 수도 있기 때문
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    //기본 생성자
    public Item() {
    }

    //id를 제외한 생성자
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}