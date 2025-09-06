package com.example.springboot.controller.hscode;

import java.util.List;
import com.example.springboot.service.hscode.HSCodeService;
import com.example.springboot.entity.hscode.HSCode;
import com.example.springboot.dto.hscode.HSCodeMinimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HSCode")
public class HSCodeController {

    private final HSCodeService serv;

    public HSCodeController(HSCodeService serv) {
        this.serv = serv;
    }

    @GetMapping
    public List<HSCode> getAllHSCodes(String endpoint) {
        return serv.getAllHSCodes(endpoint);
        /*
         * List.of(
         * new HSCode(1L, "FIRST", "Hambola", LocalDateTime.now()),
         * new HSCode(2L, "SECOND", "Foobar", LocalDateTime.now()),
         * new HSCode(3L, "THIRD", "Maximums", LocalDateTime.now()));
         */
    }

    /*
     * I WAS TESTING OUT THE DTO AND MAPPERS SO I MADE SPECIAL METHODS FOR THEM
     * IN PRODUCTION, WE ALWAYS USE A DTO REGARDLESS
     */

    @GetMapping("{id}")
    public ResponseEntity<HSCode> getHSCodeById(@PathVariable Long id, String endpoint) {
        return serv.getHSCodeById(id, endpoint);
    }

    @GetMapping(value = "{id}/minimal", produces = "application/json")
    public ResponseEntity<HSCodeMinimal> getHSCodeByIdMinimal(@PathVariable Long id, String endpoint) {
        System.out.println("HELLO WORLD");
        return serv.getHSCodeByIdMinimal(id, endpoint);
    }

    @PostMapping
    public ResponseEntity<Long> addNewHSCode(@RequestBody HSCode input, String endpoint) {
        return serv.insertHSCode(input, endpoint);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HSCode> deleteHSCode(@PathVariable Long id, String endpoint) {
        return serv.deleteHSCodeById(id, endpoint);
    }

    @PutMapping("{id}")
    public ResponseEntity<HSCodeMinimal> updateHSCode(@PathVariable Long id, @RequestBody HSCode newHSCode,
            String endpoint) {
        return serv.updateHSCode(id, newHSCode, endpoint);
    }
}
