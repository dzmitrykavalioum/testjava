package model;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private List<String> roles;
    private List<String> phones;
    private String email;

    public Person(String firstName, String lastName, List<String> roles, List<String> phones, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.phones = phones;
        this.email = email;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        //String person = firstName+"\t"+lastName+"\n"+email+"\n";
        StringBuilder person = new StringBuilder(firstName+"\t"+lastName+"\n"+email+"\n");
        for (String phone : phones
        ) {
            person.append("Phone:\t" + phone+"\n");
            //person+="Phone:\t" + phone+"\n";
        }
        for (String role : roles
        ) {
            person.append("Role:\t" + role+"\n");
            //person+="Role:\t" + role+"\n";
        }
        person.append("\n");
        //person+="\n";

        return person.toString();
    }
}
