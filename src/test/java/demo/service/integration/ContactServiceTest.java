package demo.service.integration;

import demo.Application;
import demo.domain.Contact;
import demo.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Before
    public void tearDown() {
        contactService.deleteAllContacts();
    }

    @Test
    public void saves_new_contact() {//given
        Contact newContact
                = new Contact("Somkiat", "Somkiat Puisungnoen",
                "Developer", "somkiat@gmail.com",
                "086-869-6209");

        Contact savedContact = contactService.saveContact(newContact);
        Contact actualContact = contactService.load(savedContact.getId());

        assertEquals(newContact, actualContact);
    }

    @Test
    public void finds_contacts_by_name() {
        Contact contact1 = contactService.saveContact(new Contact("Somkiat", "Somkiat Puisungnoen", "Developer", "somkiat@gmail.com","086-869-6209"));
        Contact contact2 = contactService.saveContact(new Contact("Somkiat2", "Somkiat Puisungnoen", "Developer", "somkiat@gmail.com","086-869-6209"));
        Contact contact3 = contactService.saveContact(new Contact("XXXX", "XXXX", "Developer", "xxxx@gmail.com","086-869-6209"));

        List<Contact> contacts = contactService.searchContacts("Som");

        assertThat(contacts).containsOnly(contact1, contact2);
    }

}