@scenario1


Feature: Bilyoner Uygulaması Girişi ve Voleybol Maç Seçimi


 Scenario: Kullanıcı Bilyoner uygulamasında giriş yapar, voleybol maçı seçer, bahis yapip sepette kontrol eder

Given websiteye git "https://www.bilyoner.com/"
And Giris Yapilir
Then Kullanici Girisi Kontrol Edilir
When Anasayfadaki Tum Sporlar butonuna tiklanir
And  "Voleybol" kategorisi secilir
When Voleybol kategorisinden ms1 secilir
Then Bahis oraniyla On Gosterim Karlisatirma
When Kupon Detayina Gidilir
Then Secilen macin adinin dogru sekilde gosterildigi kontrol edilir
