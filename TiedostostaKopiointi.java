/* Ohjelma, joka lukee tiedostoa, jossa on välilyönnein toisistaan erotettuja lukuja ja sanoja, 
* ja kopioi toiseen tiedostoon ne rivit, joiden alussa on luku, joka on suurempi kuin 30. 
* Luettavan tiedoston nimi ja tulostustiedoston nimi annetaan ohjelman args-parametreissä.
*
* Esimerkiksi tiedostosta:
*
* 33 hiljaa 1 hiipii
* hyvä 33 tulee
* 25 jep
* 36 1 3 5 55
* nyt 33 riittää
*
* tulee tiedosto:
*
* 33 hiljaa 1 hiipii
* 36 1 3 5 55
*/    
    
package demo.d9;
import java.io.*; 
import java.util.*; 

public class TiedostostaKopiointi{ 
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
