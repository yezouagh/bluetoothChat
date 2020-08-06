package com.smp.bluetoothchat;

import java.util.Date;

/**
 * Created by Younes on 18-Mar-17.
 */

public class cMessage {
    int Me;//=1 if the message was mine and =0 if the message is not mine
    String Msg;// the message
    long Recu;// the time message was sent
    String Adresse;// the address of the target
    public cMessage(long recu, String msg, int me, String adresse) {
        Recu = recu;
        Msg = msg;
        Me = me;
        Adresse = adresse;
    }

    public int isMe() {
        return Me;
    }
    public void setMe(int me) {
        Me = me;
    }
    public String getMsg() {
        return Msg;
    }
    public void setMsg(String msg) {
        Msg = msg;
    }
    public String getAdresse() {
        return Adresse;
    }
    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
    public long getRecu() {
        return Recu;
    }
    public void setRecu(long recu) {
        Recu = recu;
    }
}
