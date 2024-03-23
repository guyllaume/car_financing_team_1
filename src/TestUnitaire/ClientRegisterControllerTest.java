package TestUnitaire;
import controller.ClientRegisterController;
import model.Client;
import org.junit.Before;
import org.junit.Test;
import view.RegisterView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientRegisterControllerTest {

    private Client client;
    private RegisterView rv;
    private ClientRegisterController crController;
    @Before
    public void setUp() {
        //Instance des classe
        client = new Client();
        rv = new RegisterView();
        crController = new ClientRegisterController(client, rv);
    }

    //Test si l'email entrer est un email valide
    @Test
    public void testEmailValide(){
        assertEquals(true, crController.testCheckIfValidEmail("Guyllaumebeaudry@gmail.com"));// Valide email
    }

    //Test si l'email entrer est un email valide
    @Test
    public void testEmailInValide(){
        assertEquals(false, crController.testCheckIfValidEmail("asd@qw"));// Invalid email
    }

    //Test si le password est valide
    @Test
    public void testPasswordValid(){
        assertEquals(true, crController.testCheckIfPasswordRespectsCode("KiwiRough13!"));// Valid Password
    }

    //Test si le password est invalide du au manque de chiffre
    @Test
    public void testPasswordInvalidChiffre(){
        assertEquals(false, crController.testCheckIfPasswordRespectsCode("KiwiRough!"));// Invalid Password
    }

    //Test si le password est invalide du au manque de symbole
    @Test
    public void testPasswordInvalidSymbole(){
        assertEquals(false, crController.testCheckIfPasswordRespectsCode("KiwiRough13"));// Invalid Password
    }

    //Test si le password est invalide du au manque de majuscule
    @Test
    public void testPasswordInvalidUpperCase(){
        assertEquals(false, crController.testCheckIfPasswordRespectsCode("kiwirough13!"));// Invalid Password
    }

    //Test si le password est invalide du au manque de lettre
    @Test
    public void testPasswordInvalidLongueur(){
        assertEquals(false, crController.testCheckIfPasswordRespectsCode("Kiwi13!"));// Invalid Password
    }

    //Test si les deux password entrer sont egaux
    @Test
    public void testPasswordsAreEqual(){
        assertEquals(true, crController.testCheckIfPasswordsAreEquals("kiki", "kiki"));// Same Passwords
    }
    //Test si les deux password entrer ne sont pas egaux
    @Test
    public void testPasswordsAreNotEqual(){
        assertEquals(false, crController.testCheckIfPasswordsAreEquals("kiki", "KuKu"));// Same Passwords
    }
    //Test si la liste de String entrer sont TOUS rempli (aucun vide)
    @Test
    public void testListeRempli(){
        //Liste de 2 string avec des info entrer dans chaque enregistrement
        List<String> infoEntrer = new ArrayList<>();
        infoEntrer.add("ss");
        infoEntrer.add("aa");
        assertEquals(true, crController.testCheckIfInfoEnteredUser(infoEntrer));// Tous rempli
    }
    //Test si la liste de String entrer sont partiellement rempli
    @Test
    public void testListeIncomplete(){
        //Liste de 2 string avec des info entrer dans un enregistrement rempli et lautre vide
        List<String> infoEntrer = new ArrayList<>();
        infoEntrer.add("ss");
        infoEntrer.add("");
        assertEquals(false, crController.testCheckIfInfoEnteredUser(infoEntrer));// Partiellement rempli
    }
    //Test si la liste de String entrer est vide
    @Test
    public void testListeVide(){
        //Liste de 2 string vide
        List<String> infoEntrer = new ArrayList<>();
        infoEntrer.add("");
        infoEntrer.add("");
        assertEquals(false, crController.testCheckIfInfoEnteredUser(infoEntrer));// vide
    }
}
