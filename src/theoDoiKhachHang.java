import java.util.List;

public class theoDoiKhachHang  {
	public String danhSachSanPham;
	public String email;
	public int soDienThoai;
	public String diaChi;
	public String tenKhachHang;
	public String maKhachHang;
	public List<String> lichSuMuaHang;
	
	// phuong thuc construction
	public theoDoiKhachHang(String danhSachSanPham, String email, int soDienThoai, String diaChi, String tenKhachHang,
			String maKhachHang, List<String> lichSuMuaHang) {
		this.danhSachSanPham = danhSachSanPham;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.tenKhachHang = tenKhachHang;
		this.maKhachHang = maKhachHang;
		this.lichSuMuaHang = lichSuMuaHang;
	}
	// phuong thuc get and set
	public String getDanhSachSanPham() {
		return danhSachSanPham;
	}
	public void setDanhSachSanPham(String danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(int soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public List<String> getLichSuMuaHang() {
		return lichSuMuaHang;
	}
	public void setLichSuMuaHang(List<String> lichSuMuaHang) {
		this.lichSuMuaHang = lichSuMuaHang;
	}
	// cap nhat thong tin khach hang
	
	public void capNhatThongTin(String ten, String emailMoi, String soDienThoaiMoi, String diaChiMoi){
		
	}
	//lay thong tin khach hang
	public  String layThongTinKhachHang() {
		return danhSachSanPham;
		
	}
	//them lich su mua hang
	public void themLichSuMuaHang(String chiTIetDonHang) {
		
	}
	// lay danh sach lich su mua hang
	public List<String> lichSuMuaHang(){
		return lichSuMuaHang;
	}
	// xoa lich su mua haang
	public void xoaLichSuMuaHang() {
			lichSuMuaHang.clear();
	}
	
	

}
	