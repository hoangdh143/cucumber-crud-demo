package com.mitrais.demo.stepdefs;

import com.mitrais.demo.employee.Employee;
import com.mitrais.demo.employee.Phone;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class EmployeeSteps extends AbstractSteps {

    @Given("user want to (create/update) an employee with the following attributes")
    public void create_employee_entity(DataTable dataTable) {
        List<Employee> employeeList = dataTable.asList(Employee.class);
        super.testContext().setPayload(employeeList.get(0));
    }

    @And("with the following phone numbers")
    public void add_phone_numbers_to_employee_entity(DataTable dataTable) {
        List<Phone> phoneList = dataTable.asList(Phone.class);
        super.testContext().getPayload(Employee.class).setPhones(phoneList);
    }

    @When("user save the new employee {string}")
    public void save_new_employee(String entityState) {
            String url = "/v1/employees";
            post(url);
    }

    @Then("the save {string}")
    public void assert_save_result(String expectResult) {
        Response response = super.testContext().getResponse();
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
