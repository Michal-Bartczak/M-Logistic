package pl.coderslab.magazyn.entity;

import pl.coderslab.magazyn.model.ShipmentDimensionProvider;

public enum ShipmentDimensions implements ShipmentDimensionProvider {
    HP,
    EUR;



    @Override
    public ShipmentDimensions[] getValue() {
        return ShipmentDimensions.values();
    }

}
