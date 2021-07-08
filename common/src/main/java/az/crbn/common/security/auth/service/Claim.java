package az.crbn.common.security.auth.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {

    private String key;
    private String claim;

}
