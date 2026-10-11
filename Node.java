// Class Node: merepresentasikan satu buku dalam Single Linked List
public class Node {
    String kodeBuku;   // kode unik buku (maks. 5 karakter)
    String judul;      // judul buku
    String penulis;    // nama penulis
    Node next;         // pointer ke node berikutnya (null jika node terakhir)

    public Node(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null;
    }
}
