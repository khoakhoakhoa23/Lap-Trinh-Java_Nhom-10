import java.util.List;

public class quanLiGioHang {
	public List<sanPham> gioHang;
	public double tongTien;

	// construction
	public quanLiGioHang(List<sanPham> gioHang, double tongTien) {
		super();
		this.gioHang = gioHang;
		this.tongTien = tongTien;
	}

	// phuong thuc get and set
	public List<sanPham> getGioHang() {
		return gioHang;
	}

	public void setGioHang(List<sanPham> gioHang) {
		this.gioHang = gioHang;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	// them san pham vao gio hang
	public void themSanPham(sanPham sanPham, int soLuong) {
		for (sanPham sp : gioHang) {
			if (sp.getMaSanPham().equals(sanPham.getMaSanPham())) {
				System.out.println("san pham da duoc them vao gio hang, vui long nhap so luong");
				return;
			}
		}
		if (sanPham.getSoLuongTon() >= soLuong) {
			sanPham.capNhatSoLuongTon(sanPham.getSoLuongTon() - soLuong);
			gioHang.add(sanPham);
			System.out.println("thanh con them san pham");
		} else {
			System.out.println("khong du hang trong kho");

		}
	}
	// cap nhat so luong san pham

	public void capNhatSoLuong(String maSanPham, int soLuong) {
		for (sanPham sp : gioHang) {
			if (sp.getMaSanPham().equals(maSanPham)) {
				if (soLuong <= sp.getSoLuongTon()) {
					sp.capNhatSoLuongTon(sp.getSoLuongTon() - soLuong);
					System.out.println("cap nhat so luong thanh cong");
				} else {
					System.out.println("so luong cap nhat vuot qua muc cua kho");
				}
				return;
			}

		}
		System.out.println("khong tim thay sp co trong gio hang");

	}

	// tinh tong tien cua gio hang
	public double tinhTongTien() {
		tongTien = 0;
		for (sanPham sp : gioHang) {
			tongTien += sp.getGia();
		}
		return tongTien;
	}

	public void hienThiSanPham() {
		for (sanPham sp : gioHang) {
			System.out.println(sp);
		}
		System.out.println("tong tien: "+ tinhTongTien());
	}

}
