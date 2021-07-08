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
import uk.ac.reigate.applications.domain.Category

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import groovy.transform.EqualsAndHashCode

@Entity
@Table(name = "products")
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class Product extends NamedEntity implements Serializable {

    /**
     * The Category for the product
     */
    @OneToOne
    @JoinColumn(name='CATEGORY_ID')
    Category category
    
    @Column(name="CREATION_DATE")
    Date creationDate
	
	@Column(name="UPDATE_DATE")
	Date updateDate
	
	@Column(name="LAST_PURCHASED_DATE")
	Date lastPurchasedDate
            
    /**
     * Default NoArgs constructor.    
     */
    Product(){}
    
    String toString() {
        return description
    }
}
