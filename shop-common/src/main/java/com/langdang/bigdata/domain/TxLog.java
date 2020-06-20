package com.langdang.bigdata.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

//消息事物状态记录
@Entity(name = "shop_txlog")
public class TxLog {
    @Id
    private String txId;
    private Date date;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
