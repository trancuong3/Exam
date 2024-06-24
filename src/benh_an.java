package src;
import java.time.LocalDate;
public abstract class benh_an {
    private int stt;
    private String benh_an;
    private String ten_benh_nhan;
    private LocalDate ngay_nhap_vien;
    private LocalDate ngay_ra_vien;
    private String ly_do_nhap_vien;
    public benh_an(int stt, String benh_an, String ten_benh_nhan, LocalDate ngay_nhap_vien, LocalDate ngay_ra_vien, String ly_do_nhap_vien) {
        this.stt = stt;
        this.benh_an = benh_an;
        this.ten_benh_nhan = ten_benh_nhan;
        this.ngay_nhap_vien = ngay_nhap_vien;
        this.ngay_ra_vien = ngay_ra_vien;
        this.ly_do_nhap_vien = ly_do_nhap_vien;
    }
    public int getStt() {
        return stt;
    }
    public String getBenh_an() {
        return benh_an;
    }
    public String getTen_benh_nhan() {
        return ten_benh_nhan;
    }
    public LocalDate getNgay_nhap_vien() {
        return ngay_nhap_vien;
    }
    public LocalDate getNgay_ra_vien() {
        return ngay_ra_vien;
    }
    public String getLy_do_nhap_vien() {
        return ly_do_nhap_vien;
    }
    public abstract String toCSV();
}