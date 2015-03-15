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
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaaLisaaminenEiYlitayta() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaEnempaaKuinOn() {
        varasto.lisaaVarastoon(8);
        
        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(4);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
        
        varasto.lisaaVarastoon(-2);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivista() {
        varasto.lisaaVarastoon(4);
        assertEquals(0, varasto.otaVarastosta(-2), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiTehdaNegatiivisenKokoistaVarastoa() {
        Varasto varastoNeg = new Varasto(-2, 0);
        assertEquals(0, varastoNeg.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldollisenVarastonTilavuusAnnettu() {
        Varasto varastoAlku = new Varasto(20, 0);
        assertEquals(20, varastoAlku.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldollisenVarastonTilavuusEiNegatiivinen() {
        Varasto varastoAlku = new Varasto(-10, 0);
        assertEquals(0, varastoAlku.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void annettuAlkuSaldoEiVoiOllaNegatiivinen() {
        Varasto varastoNeg = new Varasto(10, -2);
        assertEquals(0, varastoNeg.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void annettuAlkuSaldoAsettuuAlkuSaldoksi() {
        Varasto varastoAlku = new Varasto(10, 4);
        assertEquals(4, varastoAlku.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void annettuAlkuSaldoEiVoiOllaLiianIso() {
        Varasto varastoTaynna = new Varasto(10, 14);
        assertEquals(10, varastoTaynna.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}