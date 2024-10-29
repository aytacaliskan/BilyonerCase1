package logic;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.CreateSession;
import logger.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.Homepage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class contains methods to perform action on home page.
 * @author shanky
 *
 */
public class HomePageHelper  {


	Homepage homepage;
	WebDriver driver ;
	String url;
	String csvFilePath;
	FileWriter writer;
	File file;
	PrintWriter pw;

	public String username="username";
	public String password="sifre";

	public HomePageHelper() throws IOException{
		driver = CreateSession.getWebDriver();
		homepage = new Homepage(driver);
		csvFilePath = ".//src//test//java//outputFiles//searchResults.csv";
	}


	/**
	 * method to open the mentioned url
	 */
	@Given("^websiteye git \"([^\"]*)\"$")
	public void user_is_on_bilyoner(String url) throws Throwable {
		homepage.get(url);
	}

	/**
	 * method to search the required string on google search
	 * @param stringtoBeSearched value to be searched
	 * @throws Throwable
	 */
	@And("^searches for \"([^\"]*)\"$")
	public void searches_for_something(String stringtoBeSearched) throws Throwable {
		homepage.waitForPageToLoad(homepage.getTitle());
		homepage.waitForVisibility(homepage.searchbox);
		homepage.findElement(homepage.searchbox).sendKeys(stringtoBeSearched);
	}
	@And("^Giris Yapilir")
	public void click_btn_girisYap() throws Throwable {
		homepage.clickOnElementUsingJs(homepage.btnGirisYap);
		homepage.findElement(homepage.txtUsername).sendKeys(username);
		homepage.findElement(homepage.txtPassword).sendKeys(password);
		homepage.clickOnElementUsingJs(homepage.btnGirisYapPopUp);

	}
	@And("^Cerezleri kapat")
	public void cookies_closed() throws Throwable {
		homepage.clickOnElementUsingJs(homepage.btnGirisYap);
	}

	@When("^Anasayfadaki Tum Sporlar butonuna tiklanir$")
	public void click_tumSporlar() throws Throwable {
		//homepage.waitForPageToLoad("Bilyoner");
		homepage.clickOnElementUsingJs(homepage.btnTumSporlar);

	}

	@And("^\"([^\"]*)\" kategorisi secilir$")
	public void click_sports_categories(String kategori) throws Throwable {
		homepage.waitForVisibility(homepage.categoriesList);
		List<WebElement> categories = driver.findElements(homepage.categoriesList);

		for (WebElement category : categories) {
			String name=category.getText();
			Log.info(name);
			if (category.getText().equalsIgnoreCase(kategori)) {
				// JavaScript Executor ile doğrudan tıklama
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", category);
				Log.info( kategori+" kategorisine tıklandı.");
				break;
			}
		}

	}

		@When("^Voleybol kategorisinden ms1 secilir")
	public void voleybol_ms1 () throws Throwable {
		//homepage.waitForPageToLoad("Bilyoner");
		homepage.waitForVisibility(homepage.voleybol_ms1);
		homepage.clickOnElementUsingJs(homepage.voleybol_ms1);

	}
	@When("^Kupon Detayina Gidilir")
	public void kupon_detay () throws Throwable {

		homepage.clickOnElementUsingJs(homepage.atrium_value);

	}

	@Then("^Kullanici Girisi Kontrol Edilir$")
	public void userInfoControl() throws Throwable {

		if(homepage.isElementPresent(homepage.lblKullaniciBilgi) &&  homepage.isElementPresent(homepage.lblBakiye))
			Log.info("Kullanici adi ve bakiyele bilgilerinin geldigi goruldu. Kullanici Adi :"+ homepage.findElement(homepage.lblKullaniciBilgi).getText() +" Bakiye :"+homepage.findElement(homepage.lblBakiye).getText());

	}

	@Then("^Bahis oraniyla On Gosterim Karlisatirma$")
	public void valueControl() throws Throwable {

		String atriumValue=homepage.findElement(homepage.atrium_value).getText();
		String voleybol_ms1_value=homepage.findElement(homepage.voleybol_ms1_value).getText();
		atriumValue=atriumValue.replace(",", ".");
		voleybol_ms1_value=voleybol_ms1_value.replace(",", ".");

		Double db_atriumValue=Double.parseDouble(atriumValue);
		Double db_voleybol_ms1_value=Double.parseDouble(voleybol_ms1_value);

		if (Double.compare(db_atriumValue, db_voleybol_ms1_value) == 0) {
			Log.info("Değerlerin aynı olduğu görüldü");
		} else {
			throw new AssertionError("Değerler aynı değil: atriumValue=" + db_atriumValue + ", voleybol_ms1_value=" + db_voleybol_ms1_value);
		}
	}

	@Then("^Secilen macin adinin dogru sekilde gosterildigi kontrol edilir$")
	public void teamNameControl() throws Throwable {
		homepage.waitForVisibility(homepage.sepet_takim_adi);
		homepage.waitForVisibility(homepage.voleybol_first_team);
		String voleybol_first_team=homepage.findElement(homepage.voleybol_first_team).getText();
		String sepet_takim_adi=homepage.findElement(homepage.sepet_takim_adi).getText();

		String pageVoleybolTeam1=voleybol_first_team.split("-")[0].replace("\n", "");
		String pageVoleybolTeam2=voleybol_first_team.split("-")[1].replace("\n", "");

		String basketVoleybolTeam1=sepet_takim_adi.split(" - ")[0];
		String basketVoleybolTeam2=sepet_takim_adi.split(" - ")[1];

	//takim isimleri page icerisinde kisa sepette uzun olabildigi goruldugu icin contains kullanildi. Ornek : Norwid Czestochowa takimi sepette Norwid Czestochowa iken page icerisinde Norwid Czestoch olarak geldi.
		if (basketVoleybolTeam1.contains(pageVoleybolTeam1) && basketVoleybolTeam2.contains(pageVoleybolTeam2))
			Log.info("Takim isim kontrolu basarili");
		else {
			throw new AssertionError("Değerler aynı değil: voleybol_first_team=" + voleybol_first_team + ", sepet_takim_adi=" + sepet_takim_adi);
		}
	}

}
