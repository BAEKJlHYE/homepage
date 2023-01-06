package anonymous.homepage.util;

import org.springframework.stereotype.Component;

@Component
public class Masking {
    // 이름 마스킹
    public String nameMasking(String text) {
        if(text == null || text.length() == 0)
            return "";

        StringBuffer stringBuffer = new StringBuffer();
        switch (text.length()) {
            case 1:
                stringBuffer.append("*");
                break;
            case 2:
                stringBuffer.append(text.substring(0, 1)).append("*");
                break;
            default:
                stringBuffer.append(text.substring(0, 1));
                for(int i=0 ; i<text.length() - 2 ; i++)
                    stringBuffer.append("*");
                stringBuffer.append(text.substring(text.length() - 1, text.length()));
                break;
        }

        return stringBuffer.toString();
    }

    // 휴대전화번호 마스킹
    public String telNoMasking(String telNo) {
        if(telNo == null || telNo.length() == 0)
            return "";

        String[] telNoSplit = telNo.split("-");
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(telNoSplit[0]).append("-");
        for(int i=0 ; i<telNoSplit[1].length() ; i++)
            stringBuffer.append("*");
        stringBuffer.append("-").append(telNoSplit[2]);

        return stringBuffer.toString();
    }
}
