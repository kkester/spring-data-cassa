package io.pivotal.cassa.board.web;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.SquareDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SquareController {

    private final ISquareRetriever squareRetriever;

    @GetMapping("/squares/{squareId}/image")
    @SneakyThrows
    public void getSquareImage(@PathVariable Integer squareId, HttpServletResponse response) {
        SquareDetails squareDetails = squareRetriever.getSquareDetailsById(squareId);
        try (InputStream in = new FileInputStream(ResourceUtils.getFile("classpath:images/" + squareDetails.getImageName())) ) {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.copy(in, response.getOutputStream());
        }
    }
}
