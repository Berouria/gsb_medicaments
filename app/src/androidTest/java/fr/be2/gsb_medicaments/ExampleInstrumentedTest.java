package fr.be2.gsb_medicaments;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("fr.be2.gsb_medicaments", appContext.getPackageName());
    }
    @Test
    public void checkMedicament() {
        // Context of the app under test.
        Medicament medicament = new Medicament();
        medicament.setCodeCIS(1234);
        medicament.setDenomination("monmedicament");
        medicament.setFormePharmaceutique("formePharmaceutiqueMedicament");
        medicament.setVoiesAdmin("voiesAdminMedicament");
        medicament.setTitulaires("titulairesMedicament");
        assertEquals("denominationMedicament", medicament.getDenomination());

    }
}