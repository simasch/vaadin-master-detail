package ch.martinelli.demo.md.views;

import ch.martinelli.demo.md.data.entity.Person;
import ch.martinelli.demo.md.data.service.AddressService;
import ch.martinelli.demo.md.data.service.PersonService;
import ch.martinelli.demo.md.views.forms.AddressForm;
import ch.martinelli.demo.md.views.forms.PersonForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@PageTitle("Person")
@Route(value = "person", layout = MainLayout.class)
public class PersonView extends VerticalLayout implements HasUrlParameter<Long> {

    private final PersonService personService;
    private final PersonForm personForm;
    private final AddressForm addressForm;

    public PersonView(PersonService personService) {
        this.personService = personService;

        add(new H1("Person"));

        personForm = new PersonForm();
        add(personForm);

        addressForm = new AddressForm();
        add(addressForm);

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> {
            UI.getCurrent().navigate(PersonListView.class);
        });

        Button save = new Button("Save");
        save.addClickListener(e -> {
            Person person = personForm.getBean();
            person.setAddress(addressForm.getBean());
            personService.update(person);

            Notification.show("Saved successfully");
        });

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);

        add(buttonLayout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long personId) {
        Optional<Person> optionalPerson = personService.get(personId);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            personForm.setBean(person);
            addressForm.setBean(person.getAddress());
        }
        else {
            personForm.clearForm();
            addressForm.clearForm();
        }
    }
}
