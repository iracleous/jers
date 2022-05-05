package gr.quedgq.jers.client;

import gr.quedgq.jers.dto.EmployeeDto;
import gr.quedgq.jers.model.Employee;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class JerseyClientMain {
    private final static String URI = "http://localhost:8080/jers_war_exploded/api/employee";

    public static void main(String[] args) {
    //    testPost();
        testGet();

    }

    static void testPost() {
        WebTarget target = ClientBuilder.newClient()
                .target(URI);
        //     .queryParam("input", "hello");

        EmployeeDto employeeSend = new EmployeeDto();
        employeeSend.setName("Nikolaos");
        EmployeeDto employee = target.request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(employeeSend), EmployeeDto.class);
        System.out.println(employee);
    }


    static void testGet() {
        WebTarget target = ClientBuilder.newClient()
                .target(URI);
           //     .queryParam("input", "hello");

        String employeeId = "1004";
        EmployeeDto employee = target.path(employeeId).request()
                .accept(MediaType.APPLICATION_JSON)
                .get(EmployeeDto.class);
        System.out.println(employee);
    }
}
