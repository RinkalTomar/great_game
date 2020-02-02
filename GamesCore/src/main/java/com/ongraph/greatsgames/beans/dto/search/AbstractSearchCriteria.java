package com.ongraph.greatsgames.beans.dto.search;

import com.ongraph.greatsgames.enums.Enumeration.ResultType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
public class AbstractSearchCriteria {

    private List<Long> selectedIds = new ArrayList<>();

    private String searchKeyword;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String sortBy;
    private String sortOrder = "ASC";

    private Integer pageNumber = 0;
    private Integer resultPerPage = 100000;

    private ResultType resultType = ResultType.FULL;

    private boolean reports = false;
}
