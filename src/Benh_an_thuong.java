package src;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Benh_an_thuong extends benh_an {
    private double phiNamVien;
    public Benh_an_thuong(int stt, String benh_an, String ten_benh_nhan, LocalDate ngay_nhap_vien, LocalDate ngay_ra_vien, String ly_do_nhap_vien, double phiNamVien) {
        super(stt, benh_an, ten_benh_nhan, ngay_nhap_vien, ngay_ra_vien, ly_do_nhap_vien);
        this.phiNamVien = phiNamVien;
    }
    public double getPhiNamVien() {
        return phiNamVien;
    }
    @Override
    public String toCSV() {
        return String.join(",",
                String.valueOf(getStt()),
                getBenh_an(),
                getTen_benh_nhan(),
                getNgay_nhap_vien().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getNgay_ra_vien().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getLy_do_nhap_vien(),
                String.valueOf(getPhiNamVien())
        );
    }
}