package demo.service;

import demo.domain.Contact;
import demo.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    @Test
    public void success_with_save_new_contact() {
        Contact newContact
                = new Contact("Somkiat", "Somkiat Puisungnoen",
                             "Developer", "somkiat@gmail.com",
                             "086-869-6209");

        contactService.saveContact(newContact);

        verify(contactRepository).save(newContact);
    }

    @Test
    public void success_with_save_new_contact_and_have_id() {
        Contact newContact
                = new Contact("Somkiat", "Somkiat Puisungnoen",
                "Developer", "somkiat@gmail.com",
                "086-8696-209");

        contactService.saveContact(newContact);

        ArgumentCaptor<Contact> argument = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepository).save(argument.capture());
        assertNotNull(argument.getValue().getId());
    }


    @Test
    public void finds_contacts_lowercase() throws Exception {
        Contact contact
                = new Contact("Somkiat", "Somkiat Puisungnoen",
                "Developer", "somkiat@gmail.com",
                "086-8696209");
        when(contactRepository.searchContacts("som"))
                .thenReturn(asList(contact));

        List<Contact> contacts = contactService.searchContacts("som");

        assertThat(contacts).contains(contact);
    }


}