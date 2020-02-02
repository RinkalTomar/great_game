package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.TransactionCategoryBean;

import java.util.List;

public interface ITransactionCategoryService {

    public List<TransactionCategoryBean> getAllCategories() throws Exception;
}
