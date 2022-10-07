package module6.backend.service;

import module6.backend.entity.employee.Employee;

public interface IEmployeeService {
    //NhiVP code tim employee bang code
    Employee findEmployeeByCode(String code);
    //NhiVP code tim employee da co account
    Employee findExistEmployeeHasAccount(String code);
    //NhiVP code tim employee chua co account
    Employee findExistEmployeeDontHasAccount(String code);
}
