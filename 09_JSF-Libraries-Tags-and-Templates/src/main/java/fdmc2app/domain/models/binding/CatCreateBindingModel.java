package fdmc2app.domain.models.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CatCreateBindingModel {

    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private String addedOn;
    private Boolean hasPassport;

    public CatCreateBindingModel() {
    }

    @NotNull
    @Size(min=2, max=10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min=5, max=20)
    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @NotNull
    @Min(1)
    @Max(31)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @NotNull
    @DecimalMin("5.00")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getHasPassport() {
        return this.hasPassport;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}