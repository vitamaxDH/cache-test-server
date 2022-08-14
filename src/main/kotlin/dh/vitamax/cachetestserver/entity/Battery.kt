package dh.vitamax.cachetestserver.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Battery (

    @Id
    val id: String,

    @Column(columnDefinition="Decimal(10,2)")
    var soc: Float
)
