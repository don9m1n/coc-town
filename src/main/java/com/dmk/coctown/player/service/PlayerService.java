package com.dmk.coctown.player.service;

import com.dmk.coctown.player.dto.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final RestTemplate restTemplate;

    @Value("${coc.token}")
    private String token;

    public Player searchPlayer(String playerTag) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        
        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://api.clashofclans.com/v1/players")
                .path("/{playerTag}")
                .buildAndExpand("#" + playerTag)
                .toUri();

        log.debug("uri: {}", uri);

        ResponseEntity<Player> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                Player.class);

        return response.getBody();

    }

}
