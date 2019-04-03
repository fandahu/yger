package com.example.hufan.yger.model;

/**
 * Created by hufan on 2019/3/31.
 */

public class Boxs {
    private String name;
    private int zs;
    private int ys;

    public Boxs(String name, int zs, int ys) {
        this.name = name;
        this.zs = zs;
        this.ys = ys;
    }

    public String getName() {
        return name;
    }

    public int getZs() {
        return zs;
    }

    public int getYs() {
        return ys;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZs(int zs) {
        this.zs = zs;
    }

    public void setYs(int ys) {
        this.ys = ys;
    }
}
