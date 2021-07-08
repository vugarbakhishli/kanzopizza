package az.crbn.kanzopizza.ms.customer.web.rest;

import static org.springframework.http.HttpStatus.CREATED;

import az.crbn.kanzopizza.ms.customer.service.AddressService;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressCreateReqDto;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/v1")
    public ResponseEntity<AddressResDto> create(@RequestBody @Validated AddressCreateReqDto reqDto) {
        return ResponseEntity.status(CREATED).body(addressService.create(reqDto));
    }

    @GetMapping("/v1/{uuid}")
    public ResponseEntity<AddressResDto> getByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(addressService.getByUuid(uuid));
    }

    @DeleteMapping("/v1/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        addressService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
