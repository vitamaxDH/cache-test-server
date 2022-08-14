package dh.vitamax.cachetestserver.service

import dh.vitamax.cachetestserver.controller.BatteryController
import dh.vitamax.cachetestserver.repository.BatteryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateBatteryService(
    private val batteryRepository: BatteryRepository
) {

    @Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 5)
    fun updateBatteries(bmsMasterData: BatteryController.BMSMasterData) {
        val packets = bmsMasterData.packets
        val batteries = batteryRepository.findByIdIn(packets.map { it.id })
        packets.forEach { packet ->
            batteries.forEach { battery ->
                if (packet.id == battery.id) {
                    battery.soc = packet.soc
                    return@forEach
                }
            }
        }
    }
}
