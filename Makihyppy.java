package demo.d7;

/**
 * Mäkihyppykilpailuohjelma demo7ssa ohjelmointi2:lla 2021 :-)
 * @author Sofia T
 * @version 
 */
public class Makihyppy {

    //---------------------------------------------------------
    
    
    /**
     * Luokka yhtä mäkihypyn kierrosta varten
     */
    public static class Kierros {
        private final static int TUOMAREITA = 5;
        private double pituus; // hyppyjen pituudet metreinä
        private final double tuomarit[] = new double[TUOMAREITA];// tuomaripisteet


        /** 
         * Alustaja
         */
        public Kierros() {
     
        }
        
        
        /** 
         * Pituuden setteri
         * @param tulosMetreina hypätty pituus metreinä
         */
        public void setPituus(double tulosMetreina) {
            this.pituus = tulosMetreina;
        }
        
        
        /** 
         * Tuomarin setteri. Laittaa pisteet tuomarin numeroa loogisesti vastaavaan
         * paikkaan tuomarijonossa.
         * @param tuomariNro tuomarin looginen numero
         * @param pisteet tuomarilta saadut pisteet
         */
        public void setTuomari(int tuomariNro, double pisteet) {
            this.tuomarit[tuomariNro-1] = pisteet;
        }
        
        
        /** 
         * Tuomaripisteiden getteri, palauttaa koko jonon
         * @return tuomaripisteet arrayna
         */
        public double[] getTuomariPisteet() {
            return this.tuomarit;
        }
        
        
        /** 
         * Kierroksella hypätyn pituuden getteri
         * @return kierroksella hypätty pituus
         */
        public double getPituus() {
            return pituus;
        }
        
        
        /** 
         * Staattinen metodi 
         * @return palauttaa staattisen tuomareiden määrän joka
         * määritelty tässä kierrosluokassa
         */
        public static int tuomareita() {
            return TUOMAREITA;
        }
    }
    
    
    //---------------------------------------------------------


    /**
     * Luokka mäkihypyn yhden kilpailijan tulosta varten.
     * Sisältää monta kierrosta.
     */
    public static class Tulos {
        private final static int KIERROKSIA = 2;
        private final Kierros kierros[] = new Kierros[KIERROKSIA];


        /** Tuloksen alustaminen
         *  luo niin monta kierrosta kierrosjonoon kuin on määritelty
         *  tämän luokan staattisessa KIERROKSIA attribuutissa
        */
        public Tulos() {
            for (int i = 0; i < KIERROKSIA; i++) {
                kierros[i] = new Kierros();
            }
        }
        
        
        /** Pituuden setteri
         *  asettaa pituuden annetulla kierroksella hypätyksi pituudeksi
         *  @param kierros kierros jolla pituus hypätty
         *  @param tulosMetreina hypätty pituus
         */
        public void setPituus(int kierros, double tulosMetreina) {
            this.kierros[kierros-1].setPituus(tulosMetreina);
        }
        
        
        /** Tuomaripisteiden setteri
         *  asettaa tuomaripisteet annetulla kierroksella saaduiksi
         *  pisteiksi tietyltä tuomarilta. 
         *  @param kierros kierros, jolla pisteet saatu
         *  @param tuomariNro tuomarin looginen numero
         *  @param pisteet tuomarilta saadut pisteet
         */
        public void setTuomari(int kierros, int tuomariNro, double pisteet) {
            this.kierros[kierros-1].setTuomari(tuomariNro, pisteet);
        }
        
        
        /** Tuomaripisteiden getteri
         *  palauttaa koko listan tietyltä kierrokselta
         *  @return tuomaripisteet tietyltä kierrokselta arrayna
         */
        public double[] getTuomariPisteet(int kierros) {
            return this.kierros[kierros-1].getTuomariPisteet();
        }
        
        
        /** Tietyllä kierroksella hypätyn pituuden getteri
         *  @param kierros kierros, jolla hypätty pituus halutaan
         *  @return kierroksella hypätty pituus
         */
        public double getPituus(int kierros) {
            return this.kierros[kierros-1].getPituus();
        }
        
        
        /** Paljonko kierroksia yhdessä tuloksessa
         * @return montako kierrosta määritetty
         */
         public static int kierroksia() {
             return KIERROKSIA;
         }
    }


    //---------------------------------------------------------
    
    
    /**
     * Luokka yhtä mäkihypyn kilpailijaa varten.
     * Sisältää nimen, kilpailijanumeron ja tuloksen
     */
    public static class Kilpailija {
        private String nimi;
        private int nro;
        private final Tulos tulos = new Tulos();


        /**
         * Kilpailijan alustaminen
         * @param nimi kilpailijan nimi
         * @param nro kilpailijan kilpailunumero
         */
        public Kilpailija(String nimi,int nro) {
            this.nimi = nimi;
            this.nro = nro;
        }
        
        
        /**
         * Hypätyn pituuden setteri
         * @param kierros kierros, jolla pituus hypätty
         * @param tulosMetreina hypätty pituus metreinä
         */
        public void setPituus(int kierros, double tulosMetreina) {
            this.tulos.setPituus(kierros, tulosMetreina);
        }
        
        
        /**
         * Tuomaripisteen setteri
         * @param kierros kierros, jota tuomaripiste koskee
         * @param tuomariNro tuomarin looginen numero
         * @param pisteet saadut tuomaripisteet
         */
        public void setTuomari(int kierros, int tuomariNro, double pisteet) {
            this.tulos.setTuomari(kierros, tuomariNro, pisteet);
        }


        /** Tulostaa kilpailijan tiedot */
        public void tulosta() {
            System.out.println("  " + nro + ": " + nimi);
            System.out.print("Kierros 1    ");
            System.out.print(tulos.getPituus(1));
            System.out.print(" m.   Tuomarit:    ");
            double[] K1 = tulos.getTuomariPisteet(1);
            for (int i = 0; i < Kierros.tuomareita(); i++) {
                System.out.print(K1[i] + " ");
            }
            
            System.out.print(" = ");
            System.out.print(this.laskeKierroksenPisteet(1));
            System.out.print(" pistettä.\n");
            
            System.out.print("Kierros 2    ");
            System.out.print(tulos.getPituus(2));
            System.out.print(" m.   Tuomarit:    ");
            double[] K2 = tulos.getTuomariPisteet(2);
            for (int i = 0; i < Kierros.tuomareita(); i++) {
                System.out.print(K2[i] + " ");
            }
            
            System.out.print(" = ");
            System.out.print(this.laskeKierroksenPisteet(2));
            System.out.print(" pistettä.\n");
            
            System.out.print("Yhteensä ");
            System.out.print(this.laskeKokonaisPisteet());
            System.out.print(" pistettä.\n");
            
            
        }
        
        
        /**
         * Laskee kilpailijan kokonaispisteet koko kilpailusta
         * @return kilpailijan kokonaispisteet
         */
        private double laskeKokonaisPisteet() {
            double pisteet = 0.0;
            
            for (int i = 1; i < (tulos.kierroksia() + 1); i++) {
                pisteet += laskeKierroksenPisteet(i);
            }
            return pisteet;
        }
        
        
        /**
         * Antaa kilpailijan pisteet tietyltä kierrokselta
         * @param kierros kierros, jonka pisteet halutaan
         * @return kierroksen pisteet
         */
        private double laskeKierroksenPisteet(int kierros) {
        double K_PISTE = 60.0;
        double METRIKERROIN = 1.0;
        double[] tuomarit = tulos.getTuomariPisteet(kierros);
        double pituus = tulos.getPituus(kierros);
        
        double summa = demo.d7.Rajat.summaHuonoinJaParasPois(tuomarit);
        double pisteet = summa > 0 ? METRIKERROIN * pituus - K_PISTE + summa : 0;
        return pisteet;
        }
        
        
        /**
         * Palauttaa voittajan kahdesta kilpailijasta: this ja vastustaja
         * @param vastustaja kilpailija, johon verrataan
         * @return 2 jos this voitti, -1 jos vastustaja voitti
         */
        public int compareTo(Kilpailija vastustaja) {
            double pisteet = this.laskeKokonaisPisteet();
            double vastustajanPisteet = vastustaja.laskeKierroksenPisteet(1) + vastustaja.laskeKierroksenPisteet(2);
            
            if (pisteet > vastustajanPisteet) return 2;
            return -1;
            
        }

    }
    
    
    //---------------------------------------------------------




    /**
     * Aliohjelma kilpailua varten
     */
  public void kisa() { 
    Kilpailija toni = new Kilpailija("Toni",3); 
    Kilpailija matti = new Kilpailija("Matti",7); 
    toni.tulosta(); 
    matti.tulosta(); 

    toni.setPituus(1,107);       // 1. kierros, 107 m
    toni.setPituus(2,100);       // 2. kierros, 100 m
    toni.setTuomari(2,1,19.0);   // 2. kierros, 1. tuomari, 19 p 
    toni.setTuomari(2,2,18.0);   // 2. kierros, 2. tuomari, 18 p
    toni.setTuomari(2,3,19.5); 
    toni.setTuomari(2,4,18.0); 
    toni.setTuomari(2,5,20.0); 

    matti.setPituus(1,125); 
    matti.setTuomari(1,1,20.0); 
    matti.setTuomari(1,2,20.0); 
    matti.setTuomari(1,3,20.0); 
    matti.setTuomari(1,4,20.0); 
    matti.setPituus(2,109); 
    matti.setTuomari(2,1,20.0); 
    matti.setTuomari(2,2,20.0); 
    matti.setTuomari(2,3,20.0); 
    matti.setTuomari(2,4,20.0); 

    toni.tulosta(); 
    matti.tulosta(); 

    if (matti.compareTo(toni) >1) System.out.println("Matti voitti !");

  } 

    /**
     * Testataan luokan kääntymistä
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Makihyppy kisa = new Makihyppy();
        kisa.kisa();
    }

}
