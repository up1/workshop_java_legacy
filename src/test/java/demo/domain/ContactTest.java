package demo.domain;

import demo.domain.Contact;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void can_be_update() {
        Contact contact = new Contact("name", "fullname", null, null, null);
        Contact newContact = new Contact("new name", "new fullname", null, null, null);
        contact.updateWith(newContact);

        assertEquals("new name", contact.getName());
        assertEquals("new fullname", contact.getFullName());
    }

    @Test
    public void can_not_be_update() {
        Contact contact = new Contact("name", "fullname", null, null, null);
        Contact newContact = new Contact(null, null, null, null, null);
        contact.updateWith(newContact);

        assertEquals("name", contact.getName());
        assertEquals("fullname", contact.getFullName());
    }

}