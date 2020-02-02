package com.ongraph.greatsgames.dao.hibernate;

import com.ongraph.greatsgames.entities.CasinoContacts;
import org.springframework.stereotype.Repository;

/**
 * Created by ongraph on 26/9/18.
 */

@Repository
public class CasinoContactDao extends AbstractDao<CasinoContacts>{

    public CasinoContactDao(){super(CasinoContacts.class);}
}
