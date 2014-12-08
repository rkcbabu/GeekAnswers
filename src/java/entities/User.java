/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import common.UserType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Chaulagai
 */
@Entity
@Table(name="Users")
//@NamedQueries({
//   @NamedQuery(name="login_query",query="SELECT id FROM User s WHERE s.email=:email AND s.password=:password")
//
//})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName ; 
    private String lastName ;
    private String email;
    private String password ; 
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registerDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    
    @Enumerated(EnumType.STRING)
    private UserType type;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questions;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answers;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reputation> reputations;
    
//    @OneToMany(mappedBy = "user")
//    private Reputation reputation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
