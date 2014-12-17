/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author acer
 */
@NamedQueries({
    @NamedQuery(name = "user.expertise.list", query = "SELECT e FROM Expertise e WHERE e.user=:user ORDER BY e.point DESC"),
    @NamedQuery(name = "user.expertise.category", query = "SELECT e FROM Expertise e WHERE e.user=:user AND e.category=:category"),
    @NamedQuery(name = "user.expertise.total.point", query = "SELECT SUM(e.point) FROM Expertise e WHERE e.user=:user "),
    @NamedQuery(name = "user.expertise.course.total.point", query = "SELECT SUM(e.point) FROM Expertise e WHERE e.user=:user AND e.category=:category"),
    @NamedQuery(name = "user.expertise.average.point", query = "SELECT AVG(e.point) FROM Expertise e WHERE e.user=:user "),
    @NamedQuery(name = "user.expertise.course.average.point", query = "SELECT AVG(e.point) FROM Expertise e WHERE e.user=:user AND e.category=:category"),}
)

@Entity
public class Expertise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private int point;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expertise)) {
            return false;
        }
        Expertise other = (Expertise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Expertise[ id=" + id + " ]";
    }

}
