/*
 * MIT License
 *
 * Copyright (c) [2017] [Meizu.inc]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.meizu.upspushsdklib.receiver.dispatcher;


import android.content.Context;

import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.upspushsdklib.UpsCommandMessage;
import com.meizu.upspushsdklib.util.UpsLogger;

class UpsPlatformUnSetAlias extends CommandMessageDispatcher<SubAliasStatus>{

    public UpsPlatformUnSetAlias(Context context, UpsCommandMessage upsCommandMessage) {
        super(context, upsCommandMessage);
    }

    @Override
    public SubAliasStatus upsPlatformMessage() {
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        ANResponse<String> anResponse = UpsPushAPI.unSetAlias(getUpsAppId(),getUpsAppKey(),
                upsCommandMessage.getCompany().code(),
                context.getPackageName(),
                getDeviceId(),
                getUpsPushId());

        if(anResponse.isSuccess()){
            subAliasStatus = new SubAliasStatus(anResponse.getResult());
            upsCommandMessage.setCode(Integer.parseInt(subAliasStatus.getCode()));
            upsCommandMessage.setMessage(subAliasStatus.getMessage());
            upsCommandMessage.setCommandResult(Boolean.toString(true));
        } else {
            upsCommandMessage.setCode(anResponse.getError().getErrorCode());
            upsCommandMessage.setMessage(anResponse.getError().getErrorBody());
            UpsLogger.e(this,"ups unset alias error "+anResponse.getError());
        }
        return subAliasStatus;
    }
}
