import java.util.List;

public class sanPham extends quanLiGioHang {

	public sanPham(List<sanPham> gioHang, double tongTien) {
		super(gioHang, tongTien);
	}

	private String maSanPham;
	private String tenSanPham;
	private double gia;
	private String moTa;
	private int soLuongTon;
	private String phanLoai;

	// phuong thuc get and set

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public String getPhanLoai() {
		return phanLoai;
	}

	public void setPhanLoai(String phanLoai) {
		this.phanLoai = phanLoai;
	}
	// cap nhat so luong ton

	public void capNhatSoLuongTon(int soLuongMoi) {
		this.soLuongTon = soLuongMoi;
	}

	// cap nhat gia san pham
	public void capNhatGia(double giaMoi) {
		this.gia = giaMoi;
	}

	public String layThongTinSanPham() {
		return "Ma sp:" + maSanPham + "Ten SP: " + tenSanPham + "Gia: " + gia + "so luong ton: " + soLuongTon
				+ "phan loai" + phanLoai;
	}

	@Override
	public String toString() {
		return layThongTinSanPham();
	}
}
