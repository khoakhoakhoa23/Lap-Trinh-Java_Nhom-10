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
	public void setDonHang(List<String> donHang) {
		this.donHang = donHang;
	}
	public List getTaiKhoanKhachHang() {
		return taiKhoanKhachHang;
	}
	public void setTaiKhoanKhachHang(List taiKhoanKhachHang) {
		this.taiKhoanKhachHang = taiKhoanKhachHang;
	}
	
	void dangNhap(String taiKhoan, String matKhau) {
		System.out.println("ten tai khoan"+"mat khau");
	}
	// tiem kiem san pham
	public void timKiemSanPham(String tenSanPham) {
		System.out.println("tiem kiem san pham" + tenSanPham);
	}
	
	String quanLyDonHang(String donHangID) {
		return donHangID;
	}
	String theoDoiKhoHang(String maSanPham) {
		return maSanPham;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
