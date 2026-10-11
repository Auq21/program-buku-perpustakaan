// Class LinkedList: Single Linked List untuk menyimpan daftar buku perpustakaan
public class LinkedList {
    private Node head;   // node pertama dalam daftar
    private int jumlah;  // total buku saat ini

    // Tambah Buku (Push): data baru selalu dimasukkan di AKHIR daftar
    public void tambahBuku(String kode, String judul, String penulis) {
        Node baru = new Node(kode, judul, penulis);
        if (head == null) {
            head = baru;              // daftar kosong -> node baru jadi head
        } else {
            Node cur = head;
            while (cur.next != null) { // telusuri sampai node terakhir
                cur = cur.next;
            }
            cur.next = baru;          // sambungkan di akhir
        }
        jumlah++;
    }

    // Hapus Buku (Pop): menghapus data buku TERAKHIR. Return false jika kosong
    public boolean hapusBukuTerakhir() {
        if (head == null) {
            return false;             // tidak ada data untuk dihapus
        }
        if (head.next == null) {
            head = null;              // hanya 1 node -> daftar jadi kosong
        } else {
            Node cur = head;
            while (cur.next.next != null) { // berhenti di node sebelum terakhir
                cur = cur.next;
            }
            cur.next = null;          // putuskan node terakhir
        }
        jumlah--;
        return true;
    }

    // Cari Buku berdasarkan kode (tidak membedakan huruf besar/kecil)
    public Node cariBuku(String kode) {
        Node cur = head;
        while (cur != null) {
            if (cur.kodeBuku.equalsIgnoreCase(kode)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;                  // tidak ditemukan
    }

    // Tampilkan Semua Buku sesuai urutan input, lalu total buku di akhir
    public void tampilkanSemua() {
        if (head == null) {
            System.out.println("Daftar buku masih kosong.");
            System.out.println("Total Buku: 0");
            return;
        }
        System.out.println("Daftar Buku:");
        Node cur = head;
        while (cur != null) {
            System.out.println("Kode: " + cur.kodeBuku + " | Judul: " + cur.judul + " | Penulis: " + cur.penulis);
            cur = cur.next;
        }
        System.out.println("Total Buku: " + jumlah);
    }

    public int getJumlah() {
        return jumlah;
    }
}
