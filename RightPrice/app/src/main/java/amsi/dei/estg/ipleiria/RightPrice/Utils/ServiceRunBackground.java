package amsi.dei.estg.ipleiria.RightPrice.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import amsi.dei.estg.ipleiria.RightPrice.Adaptadores.Admin.MqttHelper;
import amsi.dei.estg.ipleiria.RightPrice.MainActivity;
import amsi.dei.estg.ipleiria.RightPrice.MenuMainActivity;
import amsi.dei.estg.ipleiria.RightPrice.Modelo.SingletonGerirOrcamentos;
import amsi.dei.estg.ipleiria.RightPrice.R;

//Classe que corre em segundo plano no android
public class ServiceRunBackground  extends Service {
    private static final String CHANNEL_ID ="canal_ID" ;
    private String funcao = null;
    private MqttHelper mqttHelper;
    public int stringEnviaOuNaoEnviaNotificacao=0;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    //Inicia o serviço
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("---> Conected");
        stringEnviaOuNaoEnviaNotificacao=1;
        SharedPreferences sharedPreferenceuser = getSharedPreferences(MenuMainActivity.PREF_USER,MODE_PRIVATE);
        funcao = sharedPreferenceuser.getString(MenuMainActivity.FUNCAO,null);
        if (funcao!=null){
            try {
                if(funcao.equals("admin")){
                    int id_notificacao = 2;
                    startMqtt();
                }

            }catch (Exception e){
               Toast.makeText(this, "Erro a iniciar o Serviço de 2º Plano", Toast.LENGTH_LONG).show();
           }
        }

        // START_STICKY serve para executar seu serviço até que você pare ele, é reiniciado automaticamente sempre que termina
        return (super.onStartCommand(intent,flags,startId));
    }




    @Override
    public  void  onDestroy() {
        stringEnviaOuNaoEnviaNotificacao=0;
        super.onDestroy();
    }


    //funcao que corre o mosquito
    private void startMqtt(){
        int id_notificacao = 2;
        mqttHelper = new MqttHelper(getBaseContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {
                Toast.makeText(ServiceRunBackground.this, "Conecção com o servidor falada. Não irá receber mensagens", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                //Toast.makeText(MenuMainActivity.this, mqttMessage.toString(), Toast.LENGTH_LONG).show();
                CriarNotficação(mqttMessage.toString(),id_notificacao);
                SingletonGerirOrcamentos.getInstance(getApplicationContext()).getAllUtilizadoresAdminAPI(getApplicationContext());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }


    //funcao para criar a notificaçao
    public  void CriarNotficação(String message, int id_notificacao){
        createNotificationChannel(message);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        String newMessqage = removeDoubleQuotes(message);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_apagar)
                .setContentTitle("Right Price")
                .setContentText(newMessqage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        if(stringEnviaOuNaoEnviaNotificacao==1){
            notificationManager.notify(id_notificacao, builder.build());
        }
    }

    public static String removeDoubleQuotes(String input){

        StringBuilder sb = new StringBuilder();

        char[] tab = input.toCharArray();
        for( char current : tab ){
            if( current != '"' )
                sb.append( current );
        }

        return sb.toString();
    }

    //funcao que cria o canal da notificaçao
    private void createNotificationChannel(String message) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificações";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(message);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}