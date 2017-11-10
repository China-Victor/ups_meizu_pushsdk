package com.meizu.upspushsdklib.receiver.handler;

import android.content.Context;
import android.content.Intent;

import com.meizu.upspushsdklib.UpsPushMessage;
import com.meizu.upspushsdklib.util.UpsConstants;
import com.meizu.upspushsdklib.util.UpsLogger;

public class UpsNotificationDeleteHandler extends AbstractUpsReceiverHandler<UpsPushMessage>{

    public UpsNotificationDeleteHandler(Context context, UpsReceiverListener receiverListener) {
        super(context, receiverListener);
    }

    @Override
    public boolean messageMatch(Intent intent) {
        UpsLogger.i(this,"start UpsNotificationDeleteHandler");
        return UpsConstants.UPS_MEIZU_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction())
                && UpsConstants.UPS_MEIZU_PUSH_METHOD_ON_NOTIFICATION_DELETE.equals(getIntentMethod(intent));
    }

    @Override
    public String getProcessorName() {
        return UpsNotificationDeleteHandler.class.getSimpleName();
    }

    @Override
    public UpsPushMessage getMessage(Intent intent) {
        return (UpsPushMessage) intent.getSerializableExtra(UpsConstants.UPS_MEIZU_PUSH_EXTRA_UPS_MESSAGE);
    }

    @Override
    public void sendMessage(UpsPushMessage message) {
        upsReceiverListener().onNotificationDeleted(context(),message);
    }
}
