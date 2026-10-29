package br.com.estudo.veiculosapi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {
    @Autowired MockMvc mvc;
    @Autowired com.fasterxml.jackson.databind.ObjectMapper json;
    @Test void crudFiltroERegras() throws Exception {
        String body="{\"marca\": \"Chevrolet\", \"modelo\": \"Vectra\", \"ano\": 1997, \"placa\": \"ABC1D23\"}";
        String response=mvc.perform(post("/veiculos").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(header().exists("Location")).andReturn().getResponse().getContentAsString();
        long id=json.readTree(response).get("id").asLong();
        mvc.perform(get("/veiculos/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.marca").value("Chevrolet"));
        mvc.perform(get("/veiculos").param("marca","CHEVROLET")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(id));
        
        mvc.perform(put("/veiculos/"+id).contentType(MediaType.APPLICATION_JSON).content("{\"marca\": \"Chevrolet Atualizado\", \"modelo\": \"Vectra\", \"ano\": 1997, \"placa\": \"ABC1D23\"}")).andExpect(status().isOk()).andExpect(jsonPath("$.marca").value("Chevrolet Atualizado"));
        mvc.perform(delete("/veiculos/"+id)).andExpect(status().isNoContent()).andExpect(content().string(""));
        mvc.perform(get("/veiculos/"+id)).andExpect(status().isNotFound());
        mvc.perform(delete("/veiculos/"+id)).andExpect(status().isNotFound());
    }
    @Test void validacaoEContratoHttp() throws Exception {
        mvc.perform(post("/veiculos").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.campos").isArray());
        mvc.perform(post("/veiculos").contentType(MediaType.APPLICATION_JSON).content("{")).andExpect(status().isBadRequest());
        mvc.perform(get("/veiculos/abc")).andExpect(status().isBadRequest());
        mvc.perform(post("/veiculos").contentType(MediaType.TEXT_PLAIN).content("teste")).andExpect(status().isUnsupportedMediaType());
        mvc.perform(get("/veiculos").accept(MediaType.TEXT_HTML)).andExpect(status().isNotAcceptable());
    }
}
