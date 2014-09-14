package org.hibernate.envers.test.integration.collection.norevision;

import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Audited
@Entity
public class Person implements Serializable {
    @Id @GeneratedValue
    private Integer id;
    @AuditMappedBy(mappedBy = "person")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Set<Name> names;

    public Person() {
        names = new HashSet<Name>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Name> getNames() {
        return names;
    }

    public void setNames(Set<Name> names) {
        this.names = names;
    }
}
