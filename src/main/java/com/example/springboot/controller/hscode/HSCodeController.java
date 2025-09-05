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
@RequestMapping("/HSCodes")
public class HSCodeController {

    private final HSCodeService serv;

    public HSCodeController(HSCodeService serv) {
        this.serv = serv;
    }

    @GetMapping
    public List<HSCode> getAllHSCodes() {
        return serv.getAllHSCodes();
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
    public ResponseEntity<HSCode> getHSCodeById(@PathVariable Long id) {
        System.out.println("HELP");
        return serv.getHSCodeById(id);
    }

    @GetMapping(value = "{id}/minimal", produces = "application/json")
    public ResponseEntity<HSCodeMinimal> getHSCodeByIdMinimal(@PathVariable Long id) {
        System.out.println("HELLO WORLD");
        return serv.getHSCodeByIdMinimal(id);
    }

    @PostMapping
    public ResponseEntity<Long> addNewHSCode(@RequestBody HSCode input) {
        return serv.insertHSCode(input);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HSCode> deleteHSCode(@PathVariable Long id) {
        return serv.deleteHSCodeById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<HSCode> updateHSCode(@PathVariable Long id, @RequestBody HSCode newHSCode) {
        return serv.updateHSCode(id, newHSCode);
    }
}
