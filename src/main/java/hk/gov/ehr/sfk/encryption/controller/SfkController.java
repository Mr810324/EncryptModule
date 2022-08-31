package hk.gov.ehr.sfk.encryption.controller;


import com.google.gson.GsonBuilder;
import hk.gov.ehr.sfk.encryption.entity.SfkQuery;
import hk.gov.ehr.sfk.encryption.entity.resultDTO;
import hk.gov.ehr.sfk.encryption.service.SfkService;
import hk.gov.ehr.sfk.encryption.util.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/protected/cses/sfk")
public class SfkController {

    @Autowired
    SfkService sfkService;
    @Autowired
    SystemProperties sysPro;

/*    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder){
        binder.setDisallowedFields(new String[]{"SfkQuery.consumerMinVer"});
    }*/
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields(new String[]{"SfkQuery.consumerMinVer"});
    }
    @GetMapping("/testSFK")
    public String testSFK(){
        return "testSFK is succeed.";
    }

    @PostMapping(value = "/testJsonIgnore",produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void testJsonIgnore(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrNoScramble");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        System.out.println(new GsonBuilder().serializeNulls().disableHtmlEscaping().create().toJson(sfkQuery));

    }


    @PostMapping("/ehrno/scramble/getEhrNoScramble")
    public resultDTO getEhrNoScramble(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrNoScramble");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        resultDTO result = sfkService.scramble(srcStrAry);
        return result;
    }

    @PostMapping("/ehrno/scramble/getEhrNoUnscramble")
    public resultDTO getEhrNoUnscramble(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrNoUnscramble");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        resultDTO result = sfkService.unscramble(srcStrAry);
        return result;
    }

    @PostMapping("/ehrno/encryption/getEhrNoEncryption")
    public resultDTO getEhrNoEncryption(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrNoEncryption");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        resultDTO result = sfkService.encrypt(srcStrAry);
        return result;
    }
/*    @PostMapping("/ehrno/encryption/getEhrNoEncryption2")
    public resultDTO getEhrNoEncryption2(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrNoEncryption2");
        //String[] srcStrAry = sfkQuery.getSrcStrAry();
        char[][] srcChaAry = sysPro.convertStringArrayToCharArray(sfkQuery.getSrcStrAry());
        resultDTO result = sfkService.encrypt1(srcChaAry);
        return result;
    }*/


    @PostMapping("/ehrno/encryption/getEhrNoDecryption")
    public resultDTO getEhrNoDecryption(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrNoDecryption");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        resultDTO result = sfkService.decrypt(srcStrAry);
        return result;
    }

    @PostMapping("/ehrno/encryption/getEhrAccessNoEncryption")
    public resultDTO getEhrAccessNoEncryption(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrAccessNoEncryption");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        resultDTO result = sfkService.encrypt(srcStrAry);
        return result;
    }

    @PostMapping("/ehrno/encryption/getEhrAccessNoDecryption")
    public resultDTO getEhrAccessNoDecryption(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEhrAccessNoDecryption");
        String[] srcStrAry = sfkQuery.getSrcStrAry();
        resultDTO result = sfkService.decrypt(srcStrAry);
        return result;
    }

/*    @PostMapping("/ehrno/encryption/getScrambledToEncrypted")
    public resultDTO getScrambledToEncrypted(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getScrambledToEncrypted");
    }

    @PostMapping("/ehrno/scramble/getEncryptedToScrambled")
    public resultDTO getEncryptedToScrambled(@RequestBody SfkQuery sfkQuery) throws Exception {
        System.out.println("Now is run getEncryptedToScrambled");
    }*/

}
