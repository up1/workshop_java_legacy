package demo.api;

import demo.domain.Contact;
import demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/contacts")
public class ContactController extends BaseController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public List<Contact> searchContacts(@RequestParam(defaultValue = "") String keyword) {
        return contactService.searchContacts(keyword);
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createContact(@RequestBody @Valid Contact contact) {
        Contact saveContact = contactService.saveContact(contact);
        return saveContact.getId();
    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Contact updateContact(@PathVariable String id, @RequestBody @Valid Contact updatedContact) {
        Contact contact = contactService.load(id);
        contact.updateWith(updatedContact);
        return contactService.saveContact(contact);
    }

    @RequestMapping(value = "/{id}",method = DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContacts(@PathVariable String id) {
        contactService.deleteContacts(id);
    }

}
