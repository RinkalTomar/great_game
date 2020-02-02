package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.beans.dto.search.CashBoxSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.CashBoxConverter;
import com.ongraph.greatsgames.dao.hibernate.CashBoxDao;
import com.ongraph.greatsgames.dao.hibernate.UserDao;
import com.ongraph.greatsgames.entities.Cashbox;
import com.ongraph.greatsgames.entities.Transactions;
import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import com.ongraph.greatsgames.enums.Enumeration.ResultType;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.message.MessageProperties;
import com.ongraph.greatsgames.services.ICashBoxService;
import com.ongraph.greatsgames.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;



@Service
@Transactional(readOnly = true)
public class CashBoxServiceImpl extends AbstractService implements ICashBoxService {

    @Autowired
    CashBoxConverter _cashBoxConverter;

    @Autowired
    UserDao _userDao;

    @Autowired
    MessageProperties _message;

    @Autowired
    CashBoxDao _cashboxRepository;

    @Autowired
    ITransactionService _transactionService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, readOnly = false)
    public Long createCashBox(CashBoxBean bean) throws Exception {

        if (!_userDao.existsById(bean.getUserId()))
            throw new BadRequestException(_message.getMessage("no.valid.user"));

        Cashbox cashbox = _cashBoxConverter.getEntityFromBean(bean);
        _cashboxRepository.save(cashbox);

        BigDecimal initialBalance = BigDecimal.ZERO;

        if (!bean.getTransactions().isEmpty()) {

                TransactionsBean transactionsBean = bean.getTransactions().get(0);
                transactionsBean.setCashboxId(cashbox.getId());
                transactionsBean.setType(TransactionType.INITIAL);
                initialBalance = initialBalance.add(transactionsBean.getAmount());
                transactionsBean.setUpdatedBalance(initialBalance);
            _transactionService.addNewTransaction(bean.getTransactions(), true);
        }
        cashbox.setBalance(initialBalance);
        _cashboxRepository.save(cashbox);

        return cashbox.getId();

    }

    @Override
    public void getAllCasBoxes(AbstractResponseBean responseBean, CashBoxSearchCriteria searchCriteria) throws Exception {

        validateSearchCriteria(searchCriteria);
        int count = _cashboxRepository.count(searchCriteria);
        List<CashBoxBean> beans = _cashBoxConverter.getBeansFromEntity(_cashboxRepository.search(searchCriteria, false));
        responseBean.setTotalResultsCount(count);
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setObjects(beans);
    }

    @Override
    public CashBoxBean getCashboxById(Long id) throws Exception {

        CashBoxSearchCriteria searchCriteria = new CashBoxSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        validateSearchCriteria(searchCriteria);
        if(_cashboxRepository.count(searchCriteria) > 0) {
            BigDecimal totalCr = new BigDecimal(0);
            BigDecimal totalDt=new BigDecimal(0);
            CashBoxBean cashBoxBeans =  _cashBoxConverter.getBeanFromEntity(_cashboxRepository.getById(id), ResultType.SELECTION);
            for(TransactionsBean transactionsBean : cashBoxBeans.getTransactions()){
                if(transactionsBean.getType().equals(TransactionType.INCOME) || transactionsBean.getType().equals(TransactionType.INITIAL))
                    totalCr = totalCr.add(transactionsBean.getAmount());
                if(transactionsBean.getType().equals(TransactionType.EXPENSE))
                    totalDt = totalDt.add(transactionsBean.getAmount().abs());
            }
            cashBoxBeans.setTotalExpense(totalDt);
            cashBoxBeans.setTotalIncome(totalCr);
            return cashBoxBeans;
        }
        throw new GreatGamesAuthorizationException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBalance(Long cashboxId) throws Exception {

        Cashbox entity = _cashboxRepository.getById(cashboxId);
        BigDecimal balance = entity.getBalance();
        for(Transactions txn : entity.getTransactionsList())
        {
            balance = balance.add(txn.getAmount());
        }
        entity.setBalance(balance);
        _cashboxRepository.save(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void remove(Long id) throws Exception {
        CashBoxSearchCriteria searchCriteria = new CashBoxSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        validateSearchCriteria(searchCriteria);
        if(_cashboxRepository.count(searchCriteria) > 0) {
            Cashbox entity = _cashboxRepository.getById(id);

            entity.setIsDeleted(true);
            _cashboxRepository.save(entity);
        }
    }

    @Override
    public Integer count(CashBoxSearchCriteria searchCriteria) throws Exception {
        validateSearchCriteria(searchCriteria);
        return  _cashboxRepository.count(searchCriteria);
    }

    @Override
    public Boolean validateCashBoxAccess(Long id) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public void updateCashBox(CashBoxBean cashBoxBean) throws Exception {
        Cashbox cashbox = _cashBoxConverter.getEntityFromBean(cashBoxBean);
        _cashboxRepository.getSession().merge(cashbox);
    }

    @Override
    @Transactional
    public void update(CashBoxBean cashBoxBean, AbstractResponseBean responseBean) {
        Cashbox cashbox = _cashboxRepository.getById(cashBoxBean.getId());
        cashbox.setNote(cashBoxBean.getNote());
        cashbox.setName(cashBoxBean.getName());
        _cashboxRepository.getSession().merge(cashbox);
        responseBean.setSuccess(true);
    }


    private void validateSearchCriteria(CashBoxSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("CASHBOX_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();
            if(searchCriteria.getSelectedUserId().isEmpty())
            {
                searchCriteria.getSelectedUserId().add(logg_in_user);
            }
            else
            {
                if(!searchCriteria.getSelectedUserId().contains(logg_in_user))
                    throw new GreatGamesAuthorizationException();
                else{
                    //Remove additional Ids which are not accessible to the current user.
                    searchCriteria.getSelectedUserId().removeIf(i -> i != logg_in_user);
                }

                if(searchCriteria.getSelectedUserId().isEmpty())
                    throw new GreatGamesAuthorizationException();
            }

        }
    }



}
