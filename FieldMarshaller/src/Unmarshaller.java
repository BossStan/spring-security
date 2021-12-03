import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Unmarshaller {
    public static void main(String[] args) throws Exception {

        findexpDate();

        String transactionType = "";
        String val = "001001100200369202201226377302835302500180280086209407903100862094079077005Tdada078006601237";

        Map<String,String> ewalletCodes = getField48Values(val);

        for ( Map.Entry<String, String> entry : ewalletCodes.entrySet()) {
            String key = entry.getKey().trim();
            String value = entry.getValue();
            System.out.println("Check again  " + key + " ::: " + value);
            if(key.equals("049")) {
                transactionType = value;
            }

        }
    }

    private static Map<String, String> getField48Values(String field48){

        int currentPosition=6;
        String tagId;
        int fieldLength=3;
        String value;
        Map<String, String>	map = new HashMap<String, String>();
        try{
            tagId = field48.substring(0,3);//first tag id

            while(currentPosition <= field48.length()){

                fieldLength = Integer.valueOf(field48.substring(currentPosition-3,currentPosition));

                value = field48.substring(currentPosition, currentPosition + fieldLength);
                tagId = field48.substring(currentPosition - 6, currentPosition - 3);
                currentPosition = currentPosition + 6 + fieldLength;

                map.put(tagId, value);

                System.out.println(tagId + "   " + value);


            }

        }catch (Exception e) {
            // TODO: handle exception3
            e.printStackTrace();
        }


        return map;
    }
    public static String findexpDate() throws Exception {


        DateFormat df1 = new SimpleDateFormat("yyMMdd");

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH,21);
        Integer dat = Integer.parseInt(dateFormat.format(cal.getTime())) - 19000000;


        System.out.println(dat.toString());
        //System.out.println("fORWARD  Date:"+dat.toString());
        return dat.toString();
    }
}
