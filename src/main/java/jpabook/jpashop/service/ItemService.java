package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 변경감지 방법 , dirty checking
     * @param itemId
     * @param param
     */
    @Transactional      // 이것에 의해 커밋이 일어나고 플러쉬가 됨
    public void updateItem(Long itemId, Book param) {
        Item findItem = itemRepository.findOne(itemId); // 애는 영속상태
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
