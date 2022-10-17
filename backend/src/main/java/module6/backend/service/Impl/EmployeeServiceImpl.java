package module6.backend.service.Impl;

import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.repository.IPositionRepository;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IPositionRepository positionRepository;


    @Override
    public void adminUpdateEmployee(String employeeName, String employeeCode, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId, Long employeeId){
        employeeRepository.adminUpdateEmployee(employeeName,employeeCode,employeeAvatar,employeeDateOfBirth,employeeGender,employeeAddress,employeePhone,employeeSalary,employeePositionId,employeeId);
    }

    @Override
    public void saveEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId){
        employeeRepository.saveEmployee(employeeCode,employeeName,employeeAvatar,employeeDateOfBirth,employeeGender,employeeAddress,employeePhone,employeeSalary,employeePositionId);
    }
    @Override
    public List<Position> getAllPosition(){ return positionRepository.findAllPosition();}

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
