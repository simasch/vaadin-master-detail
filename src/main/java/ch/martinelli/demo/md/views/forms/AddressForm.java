package ch.martinelli.demo.md.views.forms;

import ch.martinelli.demo.md.data.entity.Address;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class AddressForm extends Div {

    private final TextField street = new TextField("Street address");
    private final TextField postalCode = new TextField("Postal code");
    private final TextField city = new TextField("City");
    private final ComboBox<String> state = new ComboBox<>("State");
    private final ComboBox<String> country = new ComboBox<>("Country");

    private final Binder<Address> binder = new Binder<>(Address.class);

    public AddressForm() {
        addClassName("address-form-view");

        add(createTitle());
        add(createFormLayout());

        binder.bindInstanceFields(this);

        clearForm();
    }

    private Component createTitle() {
        return new H3("Address");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(street, 2);
        postalCode.setAllowedCharPattern("\\d");
        country.setItems("Country 1", "Country 2", "Country 3");
        state.setItems("State A", "State B", "State C", "State D");
        formLayout.add(postalCode, city, state, country);
        return formLayout;
    }

    public void clearForm() {
        binder.setBean(new Address());
    }

    public Address getBean() {
        return binder.getBean();
    }

    public void setBean(Address address) {
        binder.setBean(address);
    }
}
