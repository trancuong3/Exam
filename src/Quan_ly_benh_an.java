package src;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Quan_ly_benh_an {
    private List<benh_an> list = new ArrayList<>();
    private static final String FILE_PATH = "data/medical_records.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public void addBenhAn() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Nhập mã bệnh án (BA-XXX): ");
            String maBenhAn = scanner.nextLine();
            validateMaBenhAn(maBenhAn);
            System.out.print("Nhập mã bệnh nhân (BN-XXX): ");
            String maBenhNhan = scanner.nextLine();
            validateMaBenhNhan(maBenhNhan);
            System.out.print("Nhập tên bệnh nhân: ");
            String tenBenhNhan = scanner.nextLine();
            System.out.print("Nhập ngày nhập viện (dd/MM/yyyy): ");
            LocalDate ngayNhapVien = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
            System.out.print("Nhập ngày ra viện (dd/MM/yyyy): ");
            LocalDate ngayRaVien = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
            if (ngayNhapVien.isAfter(ngayRaVien)) {
                throw new IllegalArgumentException("Ngày nhập viện phải trước hoặc bằng ngày ra viện.");
            }
            System.out.print("Nhập lý do nhập viện: ");
            String lyDo = scanner.nextLine();
            System.out.print("Nhập loại bệnh án (thuong/vip): ");
            String loaiBenhAn = scanner.nextLine().toLowerCase();
            Benh_an_thuong benhAn = null;
            Benh_an_vip benhVip = null;
            if (loaiBenhAn.equals("thuong")) {
                System.out.print("Nhập phí nằm viện: ");
                double phiNamVien = scanner.nextDouble();
                benhAn = new Benh_an_thuong(list.size() + 1, maBenhAn, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDo, phiNamVien);
            } else if (loaiBenhAn.equals("vip")) {
                System.out.print("Nhập loại VIP: ");
                String loaiVip = scanner.nextLine();
                benhVip = new Benh_an_vip(list.size() + 1, maBenhAn, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDo, loaiVip);
            } else {
                throw new IllegalArgumentException("Loại bệnh án không hợp lệ.");
            }

            list.add(benhAn);
            list.add(benhVip);
            saveToFile();
            System.out.println("Thêm bệnh án thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private void validateMaBenhAn(String maBenhAn) {
        if (!maBenhAn.matches("BA-\\d{3}")) {
            throw new IllegalArgumentException("Mã bệnh án phải đúng định dạng BA-XXX.");
        }
    }

    private void validateMaBenhNhan(String maBenhNhan) {
        if (!maBenhNhan.matches("BN-\\d{3}")) {
            throw new IllegalArgumentException("Mã bệnh nhân phải đúng định dạng BN-XXX.");
        }
    }
    public void removeBenhAn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã bệnh án cần xóa: ");
        String maBenhAn = scanner.nextLine();
        benh_an benhAnToRemove = null;
        for (benh_an b : list) {
            if (b.getBenh_an().equals(maBenhAn)) {
                benhAnToRemove = b;
                break;
            }
        }
        if (benhAnToRemove == null) {
            System.out.println("Bệnh án không tồn tại.");
            return;
        }
        System.out.print("Bạn có chắc chắn muốn xóa bệnh án này? (Yes/No): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("Yes")) {
            list.remove(benhAnToRemove);
            saveToFile();
            System.out.println("Xóa bệnh án thành công.");
        }
    }
    public void showDanhSachBenhAn() {
        for (benh_an b : list) {
            System.out.println(b.toCSV());
        }
    }
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (benh_an b : list) {
                writer.write(b.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Quan_ly_benh_an qlba = new Quan_ly_benh_an();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Thêm mới bệnh án");
            System.out.println("2. Xóa bệnh án");
            System.out.println("3. Hiển thị danh sách bệnh án");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    qlba.addBenhAn();
                    break;
                case 2:
                    qlba.removeBenhAn();
                    break;
                case 3:
                    qlba.showDanhSachBenhAn();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}