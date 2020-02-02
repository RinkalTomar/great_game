package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.beans.dto.search.TransactionSearchCriteria;
import com.ongraph.greatsgames.beans.request.UITransaction;
import com.ongraph.greatsgames.entities.Cashbox;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

public interface ITransactionService {

    //TODO : ADD Transaction
    TransactionsBean addNewTransaction(UITransaction bean, Enumeration.TransactionType type) throws Exception;
    List<TransactionsBean> addNewTransaction(List<TransactionsBean> beans, Boolean isVerified) throws Exception;

    TransactionsBean doTransfer(UITransaction body) throws Exception;
    TransactionsBean doCredit(TransactionsBean body) throws Exception;
    TransactionsBean doDebit(TransactionsBean body) throws Exception;
    TransactionsBean doInitial(TransactionsBean body) throws Exception;
    List<TransactionsBean> getTransactionsByCashboxId(Long cashBoxId) throws Exception;

    List<TransactionsBean> getTransactionByTransactionId(TransactionSearchCriteria searchCriteria)throws Exception;
}
