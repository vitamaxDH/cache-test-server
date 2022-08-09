package dh.vitamax.cachetestserver.controller

import dh.vitamax.cachetestserver.service.BatteryCacheService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BatteryController(
    private val batteryCacheService: BatteryCacheService
) {

    @GetMapping("/battery/ids")
    fun getAllBatterIds(): List<String> {
        return batteryCacheService.findAllBatteryIds()
    }
}


