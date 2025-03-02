package in.albertnegoro.restapi.service;

import in.albertnegoro.restapi.dto.ExpenseDTO;

import java.util.List;

/**
 * Service interface for Expense module
 *
 * @author Albert N
 */
public interface ExpenseService {
    /**
     * It will fetch the expenses from database
     *
     * @return list
     */
    List<ExpenseDTO> getAllExpenses();

    /**
     * It will fetch the expense details from database
     *
     * @param expenseId
     * @return ExpenseDTO
     */
    ExpenseDTO getExpenseByExpenseId(String expenseId);

    /**
     * It will delete the expense from database
     *
     * @param expenseId
     * @return void
     */
    void deleteExpenseByExpenseId(String expenseId);

    /**
     * It will save the expense details to database
     *
     * @param expenseDTO
     * @return ExpenseDTO
     */
    ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);
}
