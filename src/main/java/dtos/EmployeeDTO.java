package dtos;

import entities.Employee;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {

    private long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();

    }

    public static List<EmployeeDTO> getDtos(List<Employee> ems){
        List<EmployeeDTO> emDtos = new ArrayList();
        ems.forEach(em->emDtos.add(new EmployeeDTO(em)));
        return emDtos;
    }
}
