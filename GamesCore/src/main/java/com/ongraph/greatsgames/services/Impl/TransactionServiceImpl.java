package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.beans.dto.search.CashBoxSearchCriteria;
import com.ongraph.greatsgames.beans.dto.search.TransactionSearchCriteria;
import com.ongraph.greatsgames.beans.request.UITransaction;
import com.ongraph.greatsgames.converters.TransactionsConverter;
import com.ongraph.greatsgames.dao.TransactionRepository;
import com.ongraph.greatsgames.dao.hibernate.CashBoxDao;
import com.ongraph.greatsgames.dao.hibernate.TransactionDao;
import com.ongraph.greatsgames.entities.Transactions;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.exception.IncompatibleCategoryException;
import com.ongraph.greatsgames.services.ICashBoxService;
import com.ongraph.greatsgames.services.ITransactionService;
import com.ongraph.greatsgames.utils.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl extends AbstractService implements ITransactionService {

    @Autowired
    TransactionsConverter _transactionsConverter;

    @Autowired
    TransactionRepository _transactionRepository;

    @Autowired
    TransactionDao _transactionDao;

    @Autowired
    ICashBoxService _cashBoxService;

    @Autowired
    CashBoxDao _cashboxRepository;



    //TODO : ADD Transaction
    @Override
    public TransactionsBean addNewTransaction(UITransaction body, TransactionType type) throws Exception{

        switch (type)
        {
            case TRANSFER : doTransfer(body);
                            break;
            case INCOME :   doCredit(body.getTransaction());
                            break;
            case EXPENSE:   doDebit(body.getTransaction());
                            break;
            default: throw new BadRequestException("Undefined type of Transaction");

        }
        return body.getTransaction();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<TransactionsBean> addNewTransaction(List<TransactionsBean> beans, Boolean isVerified) {
        List<Transactions> entities = _transactionsConverter.getEntitiesFromBean(beans);

        for (Transactions tx : entities) {
            tx.setTransactionId(AppUtility.generateUUID());
        }
        _transactionRepository.saveAll(entities);

        return _transactionsConverter.getBeansFromEntities(entities);
    }

    @Override
    public TransactionsBean doTransfer(@Valid UITransaction body) throws Exception {
        CashBoxSearchCriteria cashBoxSearchCriteria = new CashBoxSearchCriteria();
        cashBoxSearchCriteria.getSelectedIds().add(body.getToCashbox());
        cashBoxSearchCriteria.getSelectedIds().add(body.getFromCashbox());
        if (_cashBoxService.count(cashBoxSearchCriteria) > 0)
        {
                TransactionsBean transactions = body.getTransaction();
                transactions.setCashboxId(body.getToCashbox());
                transactions.setTransactionNumber(new Date().getTime());
                doCredit(transactions);

                transactions.setCashboxId(body.getFromCashbox());
                doDebit(transactions);

        }
        return body.getTransaction();
    }

    @Override
    public TransactionsBean doCredit(TransactionsBean body) throws Exception {

        CashBoxSearchCriteria cashBoxSearchCriteria = new CashBoxSearchCriteria();
        cashBoxSearchCriteria.getSelectedIds().add(body.getCashboxId());
        if (_cashBoxService.count(cashBoxSearchCriteria) > 0)
        {
           Transactions transactions = _transactionsConverter.getEntityFromBean(body);
           //TODO : Check For Correct Transaction Category
           Boolean isExpense = transactions.getTxnCategory().getIsExpense();
            if(null == isExpense || isExpense != true)
           {
               BigDecimal amount = transactions.getAmount();
               transactions.setAmount(amount.abs());
               CashBoxBean cashBoxBean = _cashBoxService.getCashboxById(body.getCashboxId());
               BigDecimal updatedBalance = cashBoxBean.getBalance();
               updatedBalance = updatedBalance.add(amount);

               transactions.setType(TransactionType.INCOME);
               transactions.setUpdatedBalance(updatedBalance);
               transactions.setTransactionId(AppUtility.generateUUID());
               _transactionRepository.save(transactions);

               cashBoxBean.setBalance(updatedBalance);
               _cashBoxService.updateCashBox(cashBoxBean);

               return body;
           }
           else throw new IncompatibleCategoryException(_message.getMessage("expense.instead.income"));
        }
        else
            throw new GreatGamesAuthorizationException();
    }

    @Override
    public TransactionsBean doDebit(TransactionsBean body) throws Exception {
        CashBoxSearchCriteria cashBoxSearchCriteria = new CashBoxSearchCriteria();
        cashBoxSearchCriteria.getSelectedIds().add(body.getCashboxId());
        if (_cashBoxService.count(cashBoxSearchCriteria) > 0)
        {
            Transactions transactions = _transactionsConverter.getEntityFromBean(body);
            Boolean isExpense = transactions.getTxnCategory().getIsExpense();
            if(null == isExpense || isExpense == true)
            {
                BigDecimal amount = transactions.getAmount().abs();
                transactions.setAmount(amount.negate());
                transactions.setTransactionId(AppUtility.generateUUID());

                CashBoxBean cashBoxBean = _cashBoxService.getCashboxById(body.getCashboxId());
                BigDecimal updatedBalance =cashBoxBean.getBalance();
                updatedBalance = updatedBalance.subtract(amount);

                transactions.setType(TransactionType.EXPENSE);
                transactions.setUpdatedBalance(updatedBalance);

                _transactionRepository.save(transactions);

                cashBoxBean.setBalance(updatedBalance);
                _cashBoxService.updateCashBox(cashBoxBean);

                return body;
            }
            else throw new IncompatibleCategoryException(_message.getMessage("income.instead.expense"));
        }
        else
            throw new GreatGamesAuthorizationException();
    }

    @Override
    public TransactionsBean doInitial(TransactionsBean body) throws Exception {
        return null;
    }

    @Override
    public List<TransactionsBean> getTransactionsByCashboxId(Long cashBoxId) throws Exception {

        CashBoxBean bean = _cashBoxService.getCashboxById(cashBoxId);
        if(bean != null)
        {
            return bean.getTransactions();
        }
        else throw new BadRequestException("No Cashbox Available with the given Id.");

    }

    @Override
    public List<TransactionsBean> getTransactionByTransactionId(TransactionSearchCriteria searchCriteria) throws Exception {
        return  _transactionsConverter.getBeansFromEntities(_transactionDao.search(searchCriteria, false));

    }


}
