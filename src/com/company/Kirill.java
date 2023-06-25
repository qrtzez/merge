package com.company;

public class Kirill{

    private Integer id ;
    private Integer date;
    private Integer restId;
    private Integer money;
    private Integer source;




    public Kirill merge(Kirill other) {
        if (other == null) {
            return this;
        }
        if (this.restId.equals(other.restId) && this.date.equals(other.date)) {
            money = this.money == null ? other.money : this.money;
        }
        return this;
    }

    public Kirill(Integer id, Integer date, Integer restId, Integer money, Integer source) {
        this.id = id;
        this.date = date;
        this.restId = restId;
        this.money = money;
        this.source = source;
    }

    @Override
    public String toString() {
        return "Kirill{" +
                "id=" + id +
                ", date=" + date +
                ", restId=" + restId +
                ", money=" + money +
                ", source=" + source +
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
}
