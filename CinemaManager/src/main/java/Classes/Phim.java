/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.DurationAdapter;
import Classes.Adaptaters.LocalDateAdapter;
import static Classes.Khach.dateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "phim") // anotation xác định element được đọc vào file xml
@XmlAccessorType(XmlAccessType.FIELD) // đọc dưới dạng trường, nhận các thuộc tính là một element
public class Phim {

    private static int currId;
    @XmlAttribute // xác định cách đọc thuộc tính dưới dạng thuộc tính của element mẹ
    private String id;
    private String ten, theLoai;
    private int doTuoi;
    private String posterLink;
    private double dt;
    @XmlJavaTypeAdapter(DurationAdapter.class) // gán adapter cho thuộc tính dạng thời lượng để có thể đọc được vào trong file xml
    private Duration thoiLuong;
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // gán adapter cho thuộc tính dạng ngày giờ để có thể đọc được vào trong file xml
    private LocalDate TgKhoiChieu;
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String[] dsTheLoai = {"Hai huoc", "Hanh dong", "Khoa hoc vien tuong", "Kinh di", "Tinh cam", "Lich su"};
    public static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("mm");

    public Phim() {
        currId++;
        id = "Ph" + currId;
    }

    public Phim(String ten, long thoiLuong, String theLoai, int doTuoi) {
        currId++;
        this.id = "Ph" + currId;
        this.ten = ten;
        this.setThoiLuong(thoiLuong);
        this.theLoai = "Khong xac dinh";
        setTheLoai(theLoai);
        this.doTuoi = doTuoi;
    }

    public void setId(int id) {
        this.id = "Ph" + id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public final String setTheLoai(String theLoai) {
        for (String dsTheLoai1 : dsTheLoai) {
            if (theLoai == null ? dsTheLoai1 == null : theLoai.equals(dsTheLoai1)) {
                this.theLoai = theLoai;
                return "Da thay doi the loai";
            }
        }
        return "The loai khong ton tai";
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }

    public Duration getThoiLuong() {
        return thoiLuong;
    }

    public final long inThoiLuong() {
        return thoiLuong.toMinutes();
    }

    public final void setThoiLuong(long duration) {
        try {
            this.thoiLuong = Duration.ofMinutes(duration);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang phut");
        }
    }

    public LocalDate getTgKhoiChieu() {
        return TgKhoiChieu;
    }

    public void setTgKhoiChieu(CharSequence ngay) {
        try {
            TgKhoiChieu = LocalDate.parse(ngay, dateFormat);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang ngay");
        }
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String inTgKhoiChieu() {
        return TgKhoiChieu.format(dateFormat);
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public void themDt(double dt) {
        this.dt += dt;
    }

    @Override
    public String toString() {
        return "Phim{" + "id=" + id + ", ten=" + ten + ", theLoai=" + theLoai + ", doTuoi=" + doTuoi + ", thoiLuong=" + inThoiLuong() + ", TgKhoiChieu=" + inTgKhoiChieu() + '}';
    }

}
