package com.example.secutraining;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import com.example.secutraining.controllers.AuthController;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecutrainingApplicationTests {

	@Autowired
	// private AuthController authController;

	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	@WithMockUser(authorities ={ "SCOPE_ROLE_USER"})
	public void shouldAccessDemo() throws Exception {
		this.mvc.perform(get("/api/demo")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(authorities ={ "SCOPE_ROLE_ADMIN"})
	public void shouldPostOnDemo() throws Exception {
		String postJson = """
				{
					"content": "Test"
				}
				""";
		this.mvc.perform(post("/api/demo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(postJson))
				.andDo(print())
				.andExpect(status().isOk());
	}
}



