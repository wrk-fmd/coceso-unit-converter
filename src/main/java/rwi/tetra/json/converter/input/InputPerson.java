package rwi.tetra.json.converter.input;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static rwi.tetra.json.converter.ToStringStyle.STYLE;

public class InputPerson {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer personnelId;
    private String contact;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(final Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, STYLE)
                .append("id", id)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("personnelId", personnelId)
                .append("contact", contact)
                .toString();
    }
}
