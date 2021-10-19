package com.amig.Srt.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    public List<Employee> getEmployee(){

        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {

        Optional<Employee> employeeByAddress=employeeRepository.findEmployeeByAddress(employee.getAddress());
        if (employeeByAddress.isPresent()){
            throw new IllegalStateException("address exists");
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {

        boolean avail=employeeRepository.existsById(employeeId);
        if(!avail){
            throw  new IllegalStateException("employee with Id not available");
        }
        employeeRepository.deleteById(employeeId);

    }

    @Transactional
    public void updateEmployeee(int employeeId, String name, String address) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new IllegalStateException("employee with employee id "+employeeId+"does not exist"));

        if(name !=null&& name.length()>0&& !Objects.equals(employee.getName(),name)){
            employee.setName(name);
        }
        if(address !=null&&address.length()>0 && !Objects.equals(employee.getAddress(),address)){
            employee.setAddress(address);
        }


    }
}



