package hu.kr.server.mindmaster.controller;

import hu.kr.server.mindmaster.model.MasterMindGuessRequestModel;
import hu.kr.server.mindmaster.service.MindMasterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MindMasterController.class, MindMasterService.class})
public class MindMasterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MindMasterService mindMasterService;

    @Test
    public void startGameTest() throws Exception {
        mvc.perform(get("/mindmaster/start?username=Zsolti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName").value("Zsolti"));
    }

    @Test
    public void guessTest() throws Exception {
        mindMasterService.startGame("Zsolti");

        mvc.perform(
                post("/mindmaster/guess")
                    .content("{\"userName\" : \"Zsolti\" " +
                            ", \"guess\":[\"RED\",\"RED\",\"RED\",\"RED\"]" +
                            "}")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName").value("Zsolti"));
    }

    @Test
    public void filterTest() throws Exception {
        mindMasterService.startGame("Zsolti");

        Cookie cookie = new Cookie("kuki","123456");
        cookie.setMaxAge(998877);

        Cookie cookie2 = new Cookie("kuki2","123456");
        cookie2.setMaxAge(1234);

        mvc.perform(
                post("/bazzegIttZsírKirályságVan?almafa=10&tej=11")
                        .content("boci boci tarka, se füle se farka, oda megyünk lakni, ha nem kap el a Győrfi Pali!")
                        .characterEncoding("UTF-8")
                        .header("fuz_header","V3RYS3CR3TFUZH3AD3R19871112")
                        .header("heder","aa")
                        .cookie(cookie,cookie2)
                        .contentType(MediaType.APPLICATION_JSON));
    }

}