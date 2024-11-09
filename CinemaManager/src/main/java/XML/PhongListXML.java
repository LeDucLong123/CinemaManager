/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML;

import Classes.Phong;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */

//// Lớp định nghĩa danh sách đối tượng Phòng dùng để tạo element danh sách khi viết và đọc từ file xml
@XmlRootElement(name = "phongs")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhongListXML {
    private List<Phong> phong;

    public PhongListXML() {
    }

    public List<Phong> getPhong() {
        return phong;
    }

    public void setPhong(List<Phong> phong) {
        this.phong = phong;
    }
}
