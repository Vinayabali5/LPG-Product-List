package uk.ac.reigate.applications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import groovy.transform.EqualsAndHashCode


@Entity
@Table(name = "categories")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "categories_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class Category extends BaseEntity implements Serializable {

	@Column(name="CATEGORY_NAME")
	String name
	
    /**
     * Default No Args constructor
     */
    Category(){}

    String toString() {
        return name
    }
}
