import java.util.ArrayList;
import java.util.List;


public class danhMucSanPham extends tiemKiemSanPham{
	private String maDanhMuc;
	private String tenDanhMuc;
	private String moTa;
	private List<sanPham> danhSachSanPham = new ArrayList<>();
	// phuong thuc get and set
	public String getMaDanhMuc() {
		return maDanhMuc;
	}
	public void setMaDanhMuc(String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}
	public String getTenDanhMuc() {
		return tenDanhMuc;
	}
	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	// them san pham
	public void themSanPham(sanPham sanPham) {
		danhSachSanPham.add(sanPham);
	}
	// xoa san pham
	public void xoaSanPham(String maSanPham) {
		danhSachSanPham.removeIf(sanPham -> sanPham.getTenSanPham().equals(maSanPham));
	}
	// cap nhat san pham
	public void capNhatSanPham(sanPham sanPham) {
		for(int i =0; i < danhSachSanPham.size();i++) {
			if (danhSachSanPham.get(i).getTenSanPham().equals(sanPham.getMaSanPham()));
		}
	}
	// lay danh sach San pham
	public List<sanPham> layDanhSachSanPham() {
		return danhSachSanPham;
	}
}
