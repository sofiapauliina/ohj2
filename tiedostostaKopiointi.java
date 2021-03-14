package demo.d9;
import java.io.*; 
import java.util.*; 

public class tiedostostaKopiointi{ 
    public static void main(String[] args) {
        String tiedNimi = "alku.txt";
        if (args.length > 0 ) tiedNimi = args[0];
        String loppuTiedNimi = "loppu.txt";
        if (args.length > 1 ) loppuTiedNimi = args[1];
        
        //------------
        
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) { 
            File loppuTiedosto = new File(loppuTiedNimi);
            loppuTiedosto.createNewFile();
            FileWriter myWriter = new FileWriter(loppuTiedosto);
            
            while ( fi.hasNextLine() ) {
                String rivi = fi.nextLine();
                String numerot = rivi.substring(0,2);
                
                boolean onNumero = numerot.chars().allMatch( Character::isDigit );
                
                if (onNumero) {
                    int i = Integer.parseInt(numerot); 
                    
                    if (i > 30) {
                        try {
                          myWriter.write(rivi + "\n");
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                    }
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
            }
    }
}   
