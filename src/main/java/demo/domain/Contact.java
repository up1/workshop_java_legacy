package demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "CONTACTS")
public class Contact {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    @NotEmpty(message = "{validation.not-empty.message}")
    private String name;

    @Column(name = "FULL_NAME")
    @NotEmpty(message = "{validation.not-empty.message}")
    private String fullName;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "EMAIL")
    @Email(message = "{validation.email.message}")
    private String email;

    @Column(name = "MOBILE")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "{validation.mobile.message}")
    private String mobile;

    public Contact() {
    }

    public Contact(String name, String fullName, String jobTitle, String email, String mobile) {
        this.name = name;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void updateWith(Contact contact) {
        Optional.ofNullable(contact.name).ifPresent(name -> this.name = name);
        Optional.ofNullable(contact.fullName).ifPresent(fullName -> this.fullName = fullName);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}