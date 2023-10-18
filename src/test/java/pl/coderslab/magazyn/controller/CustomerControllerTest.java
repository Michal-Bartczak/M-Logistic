package pl.coderslab.magazyn.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.ShipmentDimensions;
import pl.coderslab.magazyn.service.CustomerDetailsService;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.OrderService;
import pl.coderslab.magazyn.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(model().attribute("customer", customer))
                .andExpect(model().attribute("orderList", orderList));
    }

    @Test
    public void testEditCustomerDetails() throws Exception {
        Customer customer = new Customer();
        CustomerDetails customerDetails = new CustomerDetails();
        when(customerService.getCurrentCustomerObject()).thenReturn(customer);
        when(customerService.getCurrentCustomerDetails()).thenReturn(customerDetails);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/edit-details"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/editDetails"))
                .andExpect(model().attribute("customer", customer))
                .andExpect(model().attribute("editForm", customerDetails));
    }

    @Test
    public void testSaveCustomerDetails_WithBindingResult() throws Exception {
        CustomerDetails customerDetails = new CustomerDetails();

        // Ustaw dane formularza, które prowadzą do błędów walidacji
        customerDetails.setNameCompanySender("");  // Pusty łańcuch
        customerDetails.setStreetSender("test");

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/edit-details")
                        .flashAttr("editForm", customerDetails)
                        .param("param1", customerDetails.getNameCompanySender())
                        .param("param2", customerDetails.getStreetSender()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/editDetails"))
                .andExpect(model().attributeHasErrors("editForm")); // Oczekujemy błędów walidacji
    }

    @Test
    public void testSaveCustomerDetails_WithoutBindingResult() throws Exception {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCitySender("test");
        customerDetails.setStreetSender("test");

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/edit-details")
                        .flashAttr("editForm", customerDetails)
                        .param("param1", customerDetails.getStreetSender())
                        .param("param2", customerDetails.getCitySender()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/edit-details?update=true"));

    }

    @Test
    public void testEditPassword() throws Exception {
        Customer customer = new Customer();
        customer.setUsername("test");
        when(customerService.getCurrentCustomerObject()).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/edit-password"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/editPassword"))
                .andExpect(model().attribute("customer", customer));
    }

    @Test
    public void testSaveNewPasswordWithMismatchedPasswords() throws Exception {
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";

        when(customerService.saveNewPasswordCustomer(oldPassword, newPassword)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/edit-password")
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/edit-password?save=false"));
    }

    @Test
    public void testSaveNewPassword_withoutErrorValidation() throws Exception {
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";

        when(customerService.saveNewPasswordCustomer(oldPassword, newPassword)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/edit-password")
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/edit-password?save=true"));
    }

    @Test
    public void testSendPackage() throws Exception {
        Customer customer = new Customer();
        ShipmentDimensions[] dimensions = ShipmentDimensions.values();

        when(customerService.getCurrentCustomerObject()).thenReturn(customer);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/send"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/sendPackage"))
                .andExpect(model().attribute("customer", customer))
                .andExpect(model().attribute("dimensions", dimensions))
                .andReturn();


        Object detailsPackage = result.getModelAndView().getModel().get("detailsPackage");
        assertThat(detailsPackage).isNotNull().isInstanceOf(Order.class);
    }

    @Test
    public void saveSendPackage_withBindingResult() throws Exception {
        Order order = new Order();
        order.setProvider("providerTest");
        order.setCityRecipient("");

        Customer customer = new Customer();

        ShipmentDimensions[] dimensions = ShipmentDimensions.values();

        when(customerService.getCurrentCustomerObject()).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/send")
                        .param("param1", order.getProvider())
                        .param("param2", order.getCityRecipient()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/sendPackage"))
                .andExpect(model().attributeHasErrors("detailsPackage"))
                .andExpect(model().attribute("hasDetails", false))
                .andExpect(model().attribute("dimensions", dimensions))
                .andExpect(model().attribute("customer", customer));
    }

    @Test
    public void testSaveSendPackage_withoutBindingResult() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/send"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customer/send?sent=true"));

    }

}
























