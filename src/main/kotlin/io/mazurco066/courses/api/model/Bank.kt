package io.mazurco066.courses.api.model

/*
* Kotlin auto generate getter and setter methods to class fields you only need to create them
* if you need some custom logic on them. Val = 'const' and Var = 'var'. By default kotlin set all
* fields as private, so if you need them public you just set.
* Kotlin also has Data classes that are made to transfer data between files, like an DTO.
* */
data class Bank(
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)