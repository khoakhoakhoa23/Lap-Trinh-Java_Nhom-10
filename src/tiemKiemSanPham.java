import java.util.List;

public class tiemKiemSanPham {
	private String danhMuc;
	private List<String> ketQuaTimKiem;
	// phuong thuc get and set
	
	public String getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}
	public List<String> getKetQuaTimKiem() {
		return ketQuaTimKiem;
	}
	public void setKetQuaTimKiem(List<String> ketQuaTimKiem) {
		this.ketQuaTimKiem = ketQuaTimKiem;
	}

	// tiem kiem san pham
		public void timKiemSanPham(String tenSanPham) {
			System.out.println("tiem kiem san pham" + tenSanPham);
		}
	// loc san pham
		public void locSanPham(String theoLoai, double giaTien) {
			// tu cho nhung danh sach
			List<String> sanPham = List.of("Loai1 - 100.0", "Loai2 - 200.0", "Loai1 - 150.0");
	        for (String sp : sanPham) {
	            if (sp.contains(theoLoai) && sp.contains(String.valueOf(giaTien))) {
	                ketQuaTimKiem.add(sp);
	            }
	        }
		}
	    		
}