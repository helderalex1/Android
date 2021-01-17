package amsi.dei.estg.ipleiria.RightPrice.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


//Classe que inicia o serviço quando o telemovel reniciar
public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            Intent pushIntent = new Intent(context, ServiceRunBackground.class);
            context.startService(pushIntent);
    }
}
