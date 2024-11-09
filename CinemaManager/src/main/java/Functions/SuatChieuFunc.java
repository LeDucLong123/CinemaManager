/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import XML.PhongListXML;
import XML.SuatChieuListXML;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.FileUtils;

/**
 *
 * @author Lenovo
 */
// Lớp định nghĩa các hành vi làm việc với danh sách suất chiếu
public class SuatChieuFunc {

    private static final String SCH_FILE_NAME = "xml/SuatChieu.xml";
    private static final String PHONG_FILE_NAME = "xml/Phong.xml";
    public final static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private List<SuatChieu> suatChieuList;
    private Map<String, LocalDateTime[]> schIntervals;

    public SuatChieuFunc() {
        this.suatChieuList = this.readListSuatChieus();
        this.schIntervals = new HashMap<>();
        for (SuatChieu sch : this.suatChieuList) {
            LocalDateTime tgKetThuc = sch.getThoiGianChieu().plusMinutes(sch.getPhim().getThoiLuong().toMinutes());
            this.schIntervals.put(sch.getPhongId(), new LocalDateTime[]{sch.getThoiGianChieu(), tgKetThuc});
        }
    }

    // hành vi viết vào file xml
    public void writeListSuatChieus(List<SuatChieu> schs) {
        SuatChieuListXML schXML = new SuatChieuListXML();
        schXML.setSuatChieu(schs);
        FileUtils.writeXMLtoFile(SCH_FILE_NAME, schXML);
    }

    // hành vi đọc từ file xml
    public List<SuatChieu> readListSuatChieus() {
        List<SuatChieu> list = new ArrayList<>();
        SuatChieuListXML schListXML = (SuatChieuListXML) FileUtils.readXMLFile(
                SCH_FILE_NAME, SuatChieuListXML.class);
        if (schListXML != null && schListXML.getSuatChieu() != null) {
            list = schListXML.getSuatChieu();
            List<Phong> phongList = readListPhongs();
            if (phongList != null) {
                for (SuatChieu sch : list) {
                    for (Phong ph : phongList) {
                        if (sch.getPhongId() == null ? ph.getId() == null : sch.getPhongId().equals(ph.getId())) {
                            sch.setPhong(ph);
                        }
                    }
                }
            }
        }
        return list;
    }

    public List<Phong> readListPhongs() {
        List<Phong> list = new ArrayList<>();
        PhongListXML phongListXML = (PhongListXML) FileUtils.readXMLFile(
                PHONG_FILE_NAME, PhongListXML.class);
        if (phongListXML != null) {
            list = phongListXML.getPhong();
        }
        return list;
    }

    public SuatChieu taoSuatChieu(Phim phim, Phong ph, String tgChieu) {
        LocalDateTime TgChieu = LocalDateTime.parse(tgChieu);
        System.out.println(this.schIntervals.keySet().toString());
        for (Map.Entry<String, LocalDateTime[]> dt : this.schIntervals.entrySet()) {
            if (dt.getKey().equals(ph.getId())) {
                if (TgChieu.isAfter(dt.getValue()[0]) && TgChieu.isBefore(dt.getValue()[1].plusMinutes(phim.getThoiLuong().toMinutes()))) {
                    System.out.println("no sch");
                    return null;
                }
            }
        }
        SuatChieu sch = new SuatChieu();
        sch.setPhim(phim);
        sch.setPhong(ph);
        sch.setThoiGianChieu(TgChieu.format(formatDateTime));
        LocalDateTime tgKetThuc = TgChieu.plusMinutes(sch.getPhim().getThoiLuong().toMinutes());
        this.schIntervals.put(ph.getId(), new LocalDateTime[]{sch.getThoiGianChieu(), tgKetThuc});
        return sch;
    }

    public void themSuatChieu(SuatChieu sch) {
        this.suatChieuList.add(sch);
        writeListSuatChieus(suatChieuList);
    }

    public void editSuatChieu(SuatChieu p) {
        for (int i = 0; i < this.suatChieuList.size(); i++) {
            SuatChieu ph = this.suatChieuList.get(i);
            if (ph.getId() == null ? p.getId() == null : ph.getId().equals(p.getId())) {
                ph.setPhim(p.getPhim());
                ph.setPhong(p.getPhong());
                ph.setPhongId();
                ph.setThoiGianChieu((CharSequence) p.inThoiGianChieu());
                ph.setDt(p.getDt());
            }
        }
        this.writeListSuatChieus(suatChieuList);
    }

    public void xoaSuatChieu(SuatChieu p) {
        String pId = p.getId();
        List<SuatChieu> list = new ArrayList<>();
        list = this.getSuatChieuList();
        for (SuatChieu ph : list) {
            if (ph.getId() == null ? pId == null : ph.getId().equals(pId)) {
                list.remove(ph);
                return;
            }
        }
        this.setSuatChieuList(list);
        this.writeListSuatChieus(suatChieuList);
    }

    public class SortIdSch implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortIdSch(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            if (beLon == true) {
                return o1.getId().compareTo(o2.getId());
            } else {
                System.out.println(beLon);
                return o2.getId().compareTo(o1.getId());
            }
        }

    }

    public class SortDtSch implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortDtSch(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            if (beLon) {
                return (int) o1.getDt() - (int) o2.getDt();
            } else {
                return (int) o2.getDt() - (int) o1.getDt();
            }
        }
    }

    public class SortTgSch implements Comparator<SuatChieu> {

        private boolean beLon;

        public SortTgSch(boolean beLon) {
            this.beLon = beLon;
        }

        @Override
        public int compare(SuatChieu o1, SuatChieu o2) {
            int num;
            if (o1.getThoiGianChieu().isBefore(o2.getThoiGianChieu())) {
                num = -1;
            } else if (o2.getThoiGianChieu().isBefore(o1.getThoiGianChieu())) {
                num = 1;
            } else {
                return 0;
            }
            if (beLon) {
                return num;
            } else {
                return -num;
            }
        }
    }

    public ArrayList<SuatChieu> sapXepSuatChieu(ArrayList<SuatChieu> list, String tieuChi, boolean beLon) {
        if ("id".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortIdSch(beLon));
        }
        if ("doanhthu".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortDtSch(beLon));
        }
        if ("thoigian".equals(tieuChi.toLowerCase())) {
            Collections.sort(list, new SortTgSch(beLon));
        }
        this.writeListSuatChieus(list);
        return list;
    }

    public void checkChieuXong() {
        for (SuatChieu sch : this.suatChieuList) {
            if (sch.isChieuXong()) {
                this.xoaSuatChieu(sch);
            }
        }
        this.writeListSuatChieus(this.getSuatChieuList());
    }

    public List<SuatChieu> getSuatChieuList() {
        return this.readListSuatChieus();
    }

    public void setSuatChieuList(List<SuatChieu> suatChieuList) {
        this.suatChieuList = suatChieuList;
    }

}
