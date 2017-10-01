package exam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Exam {

    public static void main(String[] args) throws ParseException {
       ArrayList<String> list = new ArrayList<String>();         
    try{
        FileInputStream fstream = new FileInputStream("D:\\1.working_time(11).log");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream , "TIS-620"));
        String strLine;
    
        while ((strLine = br.readLine()) != null)   {
            list.add(strLine);
        }
        fstream.close();

   
} catch (IOException e) {
     System.err.println("Error: " + e.getMessage());
}
        int money = 0,money2 = 0,money3 = 0 ;
        for(int n = 0; n < list.size(); n++){
            String[] arrB = list.get(n).split("[|]");
            System.out.println(n+1 +". "+arrB[0]);
            if(arrB.length == 4){
            System.out.println( "   Start date : "+arrB[1]);
            System.out.println( "   Start time : 00:00");
            System.out.println( "   Stop date  : "+arrB[3]);
            System.out.println( "   Stop time  : 00:00");
            System.out.println( "   Status     : ไม่มาทำงาน");
            System.out.println( "============================");
            }else if(arrB.length == 5){
            System.out.println( "   Start date : "+arrB[1]);
            System.out.println( "   Start time : "+arrB[2]);
            System.out.println( "   Stop date  : "+arrB[3]);
            System.out.println( "   Stop time  : "+arrB[4]);
            System.out.print( "   Status     : ทำงาน");
            
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(arrB[2]);
            Date date2 = format.parse(arrB[4]);
            long difference = (date2.getTime() - date1.getTime()); 
            long timework = difference / (1000*60);
            
                if(arrB[1].equals("10/10/2015")){
                    System.out.print("วันหยุด ");
                    
                    if(timework < 570 && timework > 300){ //ทำงานเต็มวัน
                        System.out.println("/ ทำงานเต็มวัน "); 
                        System.out.println("   ทำOT       : 00:00 ชั่วโมง");
                        /*คำนวณค่าแรง:ชั่วโมง = 290Bath/8hrs = 36.25 Bath/hrs*/
                        money = (int)(8 * 36.25 *1.5);
                        System.out.print("   ค่าแรง      : ");
                        System.out.println(money+" บาท");
                    }else if(timework < 300 && timework > 0){ //ทำงานครึ่งวัน
                        System.out.println("/ ทำงานครึ่งวัน ");
                        System.out.println("   ทำOT       : 00:00 ชั่วโมง");
                        money = (int)(4 * 36.25 *1.5);
                        System.out.print("   ค่าแรง      : ");
                        System.out.println(money+" บาท");
                    }else{
                        timework += 1440;
                        int timeworkOThrs = (int) (timework) / 60;
                        double timeworkOTmin = timework % 60;
                        timework /= 60;
                        System.out.println("\n   ทำOT       : "+timeworkOThrs+":"+(int)timeworkOTmin+" ชั่วโมง ");
                        money = (int)(timework * (36.25*2));
                        System.out.print("   ค่าแรง      : ");
                        System.out.println(money+" บาท");
                        
                    }
                        
/*=====================================================================================================*/                
                }else{
                    System.out.print("วันปกติ ");
                        if(timework < 0){
                            timework += 1440; 
                            int timeworkOThrs = (int) (timework) / 60;
                            double timeworkOTmin = timework % 60;
                            timework /= 60;
                            /*คำนวณค่าแรง:ชั่วโมง = 290Bath/8hrs = 36.25 Bath/hrs*/
                            System.out.println("\n   ทำOT       : "+timeworkOThrs+":"+(int)timeworkOTmin+" ชั่วโมง ");
                            money = (int)(timework * (36.25*1.5));
                            System.out.print("   ค่าแรง      : ");
                            System.out.println(money+" บาท");
                
                        }else{
                            if(timework < 570 ){
                                timework -= 60;
                                timework /= 60;
                                System.out.println("/ ทำงานเต็มวัน ");
                                System.out.println("   ทำOT       : 00:00 ชั่วโมง");
                                /*คำนวณค่าแรง:ชั่วโมง = 290Bath/8hrs = 36.25 Bath/hrs*/
                                money = (int)(timework * 36.25);
                                System.out.print("   ค่าแรง      : ");
                                System.out.println(money+" บาท");
                            }else{
                                
                               
                                date1 = format.parse("17:30");
                                date2 = format.parse(arrB[4]);
                                difference = (date2.getTime() - date1.getTime()); 
                                double timeworkOT = difference / (1000*60);
                                int timeworkOThrs = (int) (timeworkOT / 60);
                                money2 = (int)(timeworkOThrs * (36.25*1.5));
                                double timeworkOTmin = timeworkOT % 60;
                                money3 = (int)(timeworkOTmin * ((36.25*1.5)/60)); /*คิดค่าจ้างเพิ่มเป็นนาที*/
                                System.out.println("/ ทำงานเต็มวัน ");
                                System.out.println("   ทำOT       : "+timeworkOThrs+":"+(int)timeworkOTmin+" ชั่วโมง ");
                                money = 290+money2+money3;
                                System.out.println("   ค่าแรง      : "+(money)+" บาท");
                            }   
                        }
                    }
            String[] arrT = arrB[2].split(":");    
            if(arrT[0].equals("8")){
                Date time1 = format.parse("08:05");
                Date time2 = format.parse(arrB[2]);
                difference = (time2.getTime() - time1.getTime());
                double late = difference / (1000*60);
                if(late>=1){
                    System.out.println("   มาสาย      : "+(int)late+" นาที");
                    double latemoney = (late * ((36.25)/60));
                    System.out.println("   ค่าจ้างคงเหลือ : "+(int)(money - latemoney)+ " บาท");
                }
            }    
            System.out.println( "============================");
            }
        }
    }
}
