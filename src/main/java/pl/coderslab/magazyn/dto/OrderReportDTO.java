package pl.coderslab.magazyn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderReportDTO {

    private Long totalOrders;
    private Long ordersInWarehouse;
    private Long ordersInDelivery;
    private Long ordersDelivered;
    private Long totalUsers;
    private Long totalDrivers;


}
