package pl.coderslab.magazyn.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.DriverService;
import pl.coderslab.magazyn.service.EmployeeService;
import pl.coderslab.magazyn.service.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private CustomerService customerService;
    @Mock
    private DriverService driverService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(orderService, employeeService, customerService, driverService)).build();
    }

    @Test
    public void testGetHomepageEmployee() throws Exception {
        List<Driver> sampleListDriver = new ArrayList<>();
        Employee employee = new Employee();

        when(employeeService.getCurrentEmployeeObject()).thenReturn(employee);
        when(driverService.getAllDrivers()).thenReturn(sampleListDriver);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/homepage"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/homepageEmployee"))
                .andExpect(model().attribute("employee", employee))
                .andExpect(model().attribute("driversList", sampleListDriver));
    }

    @Test
    public void testEditPassword() throws Exception {
        Employee employee = new Employee();

        when(employeeService.getCurrentEmployeeObject()).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/edit-password"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/editPassword"))
                .andExpect(model().attribute("employee", employee));
    }

    @Test
    public void testSavePassword_withError() throws Exception {
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";

        when(employeeService.saveNewPasswordEmployee(oldPassword, newPassword)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/edit-password")
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employee/edit-password?save=false"));
    }
    @Test
    public void testSavePassword_withoutError() throws Exception {
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";

        when(employeeService.saveNewPasswordEmployee(oldPassword, newPassword)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/edit-password")
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employee/edit-password?save=true"));
    }
    @Test
    public void testShowAllCustomers() throws Exception {
        Employee employee = new Employee();
        List<Customer> sampleCustomers = new ArrayList<>();

        when(employeeService.getCurrentEmployeeObject()).thenReturn(employee);
        when(customerService.getAllCustomers()).thenReturn(sampleCustomers);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/users-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/customersList"))
                .andExpect(model().attribute("employee", employee))
                .andExpect(model().attribute("customers", sampleCustomers));
    }
    @Test
    public void testShowAllEmployees() throws Exception{
        Employee employee = new Employee();
        List<Employee> sampleEmployeesList = new ArrayList<>();
        List<Driver> sampleDriversList = new ArrayList<>();

        when(employeeService.getCurrentEmployeeObject()).thenReturn(employee);
        when(employeeService.findAllEmployees()).thenReturn(sampleEmployeesList);
        when(employeeService.findAllDrivers()).thenReturn(sampleDriversList);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/employees-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/employeesAndDrivers"))
                .andExpect(model().attribute("employeeOwn", employee))
                .andExpect(model().attribute("employees", sampleEmployeesList))
                .andExpect(model().attribute("drivers", sampleDriversList));
    }
    @Test
    public void testShowRaport() throws Exception{
        Employee employee = new Employee();

        when(employeeService.getCurrentEmployeeObject()).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/raport"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/orderRaport"))
                .andExpect(model().attribute("employee", employee));
    }

}
