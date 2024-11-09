package Functions;

import Classes.Admin;


public class AdminFunc {
    public boolean checkAdmin(Admin admin) {
        if (admin != null) {
            if ("admin".equals(admin.getTen()) 
                    && "admin".equals(admin.getMatKhau())) {
                return true;
            }
        }
        return false;
    }
}
