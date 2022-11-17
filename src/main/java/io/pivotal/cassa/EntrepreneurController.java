package io.pivotal.cassa;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entrepreneurs")
public class EntrepreneurController {

    private final EntrepreneurGenerator entrepreneurGenerator;

    @PostMapping
    Entrepreneur create(@RequestParam TokenType tokenType) {
        return entrepreneurGenerator.create(tokenType);
    }
}
