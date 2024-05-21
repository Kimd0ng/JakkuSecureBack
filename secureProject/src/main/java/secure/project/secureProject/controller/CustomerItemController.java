package secure.project.secureProject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import secure.project.secureProject.dto.request.BasketAddItemRequestDto;
import secure.project.secureProject.dto.request.UserIdRequestDto;
import secure.project.secureProject.dto.request.CustomerOrderItemRequestDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.service.CustomerItemService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerItemController {
    private final CustomerItemService customerItemService;

    @GetMapping("/item")
    public ResponseDto<Map<String, Object>> selectCustomerItem(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "latest", defaultValue = "desc") String latest,
            @RequestParam(name = "price", defaultValue = "desc") String price,
            @RequestParam(name = "searchName", required = false) String searchName
    ){

        return new ResponseDto<>(customerItemService.selectCustomerItem(page, size, latest, price, searchName));
    }

    @PostMapping("/addItem")
    public ResponseDto<Boolean> addItemToBasket(
            @Valid @RequestBody BasketAddItemRequestDto basketAddItem
            ) {
        return new ResponseDto<>(customerItemService.addItemToBasket(basketAddItem));
    }

    @GetMapping("/basket/point")
    public ResponseDto<Long> selectUserPoint(
    ) {
        return new ResponseDto<>(customerItemService.selectUserPoint());
    }

    @GetMapping("/basket")
    public ResponseDto<Map<String, Object>> selectBasketItem(
            ) {
        return new ResponseDto<>(customerItemService.selectBasketItem());
    }

    @DeleteMapping("/basket/delete/{itemId}")
    public ResponseDto<Boolean> basketItemDelete(
            @PathVariable Long itemId,
            @Valid @RequestBody UserIdRequestDto userIdReqeustDto
    ) {
        return new ResponseDto<>(customerItemService.basketItemDelete(itemId,userIdReqeustDto));
    }

    @PatchMapping("/payment")
    public ResponseDto<Boolean> paymentItem(
            @RequestBody List<CustomerOrderItemRequestDto> customerOrderItemRequestDto
            ) {
        return new ResponseDto<>(customerItemService.paymentItem(customerOrderItemRequestDto));
    }

    @GetMapping("/history")
    public ResponseDto<Map<String, Object>> selectHistoryItem(
            @RequestParam(name = "latest", defaultValue = "desc") String latest,
            @RequestParam(name = "status", defaultValue = "desc") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size
            ) {
        return new ResponseDto<>(customerItemService.selectHistroyItem(page, size, latest, status));
    }

    @GetMapping("/history/detail/{orderId}")
    public ResponseDto<Map<String, Object>> selectHistoryDetailItem(
            @PathVariable Long orderId
    ) {
        return new ResponseDto<>(customerItemService.selectHistoryDetailItem(orderId));
    }

    @PatchMapping("/refund/{orderId}")
    public ResponseDto<Boolean> refundOrder(
            @PathVariable Long orderId,
            @RequestBody UserIdRequestDto userIdReqeustDto
    ) {
        return new ResponseDto<>(customerItemService.refundOrder(userIdReqeustDto, orderId));
    }
}
