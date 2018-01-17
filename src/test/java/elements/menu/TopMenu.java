package elements.menu;

import base.element.BaseContainer;
import org.openqa.selenium.support.FindBy;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public class TopMenu extends BaseContainer {

    @FindBy (id="horizontal-sub-navigation-planandbook")
    private TopMenuItem menuPlanAndBook;

    @FindBy (id="horizontal-sub-navigation-manageyourbooking")
    private TopMenuItem menuManageYourBooking;

    @FindBy (id="horizontal-sub-navigation-service")
    private TopMenuItem menuService;



    private ManageYourBookingAdditionalMenu manageYourBookingAdditionalMenu = null ;
    private ServiceAdditionalMenu serviceAdditionalMenu = null;
    private PlanAndBookAdditionalMenu planAndBookAdditionalMenu = null;

    public void openMenuManageYourBooking() {
        manageYourBookingAdditionalMenu = new ManageYourBookingAdditionalMenu(menuManageYourBooking.openAdditionalMenu());
    }

    public void goViewBooking() {
        if(manageYourBookingAdditionalMenu==null)
        {
            openMenuManageYourBooking();
        }
        manageYourBookingAdditionalMenu.goViewBooking();
    }

    public void openServiceMenu() {
        serviceAdditionalMenu = new ServiceAdditionalMenu (menuService.openAdditionalMenu());
    }
    public void goHandLuggage() {
        if(serviceAdditionalMenu==null)
        {
            openServiceMenu();
        }
        serviceAdditionalMenu.goHandLuggage();
    }

    public void openPlanAndBookMenu() {
        planAndBookAdditionalMenu = new PlanAndBookAdditionalMenu(menuPlanAndBook.openAdditionalMenu());
    }

    public void goAdvancedSearch() {
        if(planAndBookAdditionalMenu==null)
        {
            openServiceMenu();
        }
        planAndBookAdditionalMenu.goAdvancedSearch();
    }
}
