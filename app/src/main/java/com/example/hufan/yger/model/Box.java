package com.example.hufan.yger.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by hufan on 2019/3/31.
 */

public class Box {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String xm;//姓名
    @DatabaseField
    private String bzbh;//包装编号
    @DatabaseField
    private String sku;//条码
    @DatabaseField
    private String ordno;//合同号
    @DatabaseField
    private int qty;//数量
    @DatabaseField
    private int ys;//已扫数量
    @DatabaseField
    private String cjq_id;//机器号
    @DatabaseField
    private String rq;//日期

    public Box() {
    }

    public Box(String xm, String bzbh, String sku, String ordno, int qty, int ys, String cjq_id, String rq) {
        this.xm = xm;
        this.bzbh = bzbh;
        this.sku = sku;
        this.ordno = ordno;
        this.qty = qty;
        this.ys = ys;
        this.cjq_id = cjq_id;
        this.rq = rq;
    }

    public String getXm() {
        return xm;
    }

    public String getBzbh() {
        return bzbh;
    }

    public String getSku() {
        return sku;
    }

    public String getOrdno() {
        return ordno;
    }

    public int getQty() {
        return qty;
    }

    public int getYs() {
        return ys;
    }

    public String getCjq_id() {
        return cjq_id;
    }

    public String getRq() {
        return rq;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public void setBzbh(String bzbh) {
        this.bzbh = bzbh;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setOrdno(String ordno) {
        this.ordno = ordno;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setYs(int ys) {
        this.ys = ys;
    }

    public void setCjq_id(String cjq_id) {
        this.cjq_id = cjq_id;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }
}
