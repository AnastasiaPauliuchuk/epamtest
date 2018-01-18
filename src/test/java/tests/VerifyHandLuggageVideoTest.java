package tests;

import base.page.PageManager;
import base.test.BaseTest;
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
        step(1,"Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start Page");

        step(2,"Open menu 'Service'");
        firstPage.openServiceMenu();

        step(3,"Click 'Hand luggage'");
        firstPage.goHandLuggage();
        HandLuggageInfoPage infoPage = PageManager.createPage(HandLuggageInfoPage.class,"Hand Luggage Info");

        step(4,"Scroll the page down");
        infoPage.scrollToVideo();

        step(5,"Get the video link");
        String link = infoPage.getVideoLink();

        check("Verify the video link");
        infoPage.assertVideoLink(expectedLink);

        step(6,"Open video by link ");
        infoPage.goVideoLink();
        YouTubeVideoPage youTubeVideoPage = PageManager.createPage(YouTubeVideoPage.class,"Youtube video");

        step(7,"Get the video details");
        check("Verify video title");
        youTubeVideoPage.assertTitle(expectedTitle);

        check("Verify the author name");
        youTubeVideoPage.assertAuthor(expectedAuthor);
    }
}
