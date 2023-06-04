package com.fabriciomuniz.playground.springdatarest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.hateoas.MediaTypes
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@SpringBootTest
@AutoConfigureMockMvc
class SpringDataRestApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DirtiesContext
    fun `should get an avenger list`() {

        mockMvc.get("http://localhost:8080/avengers")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaTypes.HAL_JSON_VALUE)
                }
                jsonPath("$._embedded.avenger.length()") {
                    value(10)
                }
                jsonPath("$._embedded.avenger[0].character") {
                    value("Captain America")
                }
            }
    }
}

