package demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import demo.domain.Contact;
import demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContactService {
    private final AtomicInteger idGeneration = new AtomicInteger(1000);

    @Autowired
    private ContactRepository contactRepository;


    public List<Contact> searchContacts(String keyword) {
        keyword = (keyword == null) ? "" : keyword.toLowerCase();

        return contactRepository.searchContacts(keyword);
    }

    public Contact load(String id) {
        return contactRepository.findOne(id);
    }

    @Transactional
    public Contact saveContact(@Valid Contact contact) {
        if (contact.getId() == null) {
            contact.setId(String.valueOf(idGeneration.incrementAndGet()));
        }

        return contactRepository.save(contact);
    }

    @Transactional
    public void deleteContacts(String id) {
        contactRepository.deleteContact(id);
    }

    @Transactional
    public void deleteAllContacts() {
        contactRepository.deleteAllInBatch();
    }

}
