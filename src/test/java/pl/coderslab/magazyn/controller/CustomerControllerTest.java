package pl.coderslab.magazyn.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.service.CustomerDetailsService;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.OrderService;
import pl.coderslab.magazyn.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @Mock
    private CustomerDetailsService customerDetailsService;
    @Mock
    private UserService userService;
    @Mock
    private OrderService orderService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(customerService, customerDetailsService, userService, orderService)).build();
    }

    @Test
    public void testHomePageCustomer() throws Exception {
        Customer customer = new Customer();
        List<Order> orderList = new ArrayList<>();
        when(customerService.getCurrentCustomerObject()).thenReturn(customer);
        when(orderService.getAllOrdersByCustomerIdSortedByDate()).thenReturn(orderList);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/homepage"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/homepageCustomer"))
                .andExpect(MockMvcResultMatchers.model().attribute("customer", customer))
                .andExpect(MockMvcResultMatchers.model().attribute("orderList", orderList));
    }
    @Test
    public void testEditCustomerDetails() throws Exception {
        Customer customer = new Customer();
        CustomerDetails customerDetails = new CustomerDetails();
        when(customerService.getCurrentCustomerObject()).thenReturn(customer);
        when(customerService.getCurrentCustomerDetails()).thenReturn(customerDetails);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/edit-details"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/editDetails"))
                .andExpect(MockMvcResultMatchers.model().attribute("customer", customer))
                .andExpect(MockMvcResultMatchers.model().attribute("editForm", customerDetails));
    }

}
























