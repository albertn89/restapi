package in.albertnegoro.restapi.service;

import in.albertnegoro.restapi.dto.ExpenseDTO;

import java.util.List;

/**
 * Service interface for Expense module
 * @author Albert N
 */
public interface ExpenseService {
    /**
     * It will fetch the expenses from database
     * @return list
     */
    List<ExpenseDTO> getAllExpenses();

}
