package ch.martinelli.demo.md.views;

import ch.martinelli.demo.md.data.entity.Person;
import ch.martinelli.demo.md.data.service.AddressService;
import ch.martinelli.demo.md.data.service.PersonService;
import ch.martinelli.demo.md.views.MainLayout;
import ch.martinelli.demo.md.views.forms.AddressForm;
import ch.martinelli.demo.md.views.forms.PersonForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@PageTitle("Person")
@Route(value = "", layout = MainLayout.class)
public class PersonListView extends VerticalLayout {

    public PersonListView(PersonService personService) {
        add(new H1("Person List"));

        Grid<Person> personGrid = new Grid<>();
        personGrid.addColumn(Person::getFirstName).setHeader("First Name");
        personGrid.addColumn(Person::getLastName).setHeader("Last Name");

        personGrid.setItems(query -> personService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        add(personGrid);

        Button addPerson = new Button("Add Person");
        addPerson.addClickListener(e -> UI.getCurrent().navigate(PersonView.class, 0L));
        add(addPerson);
    }
}
