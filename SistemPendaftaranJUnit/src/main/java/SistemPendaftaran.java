/** Logika bisnis sederhana sistem pendaftaran. */
public class SistemPendaftaran {
    /** Umur 17 sampai 60 (inklusif) memenuhi syarat; umur negatif ditolak. */
    public boolean isEligible(int umur) {
        if (umur < 0) throw new IllegalArgumentException("Umur tidak boleh negatif");
        return umur >= 17 && umur <= 60;
    }

    /** Biaya Rp100.000 per pendaftaran; 5-9 diskon 10%, >=10 diskon 20%; nol gratis. */
    public long hitungBiaya(int jumlah) {
        if (jumlah < 0) throw new IllegalArgumentException("Jumlah pendaftaran tidak boleh negatif");
        long total = (long) jumlah * 100_000;
        if (jumlah >= 10) return total * 80 / 100;
        else if (jumlah >= 5) return total * 90 / 100;
        else return total;
    }

    /** Nilai dalam rentang 0-100 valid; selain itu ditolak. */
    public boolean validasiNilai(int nilai) {
        if (nilai < 0 || nilai > 100) throw new IllegalArgumentException("Nilai harus berada dalam rentang 0-100");
        return true;
    }
}
