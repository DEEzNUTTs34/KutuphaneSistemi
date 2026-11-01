import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String ad;
    private String yazar;
    private int id;
    private boolean oduncteMi;

    public Book(String ad, String yazar, int id) {
        this.ad = ad;
        this.yazar = yazar;
        this.id = id;
        this.oduncteMi = false;
    }

    public String getAd() { return ad; }
    public String getYazar() { return yazar; }
    public int getId() { return id; }
    public boolean isOduncteMi() { return oduncteMi; }
    public void setOduncteMi(boolean oduncteMi) { this.oduncteMi = oduncteMi; }
}

public class KutuphaneSistemi {
    private static ArrayList<Book> kutuphane = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int secim;
        do {
            System.out.println("\n====== KUTUPHANE ======");
            System.out.println("1 - Kitaplari Listele");
            System.out.println("2 - Kitap Ekle");
            System.out.println("3 - Kitap Odunc Al");
            System.out.println("4 - Kitabi Iade Et");
            System.out.println("5 - Kitap Ara");
            System.out.println("0 - Cikis");
            System.out.print("Seciminiz: ");

            secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    kitaplariListele();
                    break;
                case 2:
                    kitapEkle();
                    break;
                case 3:
                    kitapOduncAl();
                    break;
                case 4:
                    kitapIadeEt();
                    break;
                case 5:
                    kitapAra();
                    break;
                case 0:
                    System.out.println("Sistemden cikiliyor...");
                    break;
                default:
                    System.out.println("Gecersiz secim, tekrar deneyin.");
            }
        } while (secim != 0);
    }

    private static void kitapEkle() {
        System.out.print("Kitap adi: ");
        String ad = scanner.nextLine();
        System.out.print("Yazar: ");
        String yazar = scanner.nextLine();
        System.out.print("Kitap ID'si: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book kitap : kutuphane) {
            if (kitap.getId() == id) {
                System.out.println("Hata: Bu ID'ye sahip bir kitap zaten mevcut!");
                return;
            }
        }

        kutuphane.add(new Book(ad, yazar, id));
        System.out.println("Kitap basariyla eklendi.");
    }

    private static void kitaplariListele() {
        if (kutuphane.isEmpty()) {
            System.out.println("Kutuphanede hic kitap yok.");
            return;
        }

        for (Book kitap : kutuphane) {
            String durum = kitap.isOduncteMi() ? "Oduncte" : "Musait";
            System.out.println("ID: " + kitap.getId() + " | " + kitap.getAd() + " | " + kitap.getYazar() + " | " + durum);
        }
    }

    private static void kitapOduncAl() {
        System.out.print("Odunc almak istediginiz kitabin ID'si: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book kitap : kutuphane) {
            if (kitap.getId() == id) {
                if (!kitap.isOduncteMi()) {
                    kitap.setOduncteMi(true);
                    System.out.println("Kitap basariyla odunc alindi.");
                } else {
                    System.out.println("Bu kitap zaten odunc alinmis.");
                }
                return;
            }
        }
        System.out.println("Kitap bulunamadi.");
    }

    private static void kitapIadeEt() {
        System.out.print("Iade etmek istediginiz kitabin ID'si: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book kitap : kutuphane) {
            if (kitap.getId() == id) {
                if (kitap.isOduncteMi()) {
                    kitap.setOduncteMi(false);
                    System.out.println("Kitap basariyla iade alindi ve musait yapildi.");
                } else {
                    System.out.println("Bu kitap zaten kutuphanede (musait durumda).");
                }
                return;
            }
        }
        System.out.println("Kitap bulunamadi.");
    }

    private static void kitapAra() {
        System.out.print("Aranacak ifade (Ad veya Yazar): ");
        String aranan = scanner.nextLine().toLowerCase();
        boolean bulundu = false;

        for (Book kitap : kutuphane) {
            if (kitap.getAd().toLowerCase().contains(aranan) || kitap.getYazar().toLowerCase().contains(aranan)) {
                String durum = kitap.isOduncteMi() ? "Oduncte" : "Musait";
                System.out.println("ID: " + kitap.getId() + " | " + kitap.getAd() + " | " + kitap.getYazar() + " | " + durum);
                bulundu = true;
            }
        }

        if (!bulundu) {
            System.out.println("Aranan kriterlere uygun kitap bulunamadi.");
        }
    }
}