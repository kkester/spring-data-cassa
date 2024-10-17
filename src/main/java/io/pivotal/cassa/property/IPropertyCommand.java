package io.pivotal.cassa.property;

public interface IPropertyCommand {
    void create(Property property);
    void update(Property property);
    void delete(Property property);
}
