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

@XmlRootElement(name = "gheVip")
@XmlAccessorType(XmlAccessType.FIELD)
public class GheVip extends Ghe {

    public GheVip() {
        super.setGia(120000);
        super.setLoai("Vip");
    }

    public GheVip(Khach khach) {
        super.setKhachId(khach.getId());
        super.setGia(120000);
        super.setLoai("Vip");
    }

    @Override
    public String getLoai() {
        return loai;
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
        return super.toString() + "khach: " + super.getKhachId();
    }
    
}
