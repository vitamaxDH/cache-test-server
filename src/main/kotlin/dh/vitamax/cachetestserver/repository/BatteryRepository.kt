package dh.vitamax.cachetestserver.repository

import dh.vitamax.cachetestserver.entity.Battery
import org.springframework.data.jpa.repository.JpaRepository

interface BatteryRepository: JpaRepository<Battery, String> {

    fun findByIdIn(ids: List<String>): List<Battery>
}
