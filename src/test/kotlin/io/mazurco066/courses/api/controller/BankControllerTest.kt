package io.mazurco066.courses.api.controller


import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            // when / then
            mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {
                        value("1234")
                    }
                }
        }
    }

    @Nested
    @DisplayName("getBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 1234

            // when / then
            mockMvc.get("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {
                        value("1234")
                    }
                }
        }

        @Test
        fun `should return Not Found if the account number does not exists`() {
            // given
            val accountNumber = "does_not_exists"

            // when / then
            mockMvc.get("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}