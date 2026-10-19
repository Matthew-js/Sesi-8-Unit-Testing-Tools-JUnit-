import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Test Sistem Pendaftaran")
class SistemPendaftaranTest {
    private SistemPendaftaran sistem;

    @BeforeEach
    void setUp() {
        sistem = new SistemPendaftaran();
    }

    @ParameterizedTest(name = "Umur {0} seharusnya menghasilkan {1}")
    @DisplayName("BVA - Pengujian batas umur pendaftar")
    @CsvSource({"16, false", "17, true", "18, true", "59, true", "60, true", "61, false"})
    void testBatasUmur(int umur, boolean expected) {
        // Arrange: input dan expected dari CsvSource
        boolean actual = sistem.isEligible(umur); // Act
        assertEquals(expected, actual); // Assert
    }

    @Test @DisplayName("Umur normal 25 tahun memenuhi syarat")
    void testUmurNormal() {
        int umur = 25; // Arrange
        boolean actual = sistem.isEligible(umur); // Act
        assertTrue(actual); // Assert
    }

    @Test @DisplayName("Umur negatif harus ditolak")
    void testUmurNegatif() {
        int umur = -1; // Arrange
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sistem.isEligible(umur)); // Act & Assert
        assertEquals("Umur tidak boleh negatif", exception.getMessage());
    }

    @Test @DisplayName("Jumlah nol menghasilkan biaya nol")
    void testBiayaJumlahNol() {
        int jumlah = 0; // Arrange
        long actual = sistem.hitungBiaya(jumlah); // Act
        assertEquals(0L, actual); // Assert
    }

    @Test @DisplayName("Empat pendaftaran tanpa diskon")
    void testBiayaTanpaDiskon() {
        int jumlah = 4; long expected = 400_000L; // Arrange
        long actual = sistem.hitungBiaya(jumlah); // Act
        assertEquals(expected, actual); // Assert
    }

    @Test @DisplayName("Lima pendaftaran mendapat diskon 10 persen")
    void testBiayaDiskon10Persen() {
        int jumlah = 5; long expected = 450_000L; // Arrange
        long actual = sistem.hitungBiaya(jumlah); // Act
        assertEquals(expected, actual); // Assert
    }

    @Test @DisplayName("Sepuluh pendaftaran mendapat diskon 20 persen")
    void testBiayaDiskon20Persen() {
        int jumlah = 10; long expected = 800_000L; // Arrange
        long actual = sistem.hitungBiaya(jumlah); // Act
        assertEquals(expected, actual); // Assert
    }

    @Test @DisplayName("Jumlah pendaftaran negatif harus ditolak")
    void testJumlahNegatif() {
        int jumlah = -2; // Arrange
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sistem.hitungBiaya(jumlah)); // Act & Assert
        assertEquals("Jumlah pendaftaran tidak boleh negatif", exception.getMessage());
    }

    @Test @DisplayName("Nilai normal 75 dinyatakan valid")
    void testNilaiNormal() {
        int nilai = 75; // Arrange
        boolean actual = sistem.validasiNilai(nilai); // Act
        assertTrue(actual); // Assert
    }

    @Test @DisplayName("Nilai batas bawah 0 dinyatakan valid")
    void testNilaiBatasBawah() {
        int nilai = 0; // Arrange
        boolean actual = sistem.validasiNilai(nilai); // Act
        assertTrue(actual); // Assert
    }

    @Test @DisplayName("Nilai batas atas 100 dinyatakan valid")
    void testNilaiBatasAtas() {
        int nilai = 100; // Arrange
        boolean actual = sistem.validasiNilai(nilai); // Act
        assertTrue(actual); // Assert
    }

    @Test @DisplayName("Nilai negatif harus ditolak")
    void testNilaiNegatif() {
        int nilai = -1; // Arrange
        assertThrows(IllegalArgumentException.class, () -> sistem.validasiNilai(nilai)); // Act & Assert
    }

    @Test @DisplayName("Nilai lebih dari 100 harus ditolak")
    void testNilaiMelebihiBatas() {
        int nilai = 101; // Arrange
        assertThrows(IllegalArgumentException.class, () -> sistem.validasiNilai(nilai)); // Act & Assert
    }
}
