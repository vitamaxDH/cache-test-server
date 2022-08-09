package dh.vitamax.cachetestserver

import dh.vitamax.cachetestserver.entity.Battery
import dh.vitamax.cachetestserver.repository.BatteryRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class CacheTestServerApplication{

    @Bean
    fun applicationRunner(repository: BatteryRepository): ApplicationRunner {
        return ApplicationRunner {
            val batteries = (0..9999).map { randomBattery() }
            repository.saveAll(batteries)
        }
    }

    fun randomBattery(): Battery {
        val randomString = UUID.randomUUID().toString().take(8).uppercase()
        return Battery(
            id = "BAT$randomString",
            soc = Math.random() * 99
        )
    }
}

fun main(args: Array<String>) {
    runApplication<CacheTestServerApplication>(*args)
}
