package com.company;

import java.util.function.UnaryOperator;

public class Kirill {

    private Integer id ;
    private Integer date;
    private Integer restId;
    private Integer money;
    private Integer source;
    private String guid;

    public Kirill(Integer id, Integer date, Integer restId, Integer sumMoney, Integer source) {
        this.id = id;
        this.date = date;
        this.restId = restId;
        this.money = sumMoney;
        this.source = source;
    }


    public Kirill merge(Kirill other) {
        if (other == null) {
            return this;
        }
        if (this.restId.equals(other.restId) && this.date.equals(other.date)) {
            money = this.money == null ? other.money : this.money;
        }
        return this;
    }

    public Kirill(Integer id, Integer date, Integer restId, Integer money, Integer source, String guid) {
        this.id = id;
        this.date = date;
        this.restId = restId;
        this.money = money;
        this.source = source;
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Kirill{" +
                "id=" + id +
                ", date=" + date +
                ", restId=" + restId +
                ", money=" + money +
                ", source=" + source +
                ", guid=" + guid +
                '}';
    }

    public Integer getDate() {
        return date;
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getRestId() {
        return restId;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
