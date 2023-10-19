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
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.service.DriverService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DriverControllerTest {
    @Mock
    private DriverService driverService;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new DriverController(driverService)).build();
    }

    @Test
    public void testShowHomepage() throws Exception {
        Driver driver = new Driver();
        List<Order> sampleListOrder = new ArrayList<>();

        when(driverService.getCurrentDriverObject()).thenReturn(driver);
        when(driverService.getAllOrdersForCurrentDriver()).thenReturn(sampleListOrder);

        mockMvc.perform(MockMvcRequestBuilders.get("/driver/homepage"))
                .andExpect(status().isOk())
                .andExpect(view().name("driver/homepageDriver"))
                .andExpect(model().attribute("driver", driver))
                .andExpect(model().attribute("orderlist", sampleListOrder));
    }

    @Test
    public void testEditPasswordDriver() throws Exception {
        Driver driver = new Driver();

        when(driverService.getCurrentDriverObject()).thenReturn(driver);

        mockMvc.perform(MockMvcRequestBuilders.get("/driver/edit-password"))
                .andExpect(status().isOk())
                .andExpect(view().name("driver/editPassword"))
                .andExpect(model().attribute("driver", driver));

    }

    @Test
    public void testSaveNewPasswordDriver_withError() throws Exception {
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";

        when(driverService.saveNewPasswordDriver(oldPassword,newPassword)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/driver/edit-password")
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/driver/edit-password?update=false"));
    }
    @Test
    public void testSaveNewPasswordDriver_withoutError() throws Exception {
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";

        when(driverService.saveNewPasswordDriver(oldPassword,newPassword)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/driver/edit-password")
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name( "redirect:/driver/edit-password?update=true"));
    }
    @Test
    public void testGetRaport() throws Exception {
        Driver driver = new Driver();

        when(driverService.getCurrentDriverObject()).thenReturn(driver);

        mockMvc.perform(MockMvcRequestBuilders.get("/driver/raport"))
                .andExpect(status().isOk())
                .andExpect(view().name("driver/raportDriver"));
    }
}



















