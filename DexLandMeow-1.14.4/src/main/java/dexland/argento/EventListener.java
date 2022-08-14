package dexland.argento;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventListener {
    long time1 = 0;
    long time2 = 0;
    long start_time = 0;
    public static int cooldown = 500;
    public static String killmsg = "%player%, �� ����� <3";
    public static String bedmsg = "���.. ����� ������� ��������... <3";
    public static String winmsg = "gg";
    public static String deathmsg = "��� :(";
    public static String joinmsg = "���� ������� ���� :3";
    public static boolean timer = true;

    public void send(String str) {
        if(Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(str));
        }
    }

    @SubscribeEvent
    public void join(EntityJoinWorldEvent e) {
        if(Minecraft.getInstance().player != null) {
            if (e.getEntity() instanceof PlayerEntity) {
                if (Minecraft.getInstance().player.getGameProfile().getName().equals(((PlayerEntity) e.getEntity()).getGameProfile().getName())) {
                    long time2 = System.currentTimeMillis();
                    if(time2 - time1 > 1000) {
                        send("�� ������� � ����� DexLandMeow (�����: MrAlexeiMK)");
                        time1 = time2;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onChat2(ClientChatEvent e) {
        String msg = e.getMessage();
        if(msg.contains(".helpmeow")) {
            send("���� �� �������� ��������� - ������� � ��������� ������");
            send(".setkillmsg [���������/null] - ���������� ��������� ��� ��������");
            send(".setbedmsg [���������/null] - ���������� ��������� ��� ������� �������");
            send(".setjoinmsg [���������/null] - ���������� ��������� ��� ����� � ����");
            send(".setwinmsg [���������/null] - ���������� ��������� ��� ������");
            send(".setdeathmsg [���������/null] - ���������� ��������� ��� ����� ������");
            send(".timer [on/off] - ��������/��������� ������, ���������� ����� �����");
            send(".setcooldown [�����] - ���������� �������� ����� ������ ����������� (� �������������)");
            send("� .setkillmsg ����� �������� %player%, ��� ��������� � ������, �������� �� �����");
            send("����� ��������� ��������� �������, ��������� � ��������� null");
            e.setCanceled(true);
        }
        else if(msg.contains(".setcooldown")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setcooldown")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                try {
                    int num = Integer.valueOf(m.replaceAll(" ", ""));
                    cooldown = num;
                    send("����������� ��������: " + String.valueOf(num));
                } catch(Exception ee) {
                    send(".setcooldown [�����] - ���������� �������� ����� ������ ����������� (� �������������)");
                };
            }
            else {
                send(".setcooldown [�����] - ���������� �������� ����� ������ ����������� (� �������������)");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(".timer on")) {
            timer = true;
            send("������ ���� �������");
            e.setCanceled(true);
        }
        else if(msg.contains(".timer off")) {
            timer = false;
            send("������ ���� ��������");
            e.setCanceled(true);
        }
        else if(msg.contains(".setkillmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setkillmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                killmsg = m;
                send("����������� ���������: " + m);
            }
            else {
                send(".setkillmsg [���������/null] - ���������� ��������� ��� ��������");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(".setbedmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setbedmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                bedmsg = m;
                send("����������� ���������: " + m);
            }
            else {
                send(".setbedmsg [���������/null] - ���������� ��������� ��� ������� �������");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(".setwinmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setwinmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                winmsg = m;
                send("����������� ���������: " + m);
            }
            else {
                send(".setwinmsg [���������/null] - ���������� ��������� ��� ������");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(".setdeathmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setdeathmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                deathmsg = m;
                send("����������� ���������: " + m);
            }
            else {
                send(".setdeathmsg [���������/null] - ���������� ��������� ��� ����� ������");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(".setjoinmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setjoinmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                joinmsg = m;
                send("����������� ���������: " + m);
            }
            else {
                send(".setjoinmsg [���������/null] - ���������� ��������� ��� ����� � ����");
            }
            e.setCanceled(true);
        }
    }

    public void sendInChat(String msg) {
        long time3 = System.currentTimeMillis();
        if(Minecraft.getInstance().player != null) {
            if (time3 - time2 > cooldown) {
                Minecraft.getInstance().player.sendChatMessage("!" + msg);
                time2 = time3;
            }
        }
    }

    public static void sendCDMessage(String msg, int cd) {
        Thread th = new Thread() {
            public void run() {
                try {
                    sleep(cd);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.sendMessage(new StringTextComponent(msg));
                }
            }
        };
        th.start();
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        String msg = e.getMessage().getString();
        String p_name = Minecraft.getInstance().player.getGameProfile().getName();
        if(msg.split(" ").length > 6 && msg.split(" ")[3].contains("���") && msg.split(" ")[4].contains("����") && msg.split(" ")[5].contains("�������")) {
            String killer = msg.split(" ")[6].substring(0, msg.split(" ")[6].length()-1);
            String killed = msg.split(" ")[2];
            String m = killmsg.replaceAll("%player%", killed);
            if(killer.equals(p_name) && !m.contains("null")) {
                sendInChat(m);
            }
        }
        else if(msg.split(" ").length > 8 && msg.split(" ")[3].contains("���") && msg.split(" ")[4].contains("������") && msg.split(" ")[5].contains("�") && msg.split(" ")[6].contains("������") && msg.split(" ")[7].contains("�������")) {
            String killer = msg.split(" ")[8].substring(0, msg.split(" ")[8].length()-1);
            String killed = msg.split(" ")[2];
            String m = killmsg.replaceAll("%player%", killed);
            if(killer.equals(p_name) && !m.contains("null")) {
                sendInChat(m);
            }
        }
        else if(msg.split(" ").length > 6 && msg.split(" ")[3].contains("��������") && msg.split(" ")[4].contains("�������") && msg.split(" ")[5].contains("�������")) {
            String who = msg.split(" ")[2];
            String m = bedmsg;
            if(who.equals(p_name) &&  !m.contains("null")) {
                sendInChat(m);
            }
        }
        else if(msg.contains("������� ������ ����, ���������� �������!")) {
            if(timer) {
                start_time = (long)(System.currentTimeMillis()/1000);
                sendCDMessage("����� ������� ���� �������!", 500);
            }
            if(!joinmsg.contains("null")) {
                sendInChat(joinmsg);
            }
        }
        else if(msg.contains("������������ ������� ����� 10 ������!")) {
            if(!winmsg.contains("null")) {
                sendInChat(winmsg);
            }
            if(timer) {
                long seconds = (long)(System.currentTimeMillis()/1000) - start_time;
                long minutes = seconds/60;
                seconds = seconds%60;
                long hours = minutes/60;
                minutes = minutes%60;
                String res = String.valueOf(hours)+":"+String.valueOf(minutes)+":"+String.valueOf(seconds);
                send("����� ����: "+res);
            }
        }
        else if(msg.contains(p_name+ " ���� � ������") || msg.contains(p_name+ " ��� ���� �������") || msg.contains(p_name + " ��� ������ � ������ �������")) {
            if(!deathmsg.contains("null")) {
                sendInChat(deathmsg);
            }
        }
    }
}
