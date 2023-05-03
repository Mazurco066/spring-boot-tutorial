package io.mazurco066.courses.api.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MockBankDataSourceTest {
    private val mockDataSource: MockBankDataSource = MockBankDataSource()
    @Test
    fun `should provide a collection of banks`() {
        // when
        val banks = mockDataSource.getBanks()

        // then
        assertThat(banks).isNotEmpty
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data`() {
        // when
        val banks = mockDataSource.getBanks()

        // then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }

    @Test
    fun `should provide a requested bank`() {
        // given
        val accountNumber = "1234"

        // when
        val bank = mockDataSource.getBank(accountNumber)

        // then
        assertThat(bank).isNotNull
        assertThat(bank.accountNumber).isEqualTo("1234")
        assertThat(bank.trust).isEqualTo(3.14)
        assertThat(bank.transactionFee).isEqualTo(17)
    }
}