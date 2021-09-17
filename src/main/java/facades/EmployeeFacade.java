package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createEmployee(){
        Employee employee = new Employee("August","Kan noget",12321);
        Employee employee1 = new Employee("Alex","Er sød", 123213);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.persist(employee1);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    public EmployeeDTO getEmployeeById(int id){
        EntityManager em = emf.createEntityManager();
            try{
                TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.id =:id", Employee.class);
                query.setParameter("id", id);
                Employee employee = query.getSingleResult();
                EmployeeDTO employeeDTO = new EmployeeDTO(employee);
                return employeeDTO;
            }finally {
                em.close();
            }
    }

    public List<EmployeeDTO> getEmployeesByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.name =:name", Employee.class);
            query.setParameter("name", name);
            List<Employee> employee = query.getResultList();
            List<EmployeeDTO> employeeDTO =  EmployeeDTO.getDtos(employee);
            return employeeDTO;
        }finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getAllEmployees(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e",Employee.class);
            List<Employee> employees = query.getResultList();
            List<EmployeeDTO> employeeDTOList = EmployeeDTO.getDtos(employees);
            return  employeeDTOList;
        }finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getEmployeesWithHighestSalary(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX (e.salary) FROM Employee e)", Employee.class);
            List<Employee> employee = query.getResultList();
            List<EmployeeDTO> employeeDTO = EmployeeDTO.getDtos(employee);
            return employeeDTO;
        }finally {
            em.close();
        }
    }

}
