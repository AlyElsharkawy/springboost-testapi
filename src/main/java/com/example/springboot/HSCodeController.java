package com.example.springboot;

import java.util.List;
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

  @GetMapping("{id}")
  public HSCode getHSCodeById(@PathVariable Long id) {
    return serv.getHSCodeById(id);
  }

  @PostMapping
  public void addNewHSCode(@RequestBody HSCode input) {
    serv.insertHSCode(input);
  }

  @DeleteMapping("{id}")
  public void deleteHSCode(@PathVariable Long id) {
    serv.deleteHSCodeById(id);
  }

  @PutMapping("{id}")
  public void updateHSCode(@PathVariable Long id, @RequestBody HSCode newHSCode) {
    HSCode temp = serv.updateHSCode(id, newHSCode);
    return;
  }
}
