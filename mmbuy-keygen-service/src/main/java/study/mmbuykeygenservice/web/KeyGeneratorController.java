package study.mmbuykeygenservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.mmbuykeygenservice.keyGen.KeyGenerator;

@RestController
@RequestMapping
public class KeyGeneratorController {

    @Autowired
    @Qualifier("snowFlakeKeyGenerator")
    private KeyGenerator keyGenerator;

    @RequestMapping("/keygen")
    public String generateKey() throws Exception {
        return String.valueOf(keyGenerator.generateKey().longValue());
    }
}
