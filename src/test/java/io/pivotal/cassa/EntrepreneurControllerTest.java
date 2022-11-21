package io.pivotal.cassa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(
    listeners = CassandraUnitTestExecutionListener.class,
    mergeMode = MERGE_WITH_DEFAULTS
)
@EmbeddedCassandra()
@CassandraDataSet(keyspace = "bezkoder", value = {"test.cql"})
class EntrepreneurControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createEntrepreneur_usingValidTokenType_returnsSuccess() throws Exception {

        String entrepreneurContent = this.mockMvc.perform(post("/entrepreneurs?tokenType={0}", TokenType.TOP_HAT))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        Entrepreneur entrepreneur = objectMapper.readValue(entrepreneurContent, Entrepreneur.class);
        assertThat(entrepreneur.getTokenType()).isEqualTo(TokenType.TOP_HAT);
        assertThat(entrepreneur.getFunds()).isEqualTo(1500.0);
        assertThat(entrepreneur.getName()).isNotBlank();
    }
}