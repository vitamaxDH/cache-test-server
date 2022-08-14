package dh.vitamax.cachetestserver.service

import dh.vitamax.cachetestserver.repository.BatteryRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class BatteryCacheService(
    private val batteryRepository: BatteryRepository
){

    @Cacheable("allBatteryIds")
    fun findAllBatteryIds(): List<String> {
        return batteryRepository.findAll().map { it.id }
    }

}
