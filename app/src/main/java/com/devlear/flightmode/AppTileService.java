/* *
 * Copyright (C) 2013 Mikhail Malakhov <malakhv@live.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */

package com.devlear.flightmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.service.quicksettings.TileService;
import com.malakhv.util.LogCat;

/**
 * The Quick Settings tile service.
 * @author Mikhail.Malakhov [malakhv@live.ru|https://github.com/malakhv]
 * */
public class AppTileService extends TileService {

    /***/
    private final BroadcastReceiver mModeReceiver = new ModeReceiver();

    /** {@inheritDoc} */
    @Override
    public void onTileAdded() {
        super.onTileAdded();
        LogCat.d("Tile Added");
    }

    /** {@inheritDoc} */
    @Override
    public void onStartListening() {
        super.onStartListening();
        this.registerReceiver(mModeReceiver, FlightMode.MODE_CHANGED_FILTER);
        LogCat.d("Start Listening");
    }

    /** {@inheritDoc} */
    @Override
    public void onStopListening() {
        super.onStopListening();
        this.unregisterReceiver(mModeReceiver);
        LogCat.d("Stop Listening");
    }

    /** {@inheritDoc} */
    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        LogCat.d("Tile Removed");
    }

    /**
     * Update tile state.
     * */
    private void refreshState() {

    }

    /**
     * The broadcast receiver for {@link Intent#ACTION_AIRPLANE_MODE_CHANGED} action.
     * */
    private class ModeReceiver extends BroadcastReceiver {

        /** The {@link IntentFilter} for this broadcast. */
        private IntentFilter FILTER = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        /** The current broadcast state, register or not. */
        private boolean mRegistered = false;

        /** Register this broadcast receiver. */
        public void register(Context context) {
            if (!mRegistered) {
                context.registerReceiver(this, FILTER);
                mRegistered = true;
            }
        }

        /** Unregister this broadcast receiver. */
        public void unRegister(Context context) {
            if (mRegistered) {
                context.unregisterReceiver(this);
            }
        }

        /** {@inheritDoc} */
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
                refreshState();
            }
        }
    }
}