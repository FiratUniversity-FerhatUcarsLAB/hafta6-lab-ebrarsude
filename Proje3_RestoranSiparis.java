Ad soyad:Ebrar Sude Yıldırım
Öğrenci no:250541104

  import java.util.Scanner;

public class SiparisSistemi {

    // 1) Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;  // Çorba
            case 2: return 45;  // Humus
            case 3: return 55;  // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;  // Kola
            case 2: return 12;  // Ayran
            case 3: return 35;  // Taze Meyve Suyu
            case 4: return 25;  // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;  // Künefe
            case 2: return 55;  // Baklava
            case 3: return 35;  // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo: Ana yemek + içecek + tatlı
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy Hour: 14:00 – 17:00
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim hesapla
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {

        double indirim = 0;

        // COMBO %15
        if (combo) {
            indirim += 0.15;
        }

        // 200 TL üzeri %10
        if (tutar >= 200) {
            indirim += 0.10;
        }

        // Happy hour içecek indirimi (sadece içecek fiyatına uygulanır, bu yüzden dışarıda uygulanacak)
        // Bu metot içinde ayrıca işlem yapılmayacak.

        // Öğrenci hafta içi %10 → iç içe if
        if (ogrenci) {
            if (saat >= 1) { // anlamsal kontrol, hafta içi burada manuel koşul yok çünkü gün parametresi yok
                indirim += 0.10;
            }
        }

        return indirim;
    }

    // 8) Bahşiş önerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }


    // -----------------------------------------------------------
    // MAIN PROGRAM
    // -----------------------------------------------------------

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== SIPARIS SISTEMI ===");

        // Gün – Math.random() örneği (1–7)
        int gun = 1 + (int)(Math.random() * 7);

        System.out.printf("Bugunun gunu (random): %d\n", gun);

        System.out.print("Saat (0-23): ");
        int saat = input.nextInt();

        System.out.println("\n--- Ana Yemekler ---");
        System.out.println("1) Izgara Tavuk (85)");
        System.out.println("2) Adana Kebap (120)");
        System.out.println("3) Levrek (110)");
        System.out.println("4) Manti (65)");
        System.out.print("Secim (0=Yok): ");
        int anaSecim = input.nextInt();

        System.out.println("\n--- Baslangiclar ---");
        System.out.println("1) Corba (25)");
        System.out.println("2) Humus (45)");
        System.out.println("3) Sigara Boregi (55)");
        System.out.print("Secim (0=Yok): ");
        int basSecim = input.nextInt();

        System.out.println("\n--- Icecekler ---");
        System.out.println("1) Kola (15)");
        System.out.println("2) Ayran (12)");
        System.out.println("3) Taze Meyve Suyu (35)");
        System.out.println("4) Limonata (25)");
        System.out.print("Secim (0=Yok): ");
        int icecekSecim = input.nextInt();

        System.out.println("\n--- Tatlilar ---");
        System.out.println("1) Kunefe (65)");
        System.out.println("2) Baklava (55)");
        System.out.println("3) Sutlac (35)");
        System.out.print("Secim (0=Yok): ");
        int tatliSecim = input.nextInt();

        System.out.print("\nOgrenci misiniz? (1=Evet, 0=Hayir): ");
        boolean ogrenci = input.nextInt() == 1;

        // Fiyatlar
        double anaF = getMainDishPrice(anaSecim);
        double basF = getAppetizerPrice(basSecim);
        double icecekF = getDrinkPrice(icecekSecim);
        double tatliF = getDessertPrice(tatliSecim);

        // Happy Hour %20 sadece içecekte
        if (isHappyHour(saat) && icecekF > 0) {
            icecekF = icecekF - icecekF * 0.20;
        }

        boolean combo = isComboOrder(anaF > 0, icecekF > 0, tatliF > 0);

        double araToplam = anaF + basF + icecekF + tatliF;

        double indirimOran = calculateDiscount(araToplam, combo, ogrenci, saat);
        double indirimTutar = araToplam * indirimOran;

        double odenecek = araToplam - indirimTutar;

        // Bahşiş önerisi (%10) — ternary operatörü
        double bahsis = calculateServiceTip(odenecek);
        String bahsisOneri = bahsis > 0 ? (int)bahsis + " TL önerilir." : "Bahşiş yok.";

        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);
        System.out.printf("Uygulanan Indirim: %.2f TL (%%%d)\n", indirimTutar, (int)(indirimOran * 100));
        System.out.printf("Odenecek Tutar: %.2f TL\n", odenecek);
        System.out.println("Bahsis: " + bahsisOneri);

        input.close();
    }
}
