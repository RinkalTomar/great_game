package com.ongraph.greatsgames.beans.dto.search;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CashBoxSearchCriteria extends AbstractSearchCriteria{

    List<Long> selectedUserId = new ArrayList<>();

}
