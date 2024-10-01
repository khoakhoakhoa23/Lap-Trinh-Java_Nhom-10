import java.util.List;

public class heThongQuanLi {
	private List danhSachSanPham;
	private List donHang;
	private List taiKhoanKhachHang;
	//construction
	public heThongQuanLi(List danhSachSanPham, List donHang, List taiKhoanKhachHang) {
		
		this.danhSachSanPham = danhSachSanPham;
		this.donHang = donHang;
		this.taiKhoanKhachHang = taiKhoanKhachHang;
	}
	//phuong thuc get and set
	public List getDanhSachSanPham() {
		return danhSachSanPham;
	}
	public void setDanhSachSanPham(List danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}
	public List getDonHang() {
		return donHang;
	}
	public void setDonHang(List donHang) {
		this.donHang = donHang;
	}
	public List getTaiKhoanKhachHang() {
		return taiKhoanKhachHang;
	}
	public void setTaiKhoanKhachHang(List taiKhoanKhachHang) {
		this.taiKhoanKhachHang = taiKhoanKhachHang;
	}

}
