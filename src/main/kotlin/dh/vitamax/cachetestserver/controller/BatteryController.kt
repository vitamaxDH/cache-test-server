package dh.vitamax.cachetestserver.controller

import dh.vitamax.cachetestserver.service.BatteryCacheService
import dh.vitamax.cachetestserver.service.UpdateBatteryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BatteryController(
    private val batteryCacheService: BatteryCacheService,
    private val updateBatteryService: UpdateBatteryService,
) {

    @GetMapping("/battery/ids")
    fun getAllBatterIds(): List<String> {
        return batteryCacheService.findAllBatteryIds()
    }

    data class BMSMasterData (
        val timestamp: Long,
        val packets: List<BMSPacketData>,
    ) {
        data class BMSPacketData (
            val id: String,
            val soc: Float,
        )
    }
    @PostMapping("/battery/ids")
    fun updateBatteries(@RequestBody bmsMasterData: BMSMasterData) {
        updateBatteryService.updateBatteries(bmsMasterData)
    }
}
