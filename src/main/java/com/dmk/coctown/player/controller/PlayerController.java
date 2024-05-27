package com.dmk.coctown.player.controller;

import com.dmk.coctown.player.dto.Player;
import com.dmk.coctown.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{playerTag}")
    @ResponseBody
    public Player searchPlayer(@PathVariable String playerTag) {

        log.debug("playerTag: {}", playerTag);
        return playerService.searchPlayer(playerTag);
    }
}
