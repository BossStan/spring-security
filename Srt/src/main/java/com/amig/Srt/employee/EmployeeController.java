package com.amig.Srt.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping()
    public List<Employee> employees(){

        return employeeService.getEmployee();
    }
    @PostMapping
    public void registerEmployee(@RequestBody Employee employee){

        employeeService.addEmployee(employee);
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable( "employeeId") Integer employeeId){


        employeeService.deleteEmployee(employeeId);
    }
    @PutMapping(path = "{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId")int employeeId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String address){

        System.out.println(employeeId+name+address);
        employeeService.updateEmployeee(employeeId,name,address);

    }
}

