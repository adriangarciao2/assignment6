import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BookstoreTest {
    static Playwright playwright;
    static Browser browser;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    void testBookstoreFlow(){
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1280, 720));

        Page page = context.newPage();
        page.navigate("https://depaul.bncollege.com/");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("earbuds");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).press("Enter");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand")).click();
        page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("brand JBL (12)")).getByRole(AriaRole.IMG).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
        page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Color Black (9)")).locator("svg").first().click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
        page.locator("#facet-price svg").nth(2).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).click();
        assertThat(page.getByLabel("main").getByRole(AriaRole.HEADING)).containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");
        assertThat(page.getByLabel("main")).containsText("sku 668972707");
        assertThat(page.getByLabel("main")).containsText("$164.98");
        assertThat(page.getByLabel("main")).containsText("Adaptive noise cancelling allows awareness of environment when gaming on the go. Light weight, durable, water resist. USB-C dongle for low latency connection < than 30ms.");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
        assertThat(page.locator("#headerDesktopView")).containsText("Cart 1 items");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items")).click();
        assertThat(page.getByLabel("main")).containsText("Your Shopping Cart");
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless"))).isVisible();
        assertThat(page.getByLabel("main")).containsText("qty:");
        assertThat(page.getByLabel("main")).containsText("$164.98");
        assertThat(page.getByText("Subtotal $164.98 Handling To")).isVisible();
        assertThat(page.getByLabel("main")).containsText("Subtotal $164.98");
        assertThat(page.getByLabel("main")).containsText("Handling To support the bookstore's ability to provide a best-in-class online and campus bookstore experience, and to offset the rising costs of goods and services, an online handling fee of $3.00 per transaction is charged. This fee offsets additional expenses including fulfillment, distribution, operational optimization, and personalized service. No minimum purchase required. $3.00");
        assertThat(page.getByLabel("main")).containsText("Taxes TBD");
        assertThat(page.getByLabel("main")).containsText("Estimated Total $167.98");
        assertThat(page.getByText("FAST In-Store PickupDePaul")).isVisible();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).fill("TEST");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Promo Code")).click();
        assertThat(page.locator("#js-voucher-result")).containsText("The coupon code entered is not valid.");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed To Checkout")).first().click();
        assertThat(page.getByLabel("main")).containsText("Create Account");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest")).click();
        assertThat(page.getByLabel("main")).containsText("Contact Information");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).fill("adrian");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).fill("garcia");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).fill("example@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).press("Tab");
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("United States: +")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).fill("2248000000");
        assertThat(page.getByLabel("main")).containsText("Order Summary Order Subtotal $164.98 Handling To support the bookstore's ability to provide a best-in-class online and campus bookstore experience, and to offset the rising costs of goods and services, an online handling fee of $3.00 per transaction is charged. This fee offsets additional expenses including fulfillment, distribution, operational optimization, and personalized service. No minimum purchase required. $3.00 Tax TBD Merchant Processing Fee TBD Total $167.98 167.98 $ Pick Up: 85 1 E. Jackson Boulevard, Chicago, Illinois, 60604, United States 312-362-8792 PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black Quantity: Qty: 1 $164.98");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        assertThat(page.getByLabel("main")).containsText("Full Name adrian garcia Email Address example@gmail.com Phone Number +12248000000");
        assertThat(page.locator("#bnedPickupPersonForm")).containsText("DePaul University Loop Campus & SAIC");
        assertThat(page.locator("#bnedPickupPersonForm")).containsText("I'll pick them up");
        assertThat(page.getByText("I'll pick them up")).isVisible();
        assertThat(page.getByLabel("main")).containsText("Order Summary Order Subtotal $164.98 Handling To support the bookstore's ability to provide a best-in-class online and campus bookstore experience, and to offset the rising costs of goods and services, an online handling fee of $3.00 per transaction is charged. This fee offsets additional expenses including fulfillment, distribution, operational optimization, and personalized service. No minimum purchase required. $3.00 Tax TBD Merchant Processing Fee TBD Total $167.98 167.98 $ Pick Up: 85 1 E. Jackson Boulevard, Chicago, Illinois, 60604, United States 312-362-8792 PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black Quantity: Qty: 1 $164.98");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        assertThat(page.getByLabel("main")).containsText("Order Subtotal $164.98");
        assertThat(page.getByLabel("main")).containsText("Handling To support the bookstore's ability to provide a best-in-class online and campus bookstore experience, and to offset the rising costs of goods and services, an online handling fee of $3.00 per transaction is charged. This fee offsets additional expenses including fulfillment, distribution, operational optimization, and personalized service. No minimum purchase required. $3.00");
        assertThat(page.getByLabel("main")).containsText("Tax $17.22");
        assertThat(page.getByLabel("main")).containsText("Total $185.20 185.2 $");
        assertThat(page.getByLabel("main")).containsText("PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black Quantity: Qty: 1 $164.98");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum")).click();
        assertThat(
                page.getByRole(AriaRole.HEADING,
                        new Page.GetByRoleOptions().setName("Your cart is empty"))
        ).isVisible();
        context.close();
    }
}