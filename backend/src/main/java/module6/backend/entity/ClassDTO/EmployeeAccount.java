package module6.backend.entity.ClassDTO;

import module6.backend.entity.account.Account;
import module6.backend.entity.employee.Employee;

public class EmployeeAccount {
    private Employee employee;
    private Account account;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
