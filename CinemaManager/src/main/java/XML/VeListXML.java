/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML;

import Classes.Ve;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
//// Lớp định nghĩa danh sách đối tượng Vé dùng để tạo element danh sách khi viết và đọc từ file xml
@XmlRootElement(name = "ves")
@XmlAccessorType(XmlAccessType.FIELD)
public class VeListXML {
    private List<Ve> ve;

    public VeListXML() {
    }

    public List<Ve> getVe() {
        return ve;
    }

    public void setVe(List<Ve> ve) {
        this.ve = ve;
    }  
}
