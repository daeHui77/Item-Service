package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor//final인 레포지터리 생성자를 생성
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item();

        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item")Item item){

        itemRepository.save(item);
        return "basic/item";
    }
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){//v2와v3는 같다

        itemRepository.save(item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item){ //스트링 형태면 모델어트리뷰트의 생략을 알아볼 수 있음, 객체를 기억이 안남

        itemRepository.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}")//PathVariable의 itemId가 Mapping에 들어간다.
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
    @PostConstruct//데스트데이터 미리 넣어줌 컴파일 단계에서 들어감
    public void init(){
        itemRepository.save(new Item("ItemA",10000,10));
        itemRepository.save(new Item("ItemB",20000,20));
    }

}
