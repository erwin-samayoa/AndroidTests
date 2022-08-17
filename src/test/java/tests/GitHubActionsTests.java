package tests;

import PageObjects.*;
import general.BaseTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

public class GitHubActionsTests extends BaseTests {
    //Made this class to test HitHub Actions because FirstAndroidTest has several different scenarios
    //This one simply tests one app and not expecting to have certain preconditions

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

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }

}
