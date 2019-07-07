package study.mmbuytradeservice.product.entity;

import lombok.Data;

@Data
public class PageSearch {

    private int pageNumber;
    private int pageSize;
    private String searchContent;
}
