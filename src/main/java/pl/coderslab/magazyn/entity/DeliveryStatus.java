package pl.coderslab.magazyn.entity;

public enum DeliveryStatus {
    NIE_DOSTARCZONO(0),
    DOSTARCZONO(1);

    private final int value;

    DeliveryStatus(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
