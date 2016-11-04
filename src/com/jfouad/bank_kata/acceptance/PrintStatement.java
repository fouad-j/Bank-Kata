import com.jfouad.bank_kata.src.Console;
import com.jfouad.bank_kata.src.Account;
import com.jfouad.bank_kata.src.StatementPrinter;
import com.jfouad.bank_kata.src.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatement {
    private Account account;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    @Mock private Console console;

    @Before
    public void initialise() {
        transactionRepository = new TransactionRepository();
        statementPrinter = new StatementPrinter();
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void print_statement_containing_all_transactions(){
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE || AMOUNT || BALANCE");
        inOrder.verify(console).printLine("14/01/2016 || 500.00 || 1400.00");
        inOrder.verify(console).printLine("13/01/2016 || -100.00 || 900.00");
        inOrder.verify(console).printLine("10/01/2016 || 1000.00 || 1000.00");
    }
}
