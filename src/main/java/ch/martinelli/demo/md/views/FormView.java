package ch.martinelli.demo.md.views;

import ch.martinelli.demo.md.data.service.AddressService;
import ch.martinelli.demo.md.data.service.PersonService;
import ch.martinelli.demo.md.views.forms.AddressForm;
import ch.martinelli.demo.md.views.forms.PersonForm;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class FormView extends VerticalLayout {

    public FormView(PersonService personService, AddressService addressService) {
        add(new H1("Person"));

        PersonForm personForm = new PersonForm(personService);
        add(personForm);

        AddressForm addressForm = new AddressForm(addressService);
        add(addressForm);
    }
}
