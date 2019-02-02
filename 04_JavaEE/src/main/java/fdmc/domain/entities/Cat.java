package fdmc.domain.entities;

public class Cat {
    private String name;
    private String bread;
    private String color;
    private Integer age;

    public Cat() {
    }

    public Cat(String name, String bread, String color, Integer age) {
        this.name = name;
        this.bread = bread;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBread() {
        return this.bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
