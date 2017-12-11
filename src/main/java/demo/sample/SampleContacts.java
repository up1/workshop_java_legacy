package demo.sample;

import demo.domain.Contact;
import demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("demo")
public class SampleContacts {

    private ContactService contactService;

    @Autowired
    public SampleContacts(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostConstruct
    public void createDefaultContacts() {
        contactService.saveContact(new Contact("Somkiat", "Somkiat Puisungnoen", "Developer", "somkiat@gmail.com", "086-069-6209"));
    }

}
