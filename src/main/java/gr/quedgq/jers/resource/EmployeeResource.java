package gr.quedgq.jers.resource;

import gr.quedgq.jers.dto.EmployeeDto;
import gr.quedgq.jers.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/employee")
public class EmployeeResource {
    @Inject
    EmployeeService employeeService;
    @Path("/ping")
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @Path("/{employeeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDto getEmployee(@PathParam("employeeId") int employeeId) {
        return employeeService.readEmployee(employeeId);
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDto getEmployee(EmployeeDto employee) {
        return employeeService.saveEmployee(employee);
    }


}