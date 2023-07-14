package ch.martinelli.demo.md.views;

import ch.martinelli.demo.md.data.service.AddressService;
import ch.martinelli.demo.md.data.service.PersonService;
import ch.martinelli.demo.md.views.forms.AddressForm;
import ch.martinelli.demo.md.views.forms.PersonForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Person")
@Route(value = "", layout = MainLayout.class)
public class FormView extends VerticalLayout {

    public FormView(PersonService personService, AddressService addressService) {
        add(new H1("Person"));

        PersonForm personForm = new PersonForm();
        add(personForm);

        AddressForm addressForm = new AddressForm();
        add(addressForm);

        Button cancel = new Button("Cancel");
        Button save = new Button("Save");
        cancel.addClickListener(e -> {
            personForm.clearForm();
            addressForm.clearForm();
        });
        save.addClickListener(e -> {
            personService.update(personForm.getBean());
            addressService.update(addressForm.getBean());

            Notification.show("Saved successfully");

            personForm.clearForm();
            addressForm.clearForm();
        });


        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);

        add(buttonLayout);
    }
}
