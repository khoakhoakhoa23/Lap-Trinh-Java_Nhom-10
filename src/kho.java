import java.util.List;

public class kho extends quanLiDonHang {
	private String maKho;
	private String diaChi;
	private List<sanPham> danhSachSanPham;

	// construction
	public kho(String maKho, String diaChi, List<sanPham> danhSachSanPham) {
		this.maKho = maKho;
		this.diaChi = diaChi;
		this.danhSachSanPham = danhSachSanPham;
	}

	// phuong thuc get and set
	public String getMaKho() {
		return maKho;
	}

	public void setMaKho(String maKho) {
		this.maKho = maKho;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public List<sanPham> getDanhSachSanPham() {
		return danhSachSanPham;
	}

	public void setDanhSachSanPham(List<sanPham> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}

	// kiem tra so luong ton kho cua san pham
	public int kiemTraTonKho(String maSanPham) {
		for (sanPham sp : danhSachSanPham) {
			if (sp.getMaSanPham().equals(maSanPham)) {
				return sp.getSoLuongTon();
			}
		}
		return 0;
	}

	// cap nhat so luong ton kho cua san pham
	public void capNhatSoLuongTon(String maSanPham, int soLuongMoi) {
		for (sanPham sp : danhSachSanPham) {
			if (sp.getMaSanPham().equals(maSanPham)) {
				sp.capNhatSoLuongTon(soLuongMoi);
				System.out.println("Cập nhật số lượng thành công!");
				return;
			}
		}
		System.out.println("Không tìm thấy sản phẩm trong kho.");
	}

	// Thêm sản phẩm mới vào kho
	public void themSanPhamMoi(sanPham sanPham) {
		danhSachSanPham.add(sanPham);
		System.out.println("Thêm sản phẩm mới thành công!");
	}

	// Xóa sản phẩm khỏi kho
	public void xoaSanPham(String maSanPham) {
		danhSachSanPham.removeIf(sanPham -> sanPham.getMaSanPham().equals(maSanPham));
		System.out.println("Sản phẩm đã được xóa khỏi kho.");
	}

}
