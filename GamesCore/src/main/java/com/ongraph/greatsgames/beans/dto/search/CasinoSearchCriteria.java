package com.ongraph.greatsgames.beans.dto.search;

import com.ongraph.greatsgames.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ongraph on 18/9/18.
 */

@Setter
@Getter
public class CasinoSearchCriteria extends AbstractSearchCriteria{

    private Enumeration.CasinoStatus status;
}
