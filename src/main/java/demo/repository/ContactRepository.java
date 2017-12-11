package demo.repository;

import demo.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query("select c from Contact c where lower(c.id) like :keyword% "
            + "or lower(c.name) like :keyword% "
            + "or lower(c.fullName) like :keyword% "
            + "or lower(c.jobTitle) like :keyword% "
            + "or lower(c.email) like :keyword% "
            + "or lower(c.mobile) like :keyword% "
            + "order by c.name")
    List<Contact> searchContacts(@Param("keyword") String keyword);

    @Modifying
    @Query("delete from Contact where id in (:ids)")
    void deleteContact(@Param("ids") String... ids);

}
