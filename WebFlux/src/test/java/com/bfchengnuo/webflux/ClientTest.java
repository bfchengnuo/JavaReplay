package com.bfchengnuo.webflux;

import com.bfchengnuo.webflux.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * Spring5 WebFlux Client Support
 * @see com.bfchengnuo.webflux.client.ClientTools
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class ClientTest {
    private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Test
    public void testCreateUser() throws Exception {
        final User user = new User();
        user.setName("Test");
        user.setAge(22);
        client.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("name").isEqualTo("Test");
    }
}
