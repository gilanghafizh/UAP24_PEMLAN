import java.util.Map;
import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        // Implementasi melihat list film
        System.out.println("Daftar Film:");
        for (Film film : Film.getFilms().values()) {
            System.out.println("Film: " + film.getName() + " - Deskripsi: " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    public void lihatSaldo() {
        // Implementasi lihat Saldo
        System.out.println("Saldo anda: " + Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film yang ingin dipesan: ");
        String name = scanner.nextLine();
        Film film = Film.getFilms().get(name);
        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }
        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int kuantitas = scanner.nextInt();
        System.out.println("Harga satuan tiket: " + film.getPrice());
        double totalHarga = kuantitas * film.getPrice();
        System.out.println("Total harga: " + totalHarga);
        if (kuantitas > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }
        if (Akun.getCurrentUser().getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + Akun.getCurrentUser().getSaldo());
            return;
        }
        Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalHarga);
        film.setStock(film.getStock() - kuantitas);
        Akun.getCurrentUser().addPesanan(film, kuantitas);
        System.out.println("Tiket berhasil dipesan.");
    }
        // Implementasi pemesanan film
    

    public void lihatPesanan() {
        // Implementasi melihat pesanan
         Map<String, Pesanan> pesanan = Akun.getCurrentUser().getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
        } else {
            for (Pesanan p : pesanan.values()) {
                System.out.println("Film: " + p.getFilm().getName() + " - Jumlah: " + p.getKuantitas() + " - Total Harga: " + (p.getKuantitas() * p.getFilm().getPrice()));
            }
        }
    }
}
