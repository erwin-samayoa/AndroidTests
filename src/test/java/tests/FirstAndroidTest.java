package tests;

import PageObjects.*;
import general.BaseTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

public class FirstAndroidTest extends BaseTests {



    //uiautomationviewer.bat requires PATH to contain: C:\Program Files\Android\Android Studio\jre\bin

    @Test
    public void testDemo1() throws MalformedURLException {

        /*
        //No POM code
        setUp("ApiDemos-debug.apk");
        driver.findElement(AppiumBy.accessibilityId("Graphics")).click();
        //System.out.println("Response is: ");
        assertEquals(driver.findElement(AppiumBy.accessibilityId("Arcs")).getText(),"Arcs");

         */

        //POM
        MainPage mainPage = new MainPage(setUp("ApiDemos-debug.apk"));
        GraphicsPage graphicsPage = mainPage.clickGraphics();
        assertEquals(graphicsPage.getTextForArcsElement(),"Arcs");

    }

    /*
    //No POM
    private void scrollDown() {
        Dimension windowSize = driver.manage().window().getSize();
        int dragSource = (int) (windowSize.getHeight() * 0.8);
        int dragDestination = (int) (windowSize.getHeight() * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");

        //Preparing sequence to execute
        Sequence sequence = new Sequence(finger,1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),0,dragSource));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),0,dragDestination));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Executing sequence two times (only one is not enough for the assert)
        driver.perform(Arrays.asList(sequence));

    }

     */

    @Test
    public void testDemo2() throws MalformedURLException {
        /*
        setUp("ApiDemos-debug.apk");
        By views = AppiumBy.accessibilityId("Views");
        By lists = AppiumBy.accessibilityId("Lists");

        driver.findElement(views).click();

        scrollDown();
        //Missing a timer when the finger is going from up to down not touching the screen
        scrollDown();

        //Verifying if "Lists" element is present
        assertEquals(driver.findElement(lists).getText(),"Lists");

         */

        //POM
        MainPage mainPage = new MainPage(setUp("ApiDemos-debug.apk"));
        ViewsPage viewsPage = mainPage.clickViews();

        viewsPage.scrollDown();
        //Missing a timer when the finger is going from up to down not touching the screen
        viewsPage.scrollDown();

        //Verifying if "Lists" element is present
        assertEquals(viewsPage.getTextForListsElement(),"Lists");
    }

    @Test
    public void testDemoDragAndDrop() throws MalformedURLException {
        /*
        //No PoM
        setUp("ApiDemos-debug.apk");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        int startingX = driver.findElement(AppiumBy.id("drag_dot_1")).getLocation().getX();
        int startingY = driver.findElement(AppiumBy.id("drag_dot_1")).getLocation().getY();

        int endingX = driver.findElement(AppiumBy.id("drag_dot_2")).getLocation().getX();
        int endingY = driver.findElement(AppiumBy.id("drag_dot_2")).getLocation().getY();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence sequence = new Sequence(finger,1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),startingX,startingY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1500),PointerInput.Origin.viewport(),endingX,endingY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        //Going back to the begining (just as a "sleep")
        //sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),startingX,startingY));

        driver.perform(Arrays.asList(sequence));

         */

        //POM
        MainPage mainPage = new MainPage(setUp("ApiDemos-debug.apk"));
        ViewsPage viewsPage = mainPage.clickViews();
        DragAndDropPage dragAndDropPage = viewsPage.clickDragAndDropElement();
        dragAndDropPage.dragXElementToY(1,2);
    }

    @Test
    public void testDemoMoveGallery() throws MalformedURLException {
        /*
        //No POM
        setUp("ApiDemos-debug.apk");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollDown();
        assertEquals(driver.findElement(AppiumBy.accessibilityId("Gallery")).getText(),"Gallery");
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        int startingX = driver.findElements(AppiumBy.className("android.widget.ImageView")).get(1).getLocation().getX();
        int startingY = driver.findElements(AppiumBy.className("android.widget.ImageView")).get(1).getLocation().getY();

        int endingX = driver.findElement(AppiumBy.className("android.widget.Gallery")).getLocation().getX() + 2;
        int endingY = driver.findElement(AppiumBy.className("android.widget.Gallery")).getLocation().getY() + 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence sequence = new Sequence(finger,1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),startingX,startingY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1500),PointerInput.Origin.viewport(),endingX,endingY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        //Going back to the begining (just as a "sleep")
        //sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),startingX,startingY));

        driver.perform(Arrays.asList(sequence));

         */

        //POM
        MainPage mainPage = new MainPage(setUp("ApiDemos-debug.apk"));
        ViewsPage viewsPage = mainPage.clickViews();
        viewsPage.scrollDown();
        assertEquals(viewsPage.getTextForGalleryElement(),"Gallery");

        GalleryPage galleryPage = viewsPage.clickGalleryElement();

        PhotosPage photosPage = galleryPage.clickPhotos();

        photosPage.swipeLeft();
    }

    @Test
    public void secondCalcTest7x7() throws MalformedURLException {
        /*
        //No POM
        setUp("com.android.calculator2",".Calculator");

        driver.findElement(AppiumBy.id("digit_7")).click();
        driver.findElement(AppiumBy.id("op_mul")).click();
        driver.findElement(AppiumBy.id("digit_7")).click();

        assertEquals(driver.findElement(AppiumBy.id("result")).getText(),"49");

         */

        //PoM
        CalcPage calcPage = new CalcPage(setUp("com.android.calculator2",".Calculator"));
        calcPage.clickDigit7();
        calcPage.clickMultiplication();
        calcPage.clickDigit7();
        assertEquals(calcPage.getResult(),"49");

    }

    @DataProvider(name = "sms data")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJsonData(System.getProperty("user.dir") + "/data/SMSData.json");
    }

    @Test(dataProvider = "sms data")
    public void testSMS(String phoneNumber, String message) throws MalformedURLException {

        /*
        //No PoM
        setUp("com.android.messaging",".ui.conversationlist.ConversationListActivity");

        By conversation = AppiumBy.id("conversation_snippet");
        By moreOptionsElement = AppiumBy.accessibilityId("More options");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String phoneNumber = "1235623571";
        String message = "hola1";

        driver.sendSMS(phoneNumber,message);
        var elementToProcess = driver.findElements(conversation).get(0);
        assertEquals(elementToProcess.getText(),message);
        elementToProcess.click();
        wait.until(ExpectedConditions.elementToBeClickable(moreOptionsElement)).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Delete']")).click();

        assertEquals(driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='DELETE']")).getText(),"DELETE");
        //driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='DELETE']")).click();
        driver.switchTo().alert().accept(); //Is better to use this one, no?

         */

        //PoM
        MessengerPage messenger = new MessengerPage(setUp("com.android.messaging",".ui.conversationlist.ConversationListActivity"));

        /*
        String phoneNumber = "1235623571";
        String message = "hola1";
        */
         

        messenger.sendSMS(phoneNumber,message);

        assertEquals(messenger.getMessageFrom(1),message);
        MessagePage messagePage = messenger.clickMessage(1);

        messagePage.clickMoreOptions();

        messagePage.clickDeleteOption();

        messagePage.confirmDeletion();
    }



    @Test
    public void testPic() throws IOException {
        /*
        //No PoM
        setUp("com.android.gallery3d",".app.Gallery");

        By appGeneralViewElement = AppiumBy.id("gl_root_view");
        By photoViewElement = AppiumBy.id("photopage_bottom_controls");

        File archivo = new File("C:\\Users\\User\\Downloads\\logo-TAU-gold-poweredby-applitools-467x105.png");
        driver.pushFile("/sdcard/Pictures/archivo.png",archivo);

        var wait = new WebDriverWait(driver,Duration.ofSeconds(20));

        wait.until(ExpectedConditions.presenceOfElementLocated(appGeneralViewElement)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(photoViewElement));

         */

        //PoM

        MultimediaPage multimediaPage = new MultimediaPage(setUp("com.android.gallery3d",".app.Gallery"));

        multimediaPage.uploadMediaFile("C:\\Users\\User\\Downloads\\logo-TAU-gold-poweredby-applitools-467x105.png");

        //device testes was not updating the pics gallery, so, not so much to verify

    }

    @Test
    public void testClipboard() throws MalformedURLException {
        /*
        //No PoM
        setUp("com.android.messaging",".ui.conversationlist.ConversationListActivity");
        By element968 = AppiumBy.xpath("//*[contains(@text,'968')]");
        By messageElement = AppiumBy.id("message_text");
        By boxElement = AppiumBy.id("compose_message_text");

        driver.findElement(element968).click();
        String textToVerify = driver.findElement(messageElement).getText();
        driver.setClipboardText(textToVerify);

        driver.findElement(boxElement).sendKeys(driver.getClipboardText());

        assertEquals(driver.findElement(boxElement).getText(),textToVerify);

         */

        //PoM
        testSMS("1234567890","Hola");//Requires a message from number including 123
        MessengerPage messengerPage = new MessengerPage(setUp("com.android.messaging",".ui.conversationlist.ConversationListActivity"));

        MessagePage messagePage = messengerPage.clickMessage(messengerPage.findMessageFrom("123"));

        String textToVerify = messagePage.getMessage();
        messagePage.setTextToClipboard(textToVerify);

        messagePage.setMessageToSend(messagePage.getTextFromClipboard());

        assertEquals(messagePage.getMessageToSend(),textToVerify);

        messagePage.clearMessageToSend();

    }

    @Test
    public void testWhatsApp() throws MalformedURLException {
        /*
        //No PoM
        setUp("com.whatsapp",".HomeActivity","11");

        String contactToSend = "yyy";
        driver.findElement(AppiumBy.accessibilityId("Buscar")).click();
        driver.findElement(AppiumBy.id("search_input")).sendKeys(contactToSend);
        WebElement contact = null;

        List<WebElement> elements = driver.findElements(AppiumBy.id("conversations_row_contact_name"));

        //Locating contact from list (even filtered driver detects full list)
        for(WebElement element: elements) {

            if (element.getText().toLowerCase().contains(contactToSend.toLowerCase())) {
                contact = element;
                System.out.println(element.getText());
                break;
            }
        }

        if (contact != null) {

                contact.click();

            for(int i = 0; i<10;i++) {
                driver.findElement(AppiumBy.id("com.whatsapp:id/entry")).sendKeys("Prueba " + contactToSend + " es chotito " + (i+1));

                driver.findElement(AppiumBy.accessibilityId("Enviar")).click();

            }


        }

         */

        //PoM
        WhatsAppMainPage whatsAppMainPage = new WhatsAppMainPage(setUp("com.whatsapp",".HomeActivity","11"));

        String contactToSend = "yyy";


        whatsAppMainPage.search(contactToSend);

        int conversationId = whatsAppMainPage.findMessageFrom(contactToSend);

        if (conversationId > 0) {

            WhatsMessagePage whatsMessagePage = whatsAppMainPage.clickMessage(conversationId);

            for(int i = 0; i<10;i++) {
                whatsMessagePage.sendMessage("Prueba " + contactToSend + " es chotito " + (i+1));
            }
        }

    }

    /*
    private void changeContext() {

        //No PoM
        Set<String> contexts = driver.getContextHandles();

        //System.out.println(driver.getContext()); //is NATIVE_APP

        for (String context: contexts) {
            if (context.contains("webview_shell")) {
                driver.context(context);
            }
            //System.out.println(context);
        }


    }

     */

    @Test
    public void testHibrid( ) throws MalformedURLException {
        //This kind is for testing hibrid apps (apps + webviews)
        //Appium requires to be started with selenium parameter for example:
        // appium --chromedriver-executable C:\Users\User\chromedriver.exe

        //For mobile web better to check BrowserTests class (access trough browserName capability)
        //Opening this app (which seems to be the default browser in the used device)

        /*
        //No PoM
        setUp("org.chromium.webview_shell", "WebViewBrowserActivity");
        //Going to URL
        driver.findElement(AppiumBy.id("url_field")).sendKeys("https://testpages.herokuapp.com/styled/find-by-playground-test.html");
        driver.findElement(AppiumBy.accessibilityId("Load URL")).click();
        //Switching context
        changeContext(); //It seems to need a change of context to allow acces to web elements (web elements like from a page or web container)

        //This one is too slow and next statement fail
        //driver.get("https://testpages.herokuapp.com/styled/find-by-playground-test.html");

        driver.findElement(By.linkText("jump to para 4")).click();

        //Going back to original context
        driver.context("NATIVE_APP");

         */

        //PoM

        BrowserPage browserPage = new BrowserPage(setUp("org.chromium.webview_shell", "WebViewBrowserActivity"));
        //Going to URL
        browserPage.navigate("https://testpages.herokuapp.com/styled/find-by-playground-test.html");

        browserPage.clickWebLink("jump to para 4");

    }






}
