package com.smp.bluetoothchat;

/**
 * Created by Younes on 18-Mar-17.
 */
import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Message_array_adapter extends ArrayAdapter<cMessage> {

    private LayoutInflater layoutInflater;
    private List<cMessage> Messages;Context ctx;

    //constructor, call on creation
    public Message_array_adapter(Context context, int resource, ArrayList<cMessage> objects) {
        super(context, resource, objects);
        ctx=context;
        layoutInflater = LayoutInflater.from(context);
        this.Messages = objects;
    }
    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {
        cMessage Message = Messages.get(position); // get the message object
        TextView msg,tm; // the message txt and the time
            if(Message.isMe()==1) { convertView = layoutInflater.inflate(R.layout.mymessage, null);}
            else {convertView = layoutInflater.inflate(R.layout.hismessage, null);} // set the convenient layout
        msg = (TextView) convertView.findViewById( R.id.message);
        msg.setText(Message.getMsg());
        tm = (TextView) convertView.findViewById( R.id.tm);
        String formattedDate = new SimpleDateFormat("HH:mm").format(new Date(Message.getRecu()));
        tm.setText(formattedDate);
        return  convertView;
    }
}