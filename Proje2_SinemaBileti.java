Ad soyad: Ebrar Sude Yıldırım
Öğrenci no:250541104
Proje: Sinema bilet
Tarih:23.11.2025
    
import java.util.Scanner;

public class SinemaBileti {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7;  // Cumartesi (6), Pazar (7)
    }

    // 2) Matine mi?
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {

        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (weekend) {  
            if (matinee) return 55;
            else return 85;
        } else { // Hafta içi
            if (matinee) return 45;
            else return 65;
        }
    }

    // 4) İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // Öncelik sırası: Öğretmen (Çarşamba) → 65+ → 12 yaş altı → Öğrenci
        double indirim = 0.0;

        // 65 yaş üstü (her gün %30)
        if (yas >= 65) {
            indirim = 0.30;
        }
        // 12 yaş altı (her gün %25)
        else if (yas < 12) {
            indirim = 0.25;
        }
        // Öğretmen (sadece Çarşamba %35)
        else if (meslek == 2 && gun == 3) {
            indirim = 0.35;
        }
        // Öğrenci indirimi
        else if (meslek == 1) {
            if (gun >= 1 && gun <= 4) indirim = 0.20;   // Pazartesi–Perşembe
            else indirim = 0.15;                        // Cuma–Pazar
        }

        return indirim;
    }

    // 5) Film türü ekstra ücret
    public static int getFormatExtra(int tur) {
        switch(tur) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Toplam fiyat hesaplama
    public static double calculateFinalPrice(double base, double indirim, int extra) {
        double odenecek = base - (base * indirim) + extra;
        return odenecek < 0 ? 0 : odenecek;  // ternary kullanım ✔
    }

    // 7) Bilet bilgisi üretme
    public static String generateTicketInfo(String gunStr, String meslekStr, String turStr,
                                            int saat, double base, double indirim, 
                                            int extra, double toplam) {

        return  "===== BILET BILGISI =====\n" +
                "Gun: " + gunStr + "\n" +
                "Seans Saati: " + saat + ":00\n" +
                "Musteri Turu: " + meslekStr + "\n" +
                "Film Formati: " + turStr + "\n" +
                "Temel Fiyat: " + base + " TL\n" +
                "Indirim Orani: %" + (int)(indirim * 100) + "\n" +
                "Format Ekstra: " + extra + " TL\n" +
                "Toplam Odenecek: " + toplam + " TL\n";
    }

    // MAIN — Kullanıcıdan veri alma + random ornegi
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("----- SINEMA BILET SISTEMI -----");

        // Gün seçimi
        System.out.println("1=Pzt, 2=Sali, 3=Cars., 4=Pers., 5=Cuma, 6=Cts, 7=Pazar");
        System.out.print("Gunu girin: ");
        int gun = input.nextInt();

        // Saat
        System.out.print("Seans saati (0-23): ");
        int saat = input.nextInt();

        // Yaş
        System.out.print("Yas: ");
        int yas = input.nextInt();

        // Meslek seçimi
        System.out.println("1=Ogrenci, 2=Ogretmen, 3=Diger");
        System.out.print("Meslek: ");
        int meslek = input.nextInt();

        // Film türü seçimi
        System.out.println("1=2D, 2=3D, 3=IMAX, 4=4DX");
        System.out.print("Film turu: ");
        int tur = input.nextInt();

        // Gün string (switch)
        String gunStr;
        switch(gun) {
            case 1: gunStr = "Pazartesi"; break;
            case 2: gunStr = "Salı"; break;
            case 3: gunStr = "Çarşamba"; break;
            case 4: gunStr = "Perşembe"; break;
            case 5: gunStr = "Cuma"; break;
            case 6: gunStr = "Cumartesi"; break;
            case 7: gunStr = "Pazar"; break;
            default: gunStr = "Bilinmeyen"; 
        }

        // Meslek string
        String meslekStr = (meslek == 1) ? "Öğrenci" :
                           (meslek == 2) ? "Öğretmen" : "Diğer";

        // Film türü string
        String turStr;
        switch(tur) {
            case 1: turStr = "2D"; break;
            case 2: turStr = "3D"; break;
            case 3: turStr = "IMAX"; break;
            case 4: turStr = "4DX"; break;
            default: turStr = "Unknown";
        }

        double base = calculateBasePrice(gun, saat);
        double indirim = calculateDiscount(yas, meslek, gun);
        int extra = getFormatExtra(tur);

        double toplam = calculateFinalPrice(base, indirim, extra);

        System.out.println("\n" + generateTicketInfo(
                gunStr, meslekStr, turStr, saat, base, indirim, extra, toplam));

        // (Bonus) Rastgele başka bir bilet örneği
        int randomSaat = (int)(Math.random() * 24);
        System.out.printf("Rastgele bir seans örneği: %d:00\n", randomSaat);

        input.close();
    }
}

