Ad soyad: Ebrar Sude Yıldırım
Öğrenci no:250541104

import java.util.Scanner;

public class NotDegerlendirme {

    // --- METOT 1: Ortalama Hesaplama ---
    public static double calculateAverage(int vize, int fin, int odev) {
        // Vize %30 + Final %40 + Odev %30
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    // --- METOT 2: Geçme Durumu ---
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // --- METOT 3: Harf Notu ---
    public static char getLetterGrade(double ort) {
        if (ort >= 90) return 'A';
        else if (ort >= 80) return 'B';
        else if (ort >= 70) return 'C';
        else if (ort >= 60) return 'D';
        else return 'F';
    }

    // --- METOT 4: Onur Listesi ---
    public static boolean isHonorList(double ort, int vize, int fin, int odev) {
        // Ortalama ≥ 85 VE (vize, final, odev) hiçbiri < 70 olmayacak
        return (ort >= 85) && (vize >= 70) && (fin >= 70) && (odev >= 70);
    }

    // --- METOT 5: Bütünleme Hakkı ---
    public static boolean hasRetakeRight(double ort) {
        return (ort >= 40 && ort < 50);
    }

    // --- MAIN METOT ---
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int vize, fin, odev;

        System.out.print("Vize notu: ");
        vize = input.nextInt();

        System.out.print("Final notu: ");
        fin = input.nextInt();

        System.out.print("Odev notu: ");
        odev = input.nextInt();

        // Ortalama hesapla
        double ort = calculateAverage(vize, fin, odev);

        System.out.printf("\n--- NOT RAPORU ---\n");
        System.out.printf("Ortalama: %.2f\n", ort);

        // Geçti mi kaldı mı?
        if (isPassingGrade(ort))
            System.out.println("Durum: GECTI");
        else
            System.out.println("Durum: KALDI");

        // Harf notu
        System.out.println("Harf Notu: " + getLetterGrade(ort));

        // Onur listesi
        if (isHonorList(ort, vize, fin, odev))
            System.out.println("Onur Listesi: EVET");
        else
            System.out.println("Onur Listesi: HAYIR");

        // Butunleme hakki
        if (hasRetakeRight(ort))
            System.out.println("Butunleme Hakki: VAR");
        else
            System.out.println("Butunleme Hakki: YOK");

        input.close();
    }
}
