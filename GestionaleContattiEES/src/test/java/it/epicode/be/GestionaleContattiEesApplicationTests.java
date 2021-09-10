package it.epicode.be;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.be.repository.StatoFatturaRepository;

@SpringBootTest
@AutoConfigureMockMvc
class GestionaleContattiEesApplicationTests {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private StatoFatturaRepository statoRepo;



	    @Test
	    @WithMockUser(roles="ADMIN")
	    void testGetAllClienti() throws Exception {
	            mockMvc.perform(get("/api/clienti"))
	                    .andDo(print())
	                    .andExpect(status().isOk())
						.andExpect(content().string(containsString("gianni")));
			}
	    
	    @Test
	    @WithMockUser(roles="ADMIN")
	    void testGetAllFatture() throws Exception {
	            mockMvc.perform(get("/api/fatture"))
	                    .andDo(print())
	                    .andExpect(status().isOk())
						.andExpect(content().string(containsString("1000")));
			}

	

}
