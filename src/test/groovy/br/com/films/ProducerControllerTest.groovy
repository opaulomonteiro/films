package br.com.films


import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProducerControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    def "should return producer interval awards infos"() {
        MvcResult result = mvc.perform(get("/producers/awards")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()

        String content = result.getResponse().getContentAsString();

        expect:
        content == '{"min":{"producer":"Joel Silver","interval":1,"previousWin":1990,"followingWin":1991},"max":{"producer":"Matthew Vaughn","interval":13,"previousWin":2002,"followingWin":2015}}'
    }
}