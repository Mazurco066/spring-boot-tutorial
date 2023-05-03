package io.mazurco066.courses.api.controller

import io.mazurco066.courses.api.model.Bank
import io.mazurco066.courses.api.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/banks")
class BankController(
    private val service: BankService
) {

    @GetMapping
    fun getBanks(): Collection<Bank> {
        return service.getBanks()
    }
}