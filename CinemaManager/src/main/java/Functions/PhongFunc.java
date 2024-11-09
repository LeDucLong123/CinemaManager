/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.GheDoi;
import Classes.GheThuong;
import Classes.GheVip;
import Classes.Phong;
import Classes.SuatChieu;
import XML.PhongListXML;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */

// Lớp định nghĩa các hành vi làm việc với danh sách phòng
public class PhongFunc {

    private static final String PHONG_FILE_NAME = "xml/Phong.xml";
    private List<Phong> phongList;
    private SuatChieuFunc suatChieuFunc;

    public PhongFunc() {
        this.phongList = readListPhongs();
        this.suatChieuFunc = new SuatChieuFunc();
    }

    // hành vi viết vào file xml
    public void writeListPhongs(List<Phong> phongs) {
        PhongListXML phongXML = new PhongListXML();
        phongXML.setPhong(phongs);
        FileUtils.writeXMLtoFile(PHONG_FILE_NAME, phongXML);
    }

    // hành vi đọc từ file xml
    public List<Phong> readListPhongs() {
        List<Phong> list = new ArrayList<>();
        PhongListXML phongListXML = (PhongListXML) FileUtils.readXMLFile(
                PHONG_FILE_NAME, PhongListXML.class);
        if (phongListXML != null) {
            list = phongListXML.getPhong();
        }
        return list;
    }

    public Phong taoPhong(Phong p) {
        for (int i = 0; i < p.getSlThuong(); i++) {
            GheThuong gt = new GheThuong();
            p.themGhe(gt);
        }
        for (int i = 0; i < p.getSlVip(); i++) {
            GheVip gv = new GheVip();
            p.themGhe(gv);
        }
        for (int i = 0; i < p.getSlDoi(); i++) {
            GheDoi gd = new GheDoi();
            p.themGhe(gd);
        }
        return p;
    }

    public void themPhong(Phong p) {
        if (this.phongList == null) {
            this.phongList = new ArrayList<>();
        }
        this.phongList.add(taoPhong(p));
        writeListPhongs(phongList);
    }

    public void editPhong(Phong p) {
        for (Phong ph : this.phongList) {
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setSlThuong(p.getSlThuong());
                ph.setSlVip(p.getSlVip());
                ph.setSlDoi(p.getSlDoi());
                ph.setDsGheThuong(p.getDsGheThuong());
                ph.setDsGheVip(p.getDsGheVip());
                ph.setDsGheDoi(p.getDsGheDoi());
                ph.setIsFull(p.getIsFull());
                ph.setSuatChieu(p.getSuatChieu());
                ph.setIsPlaying(p.getIsPlaying());
                if (p.getSuatChieu() != null) {
                    ph.setSuatChieu(p.getSuatChieu());
                }
            }
        }
        this.setPhongList(phongList);
        this.writeListPhongs(phongList);
    }

    public void xoaPhong(Phong p) {
        String pId = p.getId();
        List<Phong> list = new ArrayList<>();
        list = this.getPhongList();
        for (Phong ph : list) {
            if (ph.getId() == null ? pId == null : ph.getId().equals(pId)) {
                list.remove(ph);
                break;
            }
        }
        this.setPhongList(list);
        this.writeListPhongs(phongList);
    }

    public class SortDtPhong implements Comparator<Phong> {

        private boolean beLon;

        public SortDtPhong(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                return (int) o1.getDt() - (int) o2.getDt();
            } else {
                return (int) o2.getDt() - (int) o1.getDt();
            }
        }
    }

    public class SortPhongSc implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongSc(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                return o1.getSucChua() - o2.getSucChua();
            } else {
                return o2.getSucChua() - o1.getSucChua();
            }
        }
    }

    public class SortPhongTsg implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongTsg(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                return o1.getTongGhe() - o2.getTongGhe();
            } else {
                return o2.getTongGhe() - o1.getTongGhe();
            }
        }
    }

    public class SortPhongLd implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongLd(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                if (o1.getIsFull() == true) {
                    return o2.getIsFull() == false ? 1 : 0;
                } else {
                    return o2.getIsFull() == true ? -1 : 0;
                }
            } else {
                if (o1.getIsFull() == false) {
                    return o2.getIsFull() == true ? 1 : 0;
                } else {
                    return o2.getIsFull() == false ? -1 : 0;
                }
            }
        }
    }

    public class SortPhongPhim implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongPhim(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (!beLon) {
                if (o2.getSuatChieu() == null) {
                    return 1;
                }
                if (o1.getSuatChieu() == null) {
                    return 1;
                }
                return o1.getSuatChieu().getPhim().getTen().compareTo(o2.getSuatChieu().getPhim().getTen());
            } else {
                if (o2.getSuatChieu() == null) {
                    return -1;
                }
                if (o1.getSuatChieu() == null) {
                    return -1;
                }
                return o2.getSuatChieu().getPhim().getTen().compareTo(o1.getSuatChieu().getPhim().getTen());
            }
        }
    }

    public class SortPhongTt implements Comparator<Phong> {

        private boolean beLon;

        public SortPhongTt(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(Phong o1, Phong o2) {
            if (beLon) {
                if (o1.getIsPlaying() == true) {
                    return o2.getIsPlaying() == false ? 1 : 0;
                } else {
                    return o2.getIsPlaying() == true ? -1 : 0;
                }
            } else {
                if (o1.getIsPlaying() == false) {
                    return o2.getIsPlaying() == true ? 1 : 0;
                } else {
                    return o2.getIsPlaying() == false ? -1 : 0;
                }
            }
        }
    }

    public ArrayList<Phong> sapXepPhong(ArrayList<Phong> list, String tieuChi, boolean beLon) {
        if ("tổng số ghế".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongTsg(beLon));
        }
        if ("sức chứa".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongSc(beLon));
        }
        if ("doanhthu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortDtPhong(beLon));
        }
        if ("lấp đầy".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongLd(beLon));
        }
        if ("phim chiếu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongPhim(beLon));
        }
        if ("tình trạng".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new PhongFunc.SortPhongTt(beLon));
        }
        return list;
    }

    public void addDt() {
        for (Phong ph : phongList) {
            for (SuatChieu sch : suatChieuFunc.getSuatChieuList()) {
                if (sch.getPhongId() == null ? ph.getId() == null : sch.getPhongId().equals(ph.getId())) {
                    ph.themDt(sch.getDt());
                }
            }
        }
        this.writeListPhongs(phongList);
    }

    public void checkPlaying() {
        if (this.getPhongList() != null) {
            for (Phong ph : this.getPhongList()) {
                if (ph.getSuatChieu() == null) {
                    continue;
                }
                if (ph.getSuatChieu().getThoiGianChieu().isBefore(LocalDateTime.now())) {
                    ph.setIsPlaying(true);
                    Duration interval = Duration.between(LocalDateTime.now(), ph.getSuatChieu().getThoiGianChieu());
                    long intervalAbs = Math.abs(interval.toMinutes());
                    if (intervalAbs >= ph.getSuatChieu().getPhim().getThoiLuong().toMinutes() + 30) {
                        ph.setIsPlaying(false);
                    }
                }
                if (LocalDateTime.now().isAfter(ph.getSuatChieu().getThoiGianChieu().plusMinutes(ph.getSuatChieu().getPhim().getThoiLuong().toMinutes()))) {
                    ph.resetDsGhe();
                }
            }
        }
        this.writeListPhongs(phongList);
    }

    public void checkIsFull() {
        if (this.getPhongList() != null) {
            for (Phong ph : this.getPhongList()) {
                if (ph.getDsGheTrong().isEmpty()) {
                    ph.setIsFull(true);
                }
            }
        }
        this.writeListPhongs(phongList);
    }

    public void reSetPhong() {
        if (this.getPhongList() != null) {
            for (Phong ph : this.getPhongList()) {
                if (ph.getSuatChieu() != null) {
                    updateSuatChieu(ph);
                }
                if (ph.getSuatChieu() != null && ph.getSuatChieu().isChieuXong()) {
                    ph.resetDsGhe();
                    ph.setSuatChieu(null);
//                    updateSuatChieu(ph);
                }  
            }

        }
        this.writeListPhongs(phongList);
    }

    public void updateSuatChieu(Phong ph) {
        if (suatChieuFunc.getSuatChieuList() != null) {
            LocalDateTime now = LocalDateTime.now();
            long wait = 0;
            for (SuatChieu sch : suatChieuFunc.getSuatChieuList()) {
                if (sch.getPhongId().equals(ph.getId())) {
                    wait = Duration.between(now, sch.getThoiGianChieu()).toMinutes();
                    break;
                }
            }
            for (SuatChieu sch : suatChieuFunc.getSuatChieuList()) {
                if (sch.getPhongId().equals(ph.getId())) {
                    if (Duration.between(now, sch.getThoiGianChieu()).toMinutes() <= wait) {
                        wait = Duration.between(now, sch.getThoiGianChieu()).toMinutes();
                        ph.setSuatChieu(sch);
                    }
                }
            }
        }
    }

    public List<Phong> getPhongList() {
        return phongList;
    }

    public void setPhongList(List<Phong> phongList) {
        this.phongList = phongList;
    }
}
