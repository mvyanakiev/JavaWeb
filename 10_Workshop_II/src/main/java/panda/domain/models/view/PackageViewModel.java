package panda.domain.models.view;


import panda.domain.entities.Status;

import java.time.LocalDateTime;

public class PackageViewModel {

    private String id;
    private String description;
    private Double weight;
    private String shippingAddress;
    private Status status;
    private LocalDateTime estimatedDeliveryDate;
    private String recipient;

    public PackageViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return this.estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
