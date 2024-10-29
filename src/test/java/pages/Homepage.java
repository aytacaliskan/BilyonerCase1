package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.CommonMethods;

/**
 * class to interact with page
 * @author shanky
 *
 * This class has web elements of homepage which are used by HomePageHelper
 */
public class Homepage extends CommonMethods {

	
	public Homepage(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public By searchbox = By.name("q");
	public By searchButton = By.xpath("(//input[@value='Google Search'])[2]");
	public By searchResults = By.xpath("//div[@id='tvcap']//div//div//a");
	public By imagesButton = By.xpath("//a[@data-sc='I']");
	public By imageContainer = By.id("islmp");

	public By btnGirisYap = By.cssSelector("a[title='Giriş Yap']");
	public By txtUsername = By.id("username");
	public By txtPassword = By.id("password");
	public By btnGirisYapPopUp = By.cssSelector("button[title='GİRİŞ YAP']");

	public By lblKullaniciBilgi = By.className("account-info__detail__name");
	public By lblBakiye = By.className("account-balance__price");

	public By btnTumSporlar = By.cssSelector("a[title='Tüm Sporlar']");

	public By categoriesList = By.className("all-categories__content");
	public By voleybol_ms1 = By.xpath("(//div[@class='odd__name'][normalize-space()='MS 1'])");
	public By atrium_value = By.cssSelector("div[class='atrium-value'] span");
	public By voleybol_ms1_value = By.xpath("(//*[@class='odd__value'])[1]");

	public By voleybol_first_team = By.xpath("(//*[@class='event-row-prematch__cells__teams text-decoration-none'])[1]");
	public By sepet_takim_adi = By.className("betslip-row__teams");





}
