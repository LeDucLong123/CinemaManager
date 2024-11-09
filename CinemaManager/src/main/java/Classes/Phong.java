/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.*;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Lenovo
 */
@XmlRootElement(name = "phong") // anotation xác định element được đọc vào file xml
@XmlAccessorType(XmlAccessType.FIELD) // đọc dưới dạng trường, nhận các thuộc tính là một element
public class Phong {

    private static int currId;
    private int slVip, slThuong, slDoi;
    @XmlAttribute // xác định cách đọc thuộc tính dưới dạng thuộc tính của element mẹ
    private String id;
    private Map<String, GheThuong> dsGheThuong = new HashMap<>();
    private Map<String, GheVip> dsGheVip = new HashMap<>();
    private Map<String, GheDoi> dsGheDoi = new HashMap<>();
    private boolean isFull;
    private SuatChieu suatChieu;
    private boolean isPlaying;
    private int columns;
    private char rows;
    private double dt;

    public Phong() {
        currId++;
        this.id = "P" + currId;
        rows = 'A';
        columns = 1;
        this.isFull = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getSlVip() {
        return slVip;
    }

    public void setSlVip(int slVip) {
        this.slVip = slVip;
    }

    public int getSlThuong() {
        return slThuong;
    }

    public void setSlThuong(int slThuong) {
        this.slThuong = slThuong;
    }

    public int getSlDoi() {
        return slDoi;
    }

    public void setSlDoi(int slDoi) {
        this.slDoi = slDoi;
    }

    public Map<String, GheThuong> getDsGheThuong() {
        return dsGheThuong;
    }

    public void setDsGheThuong(Map<String, GheThuong> dsGheThuong) {
        this.dsGheThuong = dsGheThuong;
    }

    public Map<String, GheVip> getDsGheVip() {
        return dsGheVip;
    }

    public void setDsGheVip(Map<String, GheVip> dsGheVip) {
        this.dsGheVip = dsGheVip;
    }

    public Map<String, GheDoi> getDsGheDoi() {
        return dsGheDoi;
    }

    public void setDsGheDoi(Map<String, GheDoi> dsGheDoi) {
        this.dsGheDoi = dsGheDoi;
    }

    public void themGhe(Ghe ghe) {
        ghe.setViTri(String.format("%s%s%d", this.getId(), rows, columns));
        if (ghe instanceof GheThuong gheThuong) {
            dsGheThuong.put(ghe.getViTri(), gheThuong);
        }
        if (ghe instanceof GheVip gheVip) {
            dsGheVip.put(ghe.getViTri(), gheVip);
        }
        if (ghe instanceof GheDoi gheDoi) {
            dsGheDoi.put(ghe.getViTri(), gheDoi);
        }
        if (columns < 10) {
            columns++;
        } else {
            columns = 1;
            rows++;
        }
    }

    public boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public SuatChieu getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(SuatChieu xuatChieu) {
        this.suatChieu = xuatChieu;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int inTongTrong() {
        int tong = 0;
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                tong++;
            }
        }
        return tong;
    }

    public int inTongDat() {
        return dsGheThuong.size() + dsGheVip.size() + dsGheDoi.size() - inTongTrong();
    }

    public String inThuongDat() {
        String dsThuongDat = "";
        for (GheThuong ghe : dsGheThuong.values()) {
            if ("thuong".equals(ghe.getLoai().toLowerCase()) && ghe.getKhachId() != null) {
                dsThuongDat += ghe.getViTri() + ", ";
            }
        }
        return dsThuongDat;
    }

    public String inVipDat() {
        String dsVipDat = "";
        for (GheVip ghe : dsGheVip.values()) {
            if ("vip".equals(ghe.getLoai().toLowerCase()) && ghe.getKhachId() != null) {
                dsVipDat += ghe.getViTri() + ", ";
            }
        }
        return dsVipDat;
    }

    public String inDoiDat() {
        String dsDoiDat = "";
        for (GheDoi ghe : dsGheDoi.values()) {
            if ("doi".equals(ghe.getLoai().toLowerCase()) && ghe.getKhachId() != null) {
                dsDoiDat += ghe.getViTri() + ", ";
            }
        }
        return dsDoiDat;
    }

    public String inDsGheTrong() {
        String dsTrongThuong = "";
        String dsTrongVip = "";
        String dsTrongDoi = "";
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongThuong += g.getKey() + ", ";
            }
        }
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongVip += g.getKey() + ", ";
            }
        }
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsTrongDoi += g.getKey() + ", ";
            }
        }
        String[] thuongArr = dsTrongThuong.split(",");
        String[] vipArr = dsTrongVip.split(",");
        String[] doiArr = dsTrongDoi.split(",");
        Arrays.sort(thuongArr);
        Arrays.sort(vipArr);
        Arrays.sort(doiArr);
        return "DsGheThuong= " + Arrays.toString(thuongArr) + "\n" + "DsGheVip= " + Arrays.toString(vipArr) + "\n" + "DsGheDoi= " + Arrays.toString(doiArr) + "\n";
    }

    public List<Ghe> getDsGheTrong() {
        List<Ghe> ds = new ArrayList<>();
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                ds.add(g.getValue());
            }
        }
        Collections.sort(ds, new Comparator<Ghe>() {
            @Override
            public int compare(Ghe o1, Ghe o2) {
                int o1Int = Integer.parseInt(o1.getViTri().split(id)[1].substring(1));
                int o2Int = Integer.parseInt(o2.getViTri().split(id)[1].substring(1));
                return o1Int - o2Int;
            }
        });
        List<Ghe> dsVip = new ArrayList<>();
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsVip.add(g.getValue());
            }
        }
        Collections.sort(dsVip, new Comparator<Ghe>() {
            @Override
            public int compare(Ghe o1, Ghe o2) {
                int o1Int = Integer.parseInt(o1.getViTri().split(id)[1].substring(1));
                int o2Int = Integer.parseInt(o2.getViTri().split(id)[1].substring(1));
                return o1Int - o2Int;
            }
        });
        ds.addAll(dsVip);
        List<Ghe> dsDoi = new ArrayList<>();
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            if (g.getValue().getKhachId() == null) {
                dsDoi.add(g.getValue());
            }
        }
        Collections.sort(dsDoi, new Comparator<Ghe>() {
            @Override
            public int compare(Ghe o1, Ghe o2) {
                int o1Int = Integer.parseInt(o1.getViTri().split(id)[1].substring(1));
                int o2Int = Integer.parseInt(o2.getViTri().split(id)[1].substring(1));
                return o1Int - o2Int;
            }
        });
        ds.addAll(dsDoi);

        return ds;
    }

    public int getSucChua() {
        return this.slThuong + this.slVip + this.slDoi * 2;
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

    public int getTongGhe() {
        return slThuong + slVip + slDoi;
    }

    public void resetDsGhe() {
        for (Entry<String, GheThuong> g : this.dsGheThuong.entrySet()) {
            g.getValue().setKhachId("");
        }
        for (Entry<String, GheVip> g : this.dsGheVip.entrySet()) {
            g.getValue().setKhachId("");
        }
        for (Entry<String, GheDoi> g : this.dsGheDoi.entrySet()) {
            g.getValue().setKhachId("");
        }
    }

    @Override
    public String toString() {
        return "Phong{" + "id=" + getId() + ", slVip=" + slVip + ", slThuong=" + slThuong + ", slDoi=" + slDoi + ", sucChua=" + getSucChua() + ", dsGhe=" + inDsGheTrong() + ", isFull=" + isFull + ", suatChieu=" + suatChieu + ", isPlaying=" + isPlaying + '}' + '\n';
    }

}
