package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    @Test
    public void kayttokelvotottomanVarastontilavuusNolla() {
       Varasto olematonVarasto = new Varasto(0);
       assertEquals(0, olematonVarasto.getTilavuus(),vertailuTarkkuus); 
    }
    
    @Test
    public void kayttokelvotottomanSaldollisenVarastontilavuusNolla() {
       Varasto olematonVarasto = new Varasto(0,0);
       assertEquals(0, olematonVarasto.getTilavuus(),vertailuTarkkuus); 
    }

    @Test
    public void kayttokelvotottomanSaldollisenVarastonSaldoNolla() {
       Varasto olematonVarasto = new Varasto(0,0);
       assertEquals(0, olematonVarasto.getSaldo(),vertailuTarkkuus); 
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoaSisaltavaKonstruktoriLuoOikeanSaldoisenVaraston() {
        Varasto saldoinenVarasto = new Varasto(20,5);
        assertEquals(5, saldoinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoaSisaltavaKonstruktoriLuoOikeanTilavuudenVarastoon() {
        Varasto saldoinenVarasto = new Varasto(20,5);
        assertEquals(20, saldoinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoaSisaltavaKonstruktoriEiAnnaLuodaNegSaldoa() {
        Varasto saldoinenVarasto = new Varasto(20,-5);
        assertEquals(0, saldoinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoaSisaltavaKonstruktoriEiAnnaLuodaLiianSuurtaSaldoa() {
        Varasto saldoinenVarasto = new Varasto(20,25);
        assertEquals(10, saldoinenVarasto.getSaldo(), vertailuTarkkuus);
    }    
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenlisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(10); //laitetaan aluksi jotain, että voidaan verrata
        varasto.lisaaVarastoon(-5);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikalisaysEiLisaaSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(15); // yritetään laittaa 5 enemmän kuin tilavuus

        // saldon pitäisi olla silti 10 eli max tilavuus
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }    
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void eiSaaOttaaNegatiivista() {
        varasto.lisaaVarastoon(5);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiSaaOttaaEnemmanKuinVarastossaOn() {
        varasto.lisaaVarastoon(5);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }   
    
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void stringTulostusOnOikein() {
        varasto.lisaaVarastoon(2);
        
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto.toString());
    }
    
    private void assertEqual(int i, double tilavuus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}