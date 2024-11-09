/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Adaptaters;

import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Lenovo
 */
// Adapter cho giá trị kiểu LocalDate trước khi viết vào file xml
public class LocalDateAdapter extends XmlAdapter<javax.xml.datatype.XMLGregorianCalendar, LocalDate> {

    @Override
    public LocalDate unmarshal(XMLGregorianCalendar dayInXML) throws Exception {
        return LocalDate.of(dayInXML.getYear(), // adapter cho hành vi đọc
                dayInXML.getMonth(),
                dayInXML.getDay());
    }

    @Override
    public XMLGregorianCalendar marshal(LocalDate day) throws Exception {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(day.toString()); //adapter cho hành vi viết
    }
}
