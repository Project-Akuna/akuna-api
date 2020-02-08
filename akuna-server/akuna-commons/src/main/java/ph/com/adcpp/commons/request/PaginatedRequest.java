package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedRequest {

    private Integer pageIndex;
    private Integer pageSize;
    private String sortKey;
    private String sortValue;
}
