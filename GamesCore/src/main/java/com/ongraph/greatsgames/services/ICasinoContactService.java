package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;

import java.util.List;

public interface ICasinoContactService {
    void createCasinoContacts(List<CasinoContactBean> casinoContactBean) throws Exception;
}
