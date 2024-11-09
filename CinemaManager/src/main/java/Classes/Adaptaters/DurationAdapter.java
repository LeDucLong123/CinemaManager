/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Adaptaters;

import java.time.Duration;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;


// Adapter cho giá trị kiểu Duration trước khi viết vào file xml
public class DurationAdapter extends XmlAdapter<javax.xml.datatype.Duration, Duration> {
    @Override
    public Duration unmarshal(javax.xml.datatype.Duration v) throws Exception {
        return Duration.parse(v.toString()); // adaper cho hành vi đọc
    }

    @Override
    public javax.xml.datatype.Duration marshal(Duration v) throws Exception {
        return DatatypeFactory.newInstance().newDuration(v.toString()); // adapter cho hành vi viết
    }
}

