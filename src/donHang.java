import java.util.List;

public class donHang extends quanLiDonHang {
	private String maDonHang;
	// private khachHang khachHang;
	private List<sanPham> danhSachSanPham;
	private String trangThai;

	// phuong thuc get and set

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}

	public List<sanPham> getDanhSachSanPham() {
		return danhSachSanPham;
	}

	public void setDanhSachSanPham(List<sanPham> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	// cap nhat trang thai don hang
	public void capNhatTrangThai(String trangThaiMoi) {
		this.trangThai = trangThaiMoi;
	}

	// tinh tong so tien cua don hang
	public double tinhTongTien() {
		int tongTien = 0;
		for (sanPham sp : danhSachSanPham) {
			tongTien += sp.getGia() * sp.getSoLuongTon();
		}
		return tongTien;
	}

	// lay thong tin don hang
	public String layThongTinDonHang() {
		StringBuilder sb = new StringBuilder();
		sb.append("ma don hang: ").append("/n");

				// StringBuilder append = sb.append("Khach Hang: ").append(khachHang.
				// getTenKhachHang()).append("\n");
		
		// lay thong tin trang thai
		sb.append("Trạng Thái: ").append(trangThai).append("\n");
		// lay tong tien
		sb.append("Tổng Tiền: ").append(tinhTongTien()).append("\n");
		// lay sanh sach san pham
		sb.append("Danh Sach San Pham:\n");
		for (sanPham sp : danhSachSanPham) {
			sb.append("- ").append(sp.getTenSanPham()).append(" (").append(sp.getSoLuongTon()).append(" x ")
					.append(sp.getGia()).append(")\n");
		}
		return sb.toString();
	}

}
