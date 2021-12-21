# Genetik_Algoritma
Genetik algoritma ile şifreli metnin şifre çözme işlemini gerçekleştirir.

### KROMOZOM SAYISI
---
Kromozom popülasyon değeri belli bir değere dek büyürse şifrenin ilk nesillerde bulunmasını sağlıyor ve bilgisayarın çok az sürede bunu yapmasına imkan veriyor.
Ancak belli bir popülasyon değerinden sonra (Bu örnek için 400 alabiliriz.) time complexity çok büyüdüğünden bilgisayar 10. 12. gibi nesillerde şifreyi bulsa da bulmak için harcadığı zaman çok fazla oluyor.

##### Popülasyon = 400 iken her nesilde şifreye en yakın olan kromozomlardan bazıları…

![Bilgilendirici Resim](https://i.hizliresim.com/7m8f5oi.jpg)

### 3.2) Çaprazlama ve Mutasyon
---
Çaprazlama sayesinde her nesilde daha iyi türde bireylerin oluşma olasılığını çok arttırıyoruz. En iyi fitness skorlarına sahip kromozomlar çaprazlandığında ortaya daha iyi bireyler çıkabiliyor. Bu da problemin çözümüne bir adım daha yaklaştırıyor.
Mutasyon ise durgunluğu önleyerek yeni varyantların ortaya çıkmasını sağlıyor. Mesela 20 tane kromozom içeren popülasyonda mutasyonun önemi tam olarak anlaşılamasa da 200 tane kromozom içeren bir fonksiyonda %50 gibi bir ihtimalle çok iyi sonuçlar alınabiliyor. (Mutasyona uğrayacak kromozom ve kromozomun geni rastgele seçildiği için.) Eğer popülasyon sayısı 300’ e çıkarılsaydı iyi mutasyonların olma olasılığı %100’ e yakınlaştığı için problemin çözümü de time complexity çok büyümeden yapılırdı.
Mutasyon sayesinde daha hızlı sonuçlar alınabiliyor ve durgunluk önleniyor. 

