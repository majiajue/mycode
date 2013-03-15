package com.ma.smstranfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsTransferBordCast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		if (arg1.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {// �ж�ϵͳ����
            
			this.abortBroadcast();// �����´���
			StringBuffer sb = new StringBuffer();
			Bundle bundle = arg1.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				SmsMessage[] managers = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					managers[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);// ת����������
				}

				for (SmsMessage smsMessage : managers) {
					sb.append("��������"
							+ smsMessage.getDisplayOriginatingAddress());
					sb.append("��������" + smsMessage.getDisplayMessageBody());
				}
				Toast.makeText(arg0, sb.toString(), Toast.LENGTH_LONG).show();
			}

		}
	}

}
