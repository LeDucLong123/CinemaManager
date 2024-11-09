/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Adaptaters.LocalDateTimeAdapter;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "gheThuong") // anotation xác định element được đọc vào file xml
@XmlAccessorType(XmlAccessType.FIELD) // đọc dưới dạng trường, nhận các thuộc tính là một element
public class GheThuong extends Ghe {

    public GheThuong() {
        super.setGia(80000);
        super.setLoai("Thuong");
    }

    public GheThuong(Khach khach) {
        super.setKhachId(khach.getId());
        super.setGia(80000);
        super.setLoai("Thuong");
    }

    @Override
    public double getGia() {
        return gia;
    }

    @Override
    public String getKhachId() {
        return khachId;
    }

    @Override
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    @Override
    public String toString() {
        return super.toString() + "khach:" + super.getKhachId();
    }
}
