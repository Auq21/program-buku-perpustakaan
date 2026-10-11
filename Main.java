import java.util.Scanner;

public class Main {
    private static final int MAX_KODE = 5;  // batas panjang kode buku
    private static final int MIN_DATA = 5;  // minimal jumlah buku yang diinput

    public static void main(String[] args) {
        // Satu daftar dipakai bersama: hasil pengujian otomatis dilanjutkan di menu interaktif
        LinkedList daftar = new LinkedList();

        // ===== BAGIAN 1: PENGUJIAN OTOMATIS SETIAP OPERASI =====
        ujiSemuaOperasi(daftar);

        // ===== BAGIAN 2: PROGRAM INTERAKTIF (MENU) =====
        Scanner sc = new Scanner(System.in);
        int pilihan = 0;

        do {
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            // Baca pilihan sebagai teks agar input non-angka tidak membuat program error
            String input = sc.hasNextLine() ? sc.nextLine().trim() : "5";
            try {
                pilihan = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                pilihan = 0;
            }
            System.out.println();

            switch (pilihan) {
                case 1: // Tambah Buku (Push)
                    System.out.print("Masukkan Kode Buku: ");
                    String kode = sc.nextLine().trim();
                    // Validasi: kode tidak kosong dan maksimal 5 karakter
                    if (kode.isEmpty() || kode.length() > MAX_KODE) {
                        System.out.println("Kode buku tidak valid! Maksimal " + MAX_KODE + " karakter dan tidak boleh kosong.");
                        break;
                    }
                    // Validasi: kode tidak boleh ganda
                    if (daftar.cariBuku(kode) != null) {
                        System.out.println("Kode buku sudah terdaftar!");
                        break;
                    }
                    System.out.print("Masukkan Judul: ");
                    String judul = sc.nextLine().trim();
                    System.out.print("Masukkan Penulis: ");
                    String penulis = sc.nextLine().trim();
                    daftar.tambahBuku(kode, judul, penulis);
                    System.out.println("Data berhasil ditambahkan!");
                    if (daftar.getJumlah() < MIN_DATA) {
                        System.out.println("(Jumlah data saat ini " + daftar.getJumlah() + ", minimal " + MIN_DATA + " buku harus diinput.)");
                    }
                    break;
                case 2: // Hapus Buku (Pop) -> hapus buku terakhir
                    if (daftar.hapusBukuTerakhir()) {
                        System.out.println("Buku terakhir berhasil dihapus!");
                    } else {
                        System.out.println("Tidak ada data untuk dihapus.");
                    }
                    break;
                case 3: // Cari Buku berdasarkan kode
                    System.out.print("Masukkan Kode Buku: ");
                    Node hasil = daftar.cariBuku(sc.nextLine().trim());
                    if (hasil != null) {
                        System.out.println("Buku ditemukan:");
                        System.out.println("Kode: " + hasil.kodeBuku + " | Judul: " + hasil.judul + " | Penulis: " + hasil.penulis);
                    } else {
                        System.out.println("Buku tidak ditemukan.");
                    }
                    break;
                case 4: // Tampilkan semua buku + total
                    daftar.tampilkanSemua();
                    break;
                case 5:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Menu tidak valid, pilih 1-5.");
            }
        } while (pilihan != 5);
        sc.close();
    }

    // Menguji tambah, tampil, cari, dan hapus langsung dari main() tanpa input manual
    private static void ujiSemuaOperasi(LinkedList uji) {
        System.out.println("##### PENGUJIAN OTOMATIS OPERASI LINKED LIST #####\n");
        System.out.println("[Uji Hapus pada daftar kosong]");
        if (!uji.hapusBukuTerakhir()) {
            System.out.println("Tidak ada data untuk dihapus.");
        }

        System.out.println("\n[Uji Tambah 5 buku]");
        uji.tambahBuku("BK101", "Laskar Pelangi", "Andrea Hirata");
        uji.tambahBuku("BK102", "Sang Pemimpi", "Andrea Hirata");
        uji.tambahBuku("BK103", "Negeri 5 Menara", "Ahmad Fuadi");
        uji.tambahBuku("BK104", "5 cm", "Donny Dhirgantoro");
        uji.tambahBuku("BK105", "Perahu Kertas", "Dee Lestari ");
        System.out.println("5 buku berhasil ditambahkan.");

        System.out.println("\n[Uji Tampil Semua]");
        uji.tampilkanSemua();

        System.out.println("\n[Uji Cari BK103 (ada)]");
        Node ada = uji.cariBuku("BK103");
        System.out.println(ada != null ? "Buku ditemukan: " + ada.judul + " - " + ada.penulis : "Buku tidak ditemukan.");

        System.out.println("\n[Uji Cari XXXXX (tidak ada)]");
        System.out.println(uji.cariBuku("XXXXX") != null ? "Buku ditemukan." : "Buku tidak ditemukan.");

        System.out.println("\n[Uji Hapus buku terakhir]");
        uji.hapusBukuTerakhir();
        uji.tampilkanSemua();

        System.out.println("\n##### PENGUJIAN SELESAI, LANJUT KE PROGRAM INTERAKTIF #####");
        System.out.println("(Data hasil pengujian di atas tetap tersimpan dan bisa dipakai di menu.)");
    }
}
