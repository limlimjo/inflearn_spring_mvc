package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //실무에서는 HashMap 쓰면x, ConcurrentHashMap 사용o
    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    //상품 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //상품 개별 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    //상품 전체 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //상품 수정
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    //store 날리기
    public void clearStore() {
        store.clear();
    }

} 