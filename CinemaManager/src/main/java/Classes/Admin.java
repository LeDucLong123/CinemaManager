package Classes;

public class Admin {
    private String ten;
    private String matKhau;

    public Admin() {
    }

    public Admin(String ten, String matKhau) {
        this.ten = ten;
        this.matKhau = matKhau;
    }
    
    

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

   
}
