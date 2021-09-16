package rest;

import com.google.gson.Gson;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import java.util.List;


@Path("/employees")
public class EmployeeResource {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Path("all")
    @Produces("application/json")
    public String getAllEmployees() {
        List<EmployeeDTO> employeeList = ef.getAllEmployees();

        return new Gson().toJson(employeeList);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getEmployeeById(@PathParam("id") int id) {
        EmployeeDTO employeeDTO = ef.getEmployeeById(id);

        return new Gson().toJson(employeeDTO);
    }

    @GET
    @Path("/highestpaid")
    @Produces("application/json")
    public String getHighestPaid() {
        List<EmployeeDTO> employeeDTO = ef.getEmployeesWithHighestSalary();

        return new Gson().toJson(employeeDTO);
    }

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    public String getEmployeeByName(@PathParam("name") String name) {
        List<EmployeeDTO> employeeDTO = ef.getEmployeesByName(name);

        return new Gson().toJson(employeeDTO);
    }
}