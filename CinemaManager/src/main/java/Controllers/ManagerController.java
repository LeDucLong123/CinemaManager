/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Classes.Ghe;
import Classes.GheDoi;
import Classes.GheThuong;
import Classes.GheVip;
import Classes.Khach;
import Classes.Phim;
import Classes.Phong;
import Classes.SuatChieu;
import Classes.Ve;
import Functions.DoanhThuFunc;
import Functions.KhachFunc;
import Functions.PhimFunc;
import Functions.PhongFunc;
import Functions.SuatChieuFunc;
import Functions.VeFunc;
import Views.ManagerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lenovo
 */
public class ManagerController {

    private ManagerView managerView;
    private PhongFunc phongFunc;
    private PhimFunc phimFunc;
    private SuatChieuFunc suatChieuFunc;
    private KhachFunc khachFunc;
    private VeFunc veFunc;
    private DoanhThuFunc doanhThuFunc;

    public ManagerController(ManagerView managerView) {
        // khởi tạo lớp Jframe hiển thị giao diện
        this.managerView = managerView;
        // khởi tạo đối tượng của các lớp chứa các hành vi quản lý danh sách các đối
        // tượng
        phongFunc = new PhongFunc();
        phimFunc = new PhimFunc();
        suatChieuFunc = new SuatChieuFunc();
        khachFunc = new KhachFunc();
        doanhThuFunc = new DoanhThuFunc();
        veFunc = new VeFunc();
        // gọi hàm gán chức năng (các listener) cho các thành phần trong giao diện
        // suất chiếu
        managerView.addListSuatChieuSelectionListener(new ListSuatChieuSelectionListener());
        managerView.addEditSuatChieuListener(new EditSuatChieuListener());
        managerView.addAddSuatChieuListener(new AddSuatChieuListener());
        managerView.addClearSchFieldListener(new ClearSuatChieuListener());
        managerView.addDeleteSuatChieuListener(new DeleteSuatChieuListener());
        managerView.addSortSuatChieuListener(new SapXepSuatChieuListener());
        managerView.addSchDateTimePickerListener(new ShowSchDateTimeListener());
        // phòng
        managerView.addListPhongSelectionListener(new ListPhongSelectionListener());
        managerView.addEditPhongListener(new EditPhongListener());
        managerView.addClearPhongFieldListener(new ClearPhongListener());
        managerView.addDeletePhongListener(new DeletePhongListener());
        managerView.addSortPhongListener(new SapXepPhongListener());
        // doanh thu
        managerView.addSortDoanhThuPhimListener(new SapXepDtPhimListener());
        managerView.addSortDoanhThuSchListener(new SapXepDtSchListener());
        managerView.addAddPhongListener(new AddPhongListener());
        managerView.addSortDoanhThuPhongListener(new SapXepDtPhongListener());
        managerView.addResetDoanhThuListener(new ResetDtListner());
        managerView.addSearchDoanhThuListener(new SearchDoanhThuListener());
        // phim
        managerView.addAddPhimListener(new AddPhimListener());
        managerView.addClearPhimListener(new ClearPhimListener());
        managerView.addListPhimSelectionListener(new ListPhimSelectionListener());
        managerView.addDeletePhimListener(new DeletePhimListener());
        managerView.addEditPhimListener(new EditPhimListener());
        managerView.addSearchPhimListener(new SearchPhimListener());
        // khách
        managerView.addAddKhachListener(new AddKhachListener());
        managerView.addClearListener(new ClearKhachListener());
        managerView.addListKhachSelectionListener(new ListKhachSelectionListener());
        managerView.addDeleteKhachListener(new DeleteKhachListener());
        managerView.addEditKhachListener(new EditKhachListener());
        managerView.addSearchKhachListener(new SearchKhachListener());
        managerView.addNSDatePickerListener(new ShowNSDateListener());
        // vé
        managerView.addSearchVeListener(new SearchVeListener());
        managerView.addDeleteVeListener(new DeleteVeListener());
        managerView.addSortNameVeListener(new SortByNameVeListener());
        managerView.addSortPhimVeListener(new SortByPhimVeListener());
        // đặt vé
        managerView.addDatVeListener(new DatVeListener());
        managerView.addListSuatChieuDatVeSelectionListener(new ListSuatChieuDatVeSelectionListener());
        managerView.addListGheSelectionListener(new ListGheSelectionListener());
        // ghế
        managerView.addselectSeatListener(new SelectSeatListener());
        managerView.addListGheSelectionListener(new ListGheSelectionListener());
        // reload
        managerView.addReloadListener(new ReloadListener());

    }

    public void showManagerView() {
        // kiểm tra và cập nhật các đối tượng thay đổi theo thời gian, cụ thể là phòng
        // và suất chiếu
        phongFunc.checkPlaying();
        phongFunc.checkIsFull();
        phongFunc.reSetPhong();
        suatChieuFunc.checkChieuXong();
        // hiển thị giao diện quản lý
        this.managerView.setVisible(true);
        // khởi tạo các thành phần của trang chủ
        managerView.clearScrollList();
        managerView.showScrollPhimList(phimFunc.getPhimList());
        managerView.showScrollPhongList(phongFunc.getPhongList());
        managerView.showScrollSuatList(suatChieuFunc.getSuatChieuList());
        // hiển thị bảng danh sách phòng và khởi tạo thành phần combo (menu lựa chọn)
        managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
        managerView.setPhongSortCombo(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
        // hiển thị bảng danh sách phim và khởi tạo thành phần spinner (xoay chọn số)
        managerView.showListPhim(phimFunc.getPhimList());
        managerView.setTheLoaiComboAndDoTuoiSpinner();
        // hiển thị bảng danh sách suất chiếu và khởi tạo thành phần combo (menu lựa
        // chọn)
        managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
        managerView.setSuatChieuCombo(phimFunc.getPhimList(), phongFunc.getPhongList());
        // hiển thị danh sách bảng khách hàng và khởi tạo thành phần combo (menu lựa
        // chọn giới tính)
        managerView.showListKhach(khachFunc.getKhachList());
        managerView.setGioiTinhCombo();
        // hiển thị bảng danh sách vé và khởi tạo thành phần combo (menu lựa chọn)
        managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
        managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(), khachFunc.getKhachList());
        // hiển thị bảng danh sách suất chiếu trong phần đặt vé
        managerView.showListSuatChieuDatVe(suatChieuFunc.getSuatChieuList());
        // hiển thị các giá trị doanh thu và danh sách các đối tượng theo doanh thu
        managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
        managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
        managerView.showDoanhThuSch(suatChieuFunc.getSuatChieuList(), veFunc.getVeList());
        managerView.showDtPhongList(phongFunc.getPhongList(), veFunc.getVeList());
    }

    // Listener reload
    class ReloadListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.setVisible(false);
            showManagerView();
        }
    }

    // Quản lý Phòng listener
    class ListPhongSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillPhongFromSelectedRow(phimFunc.getPhimList());
        }
    }

    class AddPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongFunc.getPhongList());
            if (phong != null) {
                phongFunc.themPhong(phong);
                managerView.showPhong(phong);
                managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    class EditPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongFunc.getPhongList());
            for (int i = 0; i < phong.getSlThuong(); i++) {
                GheThuong gt = new GheThuong();
                phong.themGhe(gt);
            }
            for (int i = 0; i < phong.getSlVip(); i++) {
                GheVip gv = new GheVip();
                phong.themGhe(gv);
            }
            for (int i = 0; i < phong.getSlDoi(); i++) {
                GheDoi gd = new GheDoi();
                phong.themGhe(gd);
            }
            if (phong != null) {
                phongFunc.editPhong(phong);
                managerView.showPhong(phong);
                managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Cập nhật thành công!\nTất cả các ghế đều trống");
            }
        }
    }

    class ClearPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearPhongInfo();
        }
    }

    class DeletePhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phong phong = managerView.getPhongInfo(phongFunc.getPhongList());
            if (phong != null) {
                phongFunc.xoaPhong(phong);
                if (phong.getSuatChieu() != null) {
                    suatChieuFunc.xoaSuatChieu(phong.getSuatChieu());
                }
                managerView.clearPhongInfo();
                managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class SapXepPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phongFunc.setPhongList(phongFunc.sapXepPhong((ArrayList) phongFunc.getPhongList(),
                    managerView.getTieuChiSxPhong(), managerView.getPhongTangDan()));
            managerView.showListPhong(phongFunc.getPhongList(), suatChieuFunc.getSuatChieuList());
            phongFunc.writeListPhongs(phongFunc.getPhongList());
        }
    }
    // end Quản lý phòng listener

    // Quản lý phim listener
    class AddPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                phim.setPosterLink("/Images/no_poster.jpg");
                phimFunc.themPhim(phim);
                managerView.showPhim(phim);
                managerView.showListPhim(phimFunc.getPhimList());
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    class ClearPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearPhimInfo();
        }
    }

    class DeletePhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                phimFunc.xoaPhim(phim);
                for (SuatChieu sc : suatChieuFunc.getSuatChieuList()) {
                    if (sc.getPhim().getId() == null ? phim.getId() == null
                            : sc.getPhim().getId().equals(phim.getId())) {
                        suatChieuFunc.xoaSuatChieu(sc);
                    }
                }
                managerView.clearPhimInfo();
                managerView.showListPhim(phimFunc.getPhimList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class EditPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Phim phim = managerView.getPhimInfo();
            if (phim != null) {
                phimFunc.editPhim(phim);
                managerView.showPhim(phim);
                managerView.showListPhim(phimFunc.getPhimList());
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class ListPhimSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            String link = "/Images/no_poster.jpg";
            for (Phim ph : phimFunc.getPhimList()) {
                if (ph.getId() == null ? managerView.getIDPhimField() == null
                        : ph.getId().equals(managerView.getIDPhimField())) {
                    link = ph.getPosterLink();
                    break;
                }
            }
            managerView.fillPhimFromSelectedRow(link);
        }
    }

    class SearchPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int k = managerView.luachonPhimTK();
            String s = managerView.inforPhimSearch();
            switch (k) {
                case 0:
                    managerView.showListPhim(phimFunc.searchTen(s));
                    break;
                case 1:
                    managerView.showListPhim(phimFunc.searchTheLoai(s));
                    break;
                case -1:
                    managerView.showListPhim(phimFunc.getPhimList());
                    break;
                case 2:
                    managerView.showListPhim(phimFunc.searchDoTuoi(s));
                    break;
                default:
                    break;
            }

        }

    }

    // end Quản lý phim listner

    // Quản lý suất chiếu listener
    class ListSuatChieuSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillSuatChieuFromSelectedRow(phimFunc.getPhimList());
        }
    }

    class EditSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = managerView.getSchInfo(phimFunc.getPhimList(), phongFunc.getPhongList());
            if (sch != null) {
                for (Phong ph : phongFunc.getPhongList()) {
                    if (ph.getId().equals(sch.getPhongId())) {
                        if (ph.getIsPlaying()) {
                            managerView.showMessage("Phòng chưa rảnh");
                            return;
                        }
                        ph.setSuatChieu(sch);
                        phongFunc.editPhong(ph);
                    }
                }
                suatChieuFunc.editSuatChieu(sch);
                managerView.showSuatChieu(sch);
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class ShowSchDateTimeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.confirmSchDateTimePicker(managerView.getSchDateTimePicker());
        }
    }

    class AddSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("check sch");
            SuatChieu infoSch = managerView.getSchInfo(phimFunc.getPhimList(), phongFunc.getPhongList());
            if (infoSch == null) {
                return;
            }
            SuatChieu newSch = suatChieuFunc.taoSuatChieu(infoSch.getPhim(), infoSch.getPhong(),
                    infoSch.getThoiGianChieu().toString());
            if (newSch == null) {
                managerView.showMessage("Thời gian chiếu trùng với suất chiếu khác");
                return;
            }
            if (phongFunc.getPhongList() != null) {
                for (Phong ph : phongFunc.getPhongList()) {
                    if (ph.getId().equals(newSch.getPhongId())) {
                        if (ph.getIsPlaying()) {
                            managerView.showMessage("Phòng chưa rảnh");
                            return;
                        }
                        if (ph.getSuatChieu() == null) {
                            ph.setSuatChieu(newSch);
                        } else {
                            if (ph.getSuatChieu().isChieuXong()) {
                                ph.setSuatChieu(newSch);
                            }
                        }
                        phongFunc.editPhong(ph);
                    }
                }
            }
            suatChieuFunc.themSuatChieu(newSch);
            managerView.showSuatChieu(newSch);
            managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
            managerView.showMessage("Thêm thành công!");
        }
    }

    class ClearSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearSuatChieuInfo();
        }
    }

    class DeleteSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu sch = managerView.getSchInfo(phimFunc.getPhimList(), phongFunc.getPhongList());
            if (sch != null) {
                if (phongFunc.getPhongList() != null) {
                    for (Phong ph : phongFunc.getPhongList()) {
                        if (ph.getId().equals(sch.getPhongId())) {
                            ph.setSuatChieu(null);
                            ph.setIsPlaying(false);
                            phongFunc.editPhong(ph);
                        }
                    }
                }
                suatChieuFunc.xoaSuatChieu(sch);
                managerView.clearSuatChieuInfo();
                managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class SapXepSuatChieuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            suatChieuFunc.setSuatChieuList(suatChieuFunc.sapXepSuatChieu((ArrayList) suatChieuFunc.getSuatChieuList(),
                    managerView.getTieuChiSxSch(), managerView.getSchTangDan()));
            managerView.showListSuatChieu(suatChieuFunc.getSuatChieuList());
            suatChieuFunc.writeListSuatChieus(suatChieuFunc.getSuatChieuList());
        }
    }
    // end Quản lý suất chiếu listener

    // Quản lý Khach listner
    class ShowNSDateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.confirmNSPicker(managerView.getNgaySinhPicker());
        }
    }

    class AddKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Khach khach = managerView.getKhachInfo();
            if (khach != null) {
                // khachDao = new KhachDao();
                khachFunc.themKhach(khach);
                managerView.showKhach(khach);
                managerView.showListKhach(khachFunc.getKhachList());
                // studentView.showListStudents(studentDao.getListStudents());
                managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(),
                        khachFunc.getKhachList());
                managerView.showMessage("Thêm thành công!");
            }
        }
    }

    class ClearKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            managerView.clearKhachInfo();
        }
    }

    class DeleteKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Khach khach = managerView.getKhachInfo();
            if (khach != null) {
                khachFunc.xoaKhach(khach);
                managerView.clearKhachInfo();
                managerView.showListKhach(khachFunc.getKhachList());
                managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(),
                        khachFunc.getKhachList());
                managerView.showMessage("Xóa thành công!");
            }
        }
    }

    class EditKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Khach khach = managerView.getKhachInfo();
            if (khach != null) {
                khachFunc.editKhach(khach);
                managerView.showKhach(khach);
                managerView.showListKhach(khachFunc.getKhachList());
                managerView.setVeCombo(suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList(),
                        khachFunc.getKhachList());
                managerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class ListKhachSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillKhachFromSelectedRow();
        }
    }

    class SearchKhachListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int k = managerView.luachonKhachTK();
            String s = managerView.inforKhachSearch();
            if (k == 0) {
                managerView.showListKhach(khachFunc.searchTen(s));
            } else if (k == 1) {
                managerView.showListKhach(khachFunc.searchSLv(s));
            } else if (k == -1) {
                managerView.showListKhach(khachFunc.getKhachList());
            }

        }

    }
    // end Quản lý Khach listener

    // Đặt vé listener
    class DatVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SuatChieu Sch = managerView.getDatVeSchInfo(suatChieuFunc.getSuatChieuList());
            Ghe ghe = managerView.getGheInfor(Sch.getPhong());

            Ve ve = new Ve();
            Khach khach = managerView.getKhachInfor(khachFunc.getKhachList());
            ghe.setKhachId(khach.getId());
            ve.setSuat(Sch);

            ve.setGhe(ghe);
            khachFunc.muaVe(khach, ve);
            veFunc.themVe(ve);
            for (Phong ph : phongFunc.getPhongList()) {
                if (ph.getId() == null ? ve.getSuat().getPhongId() == null
                        : ph.getId().equals(ve.getSuat().getPhongId())) {
                    if ("Thuong".equals(ghe.getLoai())) {
                        ph.getDsGheThuong().get(ghe.getViTri()).setKhachId(ghe.getKhachId());
                        ph.getDsGheThuong().get(ghe.getViTri()).setThoiGianDat(LocalDateTime.now());
                    }
                    if ("Vip".equals(ghe.getLoai())) {
                        ph.getDsGheVip().get(ghe.getViTri()).setKhachId(ghe.getKhachId());
                        ph.getDsGheVip().get(ghe.getViTri()).setThoiGianDat(LocalDateTime.now());
                    }
                    if ("Doi".equals(ghe.getLoai())) {
                        ph.getDsGheDoi().get(ghe.getViTri()).setKhachId(ghe.getKhachId());
                        ph.getDsGheDoi().get(ghe.getViTri()).setThoiGianDat(LocalDateTime.now());
                    }
                    ph.themDt(ghe.getGia());
                    phongFunc.editPhong(ph);
                    break;
                }
            }
            Sch.themDt(ve.getGhe().getGia());
            suatChieuFunc.editSuatChieu(Sch);
            Sch.getPhim().themDt(ve.getGhe().getGia());
            phimFunc.editPhim(Sch.getPhim());
            suatChieuFunc.editSuatChieu(Sch);
            managerView.closeSeatDialog();
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
            managerView.showListKhach(khachFunc.getKhachList());
            managerView.showMessage("Đặt vé thành công!");
            managerView.clearSuatChieuDatVeInfo();
            managerView.showSuatChieuDatVe(Sch);
            managerView.showDoanhThu(doanhThuFunc.doanhThu("tong", (Object) null));
            managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
            managerView.showDtPhongList(phongFunc.getPhongList(), veFunc.getVeList());
            managerView.showDoanhThuSch(suatChieuFunc.getSuatChieuList(), veFunc.getVeList());
        }
    }

    class ListSuatChieuDatVeSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillSuatChieuDatVeFromSelectedRow(phimFunc.getPhimList());

        }
    }

    class ListGheSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            managerView.fillGheFromSelectedRow();
        }
    }

    class SelectSeatListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Ve ve = new Ve();
            Ghe ghe = new Ghe();
            Khach khach = managerView.getKhachInfor(khachFunc.getKhachList());
            SuatChieu Sch = managerView.getDatVeSchInfo(suatChieuFunc.getSuatChieuList());
            if (Sch.getPhong() != null) {
                ghe.setKhachId(khach.getId());
                ve.setSuat(Sch);
                managerView.showListGhe(Sch.getPhong());
                managerView.fillGheFromSelectedRow();
                managerView.showSeatDialog();
            } else {
                managerView.showMessage("Chọn suất chiếu trước khi chọn ghế");
            }
        }
    }

    // end Đặt vé listener

    // Quản lý vé listener
    class SearchVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int k = managerView.luachonVeTK();
            String s = managerView.inforVeSearch();
            switch (k) {
                case 0:
                    managerView.showListVe(veFunc.SearchIdVe(s), khachFunc.getKhachList());
                    break;
                case 1:
                    managerView.showListVe(veFunc.SearchKhachVe(s), khachFunc.getKhachList());
                    break;
                case 2:
                    managerView.showListVe(veFunc.SearchVitriVe(s), khachFunc.getKhachList());
                    break;
                case 3:
                    managerView.showListVe(veFunc.SearchLoaiGhe(s), khachFunc.getKhachList());
                    break;
                case 4:
                    managerView.showListVe(veFunc.SearchTenPhim(s), khachFunc.getKhachList());
                    break;
                case 5:
                    managerView.showListVe(veFunc.SearchPhong(s), khachFunc.getKhachList());
                    break;
                case 6:
                    managerView.showListVe(veFunc.SearchThoiGian(s), khachFunc.getKhachList());
                    break;
                default:
                    managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
                    break;
            }

        }
    }

    class DeleteVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String s = managerView.getIDVe();
            veFunc.xoaVe(s);
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
            khachFunc.writeListKhachs(khachFunc.getKhachList());
            managerView.showListKhach(khachFunc.getKhachList());

        }
    }

    class SortByNameVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            veFunc.sortVeByName(khachFunc.getKhachList());
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());

        }
    }

    class SortByPhimVeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            veFunc.sortVeByPhim(khachFunc.getKhachList());
            managerView.showListVe(veFunc.getVeList(), khachFunc.getKhachList());
        }
    }
    // end Quản lý vé listener

    // Quản lý doanh thu listener
    class SapXepDtPhimListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phimFunc.setPhimList(phimFunc.sapXepPhim((ArrayList) phimFunc.getPhimList(), "doanhthu",
                    managerView.getDtPhimTangDan()));
            managerView.showDoanhThuPhim(phimFunc.getPhimList(), veFunc.getVeList());
            phimFunc.writeListPhims(phimFunc.getPhimList());
        }
    }

    class SapXepDtSchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            suatChieuFunc.setSuatChieuList(suatChieuFunc.sapXepSuatChieu((ArrayList) suatChieuFunc.getSuatChieuList(),
                    "doanhthu", managerView.getDtSchTangDan()));
            managerView.showDoanhThuSch(suatChieuFunc.getSuatChieuList(), veFunc.getVeList());
            suatChieuFunc.writeListSuatChieus((ArrayList) suatChieuFunc.getSuatChieuList());
        }
    }

    class SapXepDtPhongListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            phongFunc.setPhongList(phongFunc.sapXepPhong((ArrayList) phongFunc.getPhongList(), "doanhthu",
                    managerView.getDtPhongTangDan()));
            managerView.showDtPhongList(phongFunc.getPhongList(), veFunc.getVeList());
            phongFunc.writeListPhongs(phongFunc.getPhongList());
        }
    }

    class ResetDtListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doanhThuFunc.resetDt(phimFunc.getPhimList(), suatChieuFunc.getSuatChieuList(), phongFunc.getPhongList());
            veFunc.setVeList(new ArrayList<>());
            veFunc.writeListVes(veFunc.getVeList());
            phimFunc.writeListPhims(phimFunc.getPhimList());
            suatChieuFunc.writeListSuatChieus(suatChieuFunc.getSuatChieuList());
            phongFunc.writeListPhongs(phongFunc.getPhongList());
            new ReloadListener().actionPerformed(e);
        }

    }

    class SearchDoanhThuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String begin = managerView.getInforDtbegin();
            String end = managerView.getInforDtend();
            managerView.showDtPhongList(doanhThuFunc.searchDoanhThuPhong(begin, end), veFunc.getVeList());
        }

    }
    // end Quản lý doanh thu listener

}
