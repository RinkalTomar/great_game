package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.beans.dto.search.TransactionSearchCriteria;
import com.ongraph.greatsgames.beans.request.UITransaction;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.TransactionsApi;
import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.services.ICashBoxService;
import com.ongraph.greatsgames.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionApiController extends AbstractController implements TransactionsApi {

    @Autowired
    ITransactionService _transactionService;

    @Autowired
    ICashBoxService _cashBoxService;

    @Override
    @PreAuthorize("hasAuthority('TRANSACTION_WRITE')")
    public ResponseEntity<AbstractResponse> addTransaction(@Valid @RequestBody UITransaction body,
                                                           @RequestParam(required = true) TransactionType type) throws Exception {

        ResponseEntity<AbstractResponse> response = null;
        _transactionService.addNewTransaction(body, type);
        /*switch (type)
        {
            case TRANSFER : response = buildResponse(_transactionService.doTransfer(body), HttpStatus.CREATED);
                            break;
            case INCOME :   response = buildResponse(_transactionService.doCredit(body), HttpStatus.CREATED);
                            break;
            case EXPENSE:   response = buildResponse(_transactionService.doDebit(body), HttpStatus.CREATED);
                            break;
            default: throw new BadRequestException("Undefined type of Transaction");

        }*/
        return response;
    }

    @Override
    public ResponseEntity<AbstractResponse> getAll() throws Exception {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('TRANSACTION_READ')")
    public ResponseEntity<AbstractResponse> getTransactionsByCashBox(@PathVariable Long id) throws Exception {

        return buildResponse(_transactionService.getTransactionsByCashboxId(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    public ResponseEntity<AbstractResponse> getTransactionsByTransactionId(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {
        TransactionSearchCriteria searchCriteria = new TransactionSearchCriteria();
        parseCommonSearchParams(searchCriteria, searchParams);
        return buildResponse(_transactionService.getTransactionByTransactionId(searchCriteria),HttpStatus.OK);
    }
}
