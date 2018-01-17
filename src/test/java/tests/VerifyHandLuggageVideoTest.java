package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.HandLuggageInfoPage;
import pages.MainSearchPage;
import pages.YouTubeVideoPage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/15/2018.
 */
public class VerifyHandLuggageVideoTest extends BaseTest {

    private String expectedLink;
    private String expectedTitle;
    private String  expectedAuthor;
    @BeforeClass
    @Parameters({"expectedLink","expectedTitle","expectedAuthor"})
    public void setUp(String expectedLink, String expectedTitle, String  expectedAuthor) {
        this.expectedLink = expectedLink;
        this.expectedTitle = expectedTitle;
        this.expectedAuthor = expectedAuthor;
    }
    @Override
    public void runTest() {
        Browser.getInstance().getDriver().navigate().refresh();
        step(1,"Open main page");
        MainSearchPage firstPage = new MainSearchPage("Start Page");
        firstPage.init((WebDriver) Browser.getInstance().getDriver());  //todo replace with createPage()
        step(2,"Open menu 'Service'");
        firstPage.openServiceMenu();
        step(3,"Click 'Hand luggage'");
        firstPage.goHandLuggage();
        HandLuggageInfoPage infoPage = new HandLuggageInfoPage("Hand Luggage Info");
        infoPage.init((WebDriver) Browser.getInstance().getDriver());
        step(4,"Scroll the page down");
        step(5,"Get the video link");
        String link = infoPage.getVideoLink();
        check("Verify the video link");
        assertEquals(link,expectedLink);
        step(6,"Open video by link ");
        infoPage.goVideoLink(link);

        YouTubeVideoPage youTubeVideoPage = new YouTubeVideoPage("Youtube video");
        youTubeVideoPage.init((WebDriver) Browser.getInstance().getDriver());
        step(7,"Get the video details");
        check("Verify video title");
        youTubeVideoPage.assertTitle(expectedTitle);
        check("Verify the author name");
        youTubeVideoPage.assertAuthor(expectedAuthor);


    }
}
