/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML;

import Classes.Phim;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Lenovo
 */

//// Lớp định nghĩa danh sách đối tượng Phim dùng để tạo element danh sách khi viết và đọc từ file xml
@XmlRootElement(name = "phims")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhimListXML {
    private List<Phim> phim;

    public PhimListXML() {
    }
    
    public List<Phim> getPhims() {
        return phim;
    }

    public void setPhims(List<Phim> phims) {
        this.phim = phims;
    }
    
    
}
