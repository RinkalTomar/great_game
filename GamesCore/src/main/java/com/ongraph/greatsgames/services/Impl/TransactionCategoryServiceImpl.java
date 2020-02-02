package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.TransactionCategoryBean;
import com.ongraph.greatsgames.converters.TransactionCategoryConverter;
import com.ongraph.greatsgames.dao.TransactionCategoryRepository;
import com.ongraph.greatsgames.services.ITransactionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service
public class TransactionCategoryServiceImpl implements ITransactionCategoryService {

    @Autowired
    TransactionCategoryRepository transactionCategoryRepository;

    @Autowired
    TransactionCategoryConverter transactionCategoryConverter;

    @Override
    public List<TransactionCategoryBean> getAllCategories() throws Exception {

        List<TransactionCategoryBean> beans = transactionCategoryConverter
                .getBeansFromEntities(transactionCategoryRepository.findAll());

        return beans;
    }

}
