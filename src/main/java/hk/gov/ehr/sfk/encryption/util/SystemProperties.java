package hk.gov.ehr.sfk.encryption.util;


import hk.gov.ehr.sfk.encryption.entity.ScrambleLogic;
import hk.gov.ehr.sfk.encryption.entity.resultDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemProperties {

    private ScrambleLogic logic ;
    private final String charSetName = "UTF8";

    private void setLogic(String logicStr) throws Exception{
        String LOGIC_SUB_SEPARATOR = ",";
        String[] logicAry = logicStr.split("#");

        if (logicAry.length != 2){ throw new Exception("Invalid logic"); }
        String[] srcChar =  logicAry[0].split(LOGIC_SUB_SEPARATOR);
        String[] desChar =  logicAry[1].split(LOGIC_SUB_SEPARATOR);

        if (srcChar.length != desChar.length){ throw new Exception("Invalid logic: src size != des size"); }

        int[] src = new int[srcChar.length];
        int[] des = new int[desChar.length];
        for (int i = 0; i < srcChar.length; i++){
            src[i] = Integer.parseInt(srcChar[i].trim());
            des[i] = Integer.parseInt(desChar[i].trim());
        }
        logic =  new ScrambleLogic(src,des);
    }

    public ScrambleLogic getLogic(String logicStr) throws Exception {
        if (logic == null) {
            setLogic(logicStr);
        }
        System.out.println("logic is: ["+ logic.getSrcChar()[0]+","+logic.getSrcChar()[1] + "], and "+logic.getDesChar());
        return logic;
    }

    public String getCharSetName() { return charSetName; }

    public resultDTO getResultDTO(String[] resultStrAry) {
        resultDTO result = new resultDTO();
        result.setStatusCd(1000);
        result.setStatusMsg("success");
        result.setResultStrAry(resultStrAry);
        return result;
    }
    public char[][] convertStringArrayToCharArray(String[] srcStrAry){
        char[][] srcChaAry = new char[srcStrAry.length][];
        for (int i = 0;i<srcStrAry.length;i++){
            srcChaAry[i] = srcStrAry[i].toCharArray();
        }
        return srcChaAry;
    }
}