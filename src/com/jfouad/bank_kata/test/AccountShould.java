import com.jfouad.bank_kata.src.Account;
import com.jfouad.bank_kata.src.StatementPrinter;
import com.jfouad.bank_kata.src.Transaction;
import com.jfouad.bank_kata.src.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {
    private Account account;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatementPrinter statementPrinter;

    @Before
    public void initialise() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void store_a_deposit_transaction() {
        account.deposit(100);
        verify(transactionRepository).addDeposit(100);
    }

    @Test
    public void store_a_withdrawal_transaction() {
        account.withdraw(100);
        verify(transactionRepository).addWithdrawal(100);
    }

    @Test
    public void print_a_statement() {
        List<Transaction> transactions = Arrays.asList(new Transaction());
        given(transactionRepository.addTransactions()).willReturn(transactions);
        account.printStatement();
        verify(statementPrinter).print(transactions);
    }
}
