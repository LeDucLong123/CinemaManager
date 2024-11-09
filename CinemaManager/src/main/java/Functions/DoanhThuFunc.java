/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Khach;
import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */

// Lớp định nghĩa các hành vi làm việc với doanh thu
public class DoanhThuFunc {

    private VeFunc veFunc;
    public PhongFunc phongFunc;

    public DoanhThuFunc() {
        veFunc = new VeFunc();
        phongFunc = new PhongFunc();
    }

    public double doanhThu(String tieuChi, Object... obs) {
        double doanhThu = 0;
        veFunc.setVeList(veFunc.readListVes());
        if ("tong".equals(tieuChi.toLowerCase()) && veFunc.getVeList() != null) {
            for (Ve ve : veFunc.getVeList()) {
                doanhThu += ve.getGhe().getGia();
            }
        }
        if ("khach".equals(tieuChi.toLowerCase())) {
            for (Khach ob : (Khach[]) obs) {
                doanhThu += ob.getTongTien();
            }
        }
        if ("phim".equals(tieuChi.toLowerCase())) {
            for (Ve ve : veFunc.getVeList()) {
                for (String phimId : (String[]) obs) {
                    if (ve.getSuat().getPhim().getId() == null ? phimId == null : ve.getSuat().getPhim().getId().equals(phimId)) {
                        doanhThu += ve.getGhe().getGia();
                    }
                }
            }
        }
        return doanhThu;
    }

    public void resetDt(List<Phim> phimList, List<SuatChieu> schList, List<Phong> phongList) {
        for (Phim ph : phimList) {
            ph.setDt(0);
        }
        for (Phong ph : phongList) {
            ph.setDt(0);
        }
        for (SuatChieu sch : schList) {
            sch.setDt(0);
        }
    }

    public List<Phong> searchDoanhThuPhong(String s, String s1) {
        List<Phong> list = new ArrayList<>(); 
        phongFunc.setPhongList(phongFunc.readListPhongs());
        List<Phong> phongList = phongFunc.getPhongList();
        list.addAll(phongList);
        double begin = Double.parseDouble(s);
        double end = Double.parseDouble(s1);
        for (Phong ph : phongList) {
            double tong = ph.getDt();
            if (tong < begin || tong > end) {
                list.remove(ph);
            }
        }
        return list;
    }
}
