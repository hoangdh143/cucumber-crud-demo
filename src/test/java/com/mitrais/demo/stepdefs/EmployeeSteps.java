package com.mitrais.demo.stepdefs;

import com.mitrais.demo.employee.Employee;
import com.mitrais.demo.employee.Phone;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class EmployeeSteps {
    @LocalServerPort
    private int port;

    private Employee employee;
    private RequestSpecification requestSpecification;
    private Response response;

    @Given("user want to create an employee with the following attributes")
    public void create_employee_entity(DataTable dataTable) {
        List<Employee> employeeList = dataTable.asList(Employee.class);
        employee = employeeList.get(0);
    }

    @And("with the following phone numbers")
    public void add_phone_numbers_to_employee_entity(DataTable dataTable) {
        List<Phone> phoneList = dataTable.asList(Phone.class);
        employee.setPhones(phoneList);
    }

    @When("user save the new employee {string}")
    public void save_new_employee(String entityState) {
        if (entityState.equalsIgnoreCase("WITH ALL REQUIRED FIELDS")) {
            String baseUri = "http://localhost:"+ port +"/v1/employees";
            response = given().contentType(ContentType.JSON).body(employee).post(baseUri);
        }
    }

    @Then("the save {string}")
    public void assert_save_result(String expectResult) {
        switch (expectResult) {
            case "IS SUCCESSFUL":
                assertThat(response.statusCode()).isIn(200, 201);
                break;
            case "FAILS":
                assertThat(response.statusCode()).isBetween(400, 504);
                break;
            default:
                fail("Unexpected error");
        }
    }
}
