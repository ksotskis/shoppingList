package com.javaguru.shoppinglist.dto;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

public class ProductDto {

    @NotNull(groups = {Update.class})
    @Null(groups = {Create.class})
    private Long id;
    @NotEmpty(groups = {Update.class, Create.class}, message = "Name must be not blank.")
    private String name;
    private String description;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto productDto = (ProductDto) o;
        return Objects.equals(id, productDto.id) &&
                Objects.equals(name, productDto.name) &&
                Objects.equals(description, productDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public interface Update {
        // empty interface
    }

    public interface Create {
        // empty interface
    }
}
