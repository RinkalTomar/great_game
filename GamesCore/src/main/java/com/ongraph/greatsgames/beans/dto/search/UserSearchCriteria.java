package com.ongraph.greatsgames.beans.dto.search;

import com.ongraph.greatsgames.enums.Enumeration.UserAccountStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchCriteria extends AbstractSearchCriteria {

    UserAccountStatus status;
}
