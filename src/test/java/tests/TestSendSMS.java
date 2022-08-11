package tests;

import PageObjects.MessagePage;
import PageObjects.MessengerPage;
import general.BaseTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

public class TestSendSMS extends BaseTests {

    //Cucumber step definitions for SendSMS

    private MessengerPage messenger;

    @Given("I'm in the messenger app")
    public void iMInTheMessengerApp() throws MalformedURLException {
        //PoM
        messenger = new MessengerPage(setUp("com.android.messaging",".ui.conversationlist.ConversationListActivity"));

        /*
        String phoneNumber = "1235623571";
        String message = "hola1";
        */



    }

    @When("I receive a {string} from {string}")
    public void iReceiveAFrom(String message, String phoneNumber) {
        messenger.sendSMS(phoneNumber,message);

        assertEquals(messenger.getMessageFrom(1),message);
    }

    @Then("I can delete it")
    public void iCanDeleteIt() {
        MessagePage messagePage = messenger.clickMessage(1);

        messagePage.clickMoreOptions();

        messagePage.clickDeleteOption();

        messagePage.confirmDeletion();
    }
}
